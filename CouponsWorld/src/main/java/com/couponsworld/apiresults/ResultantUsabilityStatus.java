package com.couponsworld.apiresults;

import java.util.List;

import com.couponsworld.dto.UsabilityStatus;
import com.couponsworld.enums.Status;

public class ResultantUsabilityStatus {

	private List<UsabilityStatus> usabilityStatus = null;
	private Status status = null;
	private List<Error> errors = null;
	private List<Link> links = null;

	public List<UsabilityStatus> getUsabilityStatus() {
		return usabilityStatus;
	}

	public void setUsabilityStatus(List<UsabilityStatus> usabilityStatus) {
		this.usabilityStatus = usabilityStatus;
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
