package com.mitrian.common.network;

import com.mitrian.common.network.exception.NetworkException;
import com.mitrian.common.network.exception.ServerNotAvailable;
import com.mitrian.common.network.model.frame.UDPFrame;
import com.mitrian.common.network.model.request.Request;
import com.mitrian.common.network.model.response.AbstractResponse;
import com.mitrian.common.network.util.NetworkUtils;
import com.mitrian.common.network.util.mapper.FrameMapper;
import com.mitrian.common.network.util.mapper.RequestMapper;
import com.mitrian.common.network.util.mapper.ResponseMapper;
import com.mitrian.common.network.util.mapper.exception.MappingException;

import java.io.IOException;
import java.net.*;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * This class is a UDPSocket client implementation
 *
 */
public class UDPSocketClient implements AutoCloseable {
	/**
	 * Opened datagram socket
	 *
	 */
	private final DatagramSocket socket;

	/**
	 * Request mapper instance
	 *
	 */
	private final RequestMapper requestMapper;

	/**
	 * Response mapper instance
	 *
	 */
	private final ResponseMapper responseMapper;

	private final InetSocketAddress serverAddr;

	/**
	 * UDPSocket client constructor.
	 *
	 * @param port the port, UDPSocket will be bind to (0 - any available port automatically / provide your own port)
	 * @param requestMapper {@link RequestMapper} instance
	 * @param responseMapper {@link ResponseMapper} instance
	 * @throws NetworkException if it's failed to open a socket and bind it to a concrete port
	 */
	public UDPSocketClient(
			int port,
			int serverPort,
			RequestMapper requestMapper,
			ResponseMapper responseMapper
	) throws NetworkException {
		try {
			this.socket = new DatagramSocket(port);
			this.serverAddr = new InetSocketAddress(serverPort);
			this.requestMapper = requestMapper;
			this.responseMapper = responseMapper;
			this.socket.setReuseAddress(true);
			this.socket.setSoTimeout(5000);
		}
		catch (SocketException e) {
			throw new NetworkException("Невозможно открыть datagram socket", e);
		}
	}

	/**
	 * This method allows you to send a request and receive a response for it
	 *
	 * @param request request to be sent
	 * @return a response instance
	 * @param <T> means the expected type of response
	 * @throws NetworkException if it failed to send the response to the server,
	 * if the server response data was corrupted, if it failed to receive response from the server or
	 * request mapping failed
	 */
	public <T extends AbstractResponse> T sendRequestAndWaitResponse(Request request) throws NetworkException, ServerNotAvailable {

		Objects.requireNonNull(request);
		request.setTo(serverAddr);
		request.setFrom(new InetSocketAddress(socket.getInetAddress(), socket.getLocalPort()));
		try {
			byte[] requestBytes = requestMapper.mapFromInstanceToBytes(request);
			if (requestBytes.length > NetworkUtils.REQUEST_BUFFER_SIZE) {
				sendRequestWithOverhead(requestBytes, request.getTo());
			}
			else {
				sendRequestNoOverhead(requestBytes, request.getTo());
			}

			return waitForResponse();
		}
		catch (MappingException e) {
			throw new NetworkException("не удалось составить request из байтов во время обработки", e);
		}
	}

	/**
	 * This method sends request with overhead after separation
	 *
	 * @param requestBytes raw request bytes
	 * @param destination request destination
	 * @throws NetworkException if it's failed to send some of DatagramPackets
	 */
	private void sendRequestWithOverhead(byte[] requestBytes, InetSocketAddress destination) throws NetworkException, ServerNotAvailable {
		List<byte[]> requestChunks = NetworkUtils.splitArrayIntoChunks(requestBytes, NetworkUtils.REQUEST_BUFFER_SIZE);
		List<UDPFrame> udpFrames = NetworkUtils.wrapChunksWithUDPFrames(requestChunks);
		List<DatagramPacket> datagramPackets = NetworkUtils.wrapUDPFramesWithDatagramPackets(
				udpFrames,
				destination
		);

		try {
			for (DatagramPacket packet : datagramPackets) {

				try {
					TimeUnit.MILLISECONDS.sleep(10);
				}
				catch (InterruptedException ignored) {}
				socket.send(packet);
			}
		}
		catch (SocketTimeoutException e) {
			throw new ServerNotAvailable("Сервер сейчас недоступен");
		}
		catch (IOException e) {
//			LOGGER.severe("Failed to send packets: " + e.getMessage());
			throw new NetworkException("Не удалось отправить пакеты", e);
		}
	}

	/**
	 * This method sends request without overhead
	 *
	 * @param requestBytes raw request bytes
	 * @param destination request destination
	 * @throws NetworkException if it's failed to send DatagramPacket
	 */
	private void sendRequestNoOverhead(byte[] requestBytes, InetSocketAddress destination) throws NetworkException, ServerNotAvailable {
		try {
//			Wrap raw bytes with UDPFrame
			UDPFrame udpFrame = new UDPFrame(requestBytes, true);

//			Get UDPFrameBytes from UDPFrame instance
			byte[] udpFrameBytes = FrameMapper.mapFromInstanceToBytes(udpFrame);

//			Wrap UDPFrame with DatagramPacket
			DatagramPacket requestPacket = new DatagramPacket(udpFrameBytes, udpFrameBytes.length, destination);

//			Trying to send the request
			socket.send(requestPacket);
		}
		catch (SocketTimeoutException e) {
			throw new ServerNotAvailable("Сервер сейчас недоступен");
		}
		catch (MappingException e) {
			throw new NetworkException("Не удалось сопоставить UDPFrame с необработанными байтами", e);
		}
		catch (IOException e) {
//			LOGGER.severe("Failed to send request with no overhead: " + e.getMessage());
			throw new NetworkException("Не удалось отправить request без заголовка", e);
		}
	}

	/**
	 * Method waits for response
	 *
	 * @return response instance
	 * @param <T> means the expected type of response
	 * @throws NetworkException if it's failed to receive response from the server
	 */
	@SuppressWarnings("unchecked")
	private <T extends AbstractResponse> T waitForResponse() throws NetworkException {
		byte[] responseBytes = new byte[NetworkUtils.RESPONSE_BUFFER_SIZE];
		DatagramPacket responsePacket = new DatagramPacket(responseBytes, responseBytes.length);

		try {
			byte[] allResponseBytes = new byte[0];
			boolean gotAll = false;
			do {
				socket.receive(responsePacket);
				byte[] currentFrameBytes = responsePacket.getData();
				UDPFrame udpFrame = FrameMapper.mapFromBytesToInstance(currentFrameBytes);
				allResponseBytes = NetworkUtils.concatTwoByteArrays(allResponseBytes, udpFrame.getData());

				if (udpFrame.isLast())
					gotAll = true;
			}
			while (!gotAll);
			AbstractResponse abstractResponse = responseMapper.mapFromBytesToInstance(allResponseBytes);
			return (T) abstractResponse;
		}
		catch (MappingException e) {
			throw new RuntimeException(e);
		}
		catch (IOException e) {
	//		e.printStackTrace();
			throw new NetworkException("Не удалось получить ответ от сервера", e);
		}
	}

	/**
	 * Method provided by {@link AutoCloseable} interface.
	 * Allows to use this class in the try-with-resources construction
	 * Automatically closes datagram socket
	 */
	@Override
	public void close()
	{
		socket.close();
	}
}
