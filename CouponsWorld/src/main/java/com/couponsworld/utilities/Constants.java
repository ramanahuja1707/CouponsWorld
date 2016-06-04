package com.couponsworld.utilities;

public class Constants {
	// createOffer link object details
	public static final String GETOFFERS = "getOffers";
	public static final String GETOFFERS_HREF = "http://1.couponsworld-1707.appspot.com/webapi/offers/<authLoginId>";
	public static final String GETOFFERS_REL = "To create an offer in database";
	public static final String GETOFFERS_METHOD = "POST";
	public static final String GETOFFERS_CONTENT_TYPE_CONSUMES = "APPLICATION_JSON";
	public static final String GETOFFERS_CONTENT_TYPE_PRODUCES = "APPLICATION_JSON";

	// getOffers link object details
	public static final String CREATEOFFER = "createOffer";
	public static final String CREATEOFFER_HREF = "http://1.couponsworld-1707.appspot.com/webapi/offers/<authLoginId>";
	public static final String CREATEOFFER_REL = "To get all Offers from database";
	public static final String CREATEOFFER_METHOD = "GET";
	public static final String CREATEOFFER_CONTENT_TYPE_CONSUMES = "TEXT_HTML";
	public static final String CREATEOFFER_CONTENT_TYPE_PRODUCES = "APPLICATION_JSON";

	// updateOffer link object details
	public static final String UPDATEOFFER = "updateOffer";
	public static final String UPDATEOFFER_HREF = "http://1.couponsworld-1707.appspot.com/webapi/offers/<offerId>/<authLoginId>";
	public static final String UPDATEOFFER_REL = "To update a particular offer in database";
	public static final String UPDATEOFFER_METHOD = "PUT";
	public static final String UPDATEOFFER_CONTENT_TYPE_CONSUMES = "APPLICATION_JSON";
	public static final String UPDATEOFFER_CONTENT_TYPE_PRODUCES = "APPLICATION_JSON";

	// deleteOffer link object details
	public static final String DELETEOFFER = "deleteOffer";
	public static final String DELETEOFFER_HREF = "http://1.couponsworld-1707.appspot.com/webapi/offers/<offerId>/<authLoginId>";
	public static final String DELETEOFFER_REL = "To delete a particular offer from database";
	public static final String DELETEOFFER_METHOD = "DELETE";
	public static final String DELETEOFFER_CONTENT_TYPE_CONSUMES = "TEXT_HTML";
	public static final String DELETEOFFER_CONTENT_TYPE_PRODUCES = "APPLICATION_JSON";

	// authentication login id for web api
	public final static String AUTH_LOGIN_ID = "garr.innovate";
}
