package com.couponsworld.exceptions;

@SuppressWarnings("serial")
public class MissingMandatoryParametersException extends Exception {
	String errorMessage;

	public MissingMandatoryParametersException(String errorMessage) {
		// TODO Auto-generated constructor stub
		super(errorMessage);
	}
}
