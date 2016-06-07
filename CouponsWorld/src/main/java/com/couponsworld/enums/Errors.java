package com.couponsworld.enums;

public enum Errors {
	// Objects for Enum
	GENERAL_ERROR(100L, "Some Error Occured..."), NULL_POINTER_ERROR(101L, "Null Values Passed to API"), OFFER_ERROR(
			102L, "Error related to Offer Occured"), CATEGORY_ERROR(103L,
					"Error related to category Occured"), API_AUTHENTICATION_ERROR(104L,
							"API Authentication Error Occured");

	// data member
	private String errorName;
	private long errorCode;

	// ctor for Enum
	private Errors(long errorCode, String errorName) {
		this.errorCode = errorCode;
		this.errorName = errorName;
	}

	public String getErrorName() {
		return errorName;
	}

	public void setErrorName(String errorName) {
		this.errorName = errorName;
	}

	public long getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(long errorCode) {
		this.errorCode = errorCode;
	}

}