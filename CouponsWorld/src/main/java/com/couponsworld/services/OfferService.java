package com.couponsworld.services;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.couponsworld.apiresults.ResultantOffer;
import com.couponsworld.database.DatabaseService;
import com.couponsworld.dto.Offer;
import com.couponsworld.enums.Errors;
import com.couponsworld.enums.Status;
import com.couponsworld.exceptions.OfferException;
import com.couponsworld.utilities.GenerateLinkService;

public class OfferService {

	// declaration of logger
	private static final Logger log = Logger.getLogger(OfferService.class.getName());

	private static List<Offer> offers = null;
	private static List<com.couponsworld.apiresults.Error> errors = null;
	private static ResultantOffer resultantOffer;

	public static ResultantOffer createOffer(Offer offer) {
		try {
			log.info("=========================== Offer Service - Start =================================");
			Object returnedObject = DatabaseService.createOfferInDatabase(offer);
			if (returnedObject instanceof Offer) {
				log.info("Offer wrapping into ResultantOffer....");
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
				log.info("ResultantOffer object returned successfully..");
				log.info("=========================== Offer Service - End =================================");
			} else {
				// Creating Error for updating Offer
				com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
				error.setErrorCode(Errors.OFFER_ERROR.getErrorCode());
				error.setErrorName(((OfferException) returnedObject).getMessage());
				log.info("Error Occured while creating the Offer..:" + error.getErrorCode() + ":"
						+ error.getErrorName());
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
				log.info("ResultantOffer object returned successfully..");
				log.info("=========================== Offer Service - End =================================");
			}
		} catch (Exception exception) {
			// Creating Error for updating Offer
			com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
			error.setErrorCode(Errors.GENERAL_ERROR.getErrorCode());
			error.setErrorName(exception.getMessage());
			log.info("Error Occured while creating the Offer..:" + error.getErrorCode() + ":" + error.getErrorName());
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
			log.info("ResultantOffer object returned successfully..");
			log.info("=========================== Offer Service - End =================================");
		}
		offers = null;
		errors = null;
		return resultantOffer;
	}

	public static ResultantOffer updateOffer(long offerId, Offer offer) {
		try {
			log.info("=========================== Offer Service - Start =================================");
			Object returnedObject = DatabaseService.updateOfferInDatabase(offerId, offer);
			if (returnedObject instanceof Offer) {
				log.info("Offer wrapping into ResultantOffer....");
				// offer successfully created and returned....
				// Creating ResultantOffer object
				offers = new ArrayList<Offer>();
				offers.add((Offer) returnedObject);
				resultantOffer = new ResultantOffer();
				resultantOffer.setOffers(offers);
				resultantOffer.setErrors(errors);
				resultantOffer.setStatus(Status.SUCCESS);
				resultantOffer.setLinks(GenerateLinkService.mapOfLinks.get("updateOffer"));
				log.info("ResultantOffer object returned successfully..");
				log.info("=========================== Offer Service - End =================================");
			} else if (returnedObject instanceof OfferException) {
				// Creating Error for updating Offer
				com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
				error.setErrorCode(Errors.OFFER_ERROR.getErrorCode());
				error.setErrorName(((OfferException) returnedObject).getMessage());
				log.info("Error Occured while updating the Offer..:" + error.getErrorCode() + ":"
						+ error.getErrorName());
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
				log.info("ResultantOffer object returned successfully..");
				log.info("=========================== Offer Service - End =================================");
			} else {
				// Creating Error for updating Offer
				com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
				error.setErrorCode(Errors.GENERAL_ERROR.getErrorCode());
				error.setErrorName(((Exception) returnedObject).getMessage());
				log.info("Error Occured while updating the Offer..:" + error.getErrorCode() + ":"
						+ error.getErrorName());
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
				log.info("ResultantOffer object returned successfully..");
				log.info("=========================== Offer Service - End =================================");
			}
		} catch (Exception e) {
			// Creating Error for updating Offer
			com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
			error.setErrorCode(Errors.GENERAL_ERROR.getErrorCode());
			error.setErrorName(e.getMessage());
			log.info("Error Occured while updating the Offer..:" + error.getErrorCode() + ":" + error.getErrorName());
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
			log.info("ResultantOffer object returned successfully..");
			log.info("=========================== Offer Service - End =================================");
			offers = null;
			errors = null;
		}
		offers = null;
		errors = null;
		return resultantOffer;
	}

	public static ResultantOffer getOffers() {
		try {
			log.info("=========================== Offer Service - Start =================================");
			Object returnedObject = DatabaseService.getOffersFromDatabase();
			if (returnedObject instanceof Exception) {
				// Creating Error for updating Offer
				com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
				error.setErrorCode(Errors.GENERAL_ERROR.getErrorCode());
				error.setErrorName(((Exception) returnedObject).getMessage());
				log.info("Error Occured while fetching all the Offers..:" + error.getErrorCode() + ":"
						+ error.getErrorName());
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
				log.info("ResultantOffer object returned successfully..");
				log.info("=========================== Offer Service - End =================================");
			} else if (returnedObject instanceof OfferException) {

				// Creating Error for updating Offer
				com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
				error.setErrorCode(Errors.OFFER_ERROR.getErrorCode());
				error.setErrorName(((OfferException) returnedObject).getMessage());
				log.info("Error Occured while fetching all the Offers..:" + error.getErrorCode() + ":"
						+ error.getErrorName());
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
				log.info("ResultantOffer object returned successfully..");
				log.info("=========================== Offer Service - End =================================");
			} else {
				// log.info("Offer wrapping into ResultantOffer....");
				if (((List<Offer>) returnedObject).size() > 0) {
					log.info("Offer wrapping into ResultantOffer....");
					resultantOffer = new ResultantOffer();
					resultantOffer.setOffers((List<Offer>) returnedObject);
					resultantOffer.setErrors(errors);
					resultantOffer.setStatus(Status.SUCCESS);
					resultantOffer.setLinks(GenerateLinkService.mapOfLinks.get("getOffers"));
				} else {
					log.info("Offer=null wrapping into ResultantOffer....");
					resultantOffer = new ResultantOffer();
					resultantOffer.setOffers(offers);
					resultantOffer.setErrors(errors);
					resultantOffer.setStatus(Status.SUCCESS);
					resultantOffer.setLinks(GenerateLinkService.mapOfLinks.get("getOffers"));
				}
				log.info("ResultantOffer object returned successfully..");
				log.info("=========================== Offer Service - End =================================");
			}
		} catch (Exception e) {
			// Creating Error for updating Offer
			com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
			error.setErrorCode(Errors.GENERAL_ERROR.getErrorCode());
			error.setErrorName(e.getMessage());
			log.info("Error Occured while fetching all the Offers..:" + error.getErrorCode() + ":"
					+ error.getErrorName());
			// wrapping the error into errors list
			errors = new ArrayList<com.couponsworld.apiresults.Error>();
			errors.add(error);

			resultantOffer = new ResultantOffer();
			resultantOffer.setOffers(offers);
			resultantOffer.setErrors(errors);
			resultantOffer.setStatus(Status.FAILURE);
			resultantOffer.setLinks(GenerateLinkService.mapOfLinks.get("getOffers"));
			log.info("ResultantOffer object returned successfully..");
			log.info("=========================== Offer Service - End =================================");
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
			log.info("=========================== Offer Service - Start =================================");
			Object returnedObject = DatabaseService.getOffersFromDatabase(company, category, subCategory, userType,
					userPlatform, usabilityStatus, cashBackMode, offerType);
			if (returnedObject instanceof Exception) {

				// Creating Error for updating Offer
				com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
				error.setErrorCode(Errors.GENERAL_ERROR.getErrorCode());
				error.setErrorName(((Exception) returnedObject).getMessage());
				log.info("Error Occured while fetching all the Offers by filtering..:" + error.getErrorCode() + ":"
						+ error.getErrorName());
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
				log.info("ResultantOffer object returned successfully..");
				log.info("=========================== Offer Service - End =================================");
			} else if (returnedObject instanceof OfferException) {

				// Creating Error for updating Offer
				com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
				error.setErrorCode(Errors.OFFER_ERROR.getErrorCode());
				error.setErrorName(((OfferException) returnedObject).getMessage());
				log.info("Error Occured while fetching all the Offers by filtering..:" + error.getErrorCode() + ":"
						+ error.getErrorName());
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
				log.info("ResultantOffer object returned successfully..");
				log.info("=========================== Offer Service - End =================================");
			} else {

				if (((List<Offer>) returnedObject).size() > 0) {
					log.info("Offer wrapping into ResultantOffer....");
					resultantOffer = new ResultantOffer();
					resultantOffer.setOffers((List<Offer>) returnedObject);
					resultantOffer.setErrors(errors);
					resultantOffer.setStatus(Status.SUCCESS);
					resultantOffer.setLinks(GenerateLinkService.mapOfLinks.get("getOffers"));
				} else {
					log.info("Offer=null wrapping into ResultantOffer....");
					resultantOffer = new ResultantOffer();
					resultantOffer.setOffers(offers);
					resultantOffer.setErrors(errors);
					resultantOffer.setStatus(Status.SUCCESS);
					resultantOffer.setLinks(GenerateLinkService.mapOfLinks.get("getOffers"));
				}
				log.info("ResultantOffer object returned successfully..");
				log.info("=========================== Offer Service - End =================================");
			}
		} catch (Exception e) {
			// Creating Error for updating Offer
			com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
			error.setErrorCode(Errors.GENERAL_ERROR.getErrorCode());
			error.setErrorName(e.getMessage());
			log.info("Error Occured while fetching all the Offers by filtering..:" + error.getErrorCode() + ":"
					+ error.getErrorName());
			// wrapping the error into errors list
			errors = new ArrayList<com.couponsworld.apiresults.Error>();
			errors.add(error);

			resultantOffer = new ResultantOffer();
			resultantOffer.setOffers(offers);
			resultantOffer.setErrors(errors);
			resultantOffer.setStatus(Status.FAILURE);
			resultantOffer.setLinks(GenerateLinkService.mapOfLinks.get("getOffers"));
			log.info("ResultantOffer object returned successfully..");
			log.info("=========================== Offer Service - End =================================");
			offers = null;
			errors = null;
		}
		offers = null;
		errors = null;
		return resultantOffer;
	}

	public static ResultantOffer deleteOffer(long offerId) {
		try {
			log.info("=========================== Offer Service - Start =================================");
			Object returnedObject = DatabaseService.deleteOfferFromDatabase(offerId);
			if (returnedObject instanceof Offer) {
				log.info("Offer wrapping into ResultantOffer....");
				// offer successfully created and returned....
				// Creating ResultantOffer object
				offers = new ArrayList<Offer>();
				offers.add((Offer) returnedObject);

				resultantOffer = new ResultantOffer();
				resultantOffer.setOffers(offers);
				resultantOffer.setErrors(errors);
				resultantOffer.setStatus(Status.SUCCESS);
				resultantOffer.setLinks(GenerateLinkService.mapOfLinks.get("deleteOffer"));
				log.info("ResultantOffer object returned successfully..");
				log.info("=========================== Offer Service - End =================================");
			} else if (returnedObject instanceof OfferException) {
				// Creating Error for updating Offer
				com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
				error.setErrorCode(Errors.OFFER_ERROR.getErrorCode());
				error.setErrorName(((OfferException) returnedObject).getMessage());
				log.info("Error Occured while deleting the Offer..:" + error.getErrorCode() + ":"
						+ error.getErrorName());
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
				log.info("ResultantOffer object returned successfully..");
				log.info("=========================== Offer Service - End =================================");
			} else {
				// Creating Error for updating Offer
				com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
				error.setErrorCode(Errors.GENERAL_ERROR.getErrorCode());
				error.setErrorName(((Exception) returnedObject).getMessage());
				log.info("Error Occured while deleting the Offer..:" + error.getErrorCode() + ":"
						+ error.getErrorName());
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
				log.info("ResultantOffer object returned successfully..");
				log.info("=========================== Offer Service - End =================================");
			}
		} catch (Exception e) {
			// Creating Error for updating Offer
			com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
			error.setErrorCode(Errors.GENERAL_ERROR.getErrorCode());
			error.setErrorName(e.getMessage());
			log.info("Error Occured while deleting the Offer..:" + error.getErrorCode() + ":" + error.getErrorName());
			// wrapping the error into errors list
			errors = new ArrayList<com.couponsworld.apiresults.Error>();
			errors.add(error);

			resultantOffer = new ResultantOffer();
			resultantOffer.setOffers(offers);
			resultantOffer.setErrors(errors);
			resultantOffer.setStatus(Status.FAILURE);
			resultantOffer.setLinks(GenerateLinkService.mapOfLinks.get("deleteOffer"));
			log.info("ResultantOffer object returned successfully..");
			log.info("=========================== Offer Service - End =================================");
			offers = null;
			errors = null;
		}
		offers = null;
		errors = null;
		return resultantOffer;
	}

}
