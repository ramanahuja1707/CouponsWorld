package com.couponsworld.apiresults;

import java.util.List;
import java.util.Map;

import com.couponsworld.dto.Offer;
import com.couponsworld.enums.Status;

public class ResultantBestOffer {

	private Map<Long, List<Offer>> mapOfBestOffer = null;
	private List<Offer> bestOffers = null;
	private Status status = null;
	private List<Error> errors = null;

	public Map<Long, List<Offer>> getMapOfBestOffer() {
		return mapOfBestOffer;
	}

	public void setMapOfBestOffer(Map<Long, List<Offer>> mapOfBestOffer) {
		this.mapOfBestOffer = mapOfBestOffer;
	}

	public List<Offer> getBestOffers() {
		return bestOffers;
	}

	public void setBestOffers(List<Offer> bestOffers) {
		this.bestOffers = bestOffers;
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
}
