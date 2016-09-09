package com.couponsworld.exceptions;

@SuppressWarnings("serial")
public class BestOfferException extends Exception {
	String errorMessage;

	public BestOfferException(String errorMessage) {
		// TODO Auto-generated constructor stub
		super(errorMessage);
	}

}
