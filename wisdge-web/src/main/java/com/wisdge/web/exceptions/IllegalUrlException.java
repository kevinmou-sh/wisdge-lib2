package com.wisdge.web.exceptions;

public class IllegalUrlException extends Exception {
	private int code;
	
	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public IllegalUrlException(int code, String message) {
		super(message);
		this.code = code;
	}

}
