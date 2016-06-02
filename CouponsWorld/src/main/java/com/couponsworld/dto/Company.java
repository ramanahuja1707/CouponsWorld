package com.couponsworld.dto;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

@Entity
public class Company {

	@Id
	private long companyId;
	private String companyName;
	private String companyDescription;
	private String companyLogoName;

	public long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyDescription() {
		return companyDescription;
	}

	public void setCompanyDescription(String companyDescription) {
		this.companyDescription = companyDescription;
	}

	public String getCompanyLogoName() {
		return companyLogoName;
	}

	public void setCompanyLogoName(String companyLogoName) {
		this.companyLogoName = companyLogoName;
	}

}
