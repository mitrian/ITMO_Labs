package com.mitrian.common.network.model.response;

import com.mitrian.common.commands.util.ExecutionResult;
import com.mitrian.common.commands.util.ExecutionStatus;

import java.net.InetSocketAddress;

public final class RegisterResponse extends AbstractResponse {

    public RegisterResponse(
            InetSocketAddress from,
            InetSocketAddress to,
            ResponseCode code
    ) {
        super(from, to, code);
    }
}
