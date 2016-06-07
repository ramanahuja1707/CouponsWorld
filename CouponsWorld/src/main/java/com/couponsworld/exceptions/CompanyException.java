package com.couponsworld.exceptions;

@SuppressWarnings("serial")
public class CompanyException extends Exception {
	String errorMessage;

	public CompanyException(String errorMessage) {
		// TODO Auto-generated constructor stub
		super(errorMessage);
	}

}
