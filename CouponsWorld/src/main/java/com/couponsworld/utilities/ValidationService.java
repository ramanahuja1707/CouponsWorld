package com.couponsworld.utilities;

import java.util.ArrayList;
import java.util.List;

import com.couponsworld.apiresults.Error;
import com.couponsworld.dto.Category;
import com.couponsworld.dto.Company;
import com.couponsworld.dto.SubCategory;
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

	public static List<Error> validateSubCategoryForPostMethod(SubCategory subCategory) {
		List<Error> errors = null;
		if (subCategory.getSubCategoryName().equals("")) {
			Error error = new Error();
			error.setErrorCode(Errors.VALIDATION_ERROR.getErrorCode());
			error.setErrorName("Sub Category Not Specified...");

			// Initializing error list
			errors = new ArrayList<>();
			errors.add(error);
			return errors;
		}
		return null;
	}

	public static List<Error> validateSubCategoryForPutMethod(SubCategory subCategory, String subCategoryId) {
		List<Error> errors = null;
		if (subCategory.getSubCategoryName().equals("") || subCategoryId.equals("")) {
			Error error = new Error();
			error.setErrorCode(Errors.VALIDATION_ERROR.getErrorCode());
			error.setErrorName("Updated SubCategory or SubCategory Id Not Specified...");

			// Initializing error list
			errors = new ArrayList<>();
			errors.add(error);
			return errors;
		}
		return null;
	}

	public static List<Error> validateSubCategoryForDeleteMethod(String subCategoryId) {
		List<Error> errors = null;
		if (subCategoryId.equals("")) {
			Error error = new Error();
			error.setErrorCode(Errors.VALIDATION_ERROR.getErrorCode());
			error.setErrorName("subCategoryId Id Not Specified...");
			// Initializing error list
			errors = new ArrayList<>();
			errors.add(error);
			return errors;
		}
		return null;
	}

	public static List<Error> validateCategoryForPostMethod(Category category) {
		List<Error> errors = null;
		if (category.getCategoryName().equals("")) {
			Error error = new Error();
			error.setErrorCode(Errors.VALIDATION_ERROR.getErrorCode());
			error.setErrorName("Category Not Specified...");

			// Initializing error list
			errors = new ArrayList<>();
			errors.add(error);
			return errors;
		}
		return null;
	}

	public static List<Error> validateCategoryForPutMethod(Category category, String categoryId) {
		List<Error> errors = null;
		if (category.getCategoryName().equals("") || categoryId.equals("")) {
			Error error = new Error();
			error.setErrorCode(Errors.VALIDATION_ERROR.getErrorCode());
			error.setErrorName("Updated Category or Category Id Not Specified...");

			// Initializing error list
			errors = new ArrayList<>();
			errors.add(error);
			return errors;
		}
		return null;
	}

	public static List<Error> validateCategoryForDeleteMethod(String categoryId) {
		List<Error> errors = null;
		if (categoryId.equals("")) {
			Error error = new Error();
			error.setErrorCode(Errors.VALIDATION_ERROR.getErrorCode());
			error.setErrorName("categoryId Id Not Specified...");
			// Initializing error list
			errors = new ArrayList<>();
			errors.add(error);
			return errors;
		}
		return null;
	}

	public static List<Error> validateCompanyForPostMethod(Company company) {
		List<Error> errors = null;
		Error error = new Error();
		errors = new ArrayList<>();
		error.setErrorCode(Errors.VALIDATION_ERROR.getErrorCode());
		if (company.getCompanyName().equals("")) {
			error.setErrorName("Company Name Not Specified...");
			errors.add(error);
			return errors;
		} else if (company.getCompanyDescription().equals("")) {
			error.setErrorName("Company Description Not Specified...");
			errors.add(error);
			return errors;
		} else if (company.getCompanyLogoName().equals("")) {
			error.setErrorName("Company Logo Name Not Specified...");
			errors.add(error);
			return errors;
		}
		return null;
	}

	public static List<Error> validateCompanyForPutMethod(Company company, String companyId) {
		List<Error> errors = null;
		Error error = new Error();
		errors = new ArrayList<>();
		error.setErrorCode(Errors.VALIDATION_ERROR.getErrorCode());
		if (company.getCompanyName().equals("")) {
			error.setErrorName("Company Name Not Specified...");
			errors.add(error);
			return errors;
		} else if (company.getCompanyDescription().equals("")) {
			error.setErrorName("Company Description Not Specified...");
			errors.add(error);
			return errors;
		} else if (company.getCompanyLogoName().equals("")) {
			error.setErrorName("Company Logo Name Not Specified...");
			errors.add(error);
			return errors;
		} else if (companyId.equals("")) {
			error.setErrorName("Company Id Not Specified...");
			errors.add(error);
			return errors;
		}
		return null;
	}

	public static List<Error> validateCompanyForDeleteMethod(String companyId) {
		List<Error> errors = null;
		if (companyId.equals("")) {
			Error error = new Error();
			error.setErrorCode(Errors.VALIDATION_ERROR.getErrorCode());
			error.setErrorName("Company Id Id Not Specified...");
			// Initializing error list
			errors = new ArrayList<>();
			errors.add(error);
			return errors;
		}
		return null;
	}
}
