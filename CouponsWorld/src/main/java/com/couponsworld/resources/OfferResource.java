package com.couponsworld.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/offers")
public class OfferResource {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getOffers() {
		return "{key:hello World}";
	}
}
