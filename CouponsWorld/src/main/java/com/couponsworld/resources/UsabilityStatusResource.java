package com.couponsworld.resources;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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

import com.couponsworld.apiresults.Link;
import com.couponsworld.apiresults.ResultantUsabilityStatus;
import com.couponsworld.dto.UsabilityStatus;
import com.couponsworld.enums.Errors;
import com.couponsworld.enums.Status;
import com.couponsworld.services.UsabilityStatusService;
import com.couponsworld.utilities.GenerateLinkService;

@Path("/usabilitystatus")
public class UsabilityStatusResource {

	// declaration of logger
	private static final Logger log = Logger.getLogger(UsabilityStatusResource.class.getName());

	private List<UsabilityStatus> usabilityStatuses = null;
	private List<com.couponsworld.apiresults.Error> errors = null;
	private ResultantUsabilityStatus resulatantUsabilityStatus;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public ResultantUsabilityStatus getUsabilityStatus() {
		try {
			log.info(
					"@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ UsabilityStatus Resource - Start @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
			return UsabilityStatusService.getUsabilityStatus();
		} catch (NullPointerException npe) {
			// creating resulatantUsabilityStatus Object
			resulatantUsabilityStatus = new ResultantUsabilityStatus();

			// creating the error getting
			com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
			error.setErrorCode(Errors.NULL_POINTER_ERROR.getErrorCode());
			error.setErrorName("Provide correct credentials to access api");
			log.info("Error Occured in UsabilityStatus Resource :" + error.getErrorCode() + ":" + error.getErrorName());

			// wrapping the error to a list of errors
			errors = new ArrayList<com.couponsworld.apiresults.Error>();
			errors.add(error);

			resulatantUsabilityStatus.setErrors(errors);
			resulatantUsabilityStatus.setLinks(GenerateLinkService.mapOfLinks.get("getUsabilityStatus"));
			resulatantUsabilityStatus.setStatus(Status.FAILURE);
			resulatantUsabilityStatus.setUsabilityStatus(usabilityStatuses);
			errors = null;
			return resulatantUsabilityStatus;
		} catch (Exception exception) {

			// creating resulatantUsabilityStatus Object
			resulatantUsabilityStatus = new ResultantUsabilityStatus();

			// creating the error getting
			com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
			error.setErrorCode(Errors.GENERAL_ERROR.getErrorCode());
			error.setErrorName(exception.getMessage());
			log.info("Error Occured in UsabilityStatus Resource :" + error.getErrorCode() + ":" + error.getErrorName());

			// wrapping the error to a list of errors
			errors = new ArrayList<com.couponsworld.apiresults.Error>();
			errors.add(error);

			resulatantUsabilityStatus.setErrors(errors);
			resulatantUsabilityStatus.setLinks(GenerateLinkService.mapOfLinks.get("getUsabilityStatus"));
			resulatantUsabilityStatus.setStatus(Status.FAILURE);
			resulatantUsabilityStatus.setUsabilityStatus(usabilityStatuses);
			errors = null;
			return resulatantUsabilityStatus;
		} finally {
			log.info(
					"@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ UsabilityStatus Resource - End @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		}

	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ResultantUsabilityStatus createUsabilityStatus(UsabilityStatus usabilityStatus) {
		try {
			log.info(
					"@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ UsabilityStatus Resource - Start @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
			return UsabilityStatusService.createUsabilityStatus(usabilityStatus);
		} catch (NullPointerException npe) {

			usabilityStatuses = new ArrayList<UsabilityStatus>();
			usabilityStatuses.add(usabilityStatus);

			// creating resulatantUsabilityStatus Object
			resulatantUsabilityStatus = new ResultantUsabilityStatus();

			// creating the error getting
			com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
			error.setErrorCode(Errors.NULL_POINTER_ERROR.getErrorCode());
			error.setErrorName("Provide correct credentials to access api");
			log.info("Error Occured in UsabilityStatus Resource :" + error.getErrorCode() + ":" + error.getErrorName());

			// wrapping the error to a list of errors
			errors = new ArrayList<com.couponsworld.apiresults.Error>();
			errors.add(error);

			resulatantUsabilityStatus.setErrors(errors);
			resulatantUsabilityStatus.setLinks(GenerateLinkService.mapOfLinks.get("createUsabilityStatus"));
			resulatantUsabilityStatus.setStatus(Status.FAILURE);
			resulatantUsabilityStatus.setUsabilityStatus(usabilityStatuses);
			errors = null;
			return resulatantUsabilityStatus;
		} catch (Exception exception) {

			usabilityStatuses = new ArrayList<UsabilityStatus>();
			usabilityStatuses.add(usabilityStatus);

			// creating resulatantUsabilityStatus Object
			resulatantUsabilityStatus = new ResultantUsabilityStatus();

			// creating the error getting
			com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
			error.setErrorCode(Errors.GENERAL_ERROR.getErrorCode());
			error.setErrorName(exception.getMessage());
			log.info("Error Occured in UsabilityStatus Resource :" + error.getErrorCode() + ":" + error.getErrorName());

			// wrapping the error to a list of errors
			errors = new ArrayList<com.couponsworld.apiresults.Error>();
			errors.add(error);

			resulatantUsabilityStatus.setErrors(errors);
			resulatantUsabilityStatus.setLinks(GenerateLinkService.mapOfLinks.get("createUsabilityStatus"));
			resulatantUsabilityStatus.setStatus(Status.FAILURE);
			resulatantUsabilityStatus.setUsabilityStatus(usabilityStatuses);
			errors = null;
			return resulatantUsabilityStatus;
		} finally {
			log.info(
					"@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ UsabilityStatus Resource - End @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		}
	}

	@PUT
	@Path("/{usabilityStatusId}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ResultantUsabilityStatus updateUsabilityStatus(@PathParam("usabilityStatusId") long usabilityStatusId,
			UsabilityStatus usabilityStatus) {
		try {
			log.info(
					"@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ UsabilityStatus Resource - Start @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
			return UsabilityStatusService.updateUsabilityStatus(usabilityStatusId, usabilityStatus);
		} catch (NullPointerException npe) {
			usabilityStatuses = new ArrayList<UsabilityStatus>();
			usabilityStatuses.add(usabilityStatus);

			// creating resulatantUsabilityStatus Object
			resulatantUsabilityStatus = new ResultantUsabilityStatus();

			// creating the error getting
			com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
			error.setErrorCode(Errors.NULL_POINTER_ERROR.getErrorCode());
			error.setErrorName("Provide correct credentials to access api");
			log.info("Error Occured in UsabilityStatus Resource :" + error.getErrorCode() + ":" + error.getErrorName());
			// wrapping the error to a list of errors
			errors = new ArrayList<com.couponsworld.apiresults.Error>();
			errors.add(error);

			resulatantUsabilityStatus.setErrors(errors);
			resulatantUsabilityStatus.setLinks(GenerateLinkService.mapOfLinks.get("updateUsabilityStatus"));
			resulatantUsabilityStatus.setStatus(Status.FAILURE);
			resulatantUsabilityStatus.setUsabilityStatus(usabilityStatuses);
			errors = null;
			return resulatantUsabilityStatus;
		} catch (Exception exception) {

			usabilityStatuses = new ArrayList<UsabilityStatus>();
			usabilityStatuses.add(usabilityStatus);

			// creating resulatantUsabilityStatus Object
			resulatantUsabilityStatus = new ResultantUsabilityStatus();

			// creating the error getting
			com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
			error.setErrorCode(Errors.GENERAL_ERROR.getErrorCode());
			error.setErrorName(exception.getMessage());
			log.info("Error Occured in UsabilityStatus Resource :" + error.getErrorCode() + ":" + error.getErrorName());
			// wrapping the error to a list of errors
			errors = new ArrayList<com.couponsworld.apiresults.Error>();
			errors.add(error);

			resulatantUsabilityStatus.setErrors(errors);
			resulatantUsabilityStatus.setLinks(GenerateLinkService.mapOfLinks.get("updateUsabilityStatus"));
			resulatantUsabilityStatus.setStatus(Status.FAILURE);
			resulatantUsabilityStatus.setUsabilityStatus(usabilityStatuses);
			errors = null;
			return resulatantUsabilityStatus;
		} finally {
			log.info(
					"@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ UsabilityStatus Resource - End @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		}
	}

	@DELETE
	@Path("/{usabilityStatusId}")
	@Produces(MediaType.APPLICATION_JSON)
	public ResultantUsabilityStatus deleteUsabilityStatus(@PathParam("usabilityStatusId") long usabilityStatusId) {
		try {
			log.info(
					"@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ UsabilityStatus Resource - Start @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
			return UsabilityStatusService.deleteUsabilityStatus(usabilityStatusId);
		} catch (NullPointerException npe) {
			// creating resulatantUsabilityStatus Object
			resulatantUsabilityStatus = new ResultantUsabilityStatus();

			// creating the error getting
			com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
			error.setErrorCode(Errors.NULL_POINTER_ERROR.getErrorCode());
			error.setErrorName("Provide correct credentials to access api");
			log.info("Error Occured in UsabilityStatus Resource :" + error.getErrorCode() + ":" + error.getErrorName());
			// wrapping the error to a list of errors
			errors = new ArrayList<com.couponsworld.apiresults.Error>();
			errors.add(error);

			resulatantUsabilityStatus.setErrors(errors);
			resulatantUsabilityStatus.setLinks(GenerateLinkService.mapOfLinks.get("deleteUsabilityStatus"));
			resulatantUsabilityStatus.setStatus(Status.FAILURE);
			resulatantUsabilityStatus.setUsabilityStatus(usabilityStatuses);
			errors = null;
			return resulatantUsabilityStatus;
		} catch (Exception exception) {

			// creating resulatantUsabilityStatus Object
			resulatantUsabilityStatus = new ResultantUsabilityStatus();

			// creating the error getting
			com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
			error.setErrorCode(Errors.GENERAL_ERROR.getErrorCode());
			error.setErrorName(exception.getMessage());
			log.info("Error Occured in UsabilityStatus Resource :" + error.getErrorCode() + ":" + error.getErrorName());
			// wrapping the error to a list of errors
			errors = new ArrayList<com.couponsworld.apiresults.Error>();
			errors.add(error);

			resulatantUsabilityStatus.setErrors(errors);
			resulatantUsabilityStatus.setLinks(GenerateLinkService.mapOfLinks.get("deleteUsabilityStatus"));
			resulatantUsabilityStatus.setStatus(Status.FAILURE);
			resulatantUsabilityStatus.setUsabilityStatus(usabilityStatuses);
			errors = null;
			return resulatantUsabilityStatus;
		} finally {
			log.info(
					"@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ UsabilityStatus Resource - End @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		}
	}
}
