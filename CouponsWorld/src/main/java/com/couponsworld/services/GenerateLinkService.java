package com.couponsworld.services;

import java.util.ArrayList;
import java.util.List;

import com.couponsworld.apiresults.Link;
import com.couponsworld.utilities.Constants;
import com.google.api.server.spi.Constant;

public class GenerateLinkService {

	// Offer CRUD Links
	private static Link getOffers_Link = null;
	private static Link createOffer_Link = null;
	private static Link updateOffer_Link = null;
	private static Link deleteOffer_Link = null;

	// Category CRUD Links
	private static Link getCategories_Link = null;
	private static Link createCategory_Link = null;
	private static Link updateCategory_Link = null;
	private static Link deleteCategory_Link = null;

	private static List<Link> offerLinks = null;
	private static List<Link> categoryLinks = null;

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

		// creating the createCategory_Link object
		createCategory_Link = new Link();
		createCategory_Link.setContentTypeConsumes(Constants.CREATECATEGORIES_CONTENT_TYPE_CONSUMES);
		createCategory_Link.setContentTypeProduces(Constants.CREATECATEGORIES_CONTENT_TYPE_PRODUCES);
		createCategory_Link.setHref(Constants.CREATECATEGORIES_HREF);
		createCategory_Link.setMethod(Constants.CREATECATEGORIES_METHOD);
		createCategory_Link.setRel(Constants.CREATECATEGORIES_REL);

		// creating the getCategories_Link object
		getCategories_Link = new Link();
		getCategories_Link.setContentTypeConsumes(Constants.GETCATEGORY_CONTENT_TYPE_CONSUMES);
		getCategories_Link.setContentTypeProduces(Constants.GETCATEGORY_CONTENT_TYPE_PRODUCES);
		getCategories_Link.setHref(Constants.GETCATEGORY_HREF);
		getCategories_Link.setMethod(Constants.GETCATEGORY_METHOD);
		getCategories_Link.setRel(Constants.GETCATEGORY_REL);

		// creating the updateCategory_Link object
		updateCategory_Link = new Link();
		updateCategory_Link.setContentTypeConsumes(Constants.UPDATECATEGORY_CONTENT_TYPE_CONSUMES);
		updateCategory_Link.setContentTypeProduces(Constants.UPDATECATEGORY_CONTENT_TYPE_PRODUCES);
		updateCategory_Link.setHref(Constants.UPDATECATEGORY_HREF);
		updateCategory_Link.setMethod(Constants.UPDATECATEGORY_METHOD);
		updateCategory_Link.setRel(Constants.UPDATECATEGORY_REL);

		// creating the deleteCategory_Link object
		deleteCategory_Link = new Link();
		deleteCategory_Link.setContentTypeConsumes(Constants.DELETECATEGORY_CONTENT_TYPE_CONSUMES);
		deleteCategory_Link.setContentTypeProduces(Constants.DELETECATEGORY_CONTENT_TYPE_PRODUCES);
		deleteCategory_Link.setHref(Constants.DELETECATEGORY_HREF);
		deleteCategory_Link.setMethod(Constants.DELETECATEGORY_METHOD);
		deleteCategory_Link.setRel(Constants.DELETECATEGORY_REL);

	}

	public static List<Link> generateOfferLink(String methodName) {
		offerLinks = new ArrayList<Link>();
		offerLinks.add(createOffer_Link);
		offerLinks.add(deleteOffer_Link);
		offerLinks.add(getOffers_Link);
		offerLinks.add(updateOffer_Link);
		return offerLinks;
	}

	public static List<Link> generateCategoryLink(String methodName) {
		categoryLinks = new ArrayList<Link>();
		categoryLinks.add(createCategory_Link);
		categoryLinks.add(deleteCategory_Link);
		categoryLinks.add(getCategories_Link);
		categoryLinks.add(updateCategory_Link);
		return categoryLinks;
	}

}
