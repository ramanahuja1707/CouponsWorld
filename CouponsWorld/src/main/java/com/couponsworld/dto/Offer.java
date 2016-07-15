package com.couponsworld.dto;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

@Entity
public class Offer {

	// Mandatory Param - M
	// Optional Parameters - O

	@Id
	private long offerId; // M
	@Index
	private String offerType;
	@Index
	private String category; // M
	@Index
	private String company; // M
	private long cashBack; // M
	@Index
	private long minimumAmount;
	@Index
	private String cashBackMode; // M
	private String description; // M
	private String termsAndConditions; // O
	@Index
	private String promoCode; // O
	@Index
	private String startDate; // M
	@Index
	private String expiryDate; // M
	private String extraOfferReference; // O
	private long totalExtraOffers; // O
	private String restrictions; // O
	@Index
	private String userType; // M
	@Index
	private String userPlatform; // M
	@Index
	private String usabilityStatus; // M
	private String catchyHeading; // O
	@Index
	private String subCategory; // M4
	@Index
	private long maximumCashBack; // depend upon condition if
									// cashBackMode=PERCENTAGE

	public long getMinimumAmount() {
		return minimumAmount;
	}

	public void setMinimumAmount(long minimumAmount) {
		this.minimumAmount = minimumAmount;
	}

	public String getOfferType() {
		return offerType;
	}

	public void setOfferType(String offerType) {
		this.offerType = offerType;
	}

	public long getOfferId() {
		return offerId;
	}

	public void setOfferId(long offerId) {
		this.offerId = offerId;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public long getCashBack() {
		return cashBack;
	}

	public void setCashBack(long cashBack) {
		this.cashBack = cashBack;
	}

	public String getCashBackMode() {
		return cashBackMode;
	}

	public void setCashBackMode(String cashBackMode) {
		this.cashBackMode = cashBackMode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTermsAndConditions() {
		return termsAndConditions;
	}

	public void setTermsAndConditions(String termsAndConditions) {
		this.termsAndConditions = termsAndConditions;
	}

	public String getPromoCode() {
		return promoCode;
	}

	public void setPromoCode(String promoCode) {
		this.promoCode = promoCode;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}

	public String getExtraOfferReference() {
		return extraOfferReference;
	}

	public void setExtraOfferReference(String extraOfferReference) {
		this.extraOfferReference = extraOfferReference;
	}

	public long getTotalExtraOffers() {
		return totalExtraOffers;
	}

	public void setTotalExtraOffers(long totalExtraOffers) {
		this.totalExtraOffers = totalExtraOffers;
	}

	public String getRestrictions() {
		return restrictions;
	}

	public void setRestrictions(String restrictions) {
		this.restrictions = restrictions;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getUserPlatform() {
		return userPlatform;
	}

	public void setUserPlatform(String userPlatform) {
		this.userPlatform = userPlatform;
	}

	public String getUsabilityStatus() {
		return usabilityStatus;
	}

	public void setUsabilityStatus(String usabilityStatus) {
		this.usabilityStatus = usabilityStatus;
	}

	public String getCatchyHeading() {
		return catchyHeading;
	}

	public void setCatchyHeading(String catchyHeading) {
		this.catchyHeading = catchyHeading;
	}

	public String getSubCategory() {
		return subCategory;
	}

	public void setSubCategory(String subCategory) {
		this.subCategory = subCategory;
	}

	public long getMaximumCashBack() {
		return maximumCashBack;
	}

	public void setMaximumCashBack(long maximumCashBack) {
		this.maximumCashBack = maximumCashBack;
	}

}
