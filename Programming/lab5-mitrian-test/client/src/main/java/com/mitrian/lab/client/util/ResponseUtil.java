package com.mitrian.lab.client.util;

import com.mitrian.lab.common.Response;
import com.mitrian.lab.common.exceptions.NetworkException;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ResponseUtil {

    public static byte[] streamResponseAsBytes(Response response) throws NetworkException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try (baos; ObjectOutputStream oos = new ObjectOutputStream(baos)) {
            oos.writeObject(response);
            return baos.toByteArray();
        } catch (Exception e) {
            throw new NetworkException("Невозможно обработать ответ сервера");
        }
    }

    public static Response responseObjectFromBytes(byte[] requestBytes) throws NetworkException {
        ByteArrayInputStream bais = new ByteArrayInputStream(requestBytes);
        try (bais; ObjectInputStream ois = new ObjectInputStream(bais)) {
            return (Response) ois.readObject();
        } catch (Exception e){
            throw new NetworkException("Из запроса невозможно получить объект");
        }
    }
}
