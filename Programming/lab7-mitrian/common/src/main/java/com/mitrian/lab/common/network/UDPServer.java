package com.mitrian.lab.common.network;

import com.mitrian.lab.common.network.exception.NetworkException;
import com.mitrian.lab.common.network.handler.RequestHandler;
import com.mitrian.lab.common.network.model.impl.command.CommandRequest;
import com.mitrian.lab.common.network.model.impl.command.CommandResponse;
import com.mitrian.lab.common.network.util.RequestMapper;
import com.mitrian.lab.common.network.util.ResponseMapper;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.Channel;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Iterator;

public class UDPServer implements AutoCloseable {

	private static final int DEFAULT_PORT = 8080;

	private final InetSocketAddress serverAddress;

	private final ByteBuffer incomingBuffer;

	private final DatagramChannel datagramChannel;
	private final Selector selector;

	public UDPServer() throws NetworkException {
		this(new InetSocketAddress(DEFAULT_PORT));
	}

	public UDPServer(int port) throws NetworkException {
		this(new InetSocketAddress(port));
	}

	public UDPServer(InetSocketAddress serverAddress) throws NetworkException {
		this.serverAddress = serverAddress;
		this.incomingBuffer = ByteBuffer.allocate(1024 * 63);

		try {
			datagramChannel = DatagramChannel.open();
			selector = Selector.open();

			datagramChannel.socket().bind(this.serverAddress);
			datagramChannel.configureBlocking(false);
			datagramChannel.register(selector, SelectionKey.OP_READ);
		}
		catch (IOException e)
		{
			throw new NetworkException("Unable to open channel or selector", e);
		}
	}

	public void run(RequestHandler<CommandRequest, CommandResponse> requestHandler) throws NetworkException {

		try {
			int selectionResult = selector.select();
				if (selectionResult != 0) return;

				for (Iterator<SelectionKey> i = selector.selectedKeys().iterator(); i.hasNext();) {
					SelectionKey selectionKey = i.next();
					i.remove();

					Channel channel = selectionKey.channel();

					if (selectionKey.isReadable() && channel == datagramChannel)
					{
						incomingBuffer.clear();
						datagramChannel.receive(incomingBuffer);
						CommandRequest request = (CommandRequest) RequestMapper.mapRequestObjectFromBytes(incomingBuffer.array());

						CommandResponse response = requestHandler.handle(request);

						datagramChannel.send(ByteBuffer.wrap(ResponseMapper.mapResponseToBytes(response)), request.getSenderAddress());
					}
				}
		}
		catch (IOException e) {
			throw new NetworkException("Unable to receive channel or sent response", e);
		}
	}

	@Override
	public void close() throws NetworkException {
		try {
			selector.close();
			datagramChannel.close();
		}
		catch (IOException e) {
			throw new NetworkException("Unable to close selector or channel", e);
		}
	}
}
