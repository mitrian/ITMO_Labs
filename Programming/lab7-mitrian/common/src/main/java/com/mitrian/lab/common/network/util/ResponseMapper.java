package com.mitrian.lab.common.network.util;

import com.mitrian.lab.common.network.exception.NetworkException;
import com.mitrian.lab.common.network.model.Response;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ResponseMapper {
	public static byte[] mapResponseToBytes(Response response) throws NetworkException
	{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try (baos; ObjectOutputStream oos = new ObjectOutputStream(baos))
		{
			oos.writeObject(response);
			return baos.toByteArray();
		}
		catch (Exception e)
		{
			throw new NetworkException("Unable to map response object to bytes array", e);
		}
	}

	public static Response mapResponseObjectFromBytes(byte[] responseBytes) throws NetworkException
	{
		ByteArrayInputStream bais = new ByteArrayInputStream(responseBytes);
		try (bais; ObjectInputStream ois = new ObjectInputStream(bais))
		{
			return (Response) ois.readObject();
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			throw new NetworkException("Unable to map response object from byte array", e);
		}
	}

}
