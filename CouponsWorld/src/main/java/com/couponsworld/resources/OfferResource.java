package com.couponsworld.resources;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.couponsworld.apiresults.ResultantOffer;
import com.couponsworld.dto.Offer;
import com.couponsworld.enums.Errors;
import com.couponsworld.enums.Status;
import com.couponsworld.services.GenerateLinkService;
import com.couponsworld.services.OfferService;

@Path("/offers")
public class OfferResource {
	private List<Offer> offers = null;
	private List<com.couponsworld.apiresults.Error> errors = null;
	private ResultantOffer resultantOffer;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public ResultantOffer getOffers() {
		try {
			return OfferService.getOffers();
		} catch (NullPointerException npe) {
			// creating resultantOffer Object
			resultantOffer = new ResultantOffer();

			// creating the error getting
			com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
			error.setErrorCode(Errors.NULL_POINTER_ERROR.getErrorCode());
			error.setErrorName("Provide correct credentials to access api");

			// wrapping the error to a list of errors
			errors = new ArrayList<com.couponsworld.apiresults.Error>();
			errors.add(error);

			resultantOffer.setErrors(errors);
			resultantOffer.setLinks(GenerateLinkService.generateOfferLink("getOffers"));
			resultantOffer.setStatus(Status.FAILURE);
			resultantOffer.setOffers(offers);
			errors = null;
			return resultantOffer;
		} catch (Exception exception) {

			// creating resultantOffer Object
			resultantOffer = new ResultantOffer();

			// creating the error getting
			com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
			error.setErrorCode(Errors.GENERAL_ERROR.getErrorCode());
			error.setErrorName(exception.getMessage());

			// wrapping the error to a list of errors
			errors = new ArrayList<com.couponsworld.apiresults.Error>();
			errors.add(error);

			resultantOffer.setErrors(errors);
			resultantOffer.setLinks(GenerateLinkService.generateOfferLink("getOffers"));
			resultantOffer.setStatus(Status.FAILURE);
			resultantOffer.setOffers(offers);
			errors = null;
			return resultantOffer;
		}

	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ResultantOffer createOffer(Offer offer) {
		try {
			return OfferService.createOffer(offer);
		} catch (NullPointerException npe) {
			// creating resultantOffer Object
			resultantOffer = new ResultantOffer();

			// creating the error getting
			com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
			error.setErrorCode(Errors.NULL_POINTER_ERROR.getErrorCode());
			error.setErrorName("Provide correct credentials to access api");

			// wrapping the error to a list of errors
			errors = new ArrayList<com.couponsworld.apiresults.Error>();
			errors.add(error);

			resultantOffer.setErrors(errors);
			resultantOffer.setLinks(GenerateLinkService.generateOfferLink("createOffer"));
			resultantOffer.setStatus(Status.FAILURE);
			resultantOffer.setOffers(offers);

			errors = null;
			return resultantOffer;
		} catch (Exception exception) {

			// creating Offer List to wrap the input Offer Object into it
			offers = new ArrayList<Offer>();
			offers.add(offer);

			// creating resultantOffer Object
			resultantOffer = new ResultantOffer();

			// creating the error getting
			com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
			error.setErrorCode(Errors.GENERAL_ERROR.getErrorCode());
			error.setErrorName(exception.getMessage().toString());

			// wrapping the error to a list of errors
			errors = new ArrayList<com.couponsworld.apiresults.Error>();
			errors.add(error);

			resultantOffer.setErrors(errors);
			resultantOffer.setLinks(GenerateLinkService.generateOfferLink("createOffer"));
			resultantOffer.setStatus(Status.FAILURE);
			resultantOffer.setOffers(offers);
			errors = null;
			return resultantOffer;
		}
	}

	@PUT
	@Path("/{offerId}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ResultantOffer updateOffer(@PathParam("offerId") long offerId, Offer offer) {
		try {
			return OfferService.updateOffer(offerId, offer);
		} catch (NullPointerException npe) {
			// creating resultantOffer Object
			resultantOffer = new ResultantOffer();

			// creating the error getting
			com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
			error.setErrorCode(Errors.NULL_POINTER_ERROR.getErrorCode());
			error.setErrorName("Provide correct credentials to access api");

			// wrapping the error to a list of errors
			errors = new ArrayList<com.couponsworld.apiresults.Error>();
			errors.add(error);

			resultantOffer.setErrors(errors);
			resultantOffer.setLinks(GenerateLinkService.generateOfferLink("updateOffer"));
			resultantOffer.setStatus(Status.FAILURE);
			resultantOffer.setOffers(offers);
			offers = null;
			errors = null;
			return resultantOffer;
		} catch (Exception exception) {

			// creating Offer List to wrap the input Offer Object into it
			offers = new ArrayList<Offer>();
			offers.add(offer);

			// creating resultantOffer Object
			resultantOffer = new ResultantOffer();

			// creating the error getting
			com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
			error.setErrorCode(Errors.GENERAL_ERROR.getErrorCode());
			error.setErrorName(exception.getMessage().toString());

			// wrapping the error to a list of errors
			errors = new ArrayList<com.couponsworld.apiresults.Error>();
			errors.add(error);

			resultantOffer.setErrors(errors);
			resultantOffer.setLinks(GenerateLinkService.generateOfferLink("updateOffer"));
			resultantOffer.setStatus(Status.FAILURE);
			resultantOffer.setOffers(offers);
			offers = null;
			errors = null;
			return resultantOffer;
		}
	}

	@DELETE
	@Path("/{offerId}/{authLoginId}")
	@Produces(MediaType.APPLICATION_JSON)
	public ResultantOffer deleteOffer(@PathParam("offerId") long offerId) {
		try {
			return OfferService.deleteOffer(offerId);
		} catch (NullPointerException npe) {
			// creating resultantOffer Object
			resultantOffer = new ResultantOffer();

			// creating the error getting
			com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
			error.setErrorCode(Errors.NULL_POINTER_ERROR.getErrorCode());
			error.setErrorName("Provide correct credentials to access api");

			// wrapping the error to a list of errors
			errors = new ArrayList<com.couponsworld.apiresults.Error>();
			errors.add(error);

			resultantOffer.setErrors(errors);
			resultantOffer.setLinks(GenerateLinkService.generateOfferLink("deleteOffer"));
			resultantOffer.setStatus(Status.FAILURE);
			resultantOffer.setOffers(offers);

			errors = null;
			return resultantOffer;
		} catch (Exception exception) {

			// creating resultantOffer Object
			resultantOffer = new ResultantOffer();

			// creating the error getting
			com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
			error.setErrorCode(Errors.GENERAL_ERROR.getErrorCode());
			error.setErrorName(exception.getMessage().toString());

			// wrapping the error to a list of errors
			errors = new ArrayList<com.couponsworld.apiresults.Error>();
			errors.add(error);

			resultantOffer.setErrors(errors);
			resultantOffer.setLinks(GenerateLinkService.generateOfferLink("deleteOffer"));
			resultantOffer.setStatus(Status.FAILURE);
			resultantOffer.setOffers(offers);

			errors = null;
			return resultantOffer;
		}

	}
}
