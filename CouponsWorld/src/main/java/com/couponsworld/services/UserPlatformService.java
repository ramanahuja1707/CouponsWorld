package com.couponsworld.services;

import java.util.ArrayList;
import java.util.List;

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

	private static List<UserPlatform> userPlatforms = null;
	private static List<com.couponsworld.apiresults.Error> errors = null;
	private static ResultantUserPlatform resultantUserPlatform;

	public static ResultantUserPlatform createUserPlatform(UserPlatform userPlatform) {
		try {
			Object returnedObject = DatabaseService.createUserPlatformInDatabase(userPlatform);
			if (returnedObject instanceof UserPlatform) {
				// SubCategory successfully created and returned....
				// wrapping the UserPlatform into UserPlatform list
				userPlatforms = new ArrayList<UserPlatform>();
				userPlatforms.add((UserPlatform) returnedObject);

				// Creating ResultantUserPlatform object
				resultantUserPlatform = new ResultantUserPlatform();

				resultantUserPlatform.setUserPlatform(userPlatforms);
				resultantUserPlatform.setErrors(errors);
				resultantUserPlatform.setStatus(Status.SUCCESS);
				resultantUserPlatform.setLinks(GenerateLinkService.generateCategoryLink(""));
			} else {
				// Creating Error for updating UserPlatform
				com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
				error.setErrorCode(Errors.GENERAL_ERROR.getErrorCode());
				error.setErrorName(((Exception) returnedObject).getMessage());

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
				resultantUserPlatform.setLinks(GenerateLinkService.generateCategoryLink(""));
			}
		} catch (Exception exception) {
			// Creating Error for updating UserPlatform
			com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
			error.setErrorCode(Errors.GENERAL_ERROR.getErrorCode());
			error.setErrorName(((Exception) exception).getMessage());

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
			resultantUserPlatform.setLinks(GenerateLinkService.generateCategoryLink(""));
		}
		userPlatforms = null;
		errors = null;
		return resultantUserPlatform;
	}

	public static ResultantUserPlatform getUserPlatforms() {
		try {
			Object returnedObject = DatabaseService.getUserPlatformFromDatabase();
			if (returnedObject instanceof Exception) {
				// Creating Error for updating UserPlatform
				com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
				error.setErrorCode(Errors.GENERAL_ERROR.getErrorCode());
				error.setErrorName(((Exception) returnedObject).getMessage());

				// wrapping the error into errors list
				errors = new ArrayList<com.couponsworld.apiresults.Error>();
				errors.add(error);

				// Creating ResultantUserPlatform object
				resultantUserPlatform = new ResultantUserPlatform();

				resultantUserPlatform.setUserPlatform(userPlatforms);
				resultantUserPlatform.setErrors(errors);
				resultantUserPlatform.setStatus(Status.FAILURE);
				resultantUserPlatform.setLinks(GenerateLinkService.generateCategoryLink(""));
			} else if (returnedObject instanceof UsabilityStatusException) {
				// Creating Error for updating UsabilityStatus
				com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
				error.setErrorCode(Errors.USER_PLATFORM_ERROR.getErrorCode());
				error.setErrorName(((UserPlatformException) returnedObject).getMessage());

				// wrapping the error into errors list
				errors = new ArrayList<com.couponsworld.apiresults.Error>();
				errors.add(error);

				// Creating ResultantUserPlatform object
				resultantUserPlatform = new ResultantUserPlatform();

				resultantUserPlatform.setUserPlatform(userPlatforms);
				resultantUserPlatform.setErrors(errors);
				resultantUserPlatform.setStatus(Status.FAILURE);
				resultantUserPlatform.setLinks(GenerateLinkService.generateCategoryLink(""));
			} else {

				if (((List<Category>) returnedObject).size() > 0) {

					// Creating ResultantUserPlatform object
					resultantUserPlatform = new ResultantUserPlatform();

					resultantUserPlatform.setUserPlatform((List<UserPlatform>) returnedObject);
					resultantUserPlatform.setErrors(errors);
					resultantUserPlatform.setStatus(Status.SUCCESS);
					resultantUserPlatform.setLinks(GenerateLinkService.generateCategoryLink(""));
				} else {
					// Creating ResultantUserPlatform object
					resultantUserPlatform = new ResultantUserPlatform();

					resultantUserPlatform.setUserPlatform(userPlatforms);
					resultantUserPlatform.setErrors(errors);
					resultantUserPlatform.setStatus(Status.SUCCESS);
					resultantUserPlatform.setLinks(GenerateLinkService.generateCategoryLink(""));
				}
			}
		} catch (Exception e) {
			// Creating Error for updating UsabilityStatus
			com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
			error.setErrorCode(Errors.GENERAL_ERROR.getErrorCode());
			error.setErrorName(((UserPlatformException) e).getMessage());

			// wrapping the error into errors list
			errors = new ArrayList<com.couponsworld.apiresults.Error>();
			errors.add(error);

			// Creating ResultantUserPlatform object
			resultantUserPlatform = new ResultantUserPlatform();

			resultantUserPlatform.setUserPlatform(userPlatforms);
			resultantUserPlatform.setErrors(errors);
			resultantUserPlatform.setStatus(Status.FAILURE);
			resultantUserPlatform.setLinks(GenerateLinkService.generateCategoryLink(""));
			userPlatforms = null;
			errors = null;
		}
		userPlatforms = null;
		errors = null;
		return resultantUserPlatform;
	}

	public static ResultantUserPlatform updateUserPlatform(long userPlatformId, UserPlatform userPlatform) {
		try {
			Object returnedObject = DatabaseService.updateUserPlatformInDatabase(userPlatformId, userPlatform);
			if (returnedObject instanceof UserPlatform) {

				// wrapping the UserPlatform into UserPlatform list
				userPlatforms = new ArrayList<UserPlatform>();
				userPlatforms.add((UserPlatform) returnedObject);

				resultantUserPlatform = new ResultantUserPlatform();
				resultantUserPlatform.setUserPlatform(userPlatforms);
				resultantUserPlatform.setErrors(errors);
				resultantUserPlatform.setStatus(Status.SUCCESS);
				resultantUserPlatform.setLinks(GenerateLinkService.generateSubcategoryLink("updateSubCategory"));
			} else if (returnedObject instanceof UserPlatformException) {
				// Creating Error for updating UserPlatform
				com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
				error.setErrorCode(Errors.USER_PLATFORM_ERROR.getErrorCode());
				error.setErrorName(((UserPlatformException) returnedObject).getMessage());

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
				resultantUserPlatform.setLinks(GenerateLinkService.generateSubcategoryLink("updateSubCategory"));
			} else {
				// Creating Error for updating userPlatforms
				com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
				error.setErrorCode(Errors.GENERAL_ERROR.getErrorCode());
				error.setErrorName(((Exception) returnedObject).getMessage());

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
				resultantUserPlatform.setLinks(GenerateLinkService.generateSubcategoryLink("updateSubCategory"));
			}
		} catch (Exception e) {
			// Creating Error for updating userPlatforms
			com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
			error.setErrorCode(Errors.GENERAL_ERROR.getErrorCode());
			error.setErrorName(((Exception) e).getMessage());

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
			resultantUserPlatform.setLinks(GenerateLinkService.generateSubcategoryLink("updateSubCategory"));
			userPlatforms = null;
			errors = null;
		}
		userPlatforms = null;
		errors = null;
		return resultantUserPlatform;
	}

	public static ResultantUserPlatform deleteUserPlatform(long userPlatformId) {
		try {
			Object returnedObject = DatabaseService.deleteUserPlatformFromDatabase(userPlatformId);
			if (returnedObject instanceof UserPlatform) {
				// wrapping the UserPlatform into UserPlatform list
				userPlatforms = new ArrayList<UserPlatform>();
				userPlatforms.add((UserPlatform) returnedObject);

				resultantUserPlatform = new ResultantUserPlatform();
				resultantUserPlatform.setUserPlatform(userPlatforms);
				resultantUserPlatform.setErrors(errors);
				resultantUserPlatform.setStatus(Status.SUCCESS);
				resultantUserPlatform.setLinks(GenerateLinkService.generateSubcategoryLink("deleteSubCategory"));
			} else if (returnedObject instanceof UserPlatformException) {
				// Creating Error for updating UserPlatform
				com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
				error.setErrorCode(Errors.USER_PLATFORM_ERROR.getErrorCode());
				error.setErrorName(((UserPlatformException) returnedObject).getMessage());

				// wrapping the error into errors list
				errors = new ArrayList<com.couponsworld.apiresults.Error>();
				errors.add(error);

				resultantUserPlatform = new ResultantUserPlatform();
				resultantUserPlatform.setUserPlatform(userPlatforms);
				resultantUserPlatform.setErrors(errors);
				resultantUserPlatform.setStatus(Status.FAILURE);
				resultantUserPlatform.setLinks(GenerateLinkService.generateSubcategoryLink("deleteSubCategory"));
			} else {
				// Creating Error for updating UserPlatform
				com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
				error.setErrorCode(Errors.GENERAL_ERROR.getErrorCode());
				error.setErrorName(((Exception) returnedObject).getMessage());

				// wrapping the error into errors list
				errors = new ArrayList<com.couponsworld.apiresults.Error>();
				errors.add(error);

				resultantUserPlatform = new ResultantUserPlatform();
				resultantUserPlatform.setUserPlatform(userPlatforms);
				resultantUserPlatform.setErrors(errors);
				resultantUserPlatform.setStatus(Status.FAILURE);
				resultantUserPlatform.setLinks(GenerateLinkService.generateSubcategoryLink("deleteSubCategory"));
			}
		} catch (Exception e) {
			// Creating Error for updating UserPlatform
			com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
			error.setErrorCode(Errors.GENERAL_ERROR.getErrorCode());
			error.setErrorName(((Exception) e).getMessage());

			// wrapping the error into errors list
			errors = new ArrayList<com.couponsworld.apiresults.Error>();
			errors.add(error);

			resultantUserPlatform = new ResultantUserPlatform();
			resultantUserPlatform.setUserPlatform(userPlatforms);
			resultantUserPlatform.setErrors(errors);
			resultantUserPlatform.setStatus(Status.FAILURE);
			resultantUserPlatform.setLinks(GenerateLinkService.generateSubcategoryLink("deleteSubCategory"));
			userPlatforms = null;
			errors = null;
		}
		userPlatforms = null;
		errors = null;
		return resultantUserPlatform;
	}
}
