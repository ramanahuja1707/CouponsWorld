package com.couponsworld.apiresults;

import java.util.List;

import com.couponsworld.dto.Company;
import com.couponsworld.enums.Status;

public class ResultantCompany {

	private List<Company> companies = null;
	private Status status = null;
	private List<Error> errors = null;
	private List<Link> links = null;

	public List<Company> getCompanies() {
		return companies;
	}

	public void setCompanies(List<Company> companies) {
		this.companies = companies;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public List<Error> getErrors() {
		return errors;
	}

	public void setErrors(List<Error> errors) {
		this.errors = errors;
	}

	public List<Link> getLinks() {
		return links;
	}

	public void setLinks(List<Link> links) {
		this.links = links;
	}

}
