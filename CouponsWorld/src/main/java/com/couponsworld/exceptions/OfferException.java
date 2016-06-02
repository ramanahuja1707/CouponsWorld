package com.couponsworld.exceptions;

@SuppressWarnings("serial")
public class OfferException extends Exception {

	String errorMessage;

	public OfferException(String errorMessage) {
		// TODO Auto-generated constructor stub
		super(errorMessage);
	}

}
