package com.couponsworld.utilities;

import java.util.ArrayList;
import java.util.List;

import com.couponsworld.apiresults.Error;
import com.couponsworld.dto.UserType;
import com.couponsworld.enums.Errors;

public class ValidationService {

	public static List<Error> validateUserType(UserType userType) {
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
}
