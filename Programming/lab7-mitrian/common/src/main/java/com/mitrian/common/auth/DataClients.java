package com.mitrian.common.auth;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class DataClients implements Serializable {

    private final String command;

    private String[] args;

    private final String name;

    private final String password;

    public DataClients(String command, String[] args, String usersName,String password) {
        this.command = command;
        this.args = args;
        this.name = usersName;
        this.password = password;
    }

    public DataClients(String commandWithArgs, String[] args) {
        command = commandWithArgs;
        this.args = args;
        name = null;
        password = null;
    }



    public byte[] getBytes()  {
        byte[] serializedObj = {};
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            new ObjectOutputStream(byteArrayOutputStream).writeObject(this);
            serializedObj = byteArrayOutputStream.toByteArray();
            byteArrayOutputStream.close();
        } catch (NullPointerException e) {
            System.out.println("Epic fail - null.");
        } catch (IOException e) {
            System.out.println("Failed to convert client message into bytes.");
        }
        return serializedObj;
    }
}
