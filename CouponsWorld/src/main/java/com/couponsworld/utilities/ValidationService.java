package com.couponsworld.utilities;

import java.util.ArrayList;
import java.util.List;

import com.couponsworld.apiresults.Error;
import com.couponsworld.dto.UsabilityStatus;
import com.couponsworld.dto.UserPlatform;
import com.couponsworld.dto.UserType;
import com.couponsworld.enums.Errors;

public class ValidationService {

	public static List<Error> validateUserTypeForPostMethod(UserType userType) {
		List<Error> errors = null;
		if (userType.getUserTypeName().equals("")) {
			Error error = new Error();
			error.setErrorCode(Errors.VALIDATION_ERROR.getErrorCode());
			error.setErrorName("User Type Not Specified...");

			// Initializing error list
			errors = new ArrayList<>();
			errors.add(error);
			return errors;
		}
		return null;
	}

	public static List<Error> validateUserTypeForPutMethod(UserType userType, String userTypeId) {
		List<Error> errors = null;
		if (userType.getUserTypeName().equals("") || userTypeId.equals("")) {
			Error error = new Error();
			error.setErrorCode(Errors.VALIDATION_ERROR.getErrorCode());
			error.setErrorName("Updated User Type or User Type Id Not Specified...");

			// Initializing error list
			errors = new ArrayList<>();
			errors.add(error);
			return errors;
		}
		return null;
	}

	public static List<Error> validateUserTypeForDeleteMethod(String userTypeId) {
		List<Error> errors = null;
		if (userTypeId.equals("")) {
			Error error = new Error();
			error.setErrorCode(Errors.VALIDATION_ERROR.getErrorCode());
			error.setErrorName("User Type Id Not Specified...");
			// Initializing error list
			errors = new ArrayList<>();
			errors.add(error);
			return errors;
		}
		return null;
	}

	public static List<Error> validateUserPlatformForPostMethod(UserPlatform userPlatform) {
		List<Error> errors = null;
		if (userPlatform.getUserPlatformName().equals("")) {
			Error error = new Error();
			error.setErrorCode(Errors.VALIDATION_ERROR.getErrorCode());
			error.setErrorName("User Platform Not Specified...");

			// Initializing error list
			errors = new ArrayList<>();
			errors.add(error);
			return errors;
		}
		return null;
	}

	public static List<Error> validateUserPlatformForPutMethod(UserPlatform userPlatform, String userPlatformId) {
		List<Error> errors = null;
		if (userPlatform.getUserPlatformName().equals("") || userPlatformId.equals("")) {
			Error error = new Error();
			error.setErrorCode(Errors.VALIDATION_ERROR.getErrorCode());
			error.setErrorName("Updated User Platform or User Platform Id Not Specified...");

			// Initializing error list
			errors = new ArrayList<>();
			errors.add(error);
			return errors;
		}
		return null;
	}

	public static List<Error> validateUserPlatformForDeleteMethod(String userPlatformId) {
		List<Error> errors = null;
		if (userPlatformId.equals("")) {
			Error error = new Error();
			error.setErrorCode(Errors.VALIDATION_ERROR.getErrorCode());
			error.setErrorName("User Platform Id Not Specified...");
			// Initializing error list
			errors = new ArrayList<>();
			errors.add(error);
			return errors;
		}
		return null;
	}

	public static List<Error> validateUsabilityStatusForPostMethod(UsabilityStatus usabilityStatus) {
		List<Error> errors = null;
		if (usabilityStatus.getUsabilityStatusName().equals("")) {
			Error error = new Error();
			error.setErrorCode(Errors.VALIDATION_ERROR.getErrorCode());
			error.setErrorName("Usability Status Not Specified...");

			// Initializing error list
			errors = new ArrayList<>();
			errors.add(error);
			return errors;
		}
		return null;
	}

	public static List<Error> validateUsabilityStatusForPutMethod(UsabilityStatus usabilityStatus,
			String usabilityStatusId) {
		List<Error> errors = null;
		if (usabilityStatus.getUsabilityStatusName().equals("") || usabilityStatusId.equals("")) {
			Error error = new Error();
			error.setErrorCode(Errors.VALIDATION_ERROR.getErrorCode());
			error.setErrorName("Updated Usabiltiy Status or Usability Status Id Not Specified...");

			// Initializing error list
			errors = new ArrayList<>();
			errors.add(error);
			return errors;
		}
		return null;
	}

	public static List<Error> validateUsabilityStatusForDeleteMethod(String usabilityStatusId) {
		List<Error> errors = null;
		if (usabilityStatusId.equals("")) {
			Error error = new Error();
			error.setErrorCode(Errors.VALIDATION_ERROR.getErrorCode());
			error.setErrorName("Usability StatusId Not Specified...");
			// Initializing error list
			errors = new ArrayList<>();
			errors.add(error);
			return errors;
		}
		return null;
	}
}
