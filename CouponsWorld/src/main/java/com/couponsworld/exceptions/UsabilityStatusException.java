package com.couponsworld.exceptions;

@SuppressWarnings("serial")
public class UsabilityStatusException extends Exception {

	String errorMessage;

	public UsabilityStatusException(String errorMessage) {
		// TODO Auto-generated constructor stub
		super(errorMessage);
	}
}
