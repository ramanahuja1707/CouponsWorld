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

import com.couponsworld.dto.Error;
import com.couponsworld.dto.Link;
import com.couponsworld.dto.Offer;
import com.couponsworld.dto.ResultantOffer;
import com.couponsworld.enums.Status;
import com.couponsworld.services.OfferService;
import com.google.appengine.api.search.query.QueryParser.restriction_return;

@Path("/offers")
public class OfferResource {
	private List<Offer> offers = null;
	private List<Error> errors = null;
	private List<Link> links = null;
	private ResultantOffer resultantOffer;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public ResultantOffer getOffers() {
		try {
			return OfferService.getOffers();
		} catch (Exception exception) {

			// creating resultantOffer Object
			resultantOffer = new ResultantOffer();

			// creating the error getting
			com.couponsworld.dto.Error error = new com.couponsworld.dto.Error();
			error.setErrorCode(101);
			error.setErrorName(exception.getMessage().toString());

			// wrapping the error to a list of errors
			errors = new ArrayList<Error>();
			errors.add(error);

			resultantOffer.setErrors(errors);
			resultantOffer.setLinks(links);
			resultantOffer.setStatus(Status.FAILURE);
			resultantOffer.setOffers(offers);
			return resultantOffer;
		}

	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ResultantOffer createOffer(Offer offer) {
		try {
			return OfferService.createOffer(offer);
		} catch (Exception exception) {

			// creating Offer List to wrap the input Offer Object into it
			offers = new ArrayList<Offer>();
			offers.add(offer);

			// creating resultantOffer Object
			resultantOffer = new ResultantOffer();

			// creating the error getting
			com.couponsworld.dto.Error error = new com.couponsworld.dto.Error();
			error.setErrorCode(101);
			error.setErrorName(exception.getMessage().toString());

			// wrapping the error to a list of errors
			errors = new ArrayList<Error>();
			errors.add(error);

			resultantOffer.setErrors(errors);
			resultantOffer.setLinks(links);
			resultantOffer.setStatus(Status.FAILURE);
			resultantOffer.setOffers(offers);
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
		} catch (Exception exception) {

			// creating Offer List to wrap the input Offer Object into it
			offers = new ArrayList<Offer>();
			offers.add(offer);

			// creating resultantOffer Object
			resultantOffer = new ResultantOffer();

			// creating the error getting
			com.couponsworld.dto.Error error = new com.couponsworld.dto.Error();
			error.setErrorCode(101);
			error.setErrorName(exception.getMessage().toString());

			// wrapping the error to a list of errors
			errors = new ArrayList<Error>();
			errors.add(error);

			resultantOffer.setErrors(errors);
			resultantOffer.setLinks(links);
			resultantOffer.setStatus(Status.FAILURE);
			resultantOffer.setOffers(offers);
			return resultantOffer;
		}
	}

	@Path("/{offerId}")
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	public ResultantOffer deleteOffer(@PathParam("offerId") long offerId) {
		Offer offer = new Offer();
		offer.setOfferId(offerId);
		List<Offer> offers = new ArrayList<>();
		offers.add(offer);
		ResultantOffer resultantOffer = new ResultantOffer();
		resultantOffer.setOffers(offers);
		return resultantOffer;
	}
}
