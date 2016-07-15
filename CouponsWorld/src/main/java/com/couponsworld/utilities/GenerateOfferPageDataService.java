package com.couponsworld.utilities;

import java.net.HttpURLConnection;

public class GenerateOfferPageDataService {
	private static String GET_METHOD = "GET";

	public static String getAllCompanies(String username, String password, String accessId, String accessPlatform) {
		try {
			HttpURLConnection httpUrlConnection = HttpUrlService.getHttpURLConnection(Constants.COMPANY_URL, GET_METHOD,
					"", "Basic " + new ApiAuthenticationService().generateAuthorizationKey(username, password),
					Constants.DO_OUTPUT_FLAG_TRUE, "", username, password, accessId, accessPlatform);
			String urlResponse = HttpUrlService.readHttpUrlResponse(httpUrlConnection.getInputStream());
			return urlResponse;
		} catch (Exception e) {
			return "";
		}

	}

	public static String getAllCategories(String username, String password, String accessId, String accessPlatform) {
		try {
			HttpURLConnection httpUrlConnection = HttpUrlService.getHttpURLConnection(Constants.CATEGORY_URL,
					GET_METHOD, "",
					"Basic " + new ApiAuthenticationService().generateAuthorizationKey(username, password),
					Constants.DO_OUTPUT_FLAG_TRUE, "", username, password, accessId, accessPlatform);
			String urlResponse = HttpUrlService.readHttpUrlResponse(httpUrlConnection.getInputStream());
			return urlResponse;
		} catch (Exception e) {
			return "";
		}

	}

	public static String getAllSubCategories(String username, String password, String accessId, String accessPlatform) {
		try {
			HttpURLConnection httpUrlConnection = HttpUrlService.getHttpURLConnection(Constants.SUBCATEGORY_URL,
					GET_METHOD, "",
					"Basic " + new ApiAuthenticationService().generateAuthorizationKey(username, password),
					Constants.DO_OUTPUT_FLAG_TRUE, "", username, password, accessId, accessPlatform);
			String urlResponse = HttpUrlService.readHttpUrlResponse(httpUrlConnection.getInputStream());
			return urlResponse;
		} catch (Exception e) {
			return "";
		}

	}

	public static String getAllUserPlatforms(String username, String password, String accessId, String accessPlatform) {
		try {
			HttpURLConnection httpUrlConnection = HttpUrlService.getHttpURLConnection(Constants.USERPLATFORM_URL,
					GET_METHOD, "",
					"Basic " + new ApiAuthenticationService().generateAuthorizationKey(username, password),
					Constants.DO_OUTPUT_FLAG_TRUE, "", username, password, accessId, accessPlatform);
			String urlResponse = HttpUrlService.readHttpUrlResponse(httpUrlConnection.getInputStream());
			return urlResponse;
		} catch (Exception e) {
			return "";
		}
	}

	public static String getAllUserTypes(String username, String password, String accessId, String accessPlatform) {
		try {
			HttpURLConnection httpUrlConnection = HttpUrlService.getHttpURLConnection(Constants.USERTYPE_URL,
					GET_METHOD, "",
					"Basic " + new ApiAuthenticationService().generateAuthorizationKey(username, password),
					Constants.DO_OUTPUT_FLAG_TRUE, "", username, password, accessId, accessPlatform);
			String urlResponse = HttpUrlService.readHttpUrlResponse(httpUrlConnection.getInputStream());
			return urlResponse;
		} catch (Exception e) {
			return "";
		}
	}

	public static String getAllUsabilityStatuses(String username, String password, String accessId,
			String accessPlatform) {
		try {
			HttpURLConnection httpUrlConnection = HttpUrlService.getHttpURLConnection(Constants.USABILITYSTATUS_URL,
					GET_METHOD, "",
					"Basic " + new ApiAuthenticationService().generateAuthorizationKey(username, password),
					Constants.DO_OUTPUT_FLAG_TRUE, "", username, password, accessId, accessPlatform);
			String urlResponse = HttpUrlService.readHttpUrlResponse(httpUrlConnection.getInputStream());
			return urlResponse;
		} catch (Exception e) {
			return "";
		}

	}

	public static String getAllCategoriesSubCategoriesMapping(String username, String password, String accessId,
			String accessPlatform) {
		try {
			HttpURLConnection httpUrlConnection = HttpUrlService.getHttpURLConnection(
					Constants.CATEGORY_SUBCATEGORY_MAPPING_URL, GET_METHOD, "",
					"Basic " + new ApiAuthenticationService().generateAuthorizationKey(username, password),
					Constants.DO_OUTPUT_FLAG_TRUE, "", username, password, accessId, accessPlatform);
			String urlResponse = HttpUrlService.readHttpUrlResponse(httpUrlConnection.getInputStream());
			return urlResponse;
		} catch (Exception e) {
			return "";
		}

	}

}
