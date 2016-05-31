package com.couponsworld.enums;

public enum Status {

	// Objects for Enum
	SUCCESS("success"), FAILED("failed");

	// data member
	String status;

	// ctor for Enum
	private Status(String status) {
		this.status = status;
	}
}
