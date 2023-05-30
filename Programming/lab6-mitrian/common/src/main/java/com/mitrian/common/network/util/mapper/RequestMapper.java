package com.mitrian.common.network.util.mapper;

import com.mitrian.common.network.model.request.Request;
import com.mitrian.common.network.util.mapper.exception.MappingException;
import org.apache.commons.lang3.SerializationException;
import org.apache.commons.lang3.SerializationUtils;

/**
 * This class converts element request instance into a byte array and in an opposite way
 *
 */
public final class RequestMapper {
	/**
	 * This method maps request instance into raw request bytes
	 *
	 * @param request request instance
	 * @return request raw bytes
	 * @throws MappingException if serialization not succeed
	 */
	public byte[] mapFromInstanceToBytes(Request request) throws MappingException {
		try {
			return SerializationUtils.serialize(request);
		}
		catch (SerializationException e) {
			throw new MappingException("Failed to map Request instance to bytes", e);
		}
	}

	/**
	 * This method maps raw request bytes into request instance
	 *
	 * @param bytes raw request bytes
	 * @return request instance
	 * @param <T> request type parameter
	 * @throws MappingException if deserialization not succeed
	 */
	@SuppressWarnings("unchecked")
	public <T extends Request> T mapFromBytesToInstance(byte[] bytes) throws MappingException {
		try {
			return SerializationUtils.deserialize(bytes);
		}
		catch (SerializationException e) {
			throw new MappingException("Failed to map Request bytes to instance", e);
		}
	}
}
