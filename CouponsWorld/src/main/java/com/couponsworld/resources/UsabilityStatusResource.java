package com.couponsworld.resources;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.couponsworld.apiresults.ResulatantUsabilityStatus;
import com.couponsworld.dto.UsabilityStatus;
import com.couponsworld.enums.Errors;
import com.couponsworld.enums.Status;
import com.couponsworld.services.UsabilityStatusService;
import com.couponsworld.utilities.GenerateLinkService;

@Path("/usabilitystatus")
public class UsabilityStatusResource {

	private List<UsabilityStatus> usabilityStatuses = null;
	private List<com.couponsworld.apiresults.Error> errors = null;
	private ResulatantUsabilityStatus resulatantUsabilityStatus;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public ResulatantUsabilityStatus getUsabilityStatus() {
		try {
			return UsabilityStatusService.getUsabilityStatus();
		} catch (NullPointerException npe) {
			// creating resulatantUsabilityStatus Object
			resulatantUsabilityStatus = new ResulatantUsabilityStatus();

			// creating the error getting
			com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
			error.setErrorCode(Errors.NULL_POINTER_ERROR.getErrorCode());
			error.setErrorName("Provide correct credentials to access api");

			// wrapping the error to a list of errors
			errors = new ArrayList<com.couponsworld.apiresults.Error>();
			errors.add(error);

			resulatantUsabilityStatus.setErrors(errors);
			resulatantUsabilityStatus.setLinks(GenerateLinkService.generateOfferLink("getOffers"));
			resulatantUsabilityStatus.setStatus(Status.FAILURE);
			resulatantUsabilityStatus.setUsabilityStatus(usabilityStatuses);
			errors = null;
			return resulatantUsabilityStatus;
		} catch (Exception exception) {

			// creating resulatantUsabilityStatus Object
			resulatantUsabilityStatus = new ResulatantUsabilityStatus();

			// creating the error getting
			com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
			error.setErrorCode(Errors.GENERAL_ERROR.getErrorCode());
			error.setErrorName(exception.getMessage());

			// wrapping the error to a list of errors
			errors = new ArrayList<com.couponsworld.apiresults.Error>();
			errors.add(error);

			resulatantUsabilityStatus.setErrors(errors);
			resulatantUsabilityStatus.setLinks(GenerateLinkService.generateOfferLink("getOffers"));
			resulatantUsabilityStatus.setStatus(Status.FAILURE);
			resulatantUsabilityStatus.setUsabilityStatus(usabilityStatuses);
			errors = null;
			return resulatantUsabilityStatus;
		}

	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ResulatantUsabilityStatus createUsabilityStatus(UsabilityStatus usabilityStatus) {
		try {
			return UsabilityStatusService.createUsabilityStatus(usabilityStatus);
		} catch (NullPointerException npe) {

			usabilityStatuses = new ArrayList<UsabilityStatus>();
			usabilityStatuses.add(usabilityStatus);

			// creating resulatantUsabilityStatus Object
			resulatantUsabilityStatus = new ResulatantUsabilityStatus();

			// creating the error getting
			com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
			error.setErrorCode(Errors.NULL_POINTER_ERROR.getErrorCode());
			error.setErrorName("Provide correct credentials to access api");

			// wrapping the error to a list of errors
			errors = new ArrayList<com.couponsworld.apiresults.Error>();
			errors.add(error);

			resulatantUsabilityStatus.setErrors(errors);
			resulatantUsabilityStatus.setLinks(GenerateLinkService.generateOfferLink("getOffers"));
			resulatantUsabilityStatus.setStatus(Status.FAILURE);
			resulatantUsabilityStatus.setUsabilityStatus(usabilityStatuses);
			errors = null;
			return resulatantUsabilityStatus;
		} catch (Exception exception) {

			usabilityStatuses = new ArrayList<UsabilityStatus>();
			usabilityStatuses.add(usabilityStatus);

			// creating resulatantUsabilityStatus Object
			resulatantUsabilityStatus = new ResulatantUsabilityStatus();

			// creating the error getting
			com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
			error.setErrorCode(Errors.GENERAL_ERROR.getErrorCode());
			error.setErrorName(exception.getMessage());

			// wrapping the error to a list of errors
			errors = new ArrayList<com.couponsworld.apiresults.Error>();
			errors.add(error);

			resulatantUsabilityStatus.setErrors(errors);
			resulatantUsabilityStatus.setLinks(GenerateLinkService.generateOfferLink("getOffers"));
			resulatantUsabilityStatus.setStatus(Status.FAILURE);
			resulatantUsabilityStatus.setUsabilityStatus(usabilityStatuses);
			errors = null;
			return resulatantUsabilityStatus;
		}
	}

	@PUT
	@Path("/{usabilityStatusId}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ResulatantUsabilityStatus updateUsabilityStatus(@PathParam("usabilityStatusId") long usabilityStatusId,
			UsabilityStatus usabilityStatus) {
		try {
			return UsabilityStatusService.updateUsabilityStatus(usabilityStatusId, usabilityStatus);
		} catch (NullPointerException npe) {
			usabilityStatuses = new ArrayList<UsabilityStatus>();
			usabilityStatuses.add(usabilityStatus);

			// creating resulatantUsabilityStatus Object
			resulatantUsabilityStatus = new ResulatantUsabilityStatus();

			// creating the error getting
			com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
			error.setErrorCode(Errors.NULL_POINTER_ERROR.getErrorCode());
			error.setErrorName("Provide correct credentials to access api");

			// wrapping the error to a list of errors
			errors = new ArrayList<com.couponsworld.apiresults.Error>();
			errors.add(error);

			resulatantUsabilityStatus.setErrors(errors);
			resulatantUsabilityStatus.setLinks(GenerateLinkService.generateOfferLink("getOffers"));
			resulatantUsabilityStatus.setStatus(Status.FAILURE);
			resulatantUsabilityStatus.setUsabilityStatus(usabilityStatuses);
			errors = null;
			return resulatantUsabilityStatus;
		} catch (Exception exception) {

			usabilityStatuses = new ArrayList<UsabilityStatus>();
			usabilityStatuses.add(usabilityStatus);

			// creating resulatantUsabilityStatus Object
			resulatantUsabilityStatus = new ResulatantUsabilityStatus();

			// creating the error getting
			com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
			error.setErrorCode(Errors.GENERAL_ERROR.getErrorCode());
			error.setErrorName(exception.getMessage());

			// wrapping the error to a list of errors
			errors = new ArrayList<com.couponsworld.apiresults.Error>();
			errors.add(error);

			resulatantUsabilityStatus.setErrors(errors);
			resulatantUsabilityStatus.setLinks(GenerateLinkService.generateOfferLink("getOffers"));
			resulatantUsabilityStatus.setStatus(Status.FAILURE);
			resulatantUsabilityStatus.setUsabilityStatus(usabilityStatuses);
			errors = null;
			return resulatantUsabilityStatus;
		}
	}

	@DELETE
	@Path("/{usabilityStatusId}")
	@Produces(MediaType.APPLICATION_JSON)
	public ResulatantUsabilityStatus deleteUsabilityStatus(@PathParam("usabilityStatusId") long usabilityStatusId) {
		try {
			return UsabilityStatusService.deleteUsabilityStatus(usabilityStatusId);
		} catch (NullPointerException npe) {
			// creating resulatantUsabilityStatus Object
			resulatantUsabilityStatus = new ResulatantUsabilityStatus();

			// creating the error getting
			com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
			error.setErrorCode(Errors.NULL_POINTER_ERROR.getErrorCode());
			error.setErrorName("Provide correct credentials to access api");

			// wrapping the error to a list of errors
			errors = new ArrayList<com.couponsworld.apiresults.Error>();
			errors.add(error);

			resulatantUsabilityStatus.setErrors(errors);
			resulatantUsabilityStatus.setLinks(GenerateLinkService.generateOfferLink("getOffers"));
			resulatantUsabilityStatus.setStatus(Status.FAILURE);
			resulatantUsabilityStatus.setUsabilityStatus(usabilityStatuses);
			errors = null;
			return resulatantUsabilityStatus;
		} catch (Exception exception) {

			// creating resulatantUsabilityStatus Object
			resulatantUsabilityStatus = new ResulatantUsabilityStatus();

			// creating the error getting
			com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
			error.setErrorCode(Errors.GENERAL_ERROR.getErrorCode());
			error.setErrorName(exception.getMessage());

			// wrapping the error to a list of errors
			errors = new ArrayList<com.couponsworld.apiresults.Error>();
			errors.add(error);

			resulatantUsabilityStatus.setErrors(errors);
			resulatantUsabilityStatus.setLinks(GenerateLinkService.generateOfferLink("getOffers"));
			resulatantUsabilityStatus.setStatus(Status.FAILURE);
			resulatantUsabilityStatus.setUsabilityStatus(usabilityStatuses);
			errors = null;
			return resulatantUsabilityStatus;
		}
	}
}
