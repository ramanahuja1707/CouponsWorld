package com.couponsworld.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

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

	// declaration of logger
	private static final Logger log = Logger.getLogger(UsabilityStatusService.class.getName());

	private static List<UsabilityStatus> usabilityStatuses = null;
	private static List<com.couponsworld.apiresults.Error> errors = null;
	private static ResultantUsabilityStatus resultantUsabilityStatus;

	public static ResultantUsabilityStatus createUsabilityStatus(UsabilityStatus usabilityStatus) {
		try {
			log.info("=========================== Usability Status Service - Start =================================");
			Object returnedObject = DatabaseService.createUsabilityStatusInDatabase(usabilityStatus);
			if (returnedObject instanceof UsabilityStatus) {
				log.info("Usability Status wrapping into resultantUsabilityStatus ..");
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
				log.info("resultantUsabilityStatus object returned successfully..");
				log.info("=============================== Usability Status Service - End ===========================");
			} else {

				// Creating Error for updating UsabilityStatus
				com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
				error.setErrorCode(Errors.GENERAL_ERROR.getErrorCode());
				error.setErrorName(((Exception) returnedObject).getMessage());
				log.info("Error Occured while creating the usability Status..:" + error.getErrorCode() + ":"
						+ error.getErrorName());
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
				log.info("resultantUsabilityStatus object returned successfully..");
				log.info("=============================== Usability Status Service - End ===========================");
			}
		} catch (Exception exception) {
			// Creating Error for updating UsabilityStatus
			com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
			error.setErrorCode(Errors.GENERAL_ERROR.getErrorCode());
			error.setErrorName(((Exception) exception).getMessage());
			log.info("Error Occured while creating the usability Status..:" + error.getErrorCode() + ":"
					+ error.getErrorName());
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
			log.info("resultantUsabilityStatus object returned successfully..");
			log.info("=============================== Usability Status Service - End ===========================");
		}
		usabilityStatuses = null;
		errors = null;
		return resultantUsabilityStatus;
	}

	public static ResultantUsabilityStatus getUsabilityStatus() {
		try {
			log.info("=========================== Usability Status Service - Start =================================");
			Object returnedObject = DatabaseService.getUsabilityStatusFromDatabase();
			if (returnedObject instanceof Exception) {

				// Creating Error for updating UsabilityStatus
				com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
				error.setErrorCode(Errors.GENERAL_ERROR.getErrorCode());
				error.setErrorName(((Exception) returnedObject).getMessage());
				log.info("Error Occured while fetching all the usability Statuses..:" + error.getErrorCode() + ":"
						+ error.getErrorName());
				// wrapping the error into errors list
				errors = new ArrayList<com.couponsworld.apiresults.Error>();
				errors.add(error);

				// Creating ResulatantUsabilityStatus object
				resultantUsabilityStatus = new ResultantUsabilityStatus();
				resultantUsabilityStatus.setUsabilityStatus(usabilityStatuses);
				resultantUsabilityStatus.setErrors(errors);
				resultantUsabilityStatus.setStatus(Status.FAILURE);
				resultantUsabilityStatus.setLinks(GenerateLinkService.mapOfLinks.get("getUsabilityStatus"));
				log.info("resultantUsabilityStatus object returned successfully..");
				log.info("=============================== Usability Status Service - End ===========================");
			} else if (returnedObject instanceof UsabilityStatusException) {
				// Creating Error for updating UsabilityStatus
				com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
				error.setErrorCode(Errors.USABILITY_STATUS_ERROR.getErrorCode());
				error.setErrorName(((CategoryException) returnedObject).getMessage());
				log.info("Error Occured while fetching all the usability Statuses..:" + error.getErrorCode() + ":"
						+ error.getErrorName());
				// wrapping the error into errors list
				errors = new ArrayList<com.couponsworld.apiresults.Error>();
				errors.add(error);

				// Creating ResulatantUsabilityStatus object
				resultantUsabilityStatus = new ResultantUsabilityStatus();
				resultantUsabilityStatus.setUsabilityStatus(usabilityStatuses);
				resultantUsabilityStatus.setErrors(errors);
				resultantUsabilityStatus.setStatus(Status.FAILURE);
				resultantUsabilityStatus.setLinks(GenerateLinkService.mapOfLinks.get("getUsabilityStatus"));
				log.info("resultantUsabilityStatus object returned successfully..");
				log.info("=============================== Usability Status Service - End ===========================");
			} else {

				if (((List<Category>) returnedObject).size() > 0) {
					log.info("Usability Status wrapping into resultantUsabilityStatus ..");
					// Creating ResulatantUsabilityStatus object
					resultantUsabilityStatus = new ResultantUsabilityStatus();
					resultantUsabilityStatus.setUsabilityStatus((List<UsabilityStatus>) returnedObject);
					resultantUsabilityStatus.setErrors(errors);
					resultantUsabilityStatus.setStatus(Status.SUCCESS);
					resultantUsabilityStatus.setLinks(GenerateLinkService.mapOfLinks.get("getUsabilityStatus"));
				} else {
					log.info("UsabilityStatus=null wrapping into resultantUsabilityStatus ..");
					// Creating ResulatantUsabilityStatus object
					resultantUsabilityStatus = new ResultantUsabilityStatus();
					resultantUsabilityStatus.setUsabilityStatus(usabilityStatuses);
					resultantUsabilityStatus.setErrors(errors);
					resultantUsabilityStatus.setStatus(Status.SUCCESS);
					resultantUsabilityStatus.setLinks(GenerateLinkService.mapOfLinks.get("getUsabilityStatus"));
				}
				log.info("resultantUsabilityStatus object returned successfully..");
				log.info("=============================== Usability Status Service - End ===========================");
			}
		} catch (Exception e) {
			// Creating Error for updating UsabilityStatus
			com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
			error.setErrorCode(Errors.GENERAL_ERROR.getErrorCode());
			error.setErrorName(((Exception) e).getMessage());
			log.info("Error Occured while fetching all the usability Statuses..:" + error.getErrorCode() + ":"
					+ error.getErrorName());
			// wrapping the error into errors list
			errors = new ArrayList<com.couponsworld.apiresults.Error>();
			errors.add(error);
			// Creating ResulatantUsabilityStatus object
			resultantUsabilityStatus = new ResultantUsabilityStatus();
			resultantUsabilityStatus.setUsabilityStatus(usabilityStatuses);
			resultantUsabilityStatus.setErrors(errors);
			resultantUsabilityStatus.setStatus(Status.FAILURE);
			resultantUsabilityStatus.setLinks(GenerateLinkService.mapOfLinks.get("getUsabilityStatus"));
			log.info("resultantUsabilityStatus object returned successfully..");
			log.info("=============================== Usability Status Service - End ===========================");
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
			log.info("=========================== Usability Status Service - Start =================================");
			Object returnedObject = DatabaseService.updateUsabilityStatusInDatabase(usabilityStatusId, usabilityStatus);
			if (returnedObject instanceof UsabilityStatus) {
				log.info("Usability Status wrapping into resultantUsabilityStatus ..");
				// wrapping the UsabilityStatus into UsabilityStatus list
				usabilityStatuses = new ArrayList<UsabilityStatus>();
				usabilityStatuses.add((UsabilityStatus) returnedObject);

				resultantUsabilityStatus = new ResultantUsabilityStatus();
				resultantUsabilityStatus.setUsabilityStatus(usabilityStatuses);
				resultantUsabilityStatus.setErrors(errors);
				resultantUsabilityStatus.setStatus(Status.SUCCESS);
				resultantUsabilityStatus.setLinks(GenerateLinkService.mapOfLinks.get("updateUsabilityStatus"));
				log.info("resultantUsabilityStatus object returned successfully..");
				log.info("=============================== Usability Status Service - End ===========================");
			} else if (returnedObject instanceof UsabilityStatusException) {
				// Creating Error for updating UsabilityStaus
				com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
				error.setErrorCode(Errors.USABILITY_STATUS_ERROR.getErrorCode());
				error.setErrorName(((UsabilityStatusException) returnedObject).getMessage());
				log.info("Error Occured while updating the usability Status..:" + error.getErrorCode() + ":"
						+ error.getErrorName());
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
				log.info("resultantUsabilityStatus object returned successfully..");
				log.info("=============================== Usability Status Service - End ===========================");
			} else {
				// Creating Error for updating UsabilityStaus
				com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
				error.setErrorCode(Errors.GENERAL_ERROR.getErrorCode());
				error.setErrorName(((UsabilityStatusException) returnedObject).getMessage());
				log.info("Error Occured while updating the usability Status..:" + error.getErrorCode() + ":"
						+ error.getErrorName());
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
				log.info("resultantUsabilityStatus object returned successfully..");
				log.info("=============================== Usability Status Service - End ===========================");
			}
		} catch (Exception e) {
			// Creating Error for updating UsabilityStaus
			com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
			error.setErrorCode(Errors.USABILITY_STATUS_ERROR.getErrorCode());
			error.setErrorName(((UsabilityStatusException) e).getMessage());
			log.info("Error Occured while updating the usability Status..:" + error.getErrorCode() + ":"
					+ error.getErrorName());
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
			log.info("resultantUsabilityStatus object returned successfully..");
			log.info("=============================== Usability Status Service - End ===========================");
			usabilityStatuses = null;
			errors = null;
		}
		usabilityStatuses = null;
		errors = null;
		return resultantUsabilityStatus;
	}

	public static ResultantUsabilityStatus deleteUsabilityStatus(long usabilityStatusId) {
		try {
			log.info("=========================== Usability Status Service - Start =================================");
			Object returnedObject = DatabaseService.deleteUsabilityStatusFromDatabase(usabilityStatusId);
			if (returnedObject instanceof UsabilityStatus) {
				log.info("Usability Status wrapping into resultantUsabilityStatus ..");
				// wrapping the UsabilityStatus into UsabilityStatus list
				usabilityStatuses = new ArrayList<UsabilityStatus>();
				usabilityStatuses.add((UsabilityStatus) returnedObject);

				resultantUsabilityStatus = new ResultantUsabilityStatus();
				resultantUsabilityStatus.setUsabilityStatus(usabilityStatuses);
				resultantUsabilityStatus.setErrors(errors);
				resultantUsabilityStatus.setStatus(Status.SUCCESS);
				resultantUsabilityStatus.setLinks(GenerateLinkService.mapOfLinks.get("deleteUsabilityStatus"));
				log.info("resultantUsabilityStatus object returned successfully..");
				log.info("=============================== Usability Status Service - End ===========================");
			} else if (returnedObject instanceof UsabilityStatusException) {
				// Creating Error for updating Usability Status
				com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
				error.setErrorCode(Errors.USABILITY_STATUS_ERROR.getErrorCode());
				error.setErrorName(((UsabilityStatusException) returnedObject).getMessage());
				log.info("Error Occured while deleting the usability Status..:" + error.getErrorCode() + ":"
						+ error.getErrorName());
				// wrapping the error into errors list
				errors = new ArrayList<com.couponsworld.apiresults.Error>();
				errors.add(error);

				resultantUsabilityStatus = new ResultantUsabilityStatus();
				resultantUsabilityStatus.setUsabilityStatus(usabilityStatuses);
				resultantUsabilityStatus.setErrors(errors);
				resultantUsabilityStatus.setStatus(Status.FAILURE);
				resultantUsabilityStatus.setLinks(GenerateLinkService.mapOfLinks.get("deleteUsabilityStatus"));
				log.info("resultantUsabilityStatus object returned successfully..");
				log.info("=============================== Usability Status Service - End ===========================");
			} else {
				// Creating Error for updating Usability Status
				com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
				error.setErrorCode(Errors.GENERAL_ERROR.getErrorCode());
				error.setErrorName(((Exception) returnedObject).getMessage());
				log.info("Error Occured while deleting the usability Status..:" + error.getErrorCode() + ":"
						+ error.getErrorName());
				// wrapping the error into errors list
				errors = new ArrayList<com.couponsworld.apiresults.Error>();
				errors.add(error);

				resultantUsabilityStatus = new ResultantUsabilityStatus();
				resultantUsabilityStatus.setUsabilityStatus(usabilityStatuses);
				resultantUsabilityStatus.setErrors(errors);
				resultantUsabilityStatus.setStatus(Status.FAILURE);
				resultantUsabilityStatus.setLinks(GenerateLinkService.mapOfLinks.get("deleteUsabilityStatus"));
				log.info("resultantUsabilityStatus object returned successfully..");
				log.info("=============================== Usability Status Service - End ===========================");
			}
		} catch (Exception e) {
			// Creating Error for updating Offer
			com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
			error.setErrorCode(Errors.GENERAL_ERROR.getErrorCode());
			error.setErrorName(((Exception) e).getMessage());
			log.info("Error Occured while deleting the usability Status..:" + error.getErrorCode() + ":"
					+ error.getErrorName());
			// wrapping the error into errors list
			errors = new ArrayList<com.couponsworld.apiresults.Error>();
			errors.add(error);

			resultantUsabilityStatus = new ResultantUsabilityStatus();
			resultantUsabilityStatus.setUsabilityStatus(usabilityStatuses);
			resultantUsabilityStatus.setErrors(errors);
			resultantUsabilityStatus.setStatus(Status.FAILURE);
			resultantUsabilityStatus.setLinks(GenerateLinkService.mapOfLinks.get("deleteUsabilityStatus"));
			log.info("resultantUsabilityStatus object returned successfully..");
			log.info("=============================== Usability Status Service - End ===========================");
			usabilityStatuses = null;
			errors = null;
		}
		usabilityStatuses = null;
		errors = null;
		return resultantUsabilityStatus;
	}
}
