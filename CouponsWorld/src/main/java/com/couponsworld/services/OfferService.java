package com.couponsworld.services;

import java.util.ArrayList;
import java.util.List;

import com.couponsworld.apiresults.ResultantOffer;
import com.couponsworld.database.DatabaseService;
import com.couponsworld.dto.Offer;
import com.couponsworld.enums.Errors;
import com.couponsworld.enums.Status;
import com.couponsworld.exceptions.OfferException;
import com.couponsworld.utilities.GenerateLinkService;

public class OfferService {
	private static List<Offer> offers = null;
	private static List<com.couponsworld.apiresults.Error> errors = null;
	private static ResultantOffer resultantOffer;

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
				resultantOffer.setLinks(GenerateLinkService.mapOfLinks.get("createOffer"));
			} else {
				// Creating Error for updating Offer
				com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
				error.setErrorCode(Errors.OFFER_ERROR.getErrorCode());
				error.setErrorName(((OfferException) returnedObject).getMessage());

				// wrapping the offer into offer list
				offers = new ArrayList<Offer>();
				offers.add(offer);

				// wrapping the error into errors list
				errors = new ArrayList<com.couponsworld.apiresults.Error>();
				errors.add(error);

				// offer does not created and error returned....
				// Creating ResultantOffer object
				resultantOffer = new ResultantOffer();
				resultantOffer.setOffers(offers);
				resultantOffer.setErrors(errors);
				resultantOffer.setStatus(Status.FAILURE);
				resultantOffer.setLinks(GenerateLinkService.mapOfLinks.get("createOffer"));
			}
		} catch (Exception exception) {
			// Creating Error for updating Offer
			com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
			error.setErrorCode(Errors.GENERAL_ERROR.getErrorCode());
			error.setErrorName(exception.getMessage());

			// wrapping the offer into offer list
			offers = new ArrayList<Offer>();
			offers.add(offer);

			// wrapping the error into errors list
			errors = new ArrayList<com.couponsworld.apiresults.Error>();
			errors.add(error);

			// offer does not created and error returned....
			// Creating ResultantOffer object
			resultantOffer = new ResultantOffer();
			resultantOffer.setOffers(offers);
			resultantOffer.setErrors(errors);
			resultantOffer.setStatus(Status.FAILURE);
			resultantOffer.setLinks(GenerateLinkService.mapOfLinks.get("createOffer"));
		}
		offers = null;
		errors = null;
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
				resultantOffer.setLinks(GenerateLinkService.mapOfLinks.get("updateOffer"));
			} else if (returnedObject instanceof OfferException) {
				// Creating Error for updating Offer
				com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
				error.setErrorCode(Errors.OFFER_ERROR.getErrorCode());
				error.setErrorName(((OfferException) returnedObject).getMessage());

				// wrapping the error into errors list
				errors = new ArrayList<com.couponsworld.apiresults.Error>();
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
				resultantOffer.setLinks(GenerateLinkService.mapOfLinks.get("updateOffer"));
			} else {
				// Creating Error for updating Offer
				com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
				error.setErrorCode(Errors.GENERAL_ERROR.getErrorCode());
				error.setErrorName(((Exception) returnedObject).getMessage());

				// wrapping the error into errors list
				errors = new ArrayList<com.couponsworld.apiresults.Error>();
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
				resultantOffer.setLinks(GenerateLinkService.mapOfLinks.get("updateOffer"));
			}
		} catch (Exception e) {
			// Creating Error for updating Offer
			com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
			error.setErrorCode(Errors.GENERAL_ERROR.getErrorCode());
			error.setErrorName(e.getMessage());

			// wrapping the offer into offer list
			offers = new ArrayList<Offer>();
			offers.add(offer);

			// wrapping the error into errors list
			errors = new ArrayList<com.couponsworld.apiresults.Error>();
			errors.add(error);

			resultantOffer = new ResultantOffer();
			resultantOffer.setOffers(offers);
			resultantOffer.setErrors(errors);
			resultantOffer.setStatus(Status.FAILURE);
			resultantOffer.setLinks(GenerateLinkService.mapOfLinks.get("updateOffer"));
			offers = null;
			errors = null;
		}
		offers = null;
		errors = null;
		return resultantOffer;
	}

	public static ResultantOffer getOffers() {
		try {
			Object returnedObject = DatabaseService.getOffersFromDatabase();
			if (returnedObject instanceof Exception) {
				// Creating Error for updating Offer
				com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
				error.setErrorCode(Errors.GENERAL_ERROR.getErrorCode());
				error.setErrorName(((Exception) returnedObject).getMessage());

				// wrapping the error into errors list
				errors = new ArrayList<com.couponsworld.apiresults.Error>();
				errors.add(error);

				// offer does not created and error returned....
				// Creating ResultantOffer object
				resultantOffer = new ResultantOffer();
				resultantOffer.setOffers(offers);
				resultantOffer.setErrors(errors);
				resultantOffer.setStatus(Status.FAILURE);
				resultantOffer.setLinks(GenerateLinkService.mapOfLinks.get("getOffers"));
			} else if (returnedObject instanceof OfferException) {

				// Creating Error for updating Offer
				com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
				error.setErrorCode(Errors.OFFER_ERROR.getErrorCode());
				error.setErrorName(((OfferException) returnedObject).getMessage());

				// wrapping the error into errors list
				errors = new ArrayList<com.couponsworld.apiresults.Error>();
				errors.add(error);

				// offer does not created and error returned....
				// Creating ResultantOffer object
				resultantOffer = new ResultantOffer();
				resultantOffer.setOffers(offers);
				resultantOffer.setErrors(errors);
				resultantOffer.setStatus(Status.FAILURE);
				resultantOffer.setLinks(GenerateLinkService.mapOfLinks.get("getOffers"));
			} else {

				if (((List<Offer>) returnedObject).size() > 0) {

					resultantOffer = new ResultantOffer();
					resultantOffer.setOffers((List<Offer>) returnedObject);
					resultantOffer.setErrors(errors);
					resultantOffer.setStatus(Status.SUCCESS);
					resultantOffer.setLinks(GenerateLinkService.mapOfLinks.get("getOffers"));
				} else {
					resultantOffer = new ResultantOffer();
					resultantOffer.setOffers(offers);
					resultantOffer.setErrors(errors);
					resultantOffer.setStatus(Status.SUCCESS);
					resultantOffer.setLinks(GenerateLinkService.mapOfLinks.get("getOffers"));
				}
			}
		} catch (Exception e) {
			// Creating Error for updating Offer
			com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
			error.setErrorCode(Errors.GENERAL_ERROR.getErrorCode());
			error.setErrorName(e.getMessage());

			// wrapping the error into errors list
			errors = new ArrayList<com.couponsworld.apiresults.Error>();
			errors.add(error);

			resultantOffer = new ResultantOffer();
			resultantOffer.setOffers(offers);
			resultantOffer.setErrors(errors);
			resultantOffer.setStatus(Status.FAILURE);
			resultantOffer.setLinks(GenerateLinkService.mapOfLinks.get("getOffers"));
			offers = null;
			errors = null;
		}
		offers = null;
		errors = null;
		return resultantOffer;
	}

	public static ResultantOffer getOffers(String company, String category, String subCategory, String userType,
			String userPlatform, String usabilityStatus, String cashBackMode, String offerType) {
		try {
			Object returnedObject = DatabaseService.getOffersFromDatabase(company, category, subCategory, userType,
					userPlatform, usabilityStatus, cashBackMode, offerType);
			if (returnedObject instanceof Exception) {
				// Creating Error for updating Offer
				com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
				error.setErrorCode(Errors.GENERAL_ERROR.getErrorCode());
				error.setErrorName(((Exception) returnedObject).getMessage());

				// wrapping the error into errors list
				errors = new ArrayList<com.couponsworld.apiresults.Error>();
				errors.add(error);

				// offer does not created and error returned....
				// Creating ResultantOffer object
				resultantOffer = new ResultantOffer();
				resultantOffer.setOffers(offers);
				resultantOffer.setErrors(errors);
				resultantOffer.setStatus(Status.FAILURE);
				resultantOffer.setLinks(GenerateLinkService.mapOfLinks.get("getOffers"));
			} else if (returnedObject instanceof OfferException) {

				// Creating Error for updating Offer
				com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
				error.setErrorCode(Errors.OFFER_ERROR.getErrorCode());
				error.setErrorName(((OfferException) returnedObject).getMessage());

				// wrapping the error into errors list
				errors = new ArrayList<com.couponsworld.apiresults.Error>();
				errors.add(error);

				// offer does not created and error returned....
				// Creating ResultantOffer object
				resultantOffer = new ResultantOffer();
				resultantOffer.setOffers(offers);
				resultantOffer.setErrors(errors);
				resultantOffer.setStatus(Status.FAILURE);
				resultantOffer.setLinks(GenerateLinkService.mapOfLinks.get("getOffers"));
			} else {

				if (((List<Offer>) returnedObject).size() > 0) {

					resultantOffer = new ResultantOffer();
					resultantOffer.setOffers((List<Offer>) returnedObject);
					resultantOffer.setErrors(errors);
					resultantOffer.setStatus(Status.SUCCESS);
					resultantOffer.setLinks(GenerateLinkService.mapOfLinks.get("getOffers"));
				} else {
					resultantOffer = new ResultantOffer();
					resultantOffer.setOffers(offers);
					resultantOffer.setErrors(errors);
					resultantOffer.setStatus(Status.SUCCESS);
					resultantOffer.setLinks(GenerateLinkService.mapOfLinks.get("getOffers"));
				}
			}
		} catch (Exception e) {
			// Creating Error for updating Offer
			com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
			error.setErrorCode(Errors.GENERAL_ERROR.getErrorCode());
			error.setErrorName(e.getMessage());

			// wrapping the error into errors list
			errors = new ArrayList<com.couponsworld.apiresults.Error>();
			errors.add(error);

			resultantOffer = new ResultantOffer();
			resultantOffer.setOffers(offers);
			resultantOffer.setErrors(errors);
			resultantOffer.setStatus(Status.FAILURE);
			resultantOffer.setLinks(GenerateLinkService.mapOfLinks.get("getOffers"));
			offers = null;
			errors = null;
		}
		offers = null;
		errors = null;
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
				resultantOffer.setLinks(GenerateLinkService.mapOfLinks.get("deleteOffer"));
			} else if (returnedObject instanceof OfferException) {
				// Creating Error for updating Offer
				com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
				error.setErrorCode(Errors.OFFER_ERROR.getErrorCode());
				error.setErrorName(((OfferException) returnedObject).getMessage());

				// wrapping the error into errors list
				errors = new ArrayList<com.couponsworld.apiresults.Error>();
				errors.add(error);

				// offer does not created and error returned....
				// Creating ResultantOffer object
				resultantOffer = new ResultantOffer();
				resultantOffer.setOffers(offers);
				resultantOffer.setErrors(errors);
				resultantOffer.setStatus(Status.FAILURE);
				resultantOffer.setLinks(GenerateLinkService.mapOfLinks.get("deleteOffer"));
			} else {
				// Creating Error for updating Offer
				com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
				error.setErrorCode(Errors.GENERAL_ERROR.getErrorCode());
				error.setErrorName(((Exception) returnedObject).getMessage());

				// wrapping the error into errors list
				errors = new ArrayList<com.couponsworld.apiresults.Error>();
				errors.add(error);

				// offer does not created and error returned....
				// Creating ResultantOffer object
				resultantOffer = new ResultantOffer();
				resultantOffer.setOffers(offers);
				resultantOffer.setErrors(errors);
				resultantOffer.setStatus(Status.FAILURE);
				resultantOffer.setLinks(GenerateLinkService.mapOfLinks.get("deleteOffer"));
			}
		} catch (Exception e) {
			// Creating Error for updating Offer
			com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
			error.setErrorCode(Errors.GENERAL_ERROR.getErrorCode());
			error.setErrorName(e.getMessage());

			// wrapping the error into errors list
			errors = new ArrayList<com.couponsworld.apiresults.Error>();
			errors.add(error);

			resultantOffer = new ResultantOffer();
			resultantOffer.setOffers(offers);
			resultantOffer.setErrors(errors);
			resultantOffer.setStatus(Status.FAILURE);
			resultantOffer.setLinks(GenerateLinkService.mapOfLinks.get("deleteOffer"));
			offers = null;
			errors = null;
		}
		offers = null;
		errors = null;
		return resultantOffer;
	}

}
