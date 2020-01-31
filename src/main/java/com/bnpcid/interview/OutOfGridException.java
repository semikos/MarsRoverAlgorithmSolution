package com.bnpcid.interview;

public class OutOfGridException extends IllegalArgumentException {

	private static final long serialVersionUID = 354054054054L;

	public OutOfGridException() {
	}

	public OutOfGridException(String message) {
		super(message);
	}

}
