package com.bonc.rdpe.exception;

public class RdpeException extends Exception {

	private static final long serialVersionUID = 8223695879162870317L;

	public RdpeException(String msg, Throwable t) {
		super(msg, t);
	}

	public RdpeException(String msg) {
		super(msg);
	}

	public RdpeException(Throwable t) {
		super(t);
	}

	public RdpeException() {
		super();
	}
}
