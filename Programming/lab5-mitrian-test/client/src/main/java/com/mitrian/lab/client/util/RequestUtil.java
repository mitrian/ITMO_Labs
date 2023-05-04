package com.mitrian.lab.client.util;

import com.mitrian.lab.common.Request;
import com.mitrian.lab.common.exceptions.NetworkException;

import java.io.*;

public class RequestUtil {
    public static byte[] streamRequestAsBytes(Request request) throws NetworkException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try (baos; ObjectOutputStream ois = new ObjectOutputStream(baos)) {
            ois.writeObject(request);
            return baos.toByteArray();
        } catch (Exception e) {
            throw new NetworkException("Невозможно преобразовать запрос в байты");
        }
    }

    public static Request requestObjectFromBytes(byte[] requestByte) throws NetworkException {
        ByteArrayInputStream bais = new ByteArrayInputStream(requestByte);
        try (bais; ObjectInputStream ois = new ObjectInputStream(bais)){
            return (Request) ois.readObject();
        } catch (Exception e){
            throw new NetworkException("Невозможно получить запрос из байтов");
        }
    }
}
