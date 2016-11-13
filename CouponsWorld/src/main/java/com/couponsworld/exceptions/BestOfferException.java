package com.couponsworld.exceptions;

import com.couponsworld.enums.CashBackMode;

@SuppressWarnings("serial")
public class BestOfferException extends Exception {
	String errorMessage;

	public BestOfferException(String errorMessage) {
		// TODO Auto-generated constructor stub

		super(errorMessage);
	}

}
