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

import com.couponsworld.apiresults.ResultantUserType;
import com.couponsworld.dto.UserType;
import com.couponsworld.enums.Errors;
import com.couponsworld.enums.Status;
import com.couponsworld.services.UserTypeService;
import com.couponsworld.utilities.GenerateLinkService;

@Path("/usertype")
public class UserTypeResource {

	// declaration of logger
	private static final Logger log = Logger.getLogger(UserTypeResource.class.getName());

	private List<UserType> userTypes = null;
	private List<com.couponsworld.apiresults.Error> errors = null;
	private ResultantUserType resultantUserType;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public ResultantUserType getUserTypes() {
		try {
			log.info("enetered into get method of resource class of REST API...");
			return UserTypeService.getUserTypes();
		} catch (NullPointerException npe) {

			// creating resultantUserType Object
			resultantUserType = new ResultantUserType();

			// creating the error getting
			com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
			error.setErrorCode(Errors.NULL_POINTER_ERROR.getErrorCode());
			error.setErrorName("Provide correct credentials to access api");

			// wrapping the error to a list of errors
			errors = new ArrayList<com.couponsworld.apiresults.Error>();
			errors.add(error);

			resultantUserType.setErrors(errors);
			resultantUserType.setLinks(GenerateLinkService.mapOfLinks.get("getUserTypes"));
			resultantUserType.setStatus(Status.FAILURE);
			resultantUserType.setUserType(userTypes);
			errors = null;
			return resultantUserType;
		} catch (Exception exception) {

			// creating resultantUserType Object
			resultantUserType = new ResultantUserType();

			// creating the error getting
			com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
			error.setErrorCode(Errors.GENERAL_ERROR.getErrorCode());
			error.setErrorName(exception.getMessage());

			// wrapping the error to a list of errors
			errors = new ArrayList<com.couponsworld.apiresults.Error>();
			errors.add(error);

			resultantUserType.setErrors(errors);
			resultantUserType.setLinks(GenerateLinkService.mapOfLinks.get("getUserTypes"));
			resultantUserType.setStatus(Status.FAILURE);
			resultantUserType.setUserType(userTypes);
			errors = null;
			return resultantUserType;
		}

	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ResultantUserType createUserType(UserType userType) {
		try {
			return UserTypeService.createUserType(userType);
		} catch (NullPointerException npe) {

			userTypes = new ArrayList<UserType>();
			userTypes.add(userType);

			// creating resultantUserType Object
			resultantUserType = new ResultantUserType();

			// creating the error getting
			com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
			error.setErrorCode(Errors.NULL_POINTER_ERROR.getErrorCode());
			error.setErrorName("Provide correct credentials to access api");

			// wrapping the error to a list of errors
			errors = new ArrayList<com.couponsworld.apiresults.Error>();
			errors.add(error);

			resultantUserType.setErrors(errors);
			resultantUserType.setLinks(GenerateLinkService.mapOfLinks.get("createUserType"));
			resultantUserType.setStatus(Status.FAILURE);
			resultantUserType.setUserType(userTypes);
			errors = null;
			return resultantUserType;
		} catch (Exception exception) {

			userTypes = new ArrayList<UserType>();
			userTypes.add(userType);

			// creating resultantUserType Object
			resultantUserType = new ResultantUserType();

			// creating the error getting
			com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
			error.setErrorCode(Errors.GENERAL_ERROR.getErrorCode());
			error.setErrorName(exception.getMessage());

			// wrapping the error to a list of errors
			errors = new ArrayList<com.couponsworld.apiresults.Error>();
			errors.add(error);

			resultantUserType.setErrors(errors);
			resultantUserType.setLinks(GenerateLinkService.mapOfLinks.get("createUserType"));
			resultantUserType.setStatus(Status.FAILURE);
			resultantUserType.setUserType(userTypes);
			errors = null;
			return resultantUserType;
		}
	}

	@PUT
	@Path("/{userTypeId}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ResultantUserType updateUserType(@PathParam("userTypeId") long userTypeId, UserType userType) {
		try {
			return UserTypeService.updateUserType(userTypeId, userType);
		} catch (NullPointerException npe) {
			userTypes = new ArrayList<UserType>();
			userTypes.add(userType);

			// creating resultantUserType Object
			resultantUserType = new ResultantUserType();

			// creating the error getting
			com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
			error.setErrorCode(Errors.NULL_POINTER_ERROR.getErrorCode());
			error.setErrorName("Provide correct credentials to access api");

			// wrapping the error to a list of errors
			errors = new ArrayList<com.couponsworld.apiresults.Error>();
			errors.add(error);

			resultantUserType.setErrors(errors);
			resultantUserType.setLinks(GenerateLinkService.mapOfLinks.get("updateUserType"));
			resultantUserType.setStatus(Status.FAILURE);
			resultantUserType.setUserType(userTypes);
			errors = null;
			return resultantUserType;
		} catch (Exception exception) {

			userTypes = new ArrayList<UserType>();
			userTypes.add(userType);

			// creating resultantUserType Object
			resultantUserType = new ResultantUserType();

			// creating the error getting
			com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
			error.setErrorCode(Errors.GENERAL_ERROR.getErrorCode());
			error.setErrorName(exception.getMessage());

			// wrapping the error to a list of errors
			errors = new ArrayList<com.couponsworld.apiresults.Error>();
			errors.add(error);

			resultantUserType.setErrors(errors);
			resultantUserType.setLinks(GenerateLinkService.mapOfLinks.get("updateUserType"));
			resultantUserType.setStatus(Status.FAILURE);
			resultantUserType.setUserType(userTypes);
			errors = null;
			return resultantUserType;
		}
	}

	@DELETE
	@Path("/{userTypeId}")
	@Produces(MediaType.APPLICATION_JSON)
	public ResultantUserType deleteUserType(@PathParam("userTypeId") long userTypeId) {
		try {
			return UserTypeService.deleteUserType(userTypeId);
		} catch (NullPointerException npe) {
			// creating resultantUserType Object
			resultantUserType = new ResultantUserType();

			// creating the error getting
			com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
			error.setErrorCode(Errors.NULL_POINTER_ERROR.getErrorCode());
			error.setErrorName("Provide correct credentials to access api");

			// wrapping the error to a list of errors
			errors = new ArrayList<com.couponsworld.apiresults.Error>();
			errors.add(error);

			resultantUserType.setErrors(errors);
			resultantUserType.setLinks((GenerateLinkService.mapOfLinks.get("deleteUserType")));
			resultantUserType.setStatus(Status.FAILURE);
			resultantUserType.setUserType(userTypes);
			errors = null;
			return resultantUserType;
		} catch (Exception exception) {

			// creating resultantUserType Object
			resultantUserType = new ResultantUserType();

			// creating the error getting
			com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
			error.setErrorCode(Errors.GENERAL_ERROR.getErrorCode());
			error.setErrorName(exception.getMessage());

			// wrapping the error to a list of errors
			errors = new ArrayList<com.couponsworld.apiresults.Error>();
			errors.add(error);

			resultantUserType.setErrors(errors);
			resultantUserType.setLinks(GenerateLinkService.mapOfLinks.get("deleteUserType"));
			resultantUserType.setStatus(Status.FAILURE);
			resultantUserType.setUserType(userTypes);
			errors = null;
			return resultantUserType;
		}

	}
}
