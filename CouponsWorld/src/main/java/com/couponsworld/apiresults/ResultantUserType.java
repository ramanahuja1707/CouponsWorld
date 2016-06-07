package com.couponsworld.apiresults;

import java.util.List;

import com.couponsworld.dto.UserType;
import com.couponsworld.enums.Status;

public class ResultantUserType {

	private List<UserType> userType = null;
	private Status status = null;
	private List<Error> errors = null;
	private List<Link> links = null;

	public List<UserType> getUserType() {
		return userType;
	}

	public void setUserType(List<UserType> userType) {
		this.userType = userType;
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
