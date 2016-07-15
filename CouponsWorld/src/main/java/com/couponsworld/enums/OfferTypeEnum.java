package com.couponsworld.enums;

public enum OfferTypeEnum {
	// Objects for Enum
	CASHBACK("cashback"), DEAL("deal"), DISCOUNT("discount");

	// data member
	String offerTypeName;

	// ctor for Enum
	private OfferTypeEnum(String offerTypeName) {
		this.offerTypeName = offerTypeName;
	}

	public String getOfferTypeName() {
		return offerTypeName;
	}

}
