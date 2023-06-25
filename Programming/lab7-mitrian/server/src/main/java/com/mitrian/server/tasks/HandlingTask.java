package com.mitrian.server.tasks;

import com.mitrian.common.commands.util.ExecutionResult;
import com.mitrian.common.network.UDPChannelServer;
import com.mitrian.common.network.model.handler.RequestHandler;
import com.mitrian.common.network.model.request.Request;
import com.mitrian.common.network.model.response.AbstractResponse;
import com.mitrian.common.network.model.response.ResponseCode;
import com.mitrian.server.threads.ResponseThread;

import java.sql.SQLException;

public class HandlingTask implements Runnable{

    private RequestHandler requestHandler;
    private UDPChannelServer udpChannelServer;

    private Request request;

    public HandlingTask(UDPChannelServer udpChannelServer, Request request){
        this.udpChannelServer = udpChannelServer;
        this.requestHandler = udpChannelServer.getRequestHandler();
        this.request = request;
    }

    @Override
    public void run() {
        AbstractResponse r = null;
        r = requestHandler.handle(request);
        System.out.println(r);
        Thread t = new ResponseThread(udpChannelServer, r);
        t.start();
    }
}
