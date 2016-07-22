package com.couponsworld.resources;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.couponsworld.apiresults.ResultantUserPlatform;
import com.couponsworld.dto.UserPlatform;
import com.couponsworld.enums.Errors;
import com.couponsworld.enums.Status;
import com.couponsworld.services.UserPlatformService;
import com.couponsworld.utilities.GenerateLinkService;

@Path("/userplatform")
public class UserPlatformResource {

	// declaration of logger
	private static final Logger log = Logger.getLogger(UserPlatformResource.class.getName());

	private List<UserPlatform> userPlatforms = null;
	private List<com.couponsworld.apiresults.Error> errors = null;
	private ResultantUserPlatform resultantUserPlatform;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public ResultantUserPlatform getUserPlatforms() {
		try {
			log.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ UserPlatform Resource - Start @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
			return UserPlatformService.getUserPlatforms();
		} catch (NullPointerException npe) {
			// creating resultantUserPlatform Object
			resultantUserPlatform = new ResultantUserPlatform();

			// creating the error getting
			com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
			error.setErrorCode(Errors.NULL_POINTER_ERROR.getErrorCode());
			error.setErrorName("Provide correct credentials to access api");
			log.info("Error Occured in UserPlatform Resource :" + error.getErrorCode() + ":" + error.getErrorName());
			// wrapping the error to a list of errors
			errors = new ArrayList<com.couponsworld.apiresults.Error>();
			errors.add(error);

			resultantUserPlatform.setErrors(errors);
			resultantUserPlatform.setLinks(GenerateLinkService.mapOfLinks.get("getOffers"));
			resultantUserPlatform.setStatus(Status.FAILURE);
			resultantUserPlatform.setUserPlatform(userPlatforms);
			errors = null;
			return resultantUserPlatform;
		} catch (Exception exception) {

			// creating resultantUserPlatform Object
			resultantUserPlatform = new ResultantUserPlatform();

			// creating the error getting
			com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
			error.setErrorCode(Errors.GENERAL_ERROR.getErrorCode());
			error.setErrorName(exception.getMessage());
			log.info("Error Occured in UserPlatform Resource :" + error.getErrorCode() + ":" + error.getErrorName());
			// wrapping the error to a list of errors
			errors = new ArrayList<com.couponsworld.apiresults.Error>();
			errors.add(error);

			resultantUserPlatform.setErrors(errors);
			resultantUserPlatform.setLinks(GenerateLinkService.mapOfLinks.get("getOffers"));
			resultantUserPlatform.setStatus(Status.FAILURE);
			resultantUserPlatform.setUserPlatform(userPlatforms);
			errors = null;
			return resultantUserPlatform;
		} finally {
			log.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ UserPlatform Resource - End @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		}

	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ResultantUserPlatform createUserPlatform(UserPlatform userPlatform) {
		try {
			log.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ UserPlatform Resource - Start @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
			return UserPlatformService.createUserPlatform(userPlatform);
		} catch (NullPointerException npe) {

			userPlatforms = new ArrayList<UserPlatform>();
			userPlatforms.add(userPlatform);

			// creating resultantUserPlatform Object
			resultantUserPlatform = new ResultantUserPlatform();

			// creating the error getting
			com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
			error.setErrorCode(Errors.NULL_POINTER_ERROR.getErrorCode());
			error.setErrorName("Provide correct credentials to access api");
			log.info("Error Occured in UserPlatform Resource :" + error.getErrorCode() + ":" + error.getErrorName());
			// wrapping the error to a list of errors
			errors = new ArrayList<com.couponsworld.apiresults.Error>();
			errors.add(error);

			resultantUserPlatform.setErrors(errors);
			resultantUserPlatform.setLinks(GenerateLinkService.mapOfLinks.get("getOffers"));
			resultantUserPlatform.setStatus(Status.FAILURE);
			resultantUserPlatform.setUserPlatform(userPlatforms);
			errors = null;
			return resultantUserPlatform;
		} catch (Exception exception) {

			userPlatforms = new ArrayList<UserPlatform>();
			userPlatforms.add(userPlatform);

			// creating resultantUserPlatform Object
			resultantUserPlatform = new ResultantUserPlatform();

			// creating the error getting
			com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
			error.setErrorCode(Errors.GENERAL_ERROR.getErrorCode());
			error.setErrorName(exception.getMessage());
			log.info("Error Occured in UserPlatform Resource :" + error.getErrorCode() + ":" + error.getErrorName());
			// wrapping the error to a list of errors
			errors = new ArrayList<com.couponsworld.apiresults.Error>();
			errors.add(error);

			resultantUserPlatform.setErrors(errors);
			resultantUserPlatform.setLinks(GenerateLinkService.mapOfLinks.get("getOffers"));
			resultantUserPlatform.setStatus(Status.FAILURE);
			resultantUserPlatform.setUserPlatform(userPlatforms);
			errors = null;
			return resultantUserPlatform;
		} finally {
			log.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ UserPlatform Resource - End @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		}

	}

	@PUT
	@Path("/{userPlatformId}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ResultantUserPlatform updateUserPlatform(@PathParam("userPlatformId") long userPlatformId,
			UserPlatform userPlatform) {
		try {
			log.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ UserPlatform Resource - Start @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
			return UserPlatformService.updateUserPlatform(userPlatformId, userPlatform);
		} catch (NullPointerException npe) {
			userPlatforms = new ArrayList<UserPlatform>();
			userPlatforms.add(userPlatform);

			// creating resultantUserPlatform Object
			resultantUserPlatform = new ResultantUserPlatform();

			// creating the error getting
			com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
			error.setErrorCode(Errors.NULL_POINTER_ERROR.getErrorCode());
			error.setErrorName("Provide correct credentials to access api");
			log.info("Error Occured in UserPlatform Resource :" + error.getErrorCode() + ":" + error.getErrorName());
			// wrapping the error to a list of errors
			errors = new ArrayList<com.couponsworld.apiresults.Error>();
			errors.add(error);

			resultantUserPlatform.setErrors(errors);
			resultantUserPlatform.setLinks(GenerateLinkService.mapOfLinks.get("getOffers"));
			resultantUserPlatform.setStatus(Status.FAILURE);
			resultantUserPlatform.setUserPlatform(userPlatforms);
			errors = null;
			return resultantUserPlatform;
		} catch (Exception exception) {

			userPlatforms = new ArrayList<UserPlatform>();
			userPlatforms.add(userPlatform);

			// creating resultantUserPlatform Object
			resultantUserPlatform = new ResultantUserPlatform();

			// creating the error getting
			com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
			error.setErrorCode(Errors.GENERAL_ERROR.getErrorCode());
			error.setErrorName(exception.getMessage());
			log.info("Error Occured in UserPlatform Resource :" + error.getErrorCode() + ":" + error.getErrorName());
			// wrapping the error to a list of errors
			errors = new ArrayList<com.couponsworld.apiresults.Error>();
			errors.add(error);

			resultantUserPlatform.setErrors(errors);
			resultantUserPlatform.setLinks(GenerateLinkService.mapOfLinks.get("getOffers"));
			resultantUserPlatform.setStatus(Status.FAILURE);
			resultantUserPlatform.setUserPlatform(userPlatforms);
			errors = null;
			return resultantUserPlatform;
		} finally {
			log.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ UserPlatform Resource - End @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		}
	}

	@DELETE
	@Path("/{userPlatformId}")
	@Produces(MediaType.APPLICATION_JSON)
	public ResultantUserPlatform deleteUserPlatform(@PathParam("userPlatformId") long userPlatformId) {
		try {
			log.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ UserPlatform Resource - Start @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
			return UserPlatformService.deleteUserPlatform(userPlatformId);
		} catch (NullPointerException npe) {
			// creating resultantUserPlatform Object
			resultantUserPlatform = new ResultantUserPlatform();

			// creating the error getting
			com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
			error.setErrorCode(Errors.NULL_POINTER_ERROR.getErrorCode());
			error.setErrorName("Provide correct credentials to access api");
			log.info("Error Occured in UserPlatform Resource :" + error.getErrorCode() + ":" + error.getErrorName());
			// wrapping the error to a list of errors
			errors = new ArrayList<com.couponsworld.apiresults.Error>();
			errors.add(error);

			resultantUserPlatform.setErrors(errors);
			resultantUserPlatform.setLinks(GenerateLinkService.mapOfLinks.get("getOffers"));
			resultantUserPlatform.setStatus(Status.FAILURE);
			resultantUserPlatform.setUserPlatform(userPlatforms);
			errors = null;
			return resultantUserPlatform;
		} catch (Exception exception) {

			// creating resultantUserPlatform Object
			resultantUserPlatform = new ResultantUserPlatform();

			// creating the error getting
			com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
			error.setErrorCode(Errors.GENERAL_ERROR.getErrorCode());
			error.setErrorName(exception.getMessage());
			log.info("Error Occured in UserPlatform Resource :" + error.getErrorCode() + ":" + error.getErrorName());
			// wrapping the error to a list of errors
			errors = new ArrayList<com.couponsworld.apiresults.Error>();
			errors.add(error);

			resultantUserPlatform.setErrors(errors);
			resultantUserPlatform.setLinks(GenerateLinkService.mapOfLinks.get("getOffers"));
			resultantUserPlatform.setStatus(Status.FAILURE);
			resultantUserPlatform.setUserPlatform(userPlatforms);
			errors = null;
			return resultantUserPlatform;
		} finally {
			log.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ UserPlatform Resource - End @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		}
	}
}
