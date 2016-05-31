package com.couponsworld.dto;

import javax.xml.bind.annotation.XmlRootElement;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

@Entity
public class Offer {

	@Id
	long offerId;
	String category;
	String company;
	long cashBack;
	String cashBackMode;
	String description;
	String termsAndConditions;
	String promoCode;
	String startDate;
	String expiryDate;
	String extraOfferReference;
	long totalExtraOffers;
	String restrictions;
	String userType;
	String userPlatform;
	String usabilityStatus;
	String catchyHeading;
	String subCategory;
	long maximumCashBack;

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
