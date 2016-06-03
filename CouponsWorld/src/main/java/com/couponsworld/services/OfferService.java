package com.couponsworld.services;

import java.util.ArrayList;
import java.util.List;

import com.couponsworld.dto.Error;
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
				// wrapping the offer into offer list
				offers = new ArrayList<Offer>();
				offers.add((Offer) returnedObject);

				// Creating ResultantOffer object
				resultantOffer = new ResultantOffer();

				resultantOffer.setOffers(offers);
				resultantOffer.setErrors(errors);
				resultantOffer.setStatus(Status.SUCCESS);
				resultantOffer.setLinks(links);
			} else {
				// Creating Error for updating Offer
				com.couponsworld.dto.Error error = new Error();
				error.setErrorCode(101);
				error.setErrorName(((OfferException) returnedObject).getMessage());

				// wrapping the offer into offer list
				offers = new ArrayList<Offer>();
				offers.add(offer);

				// wrapping the error into errors list
				errors = new ArrayList<Error>();
				errors.add(error);

				// offer does not created and error returned....
				// Creating ResultantOffer object
				resultantOffer = new ResultantOffer();
				resultantOffer.setOffers(offers);
				resultantOffer.setErrors(errors);
				resultantOffer.setStatus(Status.FAILURE);
				resultantOffer.setLinks(null);
			}
		} catch (Exception exception) {
			// Creating Error for updating Offer
			com.couponsworld.dto.Error error = new Error();
			error.setErrorCode(101);
			error.setErrorName(exception.getMessage());

			// wrapping the offer into offer list
			offers = new ArrayList<Offer>();
			offers.add(offer);

			// wrapping the error into errors list
			errors = new ArrayList<Error>();
			errors.add(error);

			// offer does not created and error returned....
			// Creating ResultantOffer object
			resultantOffer = new ResultantOffer();
			resultantOffer.setOffers(offers);
			resultantOffer.setErrors(errors);
			resultantOffer.setStatus(Status.FAILURE);
			resultantOffer.setLinks(null);
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
				// Creating Error for updating Offer
				com.couponsworld.dto.Error error = new Error();
				error.setErrorCode(101);
				error.setErrorName(((OfferException) returnedObject).getMessage());

				// wrapping the error into errors list
				errors = new ArrayList<Error>();
				errors.add(error);

				// wrapping the offer into offer list
				offers = new ArrayList<Offer>();
				offers.add(offer);

				// offer does not created and error returned....
				// Creating ResultantOffer object
				resultantOffer = new ResultantOffer();
				resultantOffer.setOffers(offers);
				resultantOffer.setErrors(errors);
				resultantOffer.setStatus(Status.FAILURE);
				resultantOffer.setLinks(links);
			} else {
				// Creating Error for updating Offer
				com.couponsworld.dto.Error error = new Error();
				error.setErrorCode(101);
				error.setErrorName(((Exception) returnedObject).getMessage());

				// wrapping the error into errors list
				errors = new ArrayList<Error>();
				errors.add(error);

				// wrapping the offer into offer list
				offers = new ArrayList<Offer>();
				offers.add(offer);

				// offer does not created and error returned....
				// Creating ResultantOffer object
				resultantOffer = new ResultantOffer();
				resultantOffer.setOffers(offers);
				resultantOffer.setErrors(errors);
				resultantOffer.setStatus(Status.FAILURE);
				resultantOffer.setLinks(links);
			}
		} catch (Exception e) {
			// Creating Error for updating Offer
			com.couponsworld.dto.Error error = new Error();
			error.setErrorCode(101);
			error.setErrorName(e.getMessage());

			// wrapping the offer into offer list
			offers = new ArrayList<Offer>();
			offers.add(offer);

			// wrapping the error into errors list
			errors = new ArrayList<Error>();
			errors.add(error);

			resultantOffer = new ResultantOffer();
			resultantOffer.setOffers(offers);
			resultantOffer.setErrors(errors);
			resultantOffer.setStatus(Status.FAILURE);
			resultantOffer.setLinks(links);
		}
		return resultantOffer;
	}

	public static ResultantOffer getOffers() {
		try {
			Object returnedObject = DatabaseService.getOffersFromDatabase();
			if (returnedObject instanceof Exception) {
				// Creating Error for updating Offer
				com.couponsworld.dto.Error error = new Error();
				error.setErrorCode(101);
				error.setErrorName(((Exception) returnedObject).getMessage());

				// wrapping the error into errors list
				errors = new ArrayList<Error>();
				errors.add(error);

				// offer does not created and error returned....
				// Creating ResultantOffer object
				resultantOffer = new ResultantOffer();
				resultantOffer.setOffers(offers);
				resultantOffer.setErrors(errors);
				resultantOffer.setStatus(Status.FAILURE);
				resultantOffer.setLinks(links);
			} else {

				if (((List<Offer>) returnedObject).size() > 0) {

					resultantOffer = new ResultantOffer();
					resultantOffer.setOffers((List<Offer>) returnedObject);
					resultantOffer.setErrors(errors);
					resultantOffer.setStatus(Status.SUCCESS);
					resultantOffer.setLinks(links);
				} else {
					resultantOffer = new ResultantOffer();
					resultantOffer.setOffers(offers);
					resultantOffer.setErrors(errors);
					resultantOffer.setStatus(Status.SUCCESS);
					resultantOffer.setLinks(links);
				}
			}
		} catch (Exception e) {
			// Creating Error for updating Offer
			com.couponsworld.dto.Error error = new Error();
			error.setErrorCode(101);
			error.setErrorName(e.getMessage());

			// wrapping the error into errors list
			errors = new ArrayList<Error>();
			errors.add(error);

			resultantOffer = new ResultantOffer();
			resultantOffer.setOffers(offers);
			resultantOffer.setErrors(errors);
			resultantOffer.setStatus(Status.FAILURE);
			resultantOffer.setLinks(links);
		}
		return resultantOffer;
	}

	public static ResultantOffer deleteOffer(long offerId) {
		try {
			Object returnedObject = DatabaseService.deleteOfferFromDatabase(offerId);
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
				// Creating Error for updating Offer
				com.couponsworld.dto.Error error = new Error();
				error.setErrorCode(101);
				error.setErrorName(((OfferException) returnedObject).getMessage());

				// wrapping the error into errors list
				errors = new ArrayList<Error>();
				errors.add(error);

				// wrapping the offer into offer list
				offers = new ArrayList<Offer>();
				offers.add((Offer) returnedObject);

				// offer does not created and error returned....
				// Creating ResultantOffer object
				resultantOffer = new ResultantOffer();
				resultantOffer.setOffers(offers);
				resultantOffer.setErrors(errors);
				resultantOffer.setStatus(Status.FAILURE);
				resultantOffer.setLinks(links);
			} else {
				// Creating Error for updating Offer
				com.couponsworld.dto.Error error = new Error();
				error.setErrorCode(101);
				error.setErrorName(((Exception) returnedObject).getMessage());

				// wrapping the error into errors list
				errors = new ArrayList<Error>();
				errors.add(error);

				// wrapping the offer into offer list
				offers = new ArrayList<Offer>();
				offers.add((Offer) returnedObject);

				// offer does not created and error returned....
				// Creating ResultantOffer object
				resultantOffer = new ResultantOffer();
				resultantOffer.setOffers(offers);
				resultantOffer.setErrors(errors);
				resultantOffer.setStatus(Status.FAILURE);
				resultantOffer.setLinks(links);
			}
		} catch (Exception e) {
			// Creating Error for updating Offer
			com.couponsworld.dto.Error error = new Error();
			error.setErrorCode(101);
			error.setErrorName(e.getMessage());

			// wrapping the error into errors list
			errors = new ArrayList<Error>();
			errors.add(error);

			resultantOffer = new ResultantOffer();
			resultantOffer.setOffers(offers);
			resultantOffer.setErrors(errors);
			resultantOffer.setStatus(Status.FAILURE);
			resultantOffer.setLinks(links);
		}
		return resultantOffer;
	}

}
