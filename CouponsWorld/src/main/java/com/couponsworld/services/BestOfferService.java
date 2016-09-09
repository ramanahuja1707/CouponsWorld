package com.couponsworld.services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

import com.couponsworld.apiresults.ResultantBestOffer;
import com.couponsworld.database.DatabaseService;
import com.couponsworld.dto.Offer;
import com.couponsworld.enums.Errors;
import com.couponsworld.enums.Status;
import com.couponsworld.exceptions.BestOfferException;
import com.couponsworld.exceptions.OfferException;

public class BestOfferService {

	// declaration of logger
	private static final Logger log = Logger.getLogger(BestOfferService.class.getName());

	public static List<Offer> bestOffers = null;
	public static List<com.couponsworld.apiresults.Error> errors = null;
	public static ResultantBestOffer resultantBestOffer;

	public static ResultantBestOffer getBestOffersForCashback(String company, String category, String subCategory,
			String userType, String userPlatform, String usabilityStatus, long amountToSpend) {
		try {
			log.info("=========================== Best Offer Service - Start =================================");
			Object returnedObject = DatabaseService.getBestOffersForCashbackFromDatabase(company, category, subCategory,
					userType, userPlatform, usabilityStatus, amountToSpend);
			if (returnedObject instanceof Exception) {

				// Creating Error for updating Offer
				com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
				error.setErrorCode(Errors.GENERAL_ERROR.getErrorCode());
				error.setErrorName(((Exception) returnedObject).getMessage());
				log.info("Error Occured while fetching all the best Offers by filtering..:" + error.getErrorCode() + ":"
						+ error.getErrorName());
				// wrapping the error into errors list
				errors = new ArrayList<com.couponsworld.apiresults.Error>();
				errors.add(error);

				// offer does not created and error returned....
				// Creating resultantBestOffer object
				resultantBestOffer = new ResultantBestOffer();
				resultantBestOffer.setMapOfBestOffer(null);
				resultantBestOffer.setBestOffers(bestOffers);
				resultantBestOffer.setErrors(errors);
				resultantBestOffer.setStatus(Status.FAILURE);
				// resultantBestOffer.setLinks(GenerateLinkService.mapOfLinks.get("getOffers"));
				log.info("ResultantOffer object returned successfully..");
				log.info("=========================== Best Offer Service - End =================================");
			} else if (returnedObject instanceof BestOfferException) {

				// Creating Error for getting best Offer
				com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
				error.setErrorCode(Errors.OFFER_ERROR.getErrorCode());
				error.setErrorName(((OfferException) returnedObject).getMessage());
				log.info("Error Occured while fetching all the best Offers by filtering..:" + error.getErrorCode() + ":"
						+ error.getErrorName());
				// wrapping the error into errors list
				errors = new ArrayList<com.couponsworld.apiresults.Error>();
				errors.add(error);

				// offer does not created and error returned....
				// Creating ResultantOffer object
				resultantBestOffer = new ResultantBestOffer();
				resultantBestOffer.setMapOfBestOffer(null);
				resultantBestOffer.setBestOffers(bestOffers);
				resultantBestOffer.setErrors(errors);
				resultantBestOffer.setStatus(Status.FAILURE);
				// resultantBestOffer.setLinks(GenerateLinkService.mapOfLinks.get("getOffers"));
				log.info("resultantBestOffer object returned successfully..");
				log.info("=========================== Best Offer Service - End =================================");
			} else {
				if (((List<Offer>) returnedObject).size() > 0) {
					if (amountToSpend != 0L) {
						log.info("Best Offers wrapping into ResultantOffer after filtering by amount passed.......");
						resultantBestOffer = new ResultantBestOffer();
						resultantBestOffer.setBestOffers((List<Offer>) returnedObject);
						resultantBestOffer.setMapOfBestOffer(DatabaseService
								.filterBestOffersForCashbackByAmount((List<Offer>) returnedObject, amountToSpend));
						resultantBestOffer.setErrors(errors);
						resultantBestOffer.setStatus(Status.SUCCESS);
					} else {
						log.info(
								"BestOffer=(List<Offer>) returnedObject wrapping into ResultantOffer....as amountToSpend is not mentioned in input");
						resultantBestOffer = new ResultantBestOffer();
						resultantBestOffer.setMapOfBestOffer(DatabaseService
								.filterBestOffersForCashbackByAmount((List<Offer>) returnedObject, amountToSpend));
						resultantBestOffer.setBestOffers((List<Offer>) returnedObject);
						resultantBestOffer.setErrors(errors);
						resultantBestOffer.setStatus(Status.SUCCESS);
						// resultantBestOffer.setLinks(GenerateLinkService.mapOfLinks.get("getOffers"));
					}

					// resultantBestOffer.setLinks(GenerateLinkService.mapOfLinks.get("getOffers"));
				} else {
					log.info("BestOffer=null wrapping into ResultantOffer....");
					resultantBestOffer = new ResultantBestOffer();
					resultantBestOffer.setMapOfBestOffer(null);
					resultantBestOffer.setBestOffers(bestOffers);
					resultantBestOffer.setErrors(errors);
					resultantBestOffer.setStatus(Status.SUCCESS);
					// resultantBestOffer.setLinks(GenerateLinkService.mapOfLinks.get("getOffers"));
				}
				log.info("ResultantOffer object returned successfully..");
				log.info("=========================== Best Offer Service - End =================================");
			}
		} catch (Exception e) {
			// Creating Error for updating Offer
			com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
			error.setErrorCode(Errors.GENERAL_ERROR.getErrorCode());
			error.setErrorName(e.getMessage());
			log.info("Error Occured while fetching all the best Offers by filtering..:" + error.getErrorCode() + ":"
					+ error.getErrorName());
			// wrapping the error into errors list
			errors = new ArrayList<com.couponsworld.apiresults.Error>();
			errors.add(error);

			resultantBestOffer = new ResultantBestOffer();
			resultantBestOffer.setBestOffers(bestOffers);
			resultantBestOffer.setMapOfBestOffer(null);
			resultantBestOffer.setErrors(errors);
			resultantBestOffer.setStatus(Status.FAILURE);
			// resultantBestOffer.setLinks(GenerateLinkService.mapOfLinks.get("getOffers"));
			log.info("ResultantBestOffer object returned successfully..");
			log.info("=========================== Best Offer Service - End =================================");
			bestOffers = null;
			errors = null;
		}
		bestOffers = null;
		errors = null;
		return resultantBestOffer;
	}
}
