package com.couponsworld.services;

import static com.googlecode.objectify.ObjectifyService.ofy;

import com.couponsworld.dto.Offer;
import com.couponsworld.exceptions.OfferException;
import com.googlecode.objectify.ObjectifyService;

public class DatabaseService {
	static {
		// Registeration of the entities (dto's)
		ObjectifyService.register(Offer.class);
	}

	public static long offerNo = 0L;

	public static Offer createOfferInDatabase(Offer offer) throws Exception {
		try {

			offer.setOfferId(++offerNo);
			ofy().save().entity(offer);
			return offer;
		} catch (Exception exception) {
			throw exception;
		}
	}

	public static Offer updateOfferInDatabase(long offerId, Offer offer) throws OfferException, Exception {
		try {
			Offer offerRetreivedFromDatabase = ofy().load().type(Offer.class).id(offerId).now();
			if (offerRetreivedFromDatabase != null) {
				offer.setOfferId(offerId);
				ofy().delete().entities(offerRetreivedFromDatabase).now();
				ofy().save().entity(offer).now();
				return offer;
			} else {
				throw new OfferException("Offer not existing with offerId : " + offerId);
			}
		} catch (Exception exception) {
			throw exception;
		}
	}

}
