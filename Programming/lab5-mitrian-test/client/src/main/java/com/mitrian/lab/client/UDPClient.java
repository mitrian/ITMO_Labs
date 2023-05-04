package com.mitrian.lab.client;

import com.mitrian.lab.common.Request;
import com.mitrian.lab.common.Response;
import com.mitrian.lab.common.exceptions.NetworkException;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.util.Arrays;

import static com.mitrian.lab.client.util.RequestUtil.streamRequestAsBytes;
import static com.mitrian.lab.client.util.ResponseUtil.responseObjectFromBytes;

public class UDPClient {

    private static final int CLIENT_PORT = 8888;
    private final InetSocketAddress clientSocketAddress;
    private final InetSocketAddress serverSocketAddress;
    private byte[] requestBuffer;
    private byte[] responseBuffer;
    private final DatagramSocket socket;

    public UDPClient(int clientPort, String serverHost, int serverPort) throws NetworkException {
        this(new InetSocketAddress(clientPort), new InetSocketAddress(serverHost, serverPort));
    }

//    public UDPClient(int clientPort, int serverPort) {
//        this(new InetSocketAddress(clientPort), new InetSocketAddress(serverHost, serverPort));
//
//
//    }

    public UDPClient(InetSocketAddress clientSocketAddress, InetSocketAddress serverSocketAddress) throws NetworkException {
        this.clientSocketAddress = clientSocketAddress;
        this.serverSocketAddress = serverSocketAddress;

        try {
            this.socket = new DatagramSocket(clientSocketAddress);
        } catch (SocketException e){
            throw new NetworkException("Невозможно создать сокет");
        }
    }

    public Response send(Request request) throws NetworkException {
        Arrays.fill(responseBuffer, (byte) 0);
        requestBuffer = streamRequestAsBytes(request);

        DatagramPacket requestPacket = new DatagramPacket(requestBuffer, requestBuffer.length, serverSocketAddress);

        try {
            socket.send(requestPacket);

            DatagramPacket responsePacket = new DatagramPacket(responseBuffer, responseBuffer.length);
            socket.receive(responsePacket);

            return responseObjectFromBytes(responsePacket.getData());
        } catch (IOException e) {
            throw new NetworkException("Невозможно отправить запрос серверу");
        }
    }
}
