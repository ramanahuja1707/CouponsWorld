package com.couponsworld.apiresults;

import java.util.List;

import com.couponsworld.enums.Status;

public class ResultantFilter {

	private Status status = null;
	private List<Error> errors = null;
	private List<List<Link>> links = null;

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

	public List<List<Link>> getLinks() {
		return links;
	}

	public void setLinks(List<List<Link>> links) {
		this.links = links;
	}

}
