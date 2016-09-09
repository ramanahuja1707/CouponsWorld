package com.couponsworld.resources;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.couponsworld.apiresults.Error;
import com.couponsworld.apiresults.ResultantBestOffer;
import com.couponsworld.dto.Offer;
import com.couponsworld.enums.Errors;
import com.couponsworld.enums.Status;
import com.couponsworld.services.BestOfferService;
import com.couponsworld.utilities.ValidationService;

@Path("/bestoffers")
public class BestOfferResource {

	// declaration of logger
	private static final Logger log = Logger.getLogger(CategoryResource.class.getName());

	private List<Offer> bestOffers = null;
	private List<com.couponsworld.apiresults.Error> errors = null;
	private ResultantBestOffer resultantBestOffer;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public ResultantBestOffer getBestOffers(@QueryParam("category") String category,
			@QueryParam("company") String company, @QueryParam("subCategory") String subCategory,
			@QueryParam("userType") String userType, @QueryParam("userPlatform") String userPlatform,
			@QueryParam("usabilityStatus") String usabilityStatus, @QueryParam("amountToSpend") long amountToSpend) {
		try {

			log.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ BestOffer Resource - Start @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
			List<Error> errorsReceivedInValidation = ValidationService
					.validateMandatoryParametersForGetMethodOfBestOfferResource(company, category, subCategory,
							userType, userPlatform, usabilityStatus, amountToSpend);
			if (errorsReceivedInValidation == null) {
				return BestOfferService.getBestOffersForCashback(company, category, subCategory, userType, userPlatform,
						usabilityStatus, amountToSpend);
			} else {

				// creating resultantBestOffer Object
				resultantBestOffer = new ResultantBestOffer();

				resultantBestOffer.setErrors(errorsReceivedInValidation);
				resultantBestOffer.setStatus(Status.FAILURE);
				resultantBestOffer.setBestOffers(bestOffers);
				errors = null;
				bestOffers = null;
				return resultantBestOffer;
			}

		} catch (NullPointerException nle) {

			// creating resultantBestOffer Object
			resultantBestOffer = new ResultantBestOffer();

			// creating the error getting
			com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
			error.setErrorCode(Errors.NULL_POINTER_ERROR.getErrorCode());
			error.setErrorName("Provide correct credentials to access api");
			log.info("Error Occured in Category Resource :" + error.getErrorCode() + ":" + error.getErrorName());
			// wrapping the error to a list of errors
			errors = new ArrayList<com.couponsworld.apiresults.Error>();
			errors.add(error);

			resultantBestOffer.setErrors(errors);
			resultantBestOffer.setStatus(Status.FAILURE);
			resultantBestOffer.setBestOffers(bestOffers);
			errors = null;
			bestOffers = null;
			return resultantBestOffer;
		} catch (Exception exception) {
			// creating resultantBestOffer Object
			resultantBestOffer = new ResultantBestOffer();

			// creating the error getting
			com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
			error.setErrorCode(Errors.GENERAL_ERROR.getErrorCode());
			error.setErrorName(exception.getMessage());
			log.info("Error Occured in Best Offer Resource :" + error.getErrorCode() + ":" + error.getErrorName());
			// wrapping the error to a list of errors
			errors = new ArrayList<com.couponsworld.apiresults.Error>();
			errors.add(error);

			resultantBestOffer.setErrors(errors);
			resultantBestOffer.setStatus(Status.FAILURE);
			resultantBestOffer.setBestOffers(bestOffers);
			errors = null;
			bestOffers = null;
			return resultantBestOffer;
		} finally {
			log.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ BestOffer Resource - End @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		}

	}

}
