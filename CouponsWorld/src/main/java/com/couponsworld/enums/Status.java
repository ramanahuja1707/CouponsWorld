package com.couponsworld.enums;

public enum Status {

	// Objects for Enum
	SUCCESS("success"), FAILURE("failure");

	// data member
	String status;

	// ctor for Enum
	private Status(String status) {
		this.status = status;
	}
}
