package com.couponsworld.utilities;

import javax.ws.rs.core.MediaType;

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

	// ----------------------------------------------------------------------------------------------------------

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
	public static final String DELETECATEGORY_REL = "To delete a particular category from database";
	public static final String DELETECATEGORY_METHOD = "DELETE";
	public static final String DELETECATEGORY_CONTENT_TYPE_CONSUMES = "TEXT_HTML";
	public static final String DELETECATEGORY_CONTENT_TYPE_PRODUCES = "APPLICATION_JSON";

	// ----------------------------------------------------------------------------------------------

	// createCompany link object details
	public static final String CREATECOMPANY = "createCompany";
	public static final String CREATECOMPANY_HREF = "http://1.couponsworld-1707.appspot.com/webapi/companies";
	public static final String CREATECOMPANY_REL = "To create a company in database";
	public static final String CREATECOMPANY_METHOD = "POST";
	public static final String CREATECOMPANY_CONTENT_TYPE_CONSUMES = "APPLICATION_JSON";
	public static final String CREATECOMPANY_CONTENT_TYPE_PRODUCES = "APPLICATION_JSON";

	// getCompanies link object details
	public static final String GETCOMPANIES = "getCompanies";
	public static final String GETCOMPANIES_HREF = "http://1.couponsworld-1707.appspot.com/webapi/companies";
	public static final String GETCOMPANIES_REL = "To get all companies from database";
	public static final String GETCOMPANIES_METHOD = "GET";
	public static final String GETCOMPANIES_CONTENT_TYPE_CONSUMES = "TEXT_HTML";
	public static final String GETCOMPANIES_CONTENT_TYPE_PRODUCES = "APPLICATION_JSON";

	// updateCompany link object details
	public static final String UPDATECOMPANY = "updateCompany";
	public static final String UPDATECOMPANY_HREF = "http://1.couponsworld-1707.appspot.com/webapi/companies/<companyId>";
	public static final String UPDATECOMPANY_REL = "To update a particular company in database";
	public static final String UPDATECOMPANY_METHOD = "PUT";
	public static final String UPDATECOMPANY_CONTENT_TYPE_CONSUMES = "APPLICATION_JSON";
	public static final String UPDATECOMPANY_CONTENT_TYPE_PRODUCES = "APPLICATION_JSON";

	// deleteCompany link object details
	public static final String DELETECOMPANY = "deleteCompany";
	public static final String DELETECOMPANY_HREF = "http://1.couponsworld-1707.appspot.com/webapi/companies/<companyId>";
	public static final String DELETECOMPANY_REL = "To delete a particular company from database";
	public static final String DELETECOMPANY_METHOD = "DELETE";
	public static final String DELETECOMPANY_CONTENT_TYPE_CONSUMES = "TEXT_HTML";
	public static final String DELETECOMPANY_CONTENT_TYPE_PRODUCES = "APPLICATION_JSON";

	// ----------------------------------------------------------------------------------------------

	// createSubCategory link object details
	public static final String CREATESUBCATEGORY = "createSubCategory";
	public static final String CREATESUBCATEGORY_HREF = "http://1.couponsworld-1707.appspot.com/webapi/subcategories";
	public static final String CREATESUBCATEGORY_REL = "To create a sub category in database";
	public static final String CREATESUBCATEGORY_METHOD = "POST";
	public static final String CREATESUBCATEGORY_CONTENT_TYPE_CONSUMES = "APPLICATION_JSON";
	public static final String CREATESUBCATEGORY_CONTENT_TYPE_PRODUCES = "APPLICATION_JSON";

	// getSubCategories link object details
	public static final String GETSUBCATEGORIES = "getSubCategories";
	public static final String GETSUBCATEGORIES_HREF = "http://1.couponsworld-1707.appspot.com/webapi/subcategories";
	public static final String GETSUBCATEGORIES_REL = "To get all sub category from database";
	public static final String GETSUBCATEGORIES_METHOD = "GET";
	public static final String GETSUBCATEGORIES_CONTENT_TYPE_CONSUMES = "TEXT_HTML";
	public static final String GETSUBCATEGORIES_CONTENT_TYPE_PRODUCES = "APPLICATION_JSON";

	// updateSubCategory link object details
	public static final String UPDATESUBCATEGORY = "updateSubCategory";
	public static final String UPDATESUBCATEGORY_HREF = "http://1.couponsworld-1707.appspot.com/webapi/subcategories/<subCategoryId>";
	public static final String UPDATESUBCATEGORY_REL = "To update a particular sub category in database";
	public static final String UPDATESUBCATEGORY_METHOD = "PUT";
	public static final String UPDATESUBCATEGORY_CONTENT_TYPE_CONSUMES = "APPLICATION_JSON";
	public static final String UPDATESUBCATEGORY_CONTENT_TYPE_PRODUCES = "APPLICATION_JSON";

	// deleteSubCategory link object details
	public static final String DELETESUBCATEGORY = "deleteSubCategory";
	public static final String DELETESUBCATEGORY_HREF = "http://1.couponsworld-1707.appspot.com/webapi/subcategories/<subCategoryId>";
	public static final String DELETESUBCATEGORY_REL = "To delete a particular sub category from database";
	public static final String DELETESUBCATEGORY_METHOD = "DELETE";
	public static final String DELETESUBCATEGORY_CONTENT_TYPE_CONSUMES = "TEXT_HTML";
	public static final String DELETESUBCATEGORY_CONTENT_TYPE_PRODUCES = "APPLICATION_JSON";

	// ----------------------------------------------------------------------------------------------

	// createUsabilityStatus link object details
	public static final String CREATEUSABILITYSTATUS = "createUsabilityStatus";
	public static final String CREATEUSABILITYSTATUS_HREF = "http://1.couponsworld-1707.appspot.com/webapi/usabilitystatus";
	public static final String CREATEUSABILITYSTATUS_REL = "To create a usability status in database";
	public static final String CREATEUSABILITYSTATUS_METHOD = "POST";
	public static final String CREATEUSABILITYSTATUS_CONTENT_TYPE_CONSUMES = "APPLICATION_JSON";
	public static final String CREATEUSABILITYSTATUS_CONTENT_TYPE_PRODUCES = "APPLICATION_JSON";

	// getUsabilityStatus link object details
	public static final String GETUSABILITYSTATUS = "getUsabilityStatus";
	public static final String GETUSABILITYSTATUS_HREF = "http://1.couponsworld-1707.appspot.com/webapi/usabilitystatus";
	public static final String GETUSABILITYSTATUS_REL = "To get all usability status from database";
	public static final String GETUSABILITYSTATUS_METHOD = "GET";
	public static final String GETUSABILITYSTATUS_CONTENT_TYPE_CONSUMES = "TEXT_HTML";
	public static final String GETUSABILITYSTATUS_CONTENT_TYPE_PRODUCES = "APPLICATION_JSON";

	// updateUsabilityStatus link object details
	public static final String UPDATEUSABILITYSTATUS = "updateUsabilityStatus";
	public static final String UPDATEUSABILITYSTATUS_HREF = "http://1.couponsworld-1707.appspot.com/webapi/usabilitystatus/<usabilityStatusId>";
	public static final String UPDATEUSABILITYSTATUS_REL = "To update a particular usability status in database";
	public static final String UPDATEUSABILITYSTATUS_METHOD = "PUT";
	public static final String UPDATEUSABILITYSTATUS_CONTENT_TYPE_CONSUMES = "APPLICATION_JSON";
	public static final String UPDATEUSABILITYSTATUS_CONTENT_TYPE_PRODUCES = "APPLICATION_JSON";

	// deleteUsabilityStatus link object details
	public static final String DELETEUSABILITYSTATUS = "deleteUsabilityStatus";
	public static final String DELETEUSABILITYSTATUS_HREF = "http://1.couponsworld-1707.appspot.com/webapi/usabilitystatus/<usabilityStatusId>";
	public static final String DELETEUSABILITYSTATUS_REL = "To delete a particular usability status from database";
	public static final String DELETEUSABILITYSTATUS_METHOD = "DELETE";
	public static final String DELETEUSABILITYSTATUS_CONTENT_TYPE_CONSUMES = "TEXT_HTML";
	public static final String DELETEUSABILITYSTATUS_CONTENT_TYPE_PRODUCES = "APPLICATION_JSON";

	// ----------------------------------------------------------------------------------------------

	// createUserPlatform_link object details
	public static final String CREATEUSERPLATFORM = "createUserPlatform";
	public static final String CREATEUSERPLATFORM_HREF = "http://1.couponsworld-1707.appspot.com/webapi/userplatform";
	public static final String CREATEUSERPLATFORM_REL = "To create a user platform in database";
	public static final String CREATEUSERPLATFORM_METHOD = "POST";
	public static final String CREATEUSERPLATFORM_CONTENT_TYPE_CONSUMES = "APPLICATION_JSON";
	public static final String CREATEUSERPLATFORM_CONTENT_TYPE_PRODUCES = "APPLICATION_JSON";

	// getUserPlatform_link object details
	public static final String GETUSERPLATFORM = "getUserPlatform";
	public static final String GETUSERPLATFORM_HREF = "http://1.couponsworld-1707.appspot.com/webapi/userplatform";
	public static final String GETUSERPLATFORM_REL = "To get all user platform from database";
	public static final String GETUSERPLATFORM_METHOD = "GET";
	public static final String GETUSERPLATFORM_CONTENT_TYPE_CONSUMES = "TEXT_HTML";
	public static final String GETUSERPLATFORM_CONTENT_TYPE_PRODUCES = "APPLICATION_JSON";

	// updateUserPlatform_link object details
	public static final String UPDATEUSERPLATFORM = "updateUserPlatform";
	public static final String UPDATEUSERPLATFORM_HREF = "http://1.couponsworld-1707.appspot.com/webapi/userplatform/<userPlatformId>";
	public static final String UPDATEUSERPLATFORM_REL = "To update a particular user platform in database";
	public static final String UPDATEUSERPLATFORM_METHOD = "PUT";
	public static final String UPDATEUSERPLATFORM_CONTENT_TYPE_CONSUMES = "APPLICATION_JSON";
	public static final String UPDATEUSERPLATFORM_CONTENT_TYPE_PRODUCES = "APPLICATION_JSON";

	// deleteUserPlatform link object details
	public static final String DELETEUSERPLATFORM = "deleteUsabilityStatus";
	public static final String DELETEUSERPLATFORM_HREF = "http://1.couponsworld-1707.appspot.com/webapi/userplatform/<userPlatformId>";
	public static final String DELETEUSERPLATFORM_REL = "To delete a particular user platform from database";
	public static final String DELETEUSERPLATFORM_METHOD = "DELETE";
	public static final String DELETEUSERPLATFORM_CONTENT_TYPE_CONSUMES = "TEXT_HTML";
	public static final String DELETEUSERPLATFORM_CONTENT_TYPE_PRODUCES = "APPLICATION_JSON";

	// ----------------------------------------------------------------------------------------------

	// createUserType_link object details
	public static final String CREATEUSERTYPE = "createUserType";
	public static final String CREATEUSERTYPE_HREF = "http://1.couponsworld-1707.appspot.com/webapi/usertype";
	public static final String CREATEUSERTYPE_REL = "To create a user type in database";
	public static final String CREATEUSERTYPE_METHOD = "POST";
	public static final String CREATEUSERTYPE_CONTENT_TYPE_CONSUMES = "APPLICATION_JSON";
	public static final String CREATEUSERTYPE_CONTENT_TYPE_PRODUCES = "APPLICATION_JSON";

	// getUserType_link object details
	public static final String GETUSERTYPE = "getUserType";
	public static final String GETUSERTYPE_HREF = "http://1.couponsworld-1707.appspot.com/webapi/usertype";
	public static final String GETUSERTYPE_REL = "To get all user type from database";
	public static final String GETUSERTYPE_METHOD = "GET";
	public static final String GETUSERTYPE_CONTENT_TYPE_CONSUMES = "TEXT_HTML";
	public static final String GETUSERTYPE_CONTENT_TYPE_PRODUCES = "APPLICATION_JSON";

	// updateUserType_link object details
	public static final String UPDATEUSERTYPE = "updateUserType";
	public static final String UPDATEUSERTYPE_HREF = "http://1.couponsworld-1707.appspot.com/webapi/usertype/<userTypeId>";
	public static final String UPDATEUSERTYPE_REL = "To update a particular user type in database";
	public static final String UPDATEUSERTYPE_METHOD = "PUT";
	public static final String UPDATEUSERTYPE_CONTENT_TYPE_CONSUMES = "APPLICATION_JSON";
	public static final String UPDATEUSERTYPE_CONTENT_TYPE_PRODUCES = "APPLICATION_JSON";

	// deleteUserType link object details
	public static final String DELETEUSERTYPE = "deleteUsabilityStatus";
	public static final String DELETEUSERTYPE_HREF = "http://1.couponsworld-1707.appspot.com/webapi/usertype/<userTypeId>";
	public static final String DELETEUSERTYPE_REL = "To delete a particular user type from database";
	public static final String DELETEUSERTYPE_METHOD = "DELETE";
	public static final String DELETEUSERTYPE_CONTENT_TYPE_CONSUMES = "TEXT_HTML";
	public static final String DELETEUSERTYPE_CONTENT_TYPE_PRODUCES = "APPLICATION_JSON";

	// ----------------------------------------------------------------------------------------------

	// authentication password for web api
	public final static String AUTH_PASSWORD = "garr.innovate";
	// Authentication username for web API
	public final static String AUTH_USERNAME = "innovate.garr";

	// URL Petterns for WEB REST API
	public static final String URL_PATTERN_ALL = "(http://1.couponsworld-1707.appspot.com/webapi)\\/(offers|categories|subcategories|companies)(\\/?)((\\d)*)";
	public static final String URL_PATTERN_GET_POST = "(http://1.couponsworld-1707.appspot.com/webapi)\\/(offers|categories|subcategories|companies)";
	public static final String URL_PATTERN_DELETE_PUT = "(http://1.couponsworld-1707.appspot.com/webapi)\\/(offers|categories|subcategories|companies)(\\/?)((\\d)*)";

	// URL OF REST-API
	public static final String USERTYPE_URL = "http://localhost:8888/webapi/usertype";

	// URL PROPERTIES
	public static final boolean DO_OUTPUT_FLAG_TRUE = true;
	public static final boolean DO_OUTPUT_FLAG_FALSE = false;
}
