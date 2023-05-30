package com.mitrian.common.network.util.mapper;

import com.mitrian.common.network.model.frame.UDPFrame;
import com.mitrian.common.network.util.mapper.exception.MappingException;
import org.apache.commons.lang3.SerializationException;
import org.apache.commons.lang3.SerializationUtils;

/**
 * This class converts UDPFrame instance into a byte array and in an opposite way
 *
 */
public final class FrameMapper {
	/**
	 * This method maps {@link UDPFrame} instance into raw response bytes
	 *
	 * @param udpFrame {@link UDPFrame} instance
	 * @return frame raw bytes
	 * @throws MappingException if serialization not succeed
	 */
	public static byte[] mapFromInstanceToBytes(UDPFrame udpFrame) throws MappingException {
		try {
			return SerializationUtils.serialize(udpFrame);
		}
		catch (SerializationException e) {
			throw new MappingException("Failed to map UDPFrame to bytes", e);
		}
	}

	/**
	 *
	 *
	 * @param udpFrameBytes raw {@link UDPFrame} bytes
	 * @return {@link UDPFrame} instance
	 * @throws MappingException if deserialization not succeed
	 */
	public static UDPFrame mapFromBytesToInstance(byte[] udpFrameBytes) throws MappingException {
		try {
			return SerializationUtils.deserialize(udpFrameBytes);
		}
		catch (SerializationException e) {
			throw new MappingException("Failed to map UDPFrame bytes to instance", e);
		}
	}
}
