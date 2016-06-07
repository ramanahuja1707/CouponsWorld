package com.couponsworld.resources;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import com.couponsworld.apiresults.ResultantCategory;
import com.couponsworld.apiresults.ResultantComapny;
import com.couponsworld.apiresults.ResultantOffer;
import com.couponsworld.dto.Category;
import com.couponsworld.dto.Company;
import com.couponsworld.dto.Offer;
import com.couponsworld.enums.Errors;
import com.couponsworld.enums.Status;
import com.couponsworld.utilities.Constants;
import com.couponsworld.utilities.GenerateLinkService;

@Provider
public class ResourcesFilter implements ContainerRequestFilter {
	private final String AUTHORIZATION_KEY = "Authorization";

	// List of resources of web api
	private List<Offer> offers = null;
	private List<Category> categories = null;
	private List<Company> companies = null;

	private List<com.couponsworld.apiresults.Error> errors = null;
	private ResultantCategory resultantCategory;
	private ResultantOffer resultantOffer;
	private ResultantComapny resultantCompany;

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		try {
			if (requestContext.getUriInfo().getPath().contains("offers")) {
				List<String> authHeaders = requestContext.getHeaders().get(AUTHORIZATION_KEY);
				if ((authHeaders.size() >= 0 && authHeaders != null)) {

					String authToken = authHeaders.get(0);
					authToken = authToken.replace("Basic ", "");

					String decodedAuthToken = org.glassfish.jersey.internal.util.Base64.decodeAsString(authToken);
					String[] authCredentials = decodedAuthToken.split(":");
					if (!(authCredentials[0].equals(Constants.AUTH_USERNAME)
							&& authCredentials[1].equals(Constants.AUTH_PASSWORD))) {
						requestContext.abortWith(Response.ok(createResultantAuthErrorForOfferURL(
								"Missing Access Credentials or wrong credentials passed")).build());
					}
				}
			} else if (requestContext.getUriInfo().getPath().contains("categories")) {
				List<String> authHeaders = requestContext.getHeaders().get(AUTHORIZATION_KEY);
				if ((authHeaders.size() > 0 && authHeaders != null)) {

					String authToken = authHeaders.get(0);
					authToken = authToken.replace("Basic ", "");
					String decodedAuthToken = org.glassfish.jersey.internal.util.Base64.decodeAsString(authToken);
					String[] authCredentials = decodedAuthToken.split(":");
					if (!(authCredentials[0].equals(Constants.AUTH_USERNAME)
							&& authCredentials[1].equals(Constants.AUTH_PASSWORD))) {
						requestContext.abortWith(Response.ok(createResultantAuthErrorForCategoryURL(
								"Missing Access Credentials or wrong credentials passed")).build());
					}
				}
			} else if (requestContext.getUriInfo().getPath().contains("companies")) {
				List<String> authHeaders = requestContext.getHeaders().get(AUTHORIZATION_KEY);
				if ((authHeaders.size() > 0 && authHeaders != null)) {
					String authToken = authHeaders.get(0);
					authToken = authToken.replace("Basic ", "");
					String decodedAuthToken = org.glassfish.jersey.internal.util.Base64.decodeAsString(authToken);
					String[] authCredentials = decodedAuthToken.split(":");
					if (!(authCredentials[0].equals(Constants.AUTH_USERNAME)
							&& authCredentials[1].equals(Constants.AUTH_PASSWORD))) {
						requestContext.abortWith(Response.ok(createResultantAuthErrorForCompanyURL(
								"Missing Access Credentials or wrong credentials passed")).build());
					}
				}
			}
		} catch (NullPointerException n) {
			// System.out.println("Null Pointer");
			if (requestContext.getUriInfo().getPath().contains("offers")) {
				requestContext.abortWith(Response.ok(
						createResultantAuthErrorForOfferURL("Missing Access Credentials or wrong credentials passed"))
						.build());
			} else if (requestContext.getUriInfo().getPath().contains("categories")) {
				requestContext.abortWith(Response.ok(createResultantAuthErrorForCategoryURL(
						"Missing Access Credentials or wrong credentials passed")).build());
			} else {
				requestContext.abortWith(Response.ok(
						createResultantAuthErrorForCompanyURL("Missing Access Credentials or wrong credentials passed"))
						.build());
			}
		} catch (Exception e) {
			// System.out.println("Exceptions");
			if (requestContext.getUriInfo().getPath().contains("offers")) {
				requestContext.abortWith(Response.ok(
						createResultantAuthErrorForOfferURL("Missing Access Credentials or wrong credentials passed"))
						.build());
			} else if (requestContext.getUriInfo().getPath().contains("categories")) {
				requestContext.abortWith(Response.ok(createResultantAuthErrorForCategoryURL(
						"Missing Access Credentials or wrong credentials passed")).build());
			} else {
				requestContext.abortWith(Response.ok(
						createResultantAuthErrorForCompanyURL("Missing Access Credentials or wrong credentials passed"))
						.build());
			}
		}
	}

	private ResultantOffer createResultantAuthErrorForOfferURL(String errorName) {
		// creating resultantOffer Object
		resultantOffer = new ResultantOffer();

		// creating the error getting
		com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
		error.setErrorCode(Errors.API_AUTHENTICATION_ERROR.getErrorCode());
		error.setErrorName("Missing Access Credentials or wrong credentials passed");

		// wrapping the error to a list of errors
		errors = new ArrayList<com.couponsworld.apiresults.Error>();
		errors.add(error);

		resultantOffer.setErrors(errors);
		resultantOffer.setLinks(GenerateLinkService.generateOfferLink("getOffers"));
		resultantOffer.setStatus(Status.FAILURE);
		resultantOffer.setOffers(offers);
		errors = null;
		return resultantOffer;
	}

	private ResultantCategory createResultantAuthErrorForCategoryURL(String errorName) {
		// creating resultantCategory Object
		resultantCategory = new ResultantCategory();

		// creating the error getting
		com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
		error.setErrorCode(Errors.API_AUTHENTICATION_ERROR.getErrorCode());
		error.setErrorName("Missing Access Credentials or wrong credentials passed");

		// wrapping the error to a list of errors
		errors = new ArrayList<com.couponsworld.apiresults.Error>();
		errors.add(error);

		resultantCategory.setErrors(errors);
		resultantCategory.setLinks(GenerateLinkService.generateCategoryLink("getOffers"));
		resultantCategory.setStatus(Status.FAILURE);
		resultantCategory.setCategories(categories);
		errors = null;
		categories = null;
		return resultantCategory;
	}

	private ResultantComapny createResultantAuthErrorForCompanyURL(String errorName) {
		// creating resultantCompany Object
		resultantCompany = new ResultantComapny();

		// creating the error getting
		com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
		error.setErrorCode(Errors.API_AUTHENTICATION_ERROR.getErrorCode());
		error.setErrorName("Missing Access Credentials or wrong credentials passed");

		// wrapping the error to a list of errors
		errors = new ArrayList<com.couponsworld.apiresults.Error>();
		errors.add(error);

		resultantCompany.setErrors(errors);
		resultantCompany.setLinks(GenerateLinkService.generateCategoryLink("getOffers"));
		resultantCompany.setStatus(Status.FAILURE);
		resultantCompany.setCompanies(companies);
		errors = null;
		companies = null;
		return resultantCompany;
	}
}
