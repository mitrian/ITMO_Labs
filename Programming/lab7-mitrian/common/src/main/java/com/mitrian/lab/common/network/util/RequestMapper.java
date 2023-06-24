package com.mitrian.lab.common.network.util;

import com.mitrian.lab.common.network.exception.NetworkException;
import com.mitrian.lab.common.network.model.Request;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class RequestMapper {

	public static byte[] mapRequestToBytes(Request request) throws NetworkException
	{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try (baos; ObjectOutputStream oos = new ObjectOutputStream(baos))
		{
			oos.writeObject(request);
			return baos.toByteArray();
		}
		catch (Exception e)
		{
			throw new NetworkException("Unable to map request object to bytes array", e);
		}
	}

	public static Request mapRequestObjectFromBytes(byte[] requestBytes) throws NetworkException
	{
		ByteArrayInputStream bais = new ByteArrayInputStream(requestBytes);
		try (bais; ObjectInputStream ois = new ObjectInputStream(bais))
		{
			return (Request) ois.readObject();
		}
		catch (Exception e)
		{
			throw new NetworkException("Unable to get request object from byte array", e);
		}
	}


}
