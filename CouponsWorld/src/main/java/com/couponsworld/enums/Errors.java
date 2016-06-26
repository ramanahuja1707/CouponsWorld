package com.couponsworld.enums;

public enum Errors {
	// Objects for Enum
	GENERAL_ERROR(100L, "Some Error Occured..."), NULL_POINTER_ERROR(101L, "Null Values Passed to API"), OFFER_ERROR(
			102L, "Error related to Offer Occured"), CATEGORY_ERROR(103L,
					"Error related to category Occured"), API_AUTHENTICATION_ERROR(104L,
							"API Authentication Error Occured"), COMPANY_ERROR(105L,
									"Error related to Company Occured"), SUBCATEGORY_ERROR(106L,
											"Error related to Sub Category Occured"), USABILITY_STATUS_ERROR(107L,
													"Error related to Usability Status Occured"), USER_PLATFORM_ERROR(
															108L,
															"Error related to Usability Status Occured"), USER_TYPE_ERROR(
																	109L,
																	"Error related to Usability Status Occured"), VALIDATION_ERROR(
																			110L,
																			"Validation Error Occured"), MISSING_MANDATORY_PARAMETERS(
																					111L,
																					"Missing Mandatory Parameters"), LOGIN_AUTHENTICATION_ERROR(
																							112L,
																							"Username/Password Incorrect"), SESSION_EXPIRED_ERROR(
																									113L,
																									"Session Expired");
	;

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
