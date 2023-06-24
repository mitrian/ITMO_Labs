package com.mitrian.server.threads;

import com.mitrian.common.network.UDPChannelServer;
import com.mitrian.common.network.exception.NetworkException;
import com.mitrian.common.network.model.response.AbstractResponse;

public class ResponseThread extends Thread{
    private final AbstractResponse abstractResponse;

    private final UDPChannelServer udpChannelServer;

    public ResponseThread(UDPChannelServer udpChannelServer, AbstractResponse abstractResponse) {
        this.udpChannelServer = udpChannelServer;
        this.abstractResponse = abstractResponse;
    }

    @Override
    public void run() {
        try {
            udpChannelServer.sendResponse(abstractResponse);
        } catch (NetworkException ignored) {}
    }
}
