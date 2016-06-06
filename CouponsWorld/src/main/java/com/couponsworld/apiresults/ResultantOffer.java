package com.couponsworld.apiresults;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.couponsworld.dto.Offer;
import com.couponsworld.enums.Status;

@XmlRootElement
public class ResultantOffer {

	private List<Offer> offers = null;
	private Status status = null;
	private List<Error> errors = null;
	private List<Link> links = null;

	public List<Offer> getOffers() {
		return offers;
	}

	public void setOffers(List<Offer> offers) {
		this.offers = offers;
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
