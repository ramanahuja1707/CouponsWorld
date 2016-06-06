package com.couponsworld.exceptions;

@SuppressWarnings("serial")
public class CategoryException extends Exception {
	String errorMessage;

	public CategoryException(String errorMessage) {
		// TODO Auto-generated constructor stub
		super(errorMessage);
	}
}
