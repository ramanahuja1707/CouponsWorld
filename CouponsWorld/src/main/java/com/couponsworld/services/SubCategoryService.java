package com.couponsworld.services;

import java.util.ArrayList;
import java.util.List;

import com.couponsworld.apiresults.ResultantSubCategory;
import com.couponsworld.database.DatabaseService;
import com.couponsworld.dto.Category;
import com.couponsworld.dto.SubCategory;
import com.couponsworld.enums.Errors;
import com.couponsworld.enums.Status;
import com.couponsworld.exceptions.CategoryException;
import com.couponsworld.exceptions.SubCategoryException;
import com.couponsworld.utilities.GenerateLinkService;

public class SubCategoryService {

	public static List<SubCategory> subCategories = null;
	public static List<com.couponsworld.apiresults.Error> errors = null;
	public static ResultantSubCategory resultantSubCategory;

	public static ResultantSubCategory createSubCategory(SubCategory subCategory) {
		try {
			Object returnedObject = DatabaseService.createSubCategoryInDatabase(subCategory);
			if (returnedObject instanceof SubCategory) {
				// SubCategory successfully created and returned....
				// wrapping the SubCategory into SubCategory list
				subCategories = new ArrayList<SubCategory>();
				subCategories.add((SubCategory) returnedObject);

				// Creating ResultantSubCategory object
				resultantSubCategory = new ResultantSubCategory();

				resultantSubCategory.setSubCategories(subCategories);
				resultantSubCategory.setErrors(errors);
				resultantSubCategory.setStatus(Status.SUCCESS);
				resultantSubCategory.setLinks(GenerateLinkService.mapOfLinks.get("createSubCategory"));
			} else {
				// Creating Error for updating Subcategory
				com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
				error.setErrorCode(Errors.GENERAL_ERROR.getErrorCode());
				error.setErrorName(((Exception) returnedObject).getMessage());

				// wrapping the SubCategory into SubCategory list
				subCategories = new ArrayList<SubCategory>();
				subCategories.add(subCategory);

				// wrapping the error into errors list
				errors = new ArrayList<com.couponsworld.apiresults.Error>();
				errors.add(error);

				// category does not created and error returned....
				// Creating ResultantCategory object
				resultantSubCategory = new ResultantSubCategory();

				resultantSubCategory.setSubCategories(subCategories);
				resultantSubCategory.setErrors(errors);
				resultantSubCategory.setStatus(Status.FAILURE);
				resultantSubCategory.setLinks(GenerateLinkService.mapOfLinks.get("createSubCategory"));
			}
		} catch (Exception exception) {
			// Creating Error for updating Offer
			com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
			error.setErrorCode(Errors.GENERAL_ERROR.getErrorCode());
			error.setErrorName(exception.getMessage());

			// wrapping the SubCategory into SubCategory list
			subCategories = new ArrayList<SubCategory>();
			subCategories.add(subCategory);

			// wrapping the error into errors list
			errors = new ArrayList<com.couponsworld.apiresults.Error>();
			errors.add(error);

			// Creating ResultantCategory object
			resultantSubCategory = new ResultantSubCategory();

			resultantSubCategory.setSubCategories(subCategories);
			resultantSubCategory.setErrors(errors);
			resultantSubCategory.setStatus(Status.FAILURE);
			resultantSubCategory.setLinks(GenerateLinkService.mapOfLinks.get("createSubCategory"));
		}
		subCategories = null;
		errors = null;
		return resultantSubCategory;
	}

	public static ResultantSubCategory getSubCategories() {
		try {
			Object returnedObject = DatabaseService.getSubCategoriesFromDatabase();
			if (returnedObject instanceof Exception) {
				// Creating Error for updating Subcategory
				com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
				error.setErrorCode(Errors.GENERAL_ERROR.getErrorCode());
				error.setErrorName(((Exception) returnedObject).getMessage());

				// wrapping the error into errors list
				errors = new ArrayList<com.couponsworld.apiresults.Error>();
				errors.add(error);

				// Creating ResultantCategory object
				resultantSubCategory = new ResultantSubCategory();
				resultantSubCategory.setSubCategories(subCategories);
				resultantSubCategory.setErrors(errors);
				resultantSubCategory.setStatus(Status.FAILURE);
				resultantSubCategory.setLinks(GenerateLinkService.mapOfLinks.get("getSubCategories"));
			} else if (returnedObject instanceof CategoryException) {
				// Creating Error for updating subcategory
				com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
				error.setErrorCode(Errors.SUBCATEGORY_ERROR.getErrorCode());
				error.setErrorName(((CategoryException) returnedObject).getMessage());

				// wrapping the error into errors list
				errors = new ArrayList<com.couponsworld.apiresults.Error>();
				errors.add(error);
				// Creating ResultantCategory object
				resultantSubCategory = new ResultantSubCategory();

				resultantSubCategory.setSubCategories(subCategories);
				resultantSubCategory.setErrors(errors);
				resultantSubCategory.setStatus(Status.FAILURE);
				resultantSubCategory.setLinks(GenerateLinkService.mapOfLinks.get("getSubCategories"));
			} else {

				if (((List<Category>) returnedObject).size() > 0) {

					// Creating ResultantCategory object
					resultantSubCategory = new ResultantSubCategory();

					resultantSubCategory.setSubCategories((List<SubCategory>) returnedObject);
					resultantSubCategory.setErrors(errors);
					resultantSubCategory.setStatus(Status.SUCCESS);
					resultantSubCategory.setLinks(GenerateLinkService.mapOfLinks.get("getSubCategories"));
				} else {
					// Creating ResultantCategory object
					resultantSubCategory = new ResultantSubCategory();

					resultantSubCategory.setSubCategories(subCategories);
					resultantSubCategory.setErrors(errors);
					resultantSubCategory.setStatus(Status.SUCCESS);
					resultantSubCategory.setLinks(GenerateLinkService.mapOfLinks.get("getSubCategories"));
				}
			}
		} catch (Exception e) {
			// Creating Error for updating subcategory
			com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
			error.setErrorCode(Errors.SUBCATEGORY_ERROR.getErrorCode());
			error.setErrorName(((Exception) e).getMessage());

			// wrapping the error into errors list
			errors = new ArrayList<com.couponsworld.apiresults.Error>();
			errors.add(error);
			// Creating ResultantCategory object
			resultantSubCategory = new ResultantSubCategory();

			resultantSubCategory.setSubCategories(subCategories);
			resultantSubCategory.setErrors(errors);
			resultantSubCategory.setStatus(Status.FAILURE);
			resultantSubCategory.setLinks(GenerateLinkService.mapOfLinks.get("getSubCategories"));
			subCategories = null;
			errors = null;
		}
		subCategories = null;
		errors = null;
		return resultantSubCategory;
	}

	public static ResultantSubCategory updateSubCategory(long subCategoryId, SubCategory subCategory) {
		try {
			Object returnedObject = DatabaseService.updateSubCategoryInDatabase(subCategoryId, subCategory);
			if (returnedObject instanceof SubCategory) {

				// wrapping the SubCategory into SubCategory list
				subCategories = new ArrayList<SubCategory>();
				subCategories.add((SubCategory) returnedObject);

				resultantSubCategory = new ResultantSubCategory();
				resultantSubCategory.setSubCategories(subCategories);
				resultantSubCategory.setErrors(errors);
				resultantSubCategory.setStatus(Status.SUCCESS);
				resultantSubCategory.setLinks(GenerateLinkService.mapOfLinks.get("updateSubCategory"));
			} else if (returnedObject instanceof SubCategoryException) {
				// Creating Error for updating Offer
				com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
				error.setErrorCode(Errors.SUBCATEGORY_ERROR.getErrorCode());
				error.setErrorName(((SubCategoryException) returnedObject).getMessage());

				// wrapping the error into errors list
				errors = new ArrayList<com.couponsworld.apiresults.Error>();
				errors.add(error);

				// wrapping the SubCategory into SubCategory list
				subCategories = new ArrayList<SubCategory>();
				subCategories.add(subCategory);

				resultantSubCategory = new ResultantSubCategory();
				resultantSubCategory.setSubCategories(subCategories);
				resultantSubCategory.setErrors(errors);
				resultantSubCategory.setStatus(Status.FAILURE);
				resultantSubCategory.setLinks(GenerateLinkService.mapOfLinks.get("updateSubCategory"));
			} else {
				// Creating Error for updating Offer
				com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
				error.setErrorCode(Errors.GENERAL_ERROR.getErrorCode());
				error.setErrorName(((Exception) returnedObject).getMessage());

				// wrapping the error into errors list
				errors = new ArrayList<com.couponsworld.apiresults.Error>();
				errors.add(error);

				// wrapping the SubCategory into SubCategory list
				subCategories = new ArrayList<SubCategory>();
				subCategories.add(subCategory);

				resultantSubCategory = new ResultantSubCategory();
				resultantSubCategory.setSubCategories(subCategories);
				resultantSubCategory.setErrors(errors);
				resultantSubCategory.setStatus(Status.FAILURE);
				resultantSubCategory.setLinks(GenerateLinkService.mapOfLinks.get("updateSubCategory"));
			}
		} catch (Exception e) {
			// Creating Error for updating Offer
			com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
			error.setErrorCode(Errors.GENERAL_ERROR.getErrorCode());
			error.setErrorName(e.getMessage());

			// wrapping the SubCategory into SubCategory list
			subCategories = new ArrayList<SubCategory>();
			subCategories.add(subCategory);

			// wrapping the error into errors list
			errors = new ArrayList<com.couponsworld.apiresults.Error>();
			errors.add(error);

			resultantSubCategory = new ResultantSubCategory();
			resultantSubCategory.setSubCategories(subCategories);
			resultantSubCategory.setErrors(errors);
			resultantSubCategory.setStatus(Status.FAILURE);
			resultantSubCategory.setLinks(GenerateLinkService.mapOfLinks.get("updateSubCategory"));
			subCategories = null;
			errors = null;
		}
		subCategories = null;
		errors = null;
		return resultantSubCategory;
	}

	public static ResultantSubCategory deleteSubCategory(long subCategoryId) {
		try {
			Object returnedObject = DatabaseService.deleteSubCategoryFromDatabase(subCategoryId);
			if (returnedObject instanceof SubCategory) {
				// wrapping the SubCategory into SubCategory list
				subCategories = new ArrayList<SubCategory>();
				subCategories.add((SubCategory) returnedObject);

				resultantSubCategory = new ResultantSubCategory();
				resultantSubCategory.setSubCategories(subCategories);
				resultantSubCategory.setErrors(errors);
				resultantSubCategory.setStatus(Status.SUCCESS);
				resultantSubCategory.setLinks(GenerateLinkService.mapOfLinks.get("deleteSubCategory"));
			} else if (returnedObject instanceof SubCategoryException) {
				// Creating Error for updating Offer
				com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
				error.setErrorCode(Errors.SUBCATEGORY_ERROR.getErrorCode());
				error.setErrorName(((SubCategoryException) returnedObject).getMessage());

				// wrapping the error into errors list
				errors = new ArrayList<com.couponsworld.apiresults.Error>();
				errors.add(error);

				resultantSubCategory = new ResultantSubCategory();
				resultantSubCategory.setSubCategories(subCategories);
				resultantSubCategory.setErrors(errors);
				resultantSubCategory.setStatus(Status.FAILURE);
				resultantSubCategory.setLinks(GenerateLinkService.mapOfLinks.get("deleteSubCategory"));
			} else {
				// Creating Error for updating Offer
				com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
				error.setErrorCode(Errors.GENERAL_ERROR.getErrorCode());
				error.setErrorName(((Exception) returnedObject).getMessage());

				// wrapping the error into errors list
				errors = new ArrayList<com.couponsworld.apiresults.Error>();
				errors.add(error);

				resultantSubCategory = new ResultantSubCategory();
				resultantSubCategory.setSubCategories(subCategories);
				resultantSubCategory.setErrors(errors);
				resultantSubCategory.setStatus(Status.FAILURE);
				resultantSubCategory.setLinks(GenerateLinkService.mapOfLinks.get("deleteSubCategory"));
			}
		} catch (Exception e) {
			// Creating Error for updating Offer
			com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
			error.setErrorCode(Errors.GENERAL_ERROR.getErrorCode());
			error.setErrorName(((Exception) e).getMessage());

			// wrapping the error into errors list
			errors = new ArrayList<com.couponsworld.apiresults.Error>();
			errors.add(error);

			resultantSubCategory = new ResultantSubCategory();
			resultantSubCategory.setSubCategories(subCategories);
			resultantSubCategory.setErrors(errors);
			resultantSubCategory.setStatus(Status.FAILURE);
			resultantSubCategory.setLinks(GenerateLinkService.mapOfLinks.get("deleteSubCategory"));
			subCategories = null;
			errors = null;
		}
		subCategories = null;
		errors = null;
		return resultantSubCategory;
	}

}
