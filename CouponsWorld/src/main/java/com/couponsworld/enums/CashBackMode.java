package com.couponsworld.enums;

public enum CashBackMode {

	// Objects for Enum
	PERCENTAGE("percentage"), AMOUNT("amount");

	// data member
	String cashBackModeName;

	// ctor for Enum
	private CashBackMode(String cashBackModeName) {
		this.cashBackModeName = cashBackModeName;
	}
}
