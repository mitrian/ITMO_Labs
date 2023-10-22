package com.mitrian.common.network.model.frame;

import java.io.Serializable;

/**
 * This class represents a piece of data sent over the UPD protocol
 *
 */
public final class UDPFrame implements Serializable {
	/**
	 * The data
	 *
	 */
	private final byte[] data;

	/**
	 * The isLast flag
	 *
	 */
	private final boolean last;

	/**
	 * UDPFrame constructor
	 *
	 * @param data the data that is being transmitted
	 * @param last is the frame last
	 */
	public UDPFrame(byte[] data, boolean last) {
		this.data = data;
		this.last = last;
	}

	/**
	 * UDPFrame data getter
	 *
	 * @return data bytes
	 */
	public byte[] getData()
	{
		return data;
	}

	/**
	 * Last flag getter
	 *
	 * @return true if the frame is last and false otherwise
	 */
	public boolean isLast()
	{
		return last;
	}

}
