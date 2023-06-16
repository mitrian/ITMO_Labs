package com.mitrian.common.auth;


import com.mitrian.common.network.model.response.Response;
import com.mitrian.common.network.model.response.ResponseCode;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class DataServer implements Serializable {

    private ArrayList<String> message;

    private ResponseCode responseCode;

    public DataServer(ArrayList<String> message, ResponseCode responseCode) {
        this.message = message;
        this.responseCode = responseCode;
    }

    public DataServer(ArrayList<String> message) {
        this.message = message;
    }

    public byte[] getBytes() {
        byte[] serializedObj = {};
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            new ObjectOutputStream(byteArrayOutputStream).writeObject(this);
            serializedObj = byteArrayOutputStream.toByteArray();
            byteArrayOutputStream.close();
        } catch (NullPointerException e) {
            System.out.println("Epic fail - null.");
        } catch (IOException e) {
            System.out.println("Не удла");
        }
        return serializedObj;
    }
}
