package com.couponsworld.services;

import java.util.ArrayList;
import java.util.List;

import com.couponsworld.apiresults.ResultantUserType;
import com.couponsworld.database.DatabaseService;
import com.couponsworld.dto.Category;
import com.couponsworld.dto.UserType;
import com.couponsworld.enums.Errors;
import com.couponsworld.enums.Status;
import com.couponsworld.exceptions.UsabilityStatusException;
import com.couponsworld.exceptions.UserPlatformException;
import com.couponsworld.exceptions.UserTypeException;
import com.couponsworld.utilities.GenerateLinkService;

public class UserTypeService {

	private static List<UserType> userTypes = null;
	private static List<com.couponsworld.apiresults.Error> errors = null;
	private static ResultantUserType resultantUserType;

	public static ResultantUserType createUserType(UserType userType) {
		try {
			Object returnedObject = DatabaseService.createUserTypeInDatabase(userType);
			if (returnedObject instanceof UserType) {
				// SubCategory successfully created and returned....
				// wrapping the UserType into UserType list
				userTypes = new ArrayList<UserType>();
				userTypes.add((UserType) returnedObject);

				// Creating ResultantUserType object
				resultantUserType = new ResultantUserType();

				resultantUserType.setUserType(userTypes);
				resultantUserType.setErrors(errors);
				resultantUserType.setStatus(Status.SUCCESS);
				resultantUserType.setLinks(GenerateLinkService.mapOfLinks.get("createUserType"));
			} else {
				// Creating Error for updating USERTYPE
				com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
				error.setErrorCode(Errors.GENERAL_ERROR.getErrorCode());
				error.setErrorName(((Exception) returnedObject).getMessage());

				// wrapping the UserType into UserType list
				userTypes = new ArrayList<UserType>();
				userTypes.add((UserType) returnedObject);

				// wrapping the error into errors list
				errors = new ArrayList<com.couponsworld.apiresults.Error>();
				errors.add(error);

				// Creating ResultantUserType object
				resultantUserType = new ResultantUserType();

				resultantUserType.setUserType(userTypes);
				resultantUserType.setErrors(errors);
				resultantUserType.setStatus(Status.FAILURE);
				resultantUserType.setLinks(GenerateLinkService.mapOfLinks.get("createUserType"));
			}
		} catch (Exception exception) {
			// Creating Error for updating USERTYPE
			com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
			error.setErrorCode(Errors.GENERAL_ERROR.getErrorCode());
			error.setErrorName(((Exception) exception).getMessage());

			// wrapping the UserType into UserType list
			userTypes = new ArrayList<UserType>();
			userTypes.add((UserType) userType);

			// wrapping the error into errors list
			errors = new ArrayList<com.couponsworld.apiresults.Error>();
			errors.add(error);

			// Creating ResultantUserType object
			resultantUserType = new ResultantUserType();

			resultantUserType.setUserType(userTypes);
			resultantUserType.setErrors(errors);
			resultantUserType.setStatus(Status.FAILURE);
			resultantUserType.setLinks(GenerateLinkService.mapOfLinks.get("createUserType"));
		}
		userTypes = null;
		errors = null;
		return resultantUserType;
	}

	public static ResultantUserType getUserTypes() {
		try {
			Object returnedObject = DatabaseService.getUserTypeFromDatabase();
			if (returnedObject instanceof Exception) {
				// Creating Error for updating UserType
				com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
				error.setErrorCode(Errors.GENERAL_ERROR.getErrorCode());
				error.setErrorName(((Exception) returnedObject).getMessage());

				// wrapping the error into errors list
				errors = new ArrayList<com.couponsworld.apiresults.Error>();
				errors.add(error);

				// Creating ResultantUserType object
				resultantUserType = new ResultantUserType();

				resultantUserType.setUserType(userTypes);
				resultantUserType.setErrors(errors);
				resultantUserType.setStatus(Status.FAILURE);
				resultantUserType.setLinks(GenerateLinkService.mapOfLinks.get("getUserTypes"));
			} else if (returnedObject instanceof UsabilityStatusException) {
				// Creating Error for updating UserType
				com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
				error.setErrorCode(Errors.USER_TYPE_ERROR.getErrorCode());
				error.setErrorName(((UserTypeException) returnedObject).getMessage());

				// wrapping the error into errors list
				errors = new ArrayList<com.couponsworld.apiresults.Error>();
				errors.add(error);

				// Creating resultantUserType object
				resultantUserType = new ResultantUserType();

				resultantUserType.setUserType(userTypes);
				resultantUserType.setErrors(errors);
				resultantUserType.setStatus(Status.FAILURE);
				resultantUserType.setLinks(GenerateLinkService.mapOfLinks.get("getUserTypes"));
			} else {

				if (((List<Category>) returnedObject).size() > 0) {

					// Creating resultantUserType object
					resultantUserType = new ResultantUserType();

					resultantUserType.setUserType((List<UserType>) returnedObject);
					resultantUserType.setErrors(errors);
					resultantUserType.setStatus(Status.SUCCESS);
					resultantUserType.setLinks((GenerateLinkService.mapOfLinks).get("getUserTypes"));
				} else {
					// Creating ResultantUserPlatform object
					resultantUserType = new ResultantUserType();

					resultantUserType.setUserType(userTypes);
					resultantUserType.setErrors(errors);
					resultantUserType.setStatus(Status.SUCCESS);
					resultantUserType.setLinks((GenerateLinkService.mapOfLinks).get("getUserTypes"));
				}
			}
		} catch (Exception e) {
			// Creating Error for updating UserType
			com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
			error.setErrorCode(Errors.GENERAL_ERROR.getErrorCode());
			error.setErrorName((e).getMessage());

			// wrapping the error into errors list
			errors = new ArrayList<com.couponsworld.apiresults.Error>();
			errors.add(error);

			// Creating resultantUserType object
			resultantUserType = new ResultantUserType();

			resultantUserType.setUserType(userTypes);
			resultantUserType.setErrors(errors);
			resultantUserType.setStatus(Status.FAILURE);
			resultantUserType.setLinks(GenerateLinkService.mapOfLinks.get("getUserTypes"));
			userTypes = null;
			errors = null;
		}
		userTypes = null;
		errors = null;
		return resultantUserType;
	}

	public static ResultantUserType updateUserType(long userTypeId, UserType userType) {
		try {
			Object returnedObject = DatabaseService.updateUserTypeInDatabase(userTypeId, userType);
			if (returnedObject instanceof UserType) {

				// wrapping the UserType into UserType list
				userTypes = new ArrayList<UserType>();
				userTypes.add((UserType) returnedObject);

				resultantUserType = new ResultantUserType();
				resultantUserType.setUserType(userTypes);
				resultantUserType.setErrors(errors);
				resultantUserType.setStatus(Status.SUCCESS);
				resultantUserType.setLinks(GenerateLinkService.mapOfLinks.get("updateUserType"));
			} else if (returnedObject instanceof UserTypeException) {
				// Creating Error for updating UserType
				com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
				error.setErrorCode(Errors.USER_TYPE_ERROR.getErrorCode());
				error.setErrorName(((UserTypeException) returnedObject).getMessage());

				// wrapping the error into errors list
				errors = new ArrayList<com.couponsworld.apiresults.Error>();
				errors.add(error);

				// wrapping the UserType into UserType list
				userTypes = new ArrayList<UserType>();
				userTypes.add(userType);

				resultantUserType = new ResultantUserType();
				resultantUserType.setUserType(userTypes);
				resultantUserType.setErrors(errors);
				resultantUserType.setStatus(Status.FAILURE);
				resultantUserType.setLinks(GenerateLinkService.mapOfLinks.get("updateUserType"));
			} else {
				// Creating Error for updating UserType
				com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
				error.setErrorCode(Errors.GENERAL_ERROR.getErrorCode());
				error.setErrorName(((Exception) returnedObject).getMessage());

				// wrapping the error into errors list
				errors = new ArrayList<com.couponsworld.apiresults.Error>();
				errors.add(error);

				// wrapping the UserType into UserType list
				userTypes = new ArrayList<UserType>();
				userTypes.add(userType);

				resultantUserType = new ResultantUserType();
				resultantUserType.setUserType(userTypes);
				resultantUserType.setErrors(errors);
				resultantUserType.setStatus(Status.FAILURE);
				resultantUserType.setLinks(GenerateLinkService.mapOfLinks.get("updateUserType"));
			}
		} catch (Exception e) {
			// Creating Error for updating UserType
			com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
			error.setErrorCode(Errors.GENERAL_ERROR.getErrorCode());
			error.setErrorName(((Exception) e).getMessage());

			// wrapping the error into errors list
			errors = new ArrayList<com.couponsworld.apiresults.Error>();
			errors.add(error);

			// wrapping the UserType into UserType list
			userTypes = new ArrayList<UserType>();
			userTypes.add(userType);

			resultantUserType = new ResultantUserType();
			resultantUserType.setUserType(userTypes);
			resultantUserType.setErrors(errors);
			resultantUserType.setStatus(Status.FAILURE);
			resultantUserType.setLinks(GenerateLinkService.mapOfLinks.get("updateUserType"));
			userTypes = null;
			errors = null;
		}
		userTypes = null;
		errors = null;
		return resultantUserType;
	}

	public static ResultantUserType deleteUserType(long userTypeId) {
		try {
			Object returnedObject = DatabaseService.deleteUserTypeFromDatabase(userTypeId);
			if (returnedObject instanceof UserType) {
				// wrapping the UserType into UserType list
				userTypes = new ArrayList<UserType>();
				userTypes.add((UserType) returnedObject);

				resultantUserType = new ResultantUserType();
				resultantUserType.setUserType(userTypes);
				resultantUserType.setErrors(errors);
				resultantUserType.setStatus(Status.SUCCESS);
				resultantUserType.setLinks(GenerateLinkService.mapOfLinks.get("deleteUserType"));
			} else if (returnedObject instanceof UserPlatformException) {
				// Creating Error for updating UserType
				com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
				error.setErrorCode(Errors.USER_TYPE_ERROR.getErrorCode());
				error.setErrorName(((UserTypeException) returnedObject).getMessage());

				// wrapping the error into errors list
				errors = new ArrayList<com.couponsworld.apiresults.Error>();
				errors.add(error);

				resultantUserType = new ResultantUserType();
				resultantUserType.setUserType(userTypes);
				resultantUserType.setErrors(errors);
				resultantUserType.setStatus(Status.FAILURE);
				resultantUserType.setLinks(GenerateLinkService.mapOfLinks.get("deleteUserType"));
			} else {
				// Creating Error for updating UserType
				com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
				error.setErrorCode(Errors.GENERAL_ERROR.getErrorCode());
				error.setErrorName(((Exception) returnedObject).getMessage());

				// wrapping the error into errors list
				errors = new ArrayList<com.couponsworld.apiresults.Error>();
				errors.add(error);

				resultantUserType = new ResultantUserType();
				resultantUserType.setUserType(userTypes);
				resultantUserType.setErrors(errors);
				resultantUserType.setStatus(Status.FAILURE);
				resultantUserType.setLinks(GenerateLinkService.mapOfLinks.get("deleteUserType"));
			}
		} catch (Exception e) {
			// Creating Error for updating UserType
			com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
			error.setErrorCode(Errors.GENERAL_ERROR.getErrorCode());
			error.setErrorName((e).getMessage());

			// wrapping the error into errors list
			errors = new ArrayList<com.couponsworld.apiresults.Error>();
			errors.add(error);

			resultantUserType = new ResultantUserType();
			resultantUserType.setUserType(userTypes);
			resultantUserType.setErrors(errors);
			resultantUserType.setStatus(Status.FAILURE);
			resultantUserType.setLinks(GenerateLinkService.mapOfLinks.get("deleteUserType"));
			userTypes = null;
			errors = null;
		}
		userTypes = null;
		errors = null;
		return resultantUserType;
	}
}
