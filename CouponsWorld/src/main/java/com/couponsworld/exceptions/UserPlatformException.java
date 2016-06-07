package com.couponsworld.exceptions;

@SuppressWarnings("serial")
public class UserPlatformException extends Exception {
	String errorMessage;

	public UserPlatformException(String errorMessage) {
		// TODO Auto-generated constructor stub
		super(errorMessage);
	}

}
