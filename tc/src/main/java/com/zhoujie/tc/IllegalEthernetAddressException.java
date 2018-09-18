package com.zhoujie.tc;

/**
 * Thrown to indicate that a ethernet address could not be created.
 ** @see EthernetAddress
 */
public class IllegalEthernetAddressException extends Exception {

	public IllegalEthernetAddressException() {
		super();
	}

	public IllegalEthernetAddressException(String message) {
		super(message);
	}
}
