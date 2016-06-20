package com.couponsworld.resources;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import com.couponsworld.apiresults.Link;
import com.couponsworld.apiresults.ResultantFilter;
import com.couponsworld.enums.Errors;
import com.couponsworld.enums.Status;
import com.couponsworld.utilities.Constants;
import com.couponsworld.utilities.GenerateLinkService;

@Provider
public class ResourcesFilter implements ContainerRequestFilter {
	private final String AUTHORIZATION_KEY = "Authorization";

	private List<com.couponsworld.apiresults.Error> errors = null;
	private ResultantFilter resultantFilter = null;
	private List<List<Link>> listOfLinksList = null;

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		try {
			System.out.println("Entered into resource filter");
			System.out.println(requestContext.getUriInfo().getPath());
			System.out.println(requestContext.getUriInfo().getAbsolutePath());
			List<String> authHeaders = requestContext.getHeaders().get(AUTHORIZATION_KEY);
			if ((authHeaders.size() >= 0 && authHeaders != null)) {

				String authToken = authHeaders.get(0);
				authToken = authToken.replace("Basic ", "");

				String decodedAuthToken = org.glassfish.jersey.internal.util.Base64.decodeAsString(authToken);
				String[] authCredentials = decodedAuthToken.split(":");
				if (!(authCredentials[0].equals(Constants.AUTH_USERNAME)
						&& authCredentials[1].equals(Constants.AUTH_PASSWORD))) {
					requestContext.abortWith(Response
							.ok(createResultForAuthError("Missing Access Credentials or wrong credentials passed"))
							.build());
				}
			}

		} catch (

		NullPointerException n) {
			// System.out.println("Null Pointer");
			requestContext.abortWith(Response
					.ok(createResultForAuthError("Missing Access Credentials or wrong credentials passed")).build());

		} catch (Exception e) {

			System.out.println("Company exception");
			requestContext.abortWith(Response
					.ok(createResultForAuthError("Missing Access Credentials or wrong credentials passed")).build());
		}

	}

	private ResultantFilter createResultForAuthError(String errorName) {
		// creating resultantOffer Object
		resultantFilter = new ResultantFilter();

		// creating the error getting
		com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
		error.setErrorCode(Errors.API_AUTHENTICATION_ERROR.getErrorCode());
		error.setErrorName("Missing Access Credentials or wrong credentials passed");

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

}
