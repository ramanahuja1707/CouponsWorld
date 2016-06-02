package com.couponsworld.services;

import java.util.ArrayList;
import java.util.List;

import com.couponsworld.dto.Link;
import com.couponsworld.dto.Offer;
import com.couponsworld.dto.ResultantOffer;
import com.couponsworld.enums.Status;
import com.couponsworld.exceptions.OfferException;

public class OfferService {
	public static List<Offer> offers = null;
	public static List<com.couponsworld.dto.Error> errors = null;
	public static List<Link> links = null;
	public static ResultantOffer resultantOffer;

	public static ResultantOffer createOffer(Offer offer) {
		try {
			Object returnedObject = DatabaseService.createOfferInDatabase(offer);
			if (returnedObject instanceof Offer) {
				// offer successfully created and returned....
				// Creating ResultantOffer object
				offers = new ArrayList<Offer>();
				offers.add((Offer) returnedObject);
				resultantOffer = new ResultantOffer();
				resultantOffer.setOffers(offers);
				resultantOffer.setErrors(errors);
				resultantOffer.setStatus(Status.SUCCESS);
				resultantOffer.setLinks(links);
			} else {
				resultantOffer = new ResultantOffer();
				resultantOffer.setOffers(new ArrayList<Offer>());
				resultantOffer.setErrors(new ArrayList<com.couponsworld.dto.Error>());
				resultantOffer.setStatus(Status.FAILURE);
				resultantOffer.setLinks(new ArrayList<Link>());
			}
		} catch (Exception e) {
			resultantOffer = new ResultantOffer();
			resultantOffer.setOffers(new ArrayList<Offer>());
			resultantOffer.setErrors(new ArrayList<com.couponsworld.dto.Error>());
			resultantOffer.setStatus(Status.FAILURE);
			resultantOffer.setLinks(new ArrayList<Link>());
		}
		return resultantOffer;
	}

	public static ResultantOffer updateOffer(long offerId, Offer offer) {
		try {
			Object returnedObject = DatabaseService.updateOfferInDatabase(offerId, offer);
			if (returnedObject instanceof Offer) {
				// offer successfully created and returned....
				// Creating ResultantOffer object
				offers = new ArrayList<Offer>();
				offers.add((Offer) returnedObject);
				resultantOffer = new ResultantOffer();
				resultantOffer.setOffers(offers);
				resultantOffer.setErrors(errors);
				resultantOffer.setStatus(Status.SUCCESS);
				resultantOffer.setLinks(links);
			} else if (returnedObject instanceof OfferException) {
				resultantOffer = new ResultantOffer();
				resultantOffer.setOffers(new ArrayList<Offer>());
				resultantOffer.setErrors(new ArrayList<com.couponsworld.dto.Error>());
				resultantOffer.setStatus(Status.FAILURE);
				resultantOffer.setLinks(new ArrayList<Link>());
			}
		} catch (Exception e) {
			resultantOffer = new ResultantOffer();
			resultantOffer.setOffers(new ArrayList<Offer>());
			resultantOffer.setErrors(new ArrayList<com.couponsworld.dto.Error>());
			resultantOffer.setStatus(Status.FAILURE);
			resultantOffer.setLinks(new ArrayList<Link>());
		}
		return resultantOffer;
	}

}
