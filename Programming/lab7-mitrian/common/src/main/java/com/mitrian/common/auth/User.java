package com.mitrian.common.auth;

import com.mitrian.common.auth.utils.SHA512Generator;
import com.mitrian.common.exceptions.impl.algorithm.SHA512Exception;

import java.io.Serializable;

public class User implements Serializable {
    private final String userName;
    private String password;

    public User(String userName, String password) {
        this.userName = userName;
        try {
            this.password = SHA512Generator.hash(password);
        } catch (SHA512Exception e){
            this.password = null;
        }
    }
}
