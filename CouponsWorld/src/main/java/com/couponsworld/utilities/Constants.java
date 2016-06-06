package com.couponsworld.utilities;

public class Constants {
	// createOffer link object details
	public static final String GETOFFERS = "createOffer";
	public static final String GETOFFERS_HREF = "http://1.couponsworld-1707.appspot.com/webapi/offers";
	public static final String GETOFFERS_REL = "To create an offer in database";
	public static final String GETOFFERS_METHOD = "POST";
	public static final String GETOFFERS_CONTENT_TYPE_CONSUMES = "APPLICATION_JSON";
	public static final String GETOFFERS_CONTENT_TYPE_PRODUCES = "APPLICATION_JSON";

	// getOffers link object details
	public static final String CREATEOFFER = "getOffers";
	public static final String CREATEOFFER_HREF = "http://1.couponsworld-1707.appspot.com/webapi/offers";
	public static final String CREATEOFFER_REL = "To get all Offers from database";
	public static final String CREATEOFFER_METHOD = "GET";
	public static final String CREATEOFFER_CONTENT_TYPE_CONSUMES = "TEXT_HTML";
	public static final String CREATEOFFER_CONTENT_TYPE_PRODUCES = "APPLICATION_JSON";

	// updateOffer link object details
	public static final String UPDATEOFFER = "updateOffer";
	public static final String UPDATEOFFER_HREF = "http://1.couponsworld-1707.appspot.com/webapi/offers/<offerId>";
	public static final String UPDATEOFFER_REL = "To update a particular offer in database";
	public static final String UPDATEOFFER_METHOD = "PUT";
	public static final String UPDATEOFFER_CONTENT_TYPE_CONSUMES = "APPLICATION_JSON";
	public static final String UPDATEOFFER_CONTENT_TYPE_PRODUCES = "APPLICATION_JSON";

	// deleteOffer link object details
	public static final String DELETEOFFER = "deleteOffer";
	public static final String DELETEOFFER_HREF = "http://1.couponsworld-1707.appspot.com/webapi/offers/<offerId>";
	public static final String DELETEOFFER_REL = "To delete a particular offer from database";
	public static final String DELETEOFFER_METHOD = "DELETE";
	public static final String DELETEOFFER_CONTENT_TYPE_CONSUMES = "TEXT_HTML";
	public static final String DELETEOFFER_CONTENT_TYPE_PRODUCES = "APPLICATION_JSON";

	// createCategories link object details
	public static final String CREATECATEGORIES = "createCategories";
	public static final String CREATECATEGORIES_HREF = "http://1.couponsworld-1707.appspot.com/webapi/categories";
	public static final String CREATECATEGORIES_REL = "To create a category in database";
	public static final String CREATECATEGORIES_METHOD = "POST";
	public static final String CREATECATEGORIES_CONTENT_TYPE_CONSUMES = "APPLICATION_JSON";
	public static final String CREATECATEGORIES_CONTENT_TYPE_PRODUCES = "APPLICATION_JSON";

	// getCategory link object details
	public static final String GETCATEGORY = "getCategory";
	public static final String GETCATEGORY_HREF = "http://1.couponsworld-1707.appspot.com/webapi/categories";
	public static final String GETCATEGORY_REL = "To get all categories from database";
	public static final String GETCATEGORY_METHOD = "GET";
	public static final String GETCATEGORY_CONTENT_TYPE_CONSUMES = "TEXT_HTML";
	public static final String GETCATEGORY_CONTENT_TYPE_PRODUCES = "APPLICATION_JSON";

	// updateCategory link object details
	public static final String UPDATECATEGORY = "updateCategory";
	public static final String UPDATECATEGORY_HREF = "http://1.couponsworld-1707.appspot.com/webapi/categories/<categoryId>";
	public static final String UPDATECATEGORY_REL = "To update a particular category in database";
	public static final String UPDATECATEGORY_METHOD = "PUT";
	public static final String UPDATECATEGORY_CONTENT_TYPE_CONSUMES = "APPLICATION_JSON";
	public static final String UPDATECATEGORY_CONTENT_TYPE_PRODUCES = "APPLICATION_JSON";

	// deleteCategory link object details
	public static final String DELETECATEGORY = "deleteCategory";
	public static final String DELETECATEGORY_HREF = "http://1.couponsworld-1707.appspot.com/webapi/categories/<categoryId>";
	public static final String DELETECATEGORY_REL = "To delete a particular offer from database";
	public static final String DELETECATEGORY_METHOD = "DELETE";
	public static final String DELETECATEGORY_CONTENT_TYPE_CONSUMES = "TEXT_HTML";
	public static final String DELETECATEGORY_CONTENT_TYPE_PRODUCES = "APPLICATION_JSON";

	// authentication password for web api
	public final static String AUTH_PASSWORD = "garr.innovate";
	// Authentication username for web API
	public final static String AUTH_USERNAME = "innovate.garr";
}
