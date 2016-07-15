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
			log.info(
					"$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$ GET-UserTypeResource-START $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
			log.info("User Type GET Resource fetched Successfully..");
			log.info("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$ GET-UserTypeResource-END $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
			return UserTypeService.getUserTypes();

		} catch (NullPointerException npe) {
			log.info("NullPointerException in the get method");
			// creating resultantUserType Object
			resultantUserType = new ResultantUserType();
			// creating the error getting
			log.info("Creating Error getting");
			com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
			error.setErrorCode(Errors.NULL_POINTER_ERROR.getErrorCode());
			error.setErrorName("Provide correct credentials to access api");
			log.info("Generating Error list");
			// wrapping the error to a list of errors
			errors = new ArrayList<com.couponsworld.apiresults.Error>();
			errors.add(error);
			log.info("Error List Creation Success");
			resultantUserType.setErrors(errors);
			resultantUserType.setLinks(GenerateLinkService.mapOfLinks.get("getUserTypes"));
			resultantUserType.setStatus(Status.FAILURE);
			resultantUserType.setUserType(userTypes);
			log.info("errors :" + errors.toString());
			log.info("status : " + Status.FAILURE);
			log.info("UserType:" + resultantUserType.getUserType());
			errors = null;
			log.info("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$ GET-UserTypeResource-END $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
			return resultantUserType;
		} catch (Exception exception) {
			log.info("Exception Caught in Get Method UserTypeResource" + exception.getMessage());
			// creating resultantUserType Object
			resultantUserType = new ResultantUserType();
			log.info("Creating Error getting");
			// creating the error getting
			com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
			error.setErrorCode(Errors.GENERAL_ERROR.getErrorCode());
			error.setErrorName(exception.getMessage());
			log.info("Creating Error list");
			// wrapping the error to a list of errors
			errors = new ArrayList<com.couponsworld.apiresults.Error>();
			errors.add(error);
			log.info("Error List Creation Success");
			resultantUserType.setErrors(errors);
			resultantUserType.setLinks(GenerateLinkService.mapOfLinks.get("getUserTypes"));
			resultantUserType.setStatus(Status.FAILURE);
			resultantUserType.setUserType(userTypes);
			log.info("errors :" + errors.toString());
			log.info("status : " + Status.FAILURE);
			log.info("UserType:" + resultantUserType.getUserType());
			errors = null;
			log.info(
					"$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$ GET-UserTypeResource-END $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");

			return resultantUserType;
		}

	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ResultantUserType createUserType(UserType userType) {
		try {
			log.info(
					"$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$ POST-UserTypeResource-START $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
			log.info("User Type POST Resource executed Successfully..");
			log.info("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$ POST-UserTypeResource-END $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
			return UserTypeService.createUserType(userType);
		} catch (NullPointerException npe) {
			log.info("Null pointer Exception in  the post method ");
			userTypes = new ArrayList<UserType>();
			userTypes.add(userType);

			// creating resultantUserType Object
			resultantUserType = new ResultantUserType();
			log.info("Creating Error getting");
			// creating the error getting
			com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
			error.setErrorCode(Errors.NULL_POINTER_ERROR.getErrorCode());
			error.setErrorName("Provide correct credentials to access api");
			log.info("Creating Error list");
			// wrapping the error to a list of errors
			errors = new ArrayList<com.couponsworld.apiresults.Error>();
			errors.add(error);
			log.info("Error List Creation Success");
			resultantUserType.setErrors(errors);
			resultantUserType.setLinks(GenerateLinkService.mapOfLinks.get("createUserType"));
			resultantUserType.setStatus(Status.FAILURE);
			resultantUserType.setUserType(userTypes);
			log.info("errors :" + errors.toString());
			log.info("status : " + Status.FAILURE);
			errors = null;
			log.info("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$ POST-UserTypeResource-END $$$$$$$$$$$$$$$$$$$$$$$$$$$$$");

			return resultantUserType;
		} catch (Exception exception) {
			log.info("Exception Caught in Post Method UserTypeResource" + exception.getMessage());
			userTypes = new ArrayList<UserType>();
			userTypes.add(userType);

			// creating resultantUserType Object
			resultantUserType = new ResultantUserType();
			log.info("Creating Error getting");
			// creating the error getting
			com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
			error.setErrorCode(Errors.GENERAL_ERROR.getErrorCode());
			error.setErrorName(exception.getMessage());
			log.info("Creating Error list");
			// wrapping the error to a list of errors
			errors = new ArrayList<com.couponsworld.apiresults.Error>();
			errors.add(error);
			log.info("Error List Creation Success");
			resultantUserType.setErrors(errors);
			resultantUserType.setLinks(GenerateLinkService.mapOfLinks.get("createUserType"));
			resultantUserType.setStatus(Status.FAILURE);
			resultantUserType.setUserType(userTypes);
			log.info("errors :" + errors.toString());
			log.info("status : " + Status.FAILURE);
			errors = null;
			log.info(
					"$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$ POST-UserTypeResource-END $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");

			return resultantUserType;
		}
	}

	@PUT
	@Path("/{userTypeId}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ResultantUserType updateUserType(@PathParam("userTypeId") long userTypeId, UserType userType) {
		try {
			log.info(
					"$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$ PUT-UserTypeResource-START $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
			log.info("User Type PUT Resource executed Successfully..");
			log.info("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$ PUT-UserTypeResource-END $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
			log.info("Entered into the PUT method of rest api");
			return UserTypeService.updateUserType(userTypeId, userType);
		} catch (NullPointerException npe) {
			log.info("Null pointer Exception in  the put method ");
			userTypes = new ArrayList<UserType>();
			userTypes.add(userType);

			// creating resultantUserType Object
			resultantUserType = new ResultantUserType();
			log.info("Creating Error getting");
			// creating the error getting
			com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
			error.setErrorCode(Errors.NULL_POINTER_ERROR.getErrorCode());
			error.setErrorName("Provide correct credentials to access api");
			log.info("Creating Error list");
			// wrapping the error to a list of errors
			errors = new ArrayList<com.couponsworld.apiresults.Error>();
			errors.add(error);
			log.info("Error List Creation Success");
			resultantUserType.setErrors(errors);
			resultantUserType.setLinks(GenerateLinkService.mapOfLinks.get("updateUserType"));
			resultantUserType.setStatus(Status.FAILURE);
			resultantUserType.setUserType(userTypes);
			log.info("errors :" + errors.toString());
			log.info("status : " + Status.FAILURE);
			errors = null;
			log.info(
					"$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$ PUT-UserTypeResource-END $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");

			return resultantUserType;
		} catch (Exception exception) {
			log.info("Exception Caught in Put Method UserTypeResource" + exception.getMessage());
			userTypes = new ArrayList<UserType>();
			userTypes.add(userType);

			// creating resultantUserType Object
			resultantUserType = new ResultantUserType();
			log.info("Creating Error getting");
			// creating the error getting
			com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
			error.setErrorCode(Errors.GENERAL_ERROR.getErrorCode());
			error.setErrorName(exception.getMessage());
			log.info("Creating Error list");
			// wrapping the error to a list of errors
			errors = new ArrayList<com.couponsworld.apiresults.Error>();
			errors.add(error);
			log.info("Error List Creation Success");
			resultantUserType.setErrors(errors);
			resultantUserType.setLinks(GenerateLinkService.mapOfLinks.get("updateUserType"));
			resultantUserType.setStatus(Status.FAILURE);
			resultantUserType.setUserType(userTypes);
			errors = null;
			log.info("errors :" + errors.toString());
			log.info("status : " + Status.FAILURE);
			errors = null;
			log.info("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$ PUT-UserTypeResource-END $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");

			return resultantUserType;
		}
	}

	@DELETE
	@Path("/{userTypeId}")
	@Produces(MediaType.APPLICATION_JSON)
	public ResultantUserType deleteUserType(@PathParam("userTypeId") long userTypeId) {
		try {
			log.info(
					"$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$ DELETE-UserTypeResource-START $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
			log.info("User Type DELETE Resource executed Successfully..");
			log.info(
					"$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$ DELETE-UserTypeResource-END $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
			return UserTypeService.deleteUserType(userTypeId);
		} catch (NullPointerException npe) {
			log.info("Null pointer Exception in  the DELETE method ");
			// creating resultantUserType Object
			resultantUserType = new ResultantUserType();
			log.info("Creating Error getting");
			// creating the error getting
			com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
			error.setErrorCode(Errors.NULL_POINTER_ERROR.getErrorCode());
			error.setErrorName("Provide correct credentials to access api");
			log.info("Creating Error list");
			// wrapping the error to a list of errors
			errors = new ArrayList<com.couponsworld.apiresults.Error>();
			errors.add(error);
			log.info("Error List Creation Success");
			resultantUserType.setErrors(errors);
			resultantUserType.setLinks((GenerateLinkService.mapOfLinks.get("deleteUserType")));
			resultantUserType.setStatus(Status.FAILURE);
			resultantUserType.setUserType(userTypes);
			log.info("errors :" + errors.toString());
			log.info("status : " + Status.FAILURE);
			errors = null;
			log.info(
					"$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$ DELETE-UserTypeResource-END $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
			return resultantUserType;
		} catch (Exception exception) {
			log.info("Exception Caught in DELETE Method UserTypeResource" + exception.getMessage());
			// creating resultantUserType Object
			resultantUserType = new ResultantUserType();

			// creating the error getting
			com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
			error.setErrorCode(Errors.GENERAL_ERROR.getErrorCode());
			error.setErrorName(exception.getMessage());
			log.info("Creating Error list");
			// wrapping the error to a list of errors
			errors = new ArrayList<com.couponsworld.apiresults.Error>();
			errors.add(error);
			log.info("Error List Creation Success");
			resultantUserType.setErrors(errors);
			resultantUserType.setLinks(GenerateLinkService.mapOfLinks.get("deleteUserType"));
			resultantUserType.setStatus(Status.FAILURE);
			resultantUserType.setUserType(userTypes);
			log.info("errors :" + errors.toString());
			log.info("status : " + Status.FAILURE);
			errors = null;
			log.info(
					"$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$ DELETE-UserTypeResource-END $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");

			return resultantUserType;
		}

	}
}