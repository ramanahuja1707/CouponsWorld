package com.couponsworld.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.couponsworld.apiresults.Link;
import com.couponsworld.apiresults.ResultantUsabilityStatus;
import com.couponsworld.database.DatabaseService;
import com.couponsworld.dto.Category;
import com.couponsworld.dto.UsabilityStatus;
import com.couponsworld.enums.Errors;
import com.couponsworld.enums.Status;
import com.couponsworld.exceptions.CategoryException;
import com.couponsworld.exceptions.UsabilityStatusException;
import com.couponsworld.utilities.GenerateLinkService;

public class UsabilityStatusService {

	private static List<UsabilityStatus> usabilityStatuses = null;
	private static List<com.couponsworld.apiresults.Error> errors = null;
	private static ResultantUsabilityStatus resultantUsabilityStatus;

	public static ResultantUsabilityStatus createUsabilityStatus(UsabilityStatus usabilityStatus) {
		try {
			Object returnedObject = DatabaseService.createUsabilityStatusInDatabase(usabilityStatus);
			if (returnedObject instanceof UsabilityStatus) {
				// SubCategory successfully created and returned....
				// wrapping the usabilityStatus into usabilityStatus list
				usabilityStatuses = new ArrayList<UsabilityStatus>();
				usabilityStatuses.add((UsabilityStatus) returnedObject);

				// Creating ResultantUsabilityStatus object
				resultantUsabilityStatus = new ResultantUsabilityStatus();

				resultantUsabilityStatus.setUsabilityStatus(usabilityStatuses);
				resultantUsabilityStatus.setErrors(errors);
				resultantUsabilityStatus.setStatus(Status.SUCCESS);
				resultantUsabilityStatus.setLinks(GenerateLinkService.mapOfLinks.get("createUsabilityStatus"));
			} else {
				// Creating Error for updating UsabilityStatus
				com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
				error.setErrorCode(Errors.GENERAL_ERROR.getErrorCode());
				error.setErrorName(((Exception) returnedObject).getMessage());

				// wrapping the usabilityStatus into usabilityStatus list
				usabilityStatuses = new ArrayList<UsabilityStatus>();
				usabilityStatuses.add((UsabilityStatus) returnedObject);

				// wrapping the error into errors list
				errors = new ArrayList<com.couponsworld.apiresults.Error>();
				errors.add(error);

				// Creating ResultantUsabilityStatus object
				resultantUsabilityStatus = new ResultantUsabilityStatus();

				resultantUsabilityStatus.setUsabilityStatus(usabilityStatuses);
				resultantUsabilityStatus.setErrors(errors);
				resultantUsabilityStatus.setStatus(Status.FAILURE);
				resultantUsabilityStatus.setLinks(GenerateLinkService.mapOfLinks.get("createUsabilityStatus"));
			}
		} catch (Exception exception) {
			// Creating Error for updating UsabilityStatus
			com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
			error.setErrorCode(Errors.GENERAL_ERROR.getErrorCode());
			error.setErrorName(((Exception) exception).getMessage());

			// wrapping the usabilityStatus into usabilityStatus list
			usabilityStatuses = new ArrayList<UsabilityStatus>();
			usabilityStatuses.add((UsabilityStatus) usabilityStatus);

			// wrapping the error into errors list
			errors = new ArrayList<com.couponsworld.apiresults.Error>();
			errors.add(error);

			// Creating ResultantUsabilityStatus object
			resultantUsabilityStatus = new ResultantUsabilityStatus();

			resultantUsabilityStatus.setUsabilityStatus(usabilityStatuses);
			resultantUsabilityStatus.setErrors(errors);
			resultantUsabilityStatus.setStatus(Status.FAILURE);
			resultantUsabilityStatus.setLinks(GenerateLinkService.mapOfLinks.get("createUsabilityStatus"));
		}
		usabilityStatuses = null;
		errors = null;
		return resultantUsabilityStatus;
	}

	public static ResultantUsabilityStatus getUsabilityStatus() {
		try {
			Object returnedObject = DatabaseService.getUsabilityStatusFromDatabase();
			if (returnedObject instanceof Exception) {
				// Creating Error for updating UsabilityStatus
				com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
				error.setErrorCode(Errors.GENERAL_ERROR.getErrorCode());
				error.setErrorName(((Exception) returnedObject).getMessage());

				// wrapping the error into errors list
				errors = new ArrayList<com.couponsworld.apiresults.Error>();
				errors.add(error);

				// Creating ResulatantUsabilityStatus object
				resultantUsabilityStatus = new ResultantUsabilityStatus();
				resultantUsabilityStatus.setUsabilityStatus(usabilityStatuses);
				resultantUsabilityStatus.setErrors(errors);
				resultantUsabilityStatus.setStatus(Status.FAILURE);
				resultantUsabilityStatus.setLinks(GenerateLinkService.mapOfLinks.get("getUsabilityStatus"));
			} else if (returnedObject instanceof UsabilityStatusException) {
				// Creating Error for updating UsabilityStatus
				com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
				error.setErrorCode(Errors.USABILITY_STATUS_ERROR.getErrorCode());
				error.setErrorName(((CategoryException) returnedObject).getMessage());

				// wrapping the error into errors list
				errors = new ArrayList<com.couponsworld.apiresults.Error>();
				errors.add(error);

				// Creating ResulatantUsabilityStatus object
				resultantUsabilityStatus = new ResultantUsabilityStatus();
				resultantUsabilityStatus.setUsabilityStatus(usabilityStatuses);
				resultantUsabilityStatus.setErrors(errors);
				resultantUsabilityStatus.setStatus(Status.FAILURE);
				resultantUsabilityStatus.setLinks(GenerateLinkService.mapOfLinks.get("getUsabilityStatus"));
			} else {

				if (((List<Category>) returnedObject).size() > 0) {

					// Creating ResulatantUsabilityStatus object
					resultantUsabilityStatus = new ResultantUsabilityStatus();
					resultantUsabilityStatus.setUsabilityStatus((List<UsabilityStatus>) returnedObject);
					resultantUsabilityStatus.setErrors(errors);
					resultantUsabilityStatus.setStatus(Status.SUCCESS);
					resultantUsabilityStatus.setLinks(GenerateLinkService.mapOfLinks.get("getUsabilityStatus"));
				} else {
					// Creating ResulatantUsabilityStatus object
					resultantUsabilityStatus = new ResultantUsabilityStatus();
					resultantUsabilityStatus.setUsabilityStatus(usabilityStatuses);
					resultantUsabilityStatus.setErrors(errors);
					resultantUsabilityStatus.setStatus(Status.SUCCESS);
					resultantUsabilityStatus.setLinks(GenerateLinkService.mapOfLinks.get("getUsabilityStatus"));
				}
			}
		} catch (Exception e) {
			// Creating Error for updating UsabilityStatus
			com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
			error.setErrorCode(Errors.GENERAL_ERROR.getErrorCode());
			error.setErrorName(((Exception) e).getMessage());

			// wrapping the error into errors list
			errors = new ArrayList<com.couponsworld.apiresults.Error>();
			errors.add(error);
			// Creating ResulatantUsabilityStatus object
			resultantUsabilityStatus = new ResultantUsabilityStatus();
			resultantUsabilityStatus.setUsabilityStatus(usabilityStatuses);
			resultantUsabilityStatus.setErrors(errors);
			resultantUsabilityStatus.setStatus(Status.FAILURE);
			resultantUsabilityStatus.setLinks(GenerateLinkService.mapOfLinks.get("getUsabilityStatus"));
			usabilityStatuses = null;
			errors = null;
		}
		usabilityStatuses = null;
		errors = null;
		return resultantUsabilityStatus;
	}

	public static ResultantUsabilityStatus updateUsabilityStatus(long usabilityStatusId,
			UsabilityStatus usabilityStatus) {
		try {
			Object returnedObject = DatabaseService.updateUsabilityStatusInDatabase(usabilityStatusId, usabilityStatus);
			if (returnedObject instanceof UsabilityStatus) {

				// wrapping the UsabilityStatus into UsabilityStatus list
				usabilityStatuses = new ArrayList<UsabilityStatus>();
				usabilityStatuses.add((UsabilityStatus) returnedObject);

				resultantUsabilityStatus = new ResultantUsabilityStatus();
				resultantUsabilityStatus.setUsabilityStatus(usabilityStatuses);
				resultantUsabilityStatus.setErrors(errors);
				resultantUsabilityStatus.setStatus(Status.SUCCESS);
				resultantUsabilityStatus.setLinks(GenerateLinkService.mapOfLinks.get("updateUsabilityStatus"));
			} else if (returnedObject instanceof UsabilityStatusException) {
				// Creating Error for updating UsabilityStaus
				com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
				error.setErrorCode(Errors.USABILITY_STATUS_ERROR.getErrorCode());
				error.setErrorName(((UsabilityStatusException) returnedObject).getMessage());

				// wrapping the error into errors list
				errors = new ArrayList<com.couponsworld.apiresults.Error>();
				errors.add(error);

				// wrapping the UsabilityStaus into UsabilityStaus list
				usabilityStatuses = new ArrayList<UsabilityStatus>();
				usabilityStatuses.add(usabilityStatus);

				resultantUsabilityStatus = new ResultantUsabilityStatus();
				resultantUsabilityStatus.setUsabilityStatus(usabilityStatuses);
				resultantUsabilityStatus.setErrors(errors);
				resultantUsabilityStatus.setStatus(Status.FAILURE);
				resultantUsabilityStatus.setLinks(GenerateLinkService.mapOfLinks.get("updateUsabilityStatus"));
			} else {
				// Creating Error for updating UsabilityStaus
				com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
				error.setErrorCode(Errors.GENERAL_ERROR.getErrorCode());
				error.setErrorName(((UsabilityStatusException) returnedObject).getMessage());

				// wrapping the error into errors list
				errors = new ArrayList<com.couponsworld.apiresults.Error>();
				errors.add(error);

				// wrapping the UsabilityStaus into UsabilityStaus list
				usabilityStatuses = new ArrayList<UsabilityStatus>();
				usabilityStatuses.add(usabilityStatus);

				resultantUsabilityStatus = new ResultantUsabilityStatus();
				resultantUsabilityStatus.setUsabilityStatus(usabilityStatuses);
				resultantUsabilityStatus.setErrors(errors);
				resultantUsabilityStatus.setStatus(Status.FAILURE);
				resultantUsabilityStatus.setLinks(GenerateLinkService.mapOfLinks.get("updateUsabilityStatus"));
			}
		} catch (Exception e) {
			// Creating Error for updating UsabilityStaus
			com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
			error.setErrorCode(Errors.USABILITY_STATUS_ERROR.getErrorCode());
			error.setErrorName(((UsabilityStatusException) e).getMessage());

			// wrapping the error into errors list
			errors = new ArrayList<com.couponsworld.apiresults.Error>();
			errors.add(error);

			// wrapping the UsabilityStaus into UsabilityStaus list
			usabilityStatuses = new ArrayList<UsabilityStatus>();
			usabilityStatuses.add(usabilityStatus);

			resultantUsabilityStatus = new ResultantUsabilityStatus();
			resultantUsabilityStatus.setUsabilityStatus(usabilityStatuses);
			resultantUsabilityStatus.setErrors(errors);
			resultantUsabilityStatus.setStatus(Status.FAILURE);
			resultantUsabilityStatus.setLinks(GenerateLinkService.mapOfLinks.get("updateUsabilityStatus"));
			usabilityStatuses = null;
			errors = null;
		}
		usabilityStatuses = null;
		errors = null;
		return resultantUsabilityStatus;
	}

	public static ResultantUsabilityStatus deleteUsabilityStatus(long usabilityStatusId) {
		try {
			Object returnedObject = DatabaseService.deleteUsabilityStatusFromDatabase(usabilityStatusId);
			if (returnedObject instanceof UsabilityStatus) {
				// wrapping the UsabilityStatus into UsabilityStatus list
				usabilityStatuses = new ArrayList<UsabilityStatus>();
				usabilityStatuses.add((UsabilityStatus) returnedObject);

				resultantUsabilityStatus = new ResultantUsabilityStatus();
				resultantUsabilityStatus.setUsabilityStatus(usabilityStatuses);
				resultantUsabilityStatus.setErrors(errors);
				resultantUsabilityStatus.setStatus(Status.SUCCESS);
				resultantUsabilityStatus.setLinks(GenerateLinkService.mapOfLinks.get("deleteUsabilityStatus"));
			} else if (returnedObject instanceof UsabilityStatusException) {
				// Creating Error for updating Usability Status
				com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
				error.setErrorCode(Errors.USABILITY_STATUS_ERROR.getErrorCode());
				error.setErrorName(((UsabilityStatusException) returnedObject).getMessage());

				// wrapping the error into errors list
				errors = new ArrayList<com.couponsworld.apiresults.Error>();
				errors.add(error);

				resultantUsabilityStatus = new ResultantUsabilityStatus();
				resultantUsabilityStatus.setUsabilityStatus(usabilityStatuses);
				resultantUsabilityStatus.setErrors(errors);
				resultantUsabilityStatus.setStatus(Status.FAILURE);
				resultantUsabilityStatus.setLinks(GenerateLinkService.mapOfLinks.get("deleteUsabilityStatus"));
			} else {
				// Creating Error for updating Usability Status
				com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
				error.setErrorCode(Errors.GENERAL_ERROR.getErrorCode());
				error.setErrorName(((Exception) returnedObject).getMessage());

				// wrapping the error into errors list
				errors = new ArrayList<com.couponsworld.apiresults.Error>();
				errors.add(error);

				resultantUsabilityStatus = new ResultantUsabilityStatus();
				resultantUsabilityStatus.setUsabilityStatus(usabilityStatuses);
				resultantUsabilityStatus.setErrors(errors);
				resultantUsabilityStatus.setStatus(Status.FAILURE);
				resultantUsabilityStatus.setLinks(GenerateLinkService.mapOfLinks.get("deleteUsabilityStatus"));
			}
		} catch (Exception e) {
			// Creating Error for updating Offer
			com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
			error.setErrorCode(Errors.GENERAL_ERROR.getErrorCode());
			error.setErrorName(((Exception) e).getMessage());

			// wrapping the error into errors list
			errors = new ArrayList<com.couponsworld.apiresults.Error>();
			errors.add(error);

			resultantUsabilityStatus = new ResultantUsabilityStatus();
			resultantUsabilityStatus.setUsabilityStatus(usabilityStatuses);
			resultantUsabilityStatus.setErrors(errors);
			resultantUsabilityStatus.setStatus(Status.FAILURE);
			resultantUsabilityStatus.setLinks(GenerateLinkService.mapOfLinks.get("deleteUsabilityStatus"));
			usabilityStatuses = null;
			errors = null;
		}
		usabilityStatuses = null;
		errors = null;
		return resultantUsabilityStatus;
	}
}
