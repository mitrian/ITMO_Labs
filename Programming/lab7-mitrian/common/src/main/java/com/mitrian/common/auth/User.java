package com.mitrian.common.auth;

import com.mitrian.common.auth.utils.SHA512Generator;
import com.mitrian.common.exceptions.impl.algorithm.SHA512Exception;

import java.io.Serializable;

public class User implements Serializable {
    private final String userName;
    private final String password;

    public User(String userName, String password) {
        String password1;
        this.userName = userName;
        try {
            password1 = SHA512Generator.hash(password);
        } catch (SHA512Exception e) {
            password1 = null;
        }
        this.password = password1;
    }

    public String getUserName(){
        return userName;
    }

    public String getPassword(){
        return password;
    }
}
