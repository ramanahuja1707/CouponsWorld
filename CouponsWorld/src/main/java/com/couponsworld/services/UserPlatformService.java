package com.couponsworld.services;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.couponsworld.apiresults.ResultantUserPlatform;
import com.couponsworld.database.DatabaseService;
import com.couponsworld.dto.Category;
import com.couponsworld.dto.UserPlatform;
import com.couponsworld.enums.Errors;
import com.couponsworld.enums.Status;
import com.couponsworld.exceptions.UsabilityStatusException;
import com.couponsworld.exceptions.UserPlatformException;
import com.couponsworld.utilities.GenerateLinkService;

public class UserPlatformService {

	// declaration of logger
	private static final Logger log = Logger.getLogger(UserPlatformService.class.getName());

	private static List<UserPlatform> userPlatforms = null;
	private static List<com.couponsworld.apiresults.Error> errors = null;
	private static ResultantUserPlatform resultantUserPlatform;

	public static ResultantUserPlatform createUserPlatform(UserPlatform userPlatform) {
		try {
			log.info("=========================== User Platform Service - Start =================================");
			Object returnedObject = DatabaseService.createUserPlatformInDatabase(userPlatform);
			if (returnedObject instanceof UserPlatform) {
				log.info("User Platform wrapping into ResultantUserPlatform ..");
				// SubCategory successfully created and returned....
				// wrapping the UserPlatform into UserPlatform list
				userPlatforms = new ArrayList<UserPlatform>();
				userPlatforms.add((UserPlatform) returnedObject);

				// Creating ResultantUserPlatform object
				resultantUserPlatform = new ResultantUserPlatform();

				resultantUserPlatform.setUserPlatform(userPlatforms);
				resultantUserPlatform.setErrors(errors);
				resultantUserPlatform.setStatus(Status.SUCCESS);
				resultantUserPlatform.setLinks(GenerateLinkService.mapOfLinks.get("createUserPlatform"));
				log.info("ResultantUserPlatform object returned successfully..");
				log.info("=============================== User Platform Service - End ===========================");
			} else {
				// Creating Error for updating UserPlatform
				com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
				error.setErrorCode(Errors.GENERAL_ERROR.getErrorCode());
				error.setErrorName(((Exception) returnedObject).getMessage());
				log.info("Error Occured while creating the User Platform..:" + error.getErrorCode() + ":"
						+ error.getErrorName());
				// wrapping the UserPlatform into UserPlatform list
				userPlatforms = new ArrayList<UserPlatform>();
				userPlatforms.add((UserPlatform) returnedObject);

				// wrapping the error into errors list
				errors = new ArrayList<com.couponsworld.apiresults.Error>();
				errors.add(error);

				// Creating ResultantUserPlatform object
				resultantUserPlatform = new ResultantUserPlatform();

				resultantUserPlatform.setUserPlatform(userPlatforms);
				resultantUserPlatform.setErrors(errors);
				resultantUserPlatform.setStatus(Status.FAILURE);
				resultantUserPlatform.setLinks(GenerateLinkService.mapOfLinks.get("createUserPlatform"));
				log.info("ResultantUserPlatform object returned successfully..");
				log.info("=============================== User Platform Service - End ===========================");
			}
		} catch (Exception exception) {
			// Creating Error for updating UserPlatform
			com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
			error.setErrorCode(Errors.GENERAL_ERROR.getErrorCode());
			error.setErrorName(((Exception) exception).getMessage());
			log.info("Error Occured while creating the User Platform..:" + error.getErrorCode() + ":"
					+ error.getErrorName());
			// wrapping the UserPlatform into UserPlatform list
			userPlatforms = new ArrayList<UserPlatform>();
			userPlatforms.add((UserPlatform) userPlatform);

			// wrapping the error into errors list
			errors = new ArrayList<com.couponsworld.apiresults.Error>();
			errors.add(error);

			// Creating ResultantUserPlatform object
			resultantUserPlatform = new ResultantUserPlatform();

			resultantUserPlatform.setUserPlatform(userPlatforms);
			resultantUserPlatform.setErrors(errors);
			resultantUserPlatform.setStatus(Status.FAILURE);
			resultantUserPlatform.setLinks(GenerateLinkService.mapOfLinks.get("createUserPlatform"));
			log.info("ResultantUserPlatform object returned successfully..");
			log.info("=============================== User Platform Service - End ===========================");
		}
		userPlatforms = null;
		errors = null;
		return resultantUserPlatform;
	}

	public static ResultantUserPlatform getUserPlatforms() {
		try {
			log.info("=========================== User Platform Service - Start =================================");
			Object returnedObject = DatabaseService.getUserPlatformFromDatabase();
			if (returnedObject instanceof Exception) {

				// Creating Error for updating UserPlatform
				com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
				error.setErrorCode(Errors.GENERAL_ERROR.getErrorCode());
				error.setErrorName(((Exception) returnedObject).getMessage());
				log.info("Error Occured while fetching all the User Platforms..:" + error.getErrorCode() + ":"
						+ error.getErrorName());
				// wrapping the error into errors list
				errors = new ArrayList<com.couponsworld.apiresults.Error>();
				errors.add(error);

				// Creating ResultantUserPlatform object
				resultantUserPlatform = new ResultantUserPlatform();

				resultantUserPlatform.setUserPlatform(userPlatforms);
				resultantUserPlatform.setErrors(errors);
				resultantUserPlatform.setStatus(Status.FAILURE);
				resultantUserPlatform.setLinks(GenerateLinkService.mapOfLinks.get("getUserPlatforms"));
				log.info("ResultantUserPlatform object returned successfully..");
				log.info("=============================== User Platform Service - End ===========================");
			} else if (returnedObject instanceof UsabilityStatusException) {
				// Creating Error for updating UsabilityStatus
				com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
				error.setErrorCode(Errors.USER_PLATFORM_ERROR.getErrorCode());
				error.setErrorName(((UserPlatformException) returnedObject).getMessage());
				log.info("Error Occured while fetching all the User Platforms..:" + error.getErrorCode() + ":"
						+ error.getErrorName());
				// wrapping the error into errors list
				errors = new ArrayList<com.couponsworld.apiresults.Error>();
				errors.add(error);

				// Creating ResultantUserPlatform object
				resultantUserPlatform = new ResultantUserPlatform();

				resultantUserPlatform.setUserPlatform(userPlatforms);
				resultantUserPlatform.setErrors(errors);
				resultantUserPlatform.setStatus(Status.FAILURE);
				resultantUserPlatform.setLinks(GenerateLinkService.mapOfLinks.get("getUserPlatforms"));
				log.info("ResultantUserPlatform object returned successfully..");
				log.info("=============================== User Platform Service - End ===========================");
			} else {

				if (((List<Category>) returnedObject).size() > 0) {
					log.info("User Platform wrapping into ResultantUserPlatform ..");
					// Creating ResultantUserPlatform object
					resultantUserPlatform = new ResultantUserPlatform();

					resultantUserPlatform.setUserPlatform((List<UserPlatform>) returnedObject);
					resultantUserPlatform.setErrors(errors);
					resultantUserPlatform.setStatus(Status.SUCCESS);
					resultantUserPlatform.setLinks(GenerateLinkService.mapOfLinks.get("getUserPlatforms"));
				} else {
					log.info("UserPlatform=null wrapping into ResultantUserPlatform ..");
					// Creating ResultantUserPlatform object
					resultantUserPlatform = new ResultantUserPlatform();

					resultantUserPlatform.setUserPlatform(userPlatforms);
					resultantUserPlatform.setErrors(errors);
					resultantUserPlatform.setStatus(Status.SUCCESS);
					resultantUserPlatform.setLinks(GenerateLinkService.mapOfLinks.get("getUserPlatforms"));
				}
				log.info("ResultantUserPlatform object returned successfully..");
				log.info("=============================== User Platform Service - End ===========================");
			}
		} catch (Exception e) {
			// Creating Error for updating UsabilityStatus
			com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
			error.setErrorCode(Errors.GENERAL_ERROR.getErrorCode());
			error.setErrorName(((UserPlatformException) e).getMessage());
			log.info("Error Occured while fetching all the User Platforms..:" + error.getErrorCode() + ":"
					+ error.getErrorName());
			// wrapping the error into errors list
			errors = new ArrayList<com.couponsworld.apiresults.Error>();
			errors.add(error);

			// Creating ResultantUserPlatform object
			resultantUserPlatform = new ResultantUserPlatform();

			resultantUserPlatform.setUserPlatform(userPlatforms);
			resultantUserPlatform.setErrors(errors);
			resultantUserPlatform.setStatus(Status.FAILURE);
			resultantUserPlatform.setLinks(GenerateLinkService.mapOfLinks.get("getUserPlatforms"));
			log.info("ResultantUserPlatform object returned successfully..");
			log.info("=============================== User Platform Service - End ===========================");
			userPlatforms = null;
			errors = null;
		}
		userPlatforms = null;
		errors = null;
		return resultantUserPlatform;
	}

	public static ResultantUserPlatform updateUserPlatform(long userPlatformId, UserPlatform userPlatform) {
		try {
			log.info("=========================== User Platform Service - Start =================================");
			Object returnedObject = DatabaseService.updateUserPlatformInDatabase(userPlatformId, userPlatform);
			if (returnedObject instanceof UserPlatform) {
				log.info("User Platform wrapping into ResultantUserPlatform ..");
				// wrapping the UserPlatform into UserPlatform list
				userPlatforms = new ArrayList<UserPlatform>();
				userPlatforms.add((UserPlatform) returnedObject);

				resultantUserPlatform = new ResultantUserPlatform();
				resultantUserPlatform.setUserPlatform(userPlatforms);
				resultantUserPlatform.setErrors(errors);
				resultantUserPlatform.setStatus(Status.SUCCESS);
				resultantUserPlatform.setLinks(GenerateLinkService.mapOfLinks.get("updateUserPlatform"));
				log.info("ResultantUserPlatform object returned successfully..");
				log.info("=============================== User Platform Service - End ===========================");
			} else if (returnedObject instanceof UserPlatformException) {
				// Creating Error for updating UserPlatform
				com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
				error.setErrorCode(Errors.USER_PLATFORM_ERROR.getErrorCode());
				error.setErrorName(((UserPlatformException) returnedObject).getMessage());
				log.info("Error Occured while updating the User Platform..:" + error.getErrorCode() + ":"
						+ error.getErrorName());
				// wrapping the error into errors list
				errors = new ArrayList<com.couponsworld.apiresults.Error>();
				errors.add(error);

				// wrapping the UserPlatform into UserPlatform list
				userPlatforms = new ArrayList<UserPlatform>();
				userPlatforms.add(userPlatform);

				resultantUserPlatform = new ResultantUserPlatform();
				resultantUserPlatform.setUserPlatform(userPlatforms);
				resultantUserPlatform.setErrors(errors);
				resultantUserPlatform.setStatus(Status.FAILURE);
				resultantUserPlatform.setLinks(GenerateLinkService.mapOfLinks.get("updateUserPlatform"));
				log.info("ResultantUserPlatform object returned successfully..");
				log.info("=============================== User Platform Service - End ===========================");
			} else {
				// Creating Error for updating userPlatforms
				com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
				error.setErrorCode(Errors.GENERAL_ERROR.getErrorCode());
				error.setErrorName(((Exception) returnedObject).getMessage());
				log.info("Error Occured while updating the User Platform..:" + error.getErrorCode() + ":"
						+ error.getErrorName());
				// wrapping the error into errors list
				errors = new ArrayList<com.couponsworld.apiresults.Error>();
				errors.add(error);

				// wrapping the userPlatforms into userPlatforms list
				userPlatforms = new ArrayList<UserPlatform>();
				userPlatforms.add(userPlatform);

				resultantUserPlatform = new ResultantUserPlatform();
				resultantUserPlatform.setUserPlatform(userPlatforms);
				resultantUserPlatform.setErrors(errors);
				resultantUserPlatform.setStatus(Status.FAILURE);
				resultantUserPlatform.setLinks(GenerateLinkService.mapOfLinks.get("updateUserPlatform"));
				log.info("ResultantUserPlatform object returned successfully..");
				log.info("=============================== User Platform Service - End ===========================");
			}
		} catch (Exception e) {
			// Creating Error for updating userPlatforms
			com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
			error.setErrorCode(Errors.GENERAL_ERROR.getErrorCode());
			error.setErrorName(((Exception) e).getMessage());
			log.info("Error Occured while updating the User Platform..:" + error.getErrorCode() + ":"
					+ error.getErrorName());
			// wrapping the error into errors list
			errors = new ArrayList<com.couponsworld.apiresults.Error>();
			errors.add(error);

			// wrapping the userPlatforms into userPlatforms list
			userPlatforms = new ArrayList<UserPlatform>();
			userPlatforms.add(userPlatform);

			resultantUserPlatform = new ResultantUserPlatform();
			resultantUserPlatform.setUserPlatform(userPlatforms);
			resultantUserPlatform.setErrors(errors);
			resultantUserPlatform.setStatus(Status.FAILURE);
			resultantUserPlatform.setLinks(GenerateLinkService.mapOfLinks.get("updateUserPlatform"));
			log.info("ResultantUserPlatform object returned successfully..");
			log.info("=============================== User Platform Service - End ===========================");
			userPlatforms = null;
			errors = null;
		}
		userPlatforms = null;
		errors = null;
		return resultantUserPlatform;
	}

	public static ResultantUserPlatform deleteUserPlatform(long userPlatformId) {
		try {
			log.info("=========================== User Platform Service - Start =================================");
			Object returnedObject = DatabaseService.deleteUserPlatformFromDatabase(userPlatformId);
			if (returnedObject instanceof UserPlatform) {
				log.info("User Platform wrapping into ResultantUserPlatform ..");
				// wrapping the UserPlatform into UserPlatform list
				userPlatforms = new ArrayList<UserPlatform>();
				userPlatforms.add((UserPlatform) returnedObject);

				resultantUserPlatform = new ResultantUserPlatform();
				resultantUserPlatform.setUserPlatform(userPlatforms);
				resultantUserPlatform.setErrors(errors);
				resultantUserPlatform.setStatus(Status.SUCCESS);
				resultantUserPlatform.setLinks(GenerateLinkService.mapOfLinks.get("deleteUserPlatform"));
				log.info("ResultantUserPlatform object returned successfully..");
				log.info("=============================== User Platform Service - End ===========================");
			} else if (returnedObject instanceof UserPlatformException) {
				// Creating Error for updating UserPlatform
				com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
				error.setErrorCode(Errors.USER_PLATFORM_ERROR.getErrorCode());
				error.setErrorName(((UserPlatformException) returnedObject).getMessage());
				log.info("Error Occured while deleting the User Platform..:" + error.getErrorCode() + ":"
						+ error.getErrorName());
				// wrapping the error into errors list
				errors = new ArrayList<com.couponsworld.apiresults.Error>();
				errors.add(error);

				resultantUserPlatform = new ResultantUserPlatform();
				resultantUserPlatform.setUserPlatform(userPlatforms);
				resultantUserPlatform.setErrors(errors);
				resultantUserPlatform.setStatus(Status.FAILURE);
				resultantUserPlatform.setLinks(GenerateLinkService.mapOfLinks.get("deleteUserPlatform"));
				log.info("ResultantUserPlatform object returned successfully..");
				log.info("=============================== User Platform Service - End ===========================");
			} else {
				// Creating Error for updating UserPlatform
				com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
				error.setErrorCode(Errors.GENERAL_ERROR.getErrorCode());
				error.setErrorName(((Exception) returnedObject).getMessage());
				log.info("Error Occured while deleting the User Platform..:" + error.getErrorCode() + ":"
						+ error.getErrorName());
				// wrapping the error into errors list
				errors = new ArrayList<com.couponsworld.apiresults.Error>();
				errors.add(error);

				resultantUserPlatform = new ResultantUserPlatform();
				resultantUserPlatform.setUserPlatform(userPlatforms);
				resultantUserPlatform.setErrors(errors);
				resultantUserPlatform.setStatus(Status.FAILURE);
				resultantUserPlatform.setLinks(GenerateLinkService.mapOfLinks.get("deleteUserPlatform"));
				log.info("ResultantUserPlatform object returned successfully..");
				log.info("=============================== User Platform Service - End ===========================");
			}
		} catch (Exception e) {
			// Creating Error for updating UserPlatform
			com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
			error.setErrorCode(Errors.GENERAL_ERROR.getErrorCode());
			error.setErrorName(((Exception) e).getMessage());
			log.info("Error Occured while deleting the User Platform..:" + error.getErrorCode() + ":"
					+ error.getErrorName());
			// wrapping the error into errors list
			errors = new ArrayList<com.couponsworld.apiresults.Error>();
			errors.add(error);

			resultantUserPlatform = new ResultantUserPlatform();
			resultantUserPlatform.setUserPlatform(userPlatforms);
			resultantUserPlatform.setErrors(errors);
			resultantUserPlatform.setStatus(Status.FAILURE);
			resultantUserPlatform.setLinks(GenerateLinkService.mapOfLinks.get("deleteUserPlatform"));
			log.info("ResultantUserPlatform object returned successfully..");
			log.info("=============================== User Platform Service - End ===========================");
			userPlatforms = null;
			errors = null;
		}
		userPlatforms = null;
		errors = null;
		return resultantUserPlatform;
	}
}
