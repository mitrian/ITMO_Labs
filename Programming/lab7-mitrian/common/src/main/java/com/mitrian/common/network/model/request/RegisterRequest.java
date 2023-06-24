package com.mitrian.common.network.model.request;

import com.mitrian.common.auth.User;

import java.io.Serializable;
import java.net.InetSocketAddress;

public final class RegisterRequest extends Request implements Serializable {

    private final User user;
    /**
     * Request constructor
     *
     * @param from  source address
     * @param to    destination address
     * @param user
     */
    public RegisterRequest(InetSocketAddress from, InetSocketAddress to, User user) {
        super(from, to);
        this.user = user;
    }

    public User getUser(){
        return this.user;
    }
}