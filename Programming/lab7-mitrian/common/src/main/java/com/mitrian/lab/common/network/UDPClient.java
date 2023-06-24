package com.mitrian.lab.common.network;

import com.mitrian.lab.common.network.exception.NetworkException;
import com.mitrian.lab.common.network.model.impl.command.CommandRequest;
import com.mitrian.lab.common.network.model.impl.command.CommandResponse;
import com.mitrian.lab.common.network.util.RequestMapper;
import com.mitrian.lab.common.network.util.ResponseMapper;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.nio.ByteBuffer;

/**
 * UDP client class
 */
public class UDPClient implements AutoCloseable {

	/**
	 * Default client port configuration
	 */
	private static final int DEFAULT_PORT = 8888;

	/**
	 * Remote server address
	 */
	private final InetSocketAddress serverAddress;

	/**
	 * Client address
	 */
	private final InetSocketAddress clientAddress;

	/**
	 * Client socket
	 */
	private final DatagramSocket socket;

	/**
	 * Request buffer
	 */
	private final ByteBuffer requestBuffer;

	/**
	 * Request buffer
	 */
	private final ByteBuffer responseBuffer;

	/**
	 * UDP Client constructor
	 *
	 * @param clientPort client port
	 * @param serverPort remote server port
	 * @throws NetworkException if something went wrong during socket creating
	 */
	public UDPClient(int clientPort, int serverPort) throws NetworkException {
		this(clientPort, new InetSocketAddress(serverPort));
	}

	/**
	 * UDP Client constructor
	 *
	 * @param serverHost remote server host
	 * @param serverPort remote server port
	 * @throws NetworkException if something went wrong during socket creating
	 */
	public UDPClient(String serverHost, int serverPort) throws NetworkException {
		this(DEFAULT_PORT, new InetSocketAddress(serverHost, serverPort));
	}

	/**
	 * UDP Client constructor
	 *
	 * @param clientPort client port
	 * @param serverHost remote server host
	 * @param serverPort remote server port
	 * @throws NetworkException if something went wrong during socket creating
	 */
	public UDPClient(int clientPort, String serverHost, int serverPort) throws NetworkException {
		this(clientPort, new InetSocketAddress(serverHost, serverPort));
	}

	/**
	 * UDP Client constructor
	 *
	 * @param clientPort client port
	 * @param serverAddress server address
	 * @throws NetworkException if something went wrong during socket creating
	 */
	public UDPClient(int clientPort, InetSocketAddress serverAddress) throws NetworkException {
		this.serverAddress = serverAddress;
		this.clientAddress = new InetSocketAddress(clientPort);

		this.requestBuffer = ByteBuffer.allocate(1024 * 63);
		this.responseBuffer = ByteBuffer.allocate(1024 * 63);

		try {
			this.socket = new DatagramSocket(this.clientAddress);
		}
		catch (SocketException e) {
			throw new NetworkException("Unable to create socket", e);
		}
	}

	/**
	 * This method allows sending command request for the server
	 *
	 * @param request command request instance
	 * @return command response instance
	 * @throws NetworkException if something went wrong during message sending
	 */
	public CommandResponse sendCommandRequest(CommandRequest request) throws NetworkException {

//		Every request's sender address should be set
		request.setSenderAddress(clientAddress);

//		Clearing request and response buffers
		requestBuffer.clear();
		responseBuffer.clear();

//		Request object converted to a byte array put into request buffer
		requestBuffer.put(RequestMapper.mapRequestToBytes(request));

		try {
//			Sending request to the server
			DatagramPacket requestPacket = new DatagramPacket(requestBuffer.array(), requestBuffer.array().length, serverAddress);
			socket.send(requestPacket);

//			Waiting response from the server
			DatagramPacket responsePacket = new DatagramPacket(responseBuffer.array(), responseBuffer.array().length);
			socket.receive(responsePacket);
			return (CommandResponse) ResponseMapper.mapResponseObjectFromBytes(responsePacket.getData());
		}
		catch (IOException e) {
			throw new NetworkException("Unable to sent request to the server or get a response", e);
		}
	}

	/**
	 * Closing client socket
	 */
	@Override
	public void close() {
		socket.close();
	}
}
