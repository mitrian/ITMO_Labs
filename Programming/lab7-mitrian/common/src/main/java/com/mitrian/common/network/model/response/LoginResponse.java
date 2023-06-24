package com.mitrian.common.network.model.response;

import com.mitrian.common.commands.util.ExecutionResult;
import com.mitrian.common.commands.util.ExecutionStatus;

import java.net.InetSocketAddress;

public final class LoginResponse extends AbstractResponse {
    public LoginResponse(
            InetSocketAddress from,
            InetSocketAddress to,
            ResponseCode code
    ) {
        super(from, to, code);
    }
}
