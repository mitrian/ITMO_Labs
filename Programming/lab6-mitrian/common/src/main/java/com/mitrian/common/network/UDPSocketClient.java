package com.mitrian.common.network;

import com.mitrian.common.network.exception.NetworkException;
import com.mitrian.common.network.exception.ServerNotAvailable;
import com.mitrian.common.network.model.frame.UDPFrame;
import com.mitrian.common.network.model.request.Request;
import com.mitrian.common.network.model.response.Response;
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
public class UDPSocketClient implements AutoCloseable
{
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
	) throws NetworkException
	{
		try
		{
			this.socket = new DatagramSocket(port);
			this.serverAddr = new InetSocketAddress(serverPort);
			this.requestMapper = requestMapper;
			this.responseMapper = responseMapper;

//			Socket configuration
			this.socket.setReuseAddress(true);
			this.socket.setSoTimeout(2000);
		}
		catch (SocketException e)
		{
			throw new NetworkException("Unable to open datagram socket", e);
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
	public <T extends Response> T sendRequestAndWaitResponse(Request request) throws NetworkException, ServerNotAvailable
	{
//		Throwing an NPE if the provided request is null
		Objects.requireNonNull(request);

		request.setTo(serverAddr);

		try
		{
//			First of all, we should get our request byte representation
			byte[] requestBytes = requestMapper.mapFromInstanceToBytes(request);

//			If request size is more than default buffer size - send with overhead : else - send without overhead
			if (requestBytes.length > NetworkUtils.REQUEST_BUFFER_SIZE)
				sendRequestWithOverhead(requestBytes, request.getTo());
			else
				sendRequestNoOverhead(requestBytes, request.getTo());

//			Waiting for response
			return waitForResponse();
		}
		catch (MappingException e)
		{
			throw new NetworkException("Failed to map request from instance to bytes during request proceeding", e);
		}
	}

	/**
	 * This method sends request with overhead after separation
	 *
	 * @param requestBytes raw request bytes
	 * @param destination request destination
	 * @throws NetworkException if it's failed to send some of DatagramPackets
	 */
	private void sendRequestWithOverhead(byte[] requestBytes, InetSocketAddress destination) throws NetworkException, ServerNotAvailable
	{
//		Get request chunks from raw request bytes
		List<byte[]> requestChunks = NetworkUtils.splitArrayIntoChunks(requestBytes, NetworkUtils.REQUEST_BUFFER_SIZE);

//		Wrap chunks with UDPFrames
		List<UDPFrame> udpFrames = NetworkUtils.wrapChunksWithUDPFrames(requestChunks);

//		Wrap UDPFrames with DatagramPackets
		List<DatagramPacket> datagramPackets = NetworkUtils.wrapUDPFramesWithDatagramPackets(
				udpFrames,
				destination
		);

//		Trying to send datagram packets
		try
		{
			for (DatagramPacket packet : datagramPackets)
			{
				try
				{
					TimeUnit.MILLISECONDS.sleep(10);
				}
				catch (InterruptedException ignored) {}
				socket.send(packet);
			}
		}
		catch (SocketTimeoutException e)
		{
			throw new ServerNotAvailable("Server is not currently available");
		}
		catch (IOException e)
		{
//			LOGGER.severe("Failed to send packets: " + e.getMessage());
			throw new NetworkException("Failed to send packets", e);
		}
	}

	/**
	 * This method sends request without overhead
	 *
	 * @param requestBytes raw request bytes
	 * @param destination request destination
	 * @throws NetworkException if it's failed to send DatagramPacket
	 */
	private void sendRequestNoOverhead(byte[] requestBytes, InetSocketAddress destination) throws NetworkException, ServerNotAvailable
	{
		try
		{
//			Wrap raw bytes with UDPFrame
			UDPFrame udpFrame = new UDPFrame(requestBytes, true);

//			Get UDPFrameBytes from UDPFrame instance
			byte[] udpFrameBytes = FrameMapper.mapFromInstanceToBytes(udpFrame);

//			Wrap UDPFrame with DatagramPacket
			DatagramPacket requestPacket = new DatagramPacket(udpFrameBytes, udpFrameBytes.length, destination);

//			Trying to send the request
			socket.send(requestPacket);
		}
		catch (SocketTimeoutException e)
		{
			throw new ServerNotAvailable("Server is not currently available");
		}
		catch (MappingException e)
		{
			throw new NetworkException("Failed to map UDPFrame to raw bytes", e);
		}
		catch (IOException e)
		{
//			LOGGER.severe("Failed to send request with no overhead: " + e.getMessage());
			throw new NetworkException("Failed to send request with no overhead", e);
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
	private <T extends Response> T waitForResponse() throws NetworkException
	{
//		Response byte buffer initiation
		byte[] responseBytes = new byte[NetworkUtils.RESPONSE_BUFFER_SIZE];

//		After the request was sent we should prepare a datagram packet for response
		DatagramPacket responsePacket = new DatagramPacket(responseBytes, responseBytes.length);

		try
		{
			byte[] allResponseBytes = new byte[0];
			boolean gotAll = false;
			do
			{
//				Receiving a response frame
				socket.receive(responsePacket);

//				Retrieving response raw bytes
				byte[] currentFrameBytes = responsePacket.getData();

//				Mapping UDPFrame from raw bytes
				UDPFrame udpFrame = FrameMapper.mapFromBytesToInstance(currentFrameBytes);

//				Enriching response bytes with new bytes
				allResponseBytes = NetworkUtils.concatTwoByteArrays(allResponseBytes, udpFrame.getData());

				if (udpFrame.isLast())
					gotAll = true;
			}
			while (!gotAll);

//			Mapping response bytes into an instance
			Response response = responseMapper.mapFromBytesToInstance(allResponseBytes);

			return (T) response;
		}
		catch (MappingException e)
		{
//			throw new NetworkException("Mapping operation failure detected", e);
			throw new RuntimeException(e);
		}
		catch (IOException e)
		{
//			LOGGER.severe("Failed to receive response from the server: " + e.getMessage());
			throw new NetworkException("Failed to receive response from the server", e);
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
