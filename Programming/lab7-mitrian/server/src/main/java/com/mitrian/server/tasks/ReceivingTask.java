package com.mitrian.server.tasks;

import com.mitrian.common.network.UDPChannelServer;
import com.mitrian.common.network.exception.NetworkException;
import com.mitrian.common.network.model.request.Request;

import java.util.concurrent.ExecutorService;

public class ReceivingTask implements Runnable{

    private final UDPChannelServer udpChannelServer;

    private final ExecutorService handlingPool;

    public ReceivingTask(UDPChannelServer udpChannelServer, ExecutorService handlingPool) {
        this.udpChannelServer = udpChannelServer;
        this.handlingPool = handlingPool;
    }


    @Override
    public void run() {
        while (true) {
            try {
                Request request = udpChannelServer.waitRequest();
                handlingPool.submit(new HandlingTask(udpChannelServer, request));
            } catch (NetworkException ignored) {}
        }
    }

}
