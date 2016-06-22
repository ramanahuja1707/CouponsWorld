package com.couponsworld.utilities;

import java.util.ArrayList;
import java.util.List;

import com.couponsworld.apiresults.Error;
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

}
