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

import com.couponsworld.dto.Offer;
import com.couponsworld.dto.ResultantOffer;

@Path("/offers")
public class OfferResource {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public ResultantOffer getOffers() {
		Offer offer = new Offer();
		offer.setOfferId(1L);
		List<Offer> offers = new ArrayList<>();
		offers.add(offer);
		ResultantOffer resultantOffer = new ResultantOffer();
		resultantOffer.setOffers(offers);
		return resultantOffer;
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ResultantOffer createOffer(Offer offer) {
		List<Offer> offers = new ArrayList<>();
		offers.add(offer);
		ResultantOffer resultantOffer = new ResultantOffer();
		resultantOffer.setOffers(offers);
		return resultantOffer;
	}

	@PUT
	@Path("/{offerId}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ResultantOffer updateOffer(@PathParam("offerId") long offerId, Offer offer) {
		offer.setOfferId(offerId);
		List<Offer> offers = new ArrayList<>();
		offers.add(offer);
		ResultantOffer resultantOffer = new ResultantOffer();
		resultantOffer.setOffers(offers);
		return resultantOffer;

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
