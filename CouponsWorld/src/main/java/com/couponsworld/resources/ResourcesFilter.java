package com.couponsworld.resources;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import com.couponsworld.apiresults.Error;
import com.couponsworld.apiresults.Link;
import com.couponsworld.apiresults.ResultantFilter;
import com.couponsworld.enums.Errors;
import com.couponsworld.enums.Status;
import com.couponsworld.utilities.Constants;
import com.couponsworld.utilities.GenerateLinkService;

@Provider
public class ResourcesFilter implements ContainerRequestFilter {
	private final String AUTHORIZATION_KEY = "Authorization";
	private final String PASSWORD_KEY = "password";
	private final String USERNAME_KEY = "username";
	private final String ACCESS_PLATFORM_KEY = "accessPlatform";
	private final String ACCESS_ID_KEY = "accessId";

	private List<com.couponsworld.apiresults.Error> errors = null;
	private ResultantFilter resultantFilter = null;
	private List<List<Link>> listOfLinksList = null;

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		try {
			Error error = checkMandatoryRequestParametersForRestApi(requestContext);

			if (error != null) {
				requestContext.abortWith(Response.ok(createResultForAuthError(error)).build());
			}

		} catch (NullPointerException n) {
			System.out.println("Null Pointer");
			// creating the error getting
			com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
			error.setErrorCode(Errors.NULL_POINTER_ERROR.getErrorCode());
			error.setErrorName("Missing Mandatory parameters in request...");

			requestContext.abortWith(Response.ok(createResultForAuthError(error)).build());

		} catch (Exception e) {
			System.out.println("Exception Occured :" + e.getMessage());
			// creating the error getting
			com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
			error.setErrorCode(Errors.GENERAL_ERROR.getErrorCode());
			error.setErrorName(e.getMessage());

			requestContext.abortWith(Response.ok(createResultForAuthError(error)).build());
		}

	}

	private ResultantFilter createResultForAuthError(Error errorReceived) {
		// creating resultantOffer Object
		resultantFilter = new ResultantFilter();

		// creating the error getting
		com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
		error.setErrorCode(errorReceived.getErrorCode());
		error.setErrorName(errorReceived.getErrorName());

		// wrapping the error to a list of errors
		errors = new ArrayList<com.couponsworld.apiresults.Error>();
		errors.add(error);

		listOfLinksList = new ArrayList<>();
		listOfLinksList.add(GenerateLinkService.mapOfLinks.get("createUserType"));
		listOfLinksList.add(GenerateLinkService.mapOfLinks.get("createOffer"));
		listOfLinksList.add(GenerateLinkService.mapOfLinks.get("createCategory"));
		listOfLinksList.add(GenerateLinkService.mapOfLinks.get("createSubCategory"));
		listOfLinksList.add(GenerateLinkService.mapOfLinks.get("createCompany"));
		listOfLinksList.add(GenerateLinkService.mapOfLinks.get("createUsabilityStatus"));
		listOfLinksList.add(GenerateLinkService.mapOfLinks.get("createUserPlatform"));

		resultantFilter.setErrors(errors);
		resultantFilter.setLinks(listOfLinksList);
		resultantFilter.setStatus(Status.FAILURE);
		errors = null;
		return resultantFilter;
	}

	private Error checkMandatoryRequestParametersForRestApi(ContainerRequestContext requestContext) {
		String authenticationValue = requestContext.getHeaders().getFirst(AUTHORIZATION_KEY);
		String usernameValue = requestContext.getHeaders().getFirst(USERNAME_KEY);
		String passwordValue = requestContext.getHeaders().getFirst(PASSWORD_KEY);
		String accessIdValue = requestContext.getHeaders().getFirst(ACCESS_ID_KEY);
		String accessPlatformValue = requestContext.getHeaders().getFirst(ACCESS_PLATFORM_KEY);

		if (usernameValue == null || usernameValue.equals("")) {
			Error error = new Error();
			error.setErrorCode(Errors.API_AUTHENTICATION_ERROR.getErrorCode());
			error.setErrorName("Missing username in request");
			return error;
		} else if (passwordValue == null || passwordValue.equals("")) {
			Error error = new Error();
			error.setErrorCode(Errors.API_AUTHENTICATION_ERROR.getErrorCode());
			error.setErrorName("Missing password in request");
			return error;
		} else if (accessIdValue == null || accessIdValue.equals("")) {
			Error error = new Error();
			error.setErrorCode(Errors.API_AUTHENTICATION_ERROR.getErrorCode());
			error.setErrorName("Missing access id in request");
			return error;
		} else if (accessPlatformValue == null || accessPlatformValue.equals("")) {
			Error error = new Error();
			error.setErrorCode(Errors.API_AUTHENTICATION_ERROR.getErrorCode());
			error.setErrorName("Missing access platform in request");
			return error;
		} else {
			if ((!(authenticationValue.equals("")) && authenticationValue != null)) {

				String authToken = authenticationValue;
				authToken = authToken.replace("Basic ", "");
				// System.out.println("not null");
				String decodedAuthToken = org.glassfish.jersey.internal.util.Base64.decodeAsString(authToken);
				// System.out.println(decodedAuthToken);
				String[] authCredentials = decodedAuthToken.split(":");
				if (authCredentials.length != 2) {
					Error error = new Error();
					error.setErrorCode(Errors.API_AUTHENTICATION_ERROR.getErrorCode());
					error.setErrorName("Authentication Key is not correct");
					return error;
				} else if (!(authCredentials[0].equals(Constants.AUTH_USERNAME)
						&& authCredentials[1].equals(Constants.AUTH_PASSWORD))) {
					// System.out.println("USERNAME AND PASSWORD ARE VALIDATED
					// FAILED...");
					Error error = new Error();
					error.setErrorCode(Errors.API_AUTHENTICATION_ERROR.getErrorCode());
					error.setErrorName("Authenication Key is not correct");
					return error;
					// requestContext.abortWith(Response.ok(createResultForAuthError(error)).build());
				} else {
					// System.out.println("USERNAME AND PASSWORD ARE VALIDATED
					// SUCCESS...");
					return null;
				}
			} else {
				Error error = new Error();
				error.setErrorCode(Errors.API_AUTHENTICATION_ERROR.getErrorCode());
				error.setErrorName("Missing Authentication Key in request");
				return error;
			}

		}
	}
}
