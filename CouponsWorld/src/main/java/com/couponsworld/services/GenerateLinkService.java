package com.couponsworld.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.couponsworld.dto.Link;
import com.couponsworld.utilities.Constants;

public class GenerateLinkService {

	private static Link getOffers_Link = null;
	private static Link createOffer_Link = null;
	private static Link updateOffer_Link = null;
	private static Link deleteOffer_Link = null;
	private static List<Link> links = null;

	static {
		// creating the getoffers_Link object
		getOffers_Link = new Link();
		getOffers_Link.setContentTypeConsumes(Constants.GETOFFERS_CONTENT_TYPE_CONSUMES);
		getOffers_Link.setContentTypeProduces(Constants.GETOFFERS_CONTENT_TYPE_PRODUCES);
		getOffers_Link.setHref(Constants.GETOFFERS_HREF);
		getOffers_Link.setMethod(Constants.GETOFFERS_METHOD);
		getOffers_Link.setRel(Constants.GETOFFERS_REL);

		// creating the createOffers_Link object
		createOffer_Link = new Link();
		createOffer_Link.setContentTypeConsumes(Constants.CREATEOFFER_CONTENT_TYPE_CONSUMES);
		createOffer_Link.setContentTypeProduces(Constants.CREATEOFFER_CONTENT_TYPE_PRODUCES);
		createOffer_Link.setHref(Constants.CREATEOFFER_HREF);
		createOffer_Link.setMethod(Constants.CREATEOFFER_METHOD);
		createOffer_Link.setRel(Constants.CREATEOFFER_REL);

		// creating the updateOffer_Link object
		updateOffer_Link = new Link();
		updateOffer_Link.setContentTypeConsumes(Constants.UPDATEOFFER_CONTENT_TYPE_CONSUMES);
		updateOffer_Link.setContentTypeProduces(Constants.UPDATEOFFER_CONTENT_TYPE_PRODUCES);
		updateOffer_Link.setHref(Constants.UPDATEOFFER_HREF);
		updateOffer_Link.setMethod(Constants.UPDATEOFFER_METHOD);
		updateOffer_Link.setRel(Constants.UPDATEOFFER_REL);

		// creating the deleteOffer_Link object
		deleteOffer_Link = new Link();
		deleteOffer_Link.setContentTypeConsumes(Constants.DELETEOFFER_CONTENT_TYPE_CONSUMES);
		deleteOffer_Link.setContentTypeProduces(Constants.DELETEOFFER_CONTENT_TYPE_PRODUCES);
		deleteOffer_Link.setHref(Constants.DELETEOFFER_HREF);
		deleteOffer_Link.setMethod(Constants.DELETEOFFER_METHOD);
		deleteOffer_Link.setRel(Constants.DELETEOFFER_REL);
	}

	public static List<Link> generateLink(String methodName) {
		links = new ArrayList<Link>();
		links.add(createOffer_Link);
		links.add(deleteOffer_Link);
		links.add(getOffers_Link);
		links.add(updateOffer_Link);
		return links;
	}

}
