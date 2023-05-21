package com.mitrian.common.network;

import com.mitrian.common.network.exception.NetworkException;
import com.mitrian.common.network.model.frame.UDPFrame;
import com.mitrian.common.network.model.handler.RequestHandler;
import com.mitrian.common.network.model.request.Request;
import com.mitrian.common.network.model.response.Response;
import com.mitrian.common.network.util.NetworkUtils;
import com.mitrian.common.network.util.mapper.RequestMapper;
import com.mitrian.common.network.util.mapper.ResponseMapper;
import com.mitrian.common.network.util.mapper.FrameMapper;
import com.mitrian.common.network.util.mapper.exception.MappingException;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.StandardSocketOptions;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

/**
 * This class is a UDPChannel server implementation
 */
public class UDPChannelServer implements AutoCloseable
{
	/**
	 * Logger instance
	 *
	 */
	public static final Logger LOGGER = Logger.getLogger(UDPChannelServer.class.getName());

	/**
	 * Default server port
	 *
	 */
	private static final int DEFAULT_PORT = 8080;

	/**
	 * Datagram channel instance
	 *
	 */
	private final DatagramChannel channel;

	/**
	 * Request mapper instance
	 */
	private final RequestMapper requestMapper;

	/**
	 * Response mapper instance
	 */
	private final ResponseMapper responseMapper;

	/**
	 * UDPChannelServer constructor without port specified.
	 * Server will be bind to DEFAULT_PORT
	 *
	 */
	public UDPChannelServer(RequestMapper requestMapper, ResponseMapper responseMapper) throws NetworkException
	{
		this(DEFAULT_PORT, requestMapper, responseMapper);
	}

	/**
	 * UDPChannelServer constructor with port specified.
	 * Server will be bind to provided port
	 *
	 */
	public UDPChannelServer(
			int port,
			RequestMapper requestMapper,
			ResponseMapper responseMapper
	) throws NetworkException
	{
		this(new InetSocketAddress("localhost", port), requestMapper, responseMapper);
	}

	/**
	 * UDPChannelServer constructor with server address specified
	 * Server will be bind to provided address
	 *
	 */
	public UDPChannelServer(
			InetSocketAddress address,
			RequestMapper requestMapper,
			ResponseMapper responseMapper
	) throws NetworkException
	{
		this.requestMapper = requestMapper;
		this.responseMapper = responseMapper;

		try
		{
			this.channel = DatagramChannel.open();

//			Channel configuration
			channel.bind(address);
			this.channel.configureBlocking(false);
			this.channel.setOption(StandardSocketOptions.SO_REUSEADDR, true);
			this.channel.setOption(StandardSocketOptions.SO_REUSEPORT, true);

			LOGGER.info("Server successfully started on port " + address.getPort());
		}
		catch (IOException e)
		{
			throw new NetworkException("Failed to open DatagramChannel or Selector", e);
		}

	}

	/**
	 * This method handles the request with provided {@link RequestHandler} and
	 * sends response
	 *
	 * @param handler request handler
	 * @throws NetworkException if it's failed to select a channel or send the response
	 */
	public void listenAndHandleRequests(RequestHandler handler) throws NetworkException
	{
//		while (true)
//		{
			Request request = waitRequest();
			Response response = handler.handle(request);

			sendResponse(response);
//		}
	}

	/**
	 * Waiting request from clients
	 *
	 * @return request instance
	 * @param <T> request type parameter
	 * @throws NetworkException if it's failed to receive the request from client
	 */
	public <T extends Request> T waitRequest() throws NetworkException
	{
		ByteBuffer incomingBuffer = ByteBuffer.allocate(NetworkUtils.REQUEST_BUFFER_SIZE * 2);

		try
		{
			byte[] allRequestBytes = new byte[0];
			boolean gotAll = false;

			do
			{
//				Receiving incoming byte buffer
				incomingBuffer.clear();
				SocketAddress addr = channel.receive(incomingBuffer);
//				Skip current iteration if nothing was got in receive
				if (addr == null) continue;
				LOGGER.info("Got new request");

//				Retrieving current frame bytes from incoming byte buffer
				byte[] currentFrameBytes = new byte[incomingBuffer.position()];
				incomingBuffer.rewind();
				incomingBuffer.get(currentFrameBytes);

//				Mapping UDPFrame from raw bytes
				UDPFrame currentFrame = FrameMapper.mapFromBytesToInstance(currentFrameBytes);
//				Enriching request bytes with new bytes
				allRequestBytes = NetworkUtils.concatTwoByteArrays(allRequestBytes, currentFrame.getData());

//				Change gotAll state if got the last UDPFrame
				if (currentFrame.isLast())
				{
					gotAll = true;

				}

			} while (!gotAll);

//			Mapping request instance from raw request bytes
			return requestMapper.mapFromBytesToInstance(allRequestBytes);

		}
		catch (MappingException e)
		{
			throw new NetworkException("Mapping failure detected", e);
		}
		catch (IOException e)
		{
			throw new NetworkException("Failed to receive request from server", e);
		}
	}

	/**
	 * This method sends response
	 *
	 * @param response response instance
	 * @throws NetworkException if it's failed to send response with an overhead or
	 * if it's failed to send response without an overhead
	 */
	public void sendResponse(Response response) throws NetworkException
	{
//		Throwing NPE if response is null
		Objects.requireNonNull(response);

		try
		{
//			Mapping response to a byte array
			byte[] responseBytes = responseMapper.mapFromInstanceToBytes(response);

//			Check if response should be divided into separate chunks
			if (responseBytes.length > NetworkUtils.RESPONSE_BUFFER_SIZE)
				sendResponseWithOverhead(responseBytes, response.getTo());
			else
				sendResponseNoOverhead(responseBytes, response.getTo());
		}
		catch (MappingException e)
		{
			throw new NetworkException("Failed to map response instance to bytes", e);
		}
	}

	/**
	 * This method sends response with an overhead
	 *
	 * @param responseBytes raw response bytes
	 * @param destination response destination
	 * @throws NetworkException if it's failed to send response with an overhead
	 */
	private void sendResponseWithOverhead(byte[] responseBytes, InetSocketAddress destination) throws NetworkException
	{
//		Get response chunks from rew response bytes
		List<byte[]> responseChunks = NetworkUtils.splitArrayIntoChunks(responseBytes, NetworkUtils.RESPONSE_BUFFER_SIZE);

//		Wrap chunks with UDPFrames
		List<UDPFrame> udpFrames = NetworkUtils.wrapChunksWithUDPFrames(responseChunks);

//		Map udpFrames to bytes
		List<byte[]> framesBytes = NetworkUtils.udpFramesToBytes(udpFrames);

//		Sending all response frames to the client
		try
		{
			for (byte[] frameBytes : framesBytes)
				channel.send(ByteBuffer.wrap(frameBytes), destination);
		}
		catch (IOException e)
		{
			LOGGER.severe("Failed to send response with overhead: " + e.getMessage());
			throw new NetworkException("Failed to send response with an overhead", e);
		}
	}

	/**
	 * This method sends response without an overhead
	 *
	 * @param responseBytes raw response bytes
	 * @param destination response destination
	 * @throws NetworkException if it's failed to send response without an overhead
	 */
	public void sendResponseNoOverhead(byte[] responseBytes, InetSocketAddress destination) throws NetworkException
	{
		try
		{
//			Wrap raw response bytes with UDPFrame
			UDPFrame udpFrame = new UDPFrame(responseBytes, true);

//			Get UDPFrame bytes
			byte[] udpFrameBytes = FrameMapper.mapFromInstanceToBytes(udpFrame);

//			Sending response frame to the client
			channel.send(ByteBuffer.wrap(udpFrameBytes), destination);
		}
		catch (MappingException e)
		{
			throw new NetworkException("Failed to map frame to bytes", e);
		}
		catch (IOException e)
		{
			LOGGER.severe("Failed to send response without overhead: " + e.getMessage());
			throw new NetworkException("Failed to send response without an overhead", e);
		}
	}

	/**
	 * Method provided by {@link AutoCloseable} interface.
	 * Allows to use this class in the try-with-resources construction.
	 * Automatically closes selector and datagram channel
	 *
	 */
	@Override
	public void close() throws NetworkException
	{
		try
		{
			channel.close();
		}
		catch (IOException e)
		{
			throw new NetworkException("Unable to close Selector or DatagramChannel", e);
		}
	}
}
