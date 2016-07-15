package com.couponsworld.services;

import java.util.ArrayList;
import java.util.List;

import com.couponsworld.apiresults.ResultantCategory;
import com.couponsworld.apiresults.ResultantCategorySubCategoryMapping;
import com.couponsworld.database.DatabaseService;
import com.couponsworld.dto.Category;
import com.couponsworld.dto.CategorySubCategoryMapping;
import com.couponsworld.enums.Errors;
import com.couponsworld.enums.Status;
import com.couponsworld.exceptions.CategoryException;
import com.couponsworld.exceptions.CategorySubCategoryMappingException;
import com.couponsworld.utilities.GenerateLinkService;

public class CategorySubCategoryMappingService {
	public static List<CategorySubCategoryMapping> categorySubCategoryMappings = null;
	public static List<com.couponsworld.apiresults.Error> errors = null;
	public static ResultantCategorySubCategoryMapping resultantCategorySubCategoryMapping;

	public static ResultantCategorySubCategoryMapping createCategorySubCategoryMapping(
			CategorySubCategoryMapping categorySubCategoryMapping) {
		try {
			Object returnedObject = DatabaseService
					.createCategorySubCategoryMappingInDatabase(categorySubCategoryMapping);
			if (returnedObject instanceof CategorySubCategoryMapping) {

				// wrapping the categorySubCategoryMapping into
				// categorySubCategoryMappings list
				categorySubCategoryMappings = new ArrayList<CategorySubCategoryMapping>();
				categorySubCategoryMappings.add((CategorySubCategoryMapping) returnedObject);

				// Creating resultantCategorySubCategoryMapping object
				resultantCategorySubCategoryMapping = new ResultantCategorySubCategoryMapping();

				resultantCategorySubCategoryMapping.setCategorySubCategoryMappings(categorySubCategoryMappings);
				resultantCategorySubCategoryMapping.setErrors(errors);
				resultantCategorySubCategoryMapping.setStatus(Status.SUCCESS);
				resultantCategorySubCategoryMapping.setLinks(
						GenerateLinkService.generateCategorySubCategoryMappingLink("createCategorySubCategoryMapping"));
			} else {
				// Creating Error for updating Offer
				com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
				error.setErrorCode(Errors.GENERAL_ERROR.getErrorCode());
				error.setErrorName(((Exception) returnedObject).getMessage());

				// wrapping the categorySubCategoryMapping into
				// categorySubCategoryMappings list
				categorySubCategoryMappings = new ArrayList<CategorySubCategoryMapping>();
				categorySubCategoryMappings.add(categorySubCategoryMapping);

				// wrapping the error into errors list
				errors = new ArrayList<com.couponsworld.apiresults.Error>();
				errors.add(error);

				// Creating resultantCategorySubCategoryMapping object
				resultantCategorySubCategoryMapping = new ResultantCategorySubCategoryMapping();

				resultantCategorySubCategoryMapping.setCategorySubCategoryMappings(categorySubCategoryMappings);
				resultantCategorySubCategoryMapping.setErrors(errors);
				resultantCategorySubCategoryMapping.setStatus(Status.FAILURE);
				resultantCategorySubCategoryMapping.setLinks(
						GenerateLinkService.generateCategorySubCategoryMappingLink("createCategorySubCategoryMapping"));
			}
		} catch (Exception exception) {
			// Creating Error for updating Offer
			com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
			error.setErrorCode(Errors.GENERAL_ERROR.getErrorCode());
			error.setErrorName(exception.getMessage());

			// wrapping the categorySubCategoryMapping into
			// categorySubCategoryMappings list
			categorySubCategoryMappings = new ArrayList<CategorySubCategoryMapping>();
			categorySubCategoryMappings.add(categorySubCategoryMapping);

			// wrapping the error into errors list
			errors = new ArrayList<com.couponsworld.apiresults.Error>();
			errors.add(error);

			// Creating resultantCategorySubCategoryMapping object
			resultantCategorySubCategoryMapping = new ResultantCategorySubCategoryMapping();

			resultantCategorySubCategoryMapping.setCategorySubCategoryMappings(categorySubCategoryMappings);
			resultantCategorySubCategoryMapping.setErrors(errors);
			resultantCategorySubCategoryMapping.setStatus(Status.FAILURE);
			resultantCategorySubCategoryMapping.setLinks(
					GenerateLinkService.generateCategorySubCategoryMappingLink("createCategorySubCategoryMapping"));

		}
		categorySubCategoryMappings = null;
		errors = null;
		return resultantCategorySubCategoryMapping;
	}

	public static ResultantCategorySubCategoryMapping getCategorySubCategoryMappings() {
		try {
			Object returnedObject = DatabaseService.getCategorySubCategoryMappingsFromDatabase();
			if (returnedObject instanceof Exception) {
				// Creating Error for updating Offer
				com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
				error.setErrorCode(Errors.GENERAL_ERROR.getErrorCode());
				error.setErrorName(((Exception) returnedObject).getMessage());

				// wrapping the error into errors list
				errors = new ArrayList<com.couponsworld.apiresults.Error>();
				errors.add(error);

				// Creating resultantCategorySubCategoryMapping object
				resultantCategorySubCategoryMapping = new ResultantCategorySubCategoryMapping();

				resultantCategorySubCategoryMapping.setCategorySubCategoryMappings(categorySubCategoryMappings);
				resultantCategorySubCategoryMapping.setErrors(errors);
				resultantCategorySubCategoryMapping.setStatus(Status.FAILURE);
				resultantCategorySubCategoryMapping.setLinks(
						GenerateLinkService.generateCategorySubCategoryMappingLink("getCategorySubCategoryMappings"));

			} else if (returnedObject instanceof CategorySubCategoryMappingException) {
				// Creating Error for updating Offer
				com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
				error.setErrorCode(Errors.CATEGORY_SUBCATEGORY_MAPPING_ERROR.getErrorCode());
				error.setErrorName(((CategorySubCategoryMappingException) returnedObject).getMessage());

				// wrapping the error into errors list
				errors = new ArrayList<com.couponsworld.apiresults.Error>();
				errors.add(error);

				// Creating resultantCategorySubCategoryMapping object
				resultantCategorySubCategoryMapping = new ResultantCategorySubCategoryMapping();

				resultantCategorySubCategoryMapping.setCategorySubCategoryMappings(categorySubCategoryMappings);
				resultantCategorySubCategoryMapping.setErrors(errors);
				resultantCategorySubCategoryMapping.setStatus(Status.FAILURE);
				resultantCategorySubCategoryMapping.setLinks(
						GenerateLinkService.generateCategorySubCategoryMappingLink("getCategorySubCategoryMappings"));

			} else {

				if (((List<Category>) returnedObject).size() > 0) {

					// Creating resultantCategorySubCategoryMapping object
					resultantCategorySubCategoryMapping = new ResultantCategorySubCategoryMapping();

					resultantCategorySubCategoryMapping
							.setCategorySubCategoryMappings((List<CategorySubCategoryMapping>) returnedObject);
					resultantCategorySubCategoryMapping.setErrors(errors);
					resultantCategorySubCategoryMapping.setStatus(Status.SUCCESS);
					resultantCategorySubCategoryMapping.setLinks(GenerateLinkService
							.generateCategorySubCategoryMappingLink("getCategorySubCategoryMappings"));

				} else {

					// Creating resultantCategorySubCategoryMapping object
					resultantCategorySubCategoryMapping = new ResultantCategorySubCategoryMapping();

					resultantCategorySubCategoryMapping.setCategorySubCategoryMappings(categorySubCategoryMappings);
					resultantCategorySubCategoryMapping.setErrors(errors);
					resultantCategorySubCategoryMapping.setStatus(Status.SUCCESS);
					resultantCategorySubCategoryMapping.setLinks(GenerateLinkService
							.generateCategorySubCategoryMappingLink("getCategorySubCategoryMappings"));
				}
			}
		} catch (Exception e) {
			// Creating Error for updating Offer
			com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
			error.setErrorCode(Errors.GENERAL_ERROR.getErrorCode());
			error.setErrorName(e.getMessage());

			// wrapping the error into errors list
			errors = new ArrayList<com.couponsworld.apiresults.Error>();
			errors.add(error);

			// Creating resultantCategorySubCategoryMapping object
			resultantCategorySubCategoryMapping = new ResultantCategorySubCategoryMapping();

			resultantCategorySubCategoryMapping.setCategorySubCategoryMappings(categorySubCategoryMappings);
			resultantCategorySubCategoryMapping.setErrors(errors);
			resultantCategorySubCategoryMapping.setStatus(Status.FAILURE);
			resultantCategorySubCategoryMapping.setLinks(
					GenerateLinkService.generateCategorySubCategoryMappingLink("getCategorySubCategoryMappings"));

			categorySubCategoryMappings = null;
			errors = null;
		}
		categorySubCategoryMappings = null;
		errors = null;
		return resultantCategorySubCategoryMapping;
	}

	public static ResultantCategorySubCategoryMapping updateCategorySubCategoryMapping(
			long categorySubCategoryMappingId, CategorySubCategoryMapping categorySubCategoryMapping) {
		try {
			Object returnedObject = DatabaseService.updateCategorySubCategoryMappingInDatabase(
					categorySubCategoryMappingId, categorySubCategoryMapping);
			if (returnedObject instanceof CategorySubCategoryMapping) {
				// wrapping the categorySubCategoryMapping into
				// categorySubCategoryMappings list
				categorySubCategoryMappings = new ArrayList<CategorySubCategoryMapping>();
				categorySubCategoryMappings.add((CategorySubCategoryMapping) returnedObject);

				// Creating resultantCategorySubCategoryMapping object
				resultantCategorySubCategoryMapping = new ResultantCategorySubCategoryMapping();

				resultantCategorySubCategoryMapping.setCategorySubCategoryMappings(categorySubCategoryMappings);
				resultantCategorySubCategoryMapping.setErrors(errors);
				resultantCategorySubCategoryMapping.setStatus(Status.SUCCESS);
				resultantCategorySubCategoryMapping.setLinks(
						GenerateLinkService.generateCategorySubCategoryMappingLink("updateCategorySubCategoryMapping"));

			} else if (returnedObject instanceof CategorySubCategoryMappingException) {
				// Creating Error for updating Offer
				com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
				error.setErrorCode(Errors.CATEGORY_SUBCATEGORY_MAPPING_ERROR.getErrorCode());
				error.setErrorName(((CategorySubCategoryMappingException) returnedObject).getMessage());

				// wrapping the error into errors list
				errors = new ArrayList<com.couponsworld.apiresults.Error>();
				errors.add(error);

				// wrapping the categorySubCategoryMapping into
				// categorySubCategoryMappings list
				categorySubCategoryMappings = new ArrayList<CategorySubCategoryMapping>();
				categorySubCategoryMappings.add(categorySubCategoryMapping);

				// Creating resultantCategorySubCategoryMapping object
				resultantCategorySubCategoryMapping = new ResultantCategorySubCategoryMapping();

				resultantCategorySubCategoryMapping.setCategorySubCategoryMappings(categorySubCategoryMappings);
				resultantCategorySubCategoryMapping.setErrors(errors);
				resultantCategorySubCategoryMapping.setStatus(Status.FAILURE);
				resultantCategorySubCategoryMapping.setLinks(
						GenerateLinkService.generateCategorySubCategoryMappingLink("updateCategorySubCategoryMapping"));
			} else {
				// Creating Error for updating Offer
				com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
				error.setErrorCode(Errors.GENERAL_ERROR.getErrorCode());
				error.setErrorName(((Exception) returnedObject).getMessage());

				// wrapping the error into errors list
				errors = new ArrayList<com.couponsworld.apiresults.Error>();
				errors.add(error);

				// wrapping the categorySubCategoryMapping into
				// categorySubCategoryMappings list
				categorySubCategoryMappings = new ArrayList<CategorySubCategoryMapping>();
				categorySubCategoryMappings.add(categorySubCategoryMapping);

				// Creating resultantCategorySubCategoryMapping object
				resultantCategorySubCategoryMapping = new ResultantCategorySubCategoryMapping();

				resultantCategorySubCategoryMapping.setCategorySubCategoryMappings(categorySubCategoryMappings);
				resultantCategorySubCategoryMapping.setErrors(errors);
				resultantCategorySubCategoryMapping.setStatus(Status.FAILURE);
				resultantCategorySubCategoryMapping.setLinks(
						GenerateLinkService.generateCategorySubCategoryMappingLink("updateCategorySubCategoryMapping"));

			}
		} catch (Exception e) {
			// Creating Error for updating Offer
			com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
			error.setErrorCode(Errors.GENERAL_ERROR.getErrorCode());
			error.setErrorName(e.getMessage());

			// wrapping the categorySubCategoryMapping into
			// categorySubCategoryMappings list
			categorySubCategoryMappings = new ArrayList<CategorySubCategoryMapping>();
			categorySubCategoryMappings.add(categorySubCategoryMapping);

			// wrapping the error into errors list
			errors = new ArrayList<com.couponsworld.apiresults.Error>();
			errors.add(error);

			// Creating resultantCategorySubCategoryMapping object
			resultantCategorySubCategoryMapping = new ResultantCategorySubCategoryMapping();

			resultantCategorySubCategoryMapping.setCategorySubCategoryMappings(categorySubCategoryMappings);
			resultantCategorySubCategoryMapping.setErrors(errors);
			resultantCategorySubCategoryMapping.setStatus(Status.FAILURE);
			resultantCategorySubCategoryMapping.setLinks(
					GenerateLinkService.generateCategorySubCategoryMappingLink("updateCategorySubCategoryMapping"));

			categorySubCategoryMappings = null;
			errors = null;
		}
		categorySubCategoryMappings = null;
		errors = null;
		return resultantCategorySubCategoryMapping;
	}

	public static ResultantCategorySubCategoryMapping deleteCategorySubCategoryMapping(
			long categorySubCategoryMappingId) {
		try {
			Object returnedObject = DatabaseService
					.deleteCategorySubCategoryMappingFromDatabase(categorySubCategoryMappingId);
			if (returnedObject instanceof CategorySubCategoryMapping) {
				// wrapping the categorySubCategoryMapping into
				// categorySubCategoryMappings list
				categorySubCategoryMappings = new ArrayList<CategorySubCategoryMapping>();
				categorySubCategoryMappings.add((CategorySubCategoryMapping) returnedObject);

				// Creating resultantCategorySubCategoryMapping object
				resultantCategorySubCategoryMapping = new ResultantCategorySubCategoryMapping();

				resultantCategorySubCategoryMapping.setCategorySubCategoryMappings(categorySubCategoryMappings);
				resultantCategorySubCategoryMapping.setErrors(errors);
				resultantCategorySubCategoryMapping.setStatus(Status.SUCCESS);
				resultantCategorySubCategoryMapping.setLinks(
						GenerateLinkService.generateCategorySubCategoryMappingLink("deleteCategorySubCategoryMapping"));
			} else if (returnedObject instanceof CategorySubCategoryMappingException) {
				// Creating Error for updating Offer
				com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
				error.setErrorCode(Errors.CATEGORY_SUBCATEGORY_MAPPING_ERROR.getErrorCode());
				error.setErrorName(((CategorySubCategoryMappingException) returnedObject).getMessage());

				// wrapping the error into errors list
				errors = new ArrayList<com.couponsworld.apiresults.Error>();
				errors.add(error);

				// Creating resultantCategorySubCategoryMapping object
				resultantCategorySubCategoryMapping = new ResultantCategorySubCategoryMapping();

				resultantCategorySubCategoryMapping.setCategorySubCategoryMappings(categorySubCategoryMappings);
				resultantCategorySubCategoryMapping.setErrors(errors);
				resultantCategorySubCategoryMapping.setStatus(Status.FAILURE);
				resultantCategorySubCategoryMapping.setLinks(
						GenerateLinkService.generateCategorySubCategoryMappingLink("deleteCategorySubCategoryMapping"));

			} else {
				// Creating Error for updating Offer
				com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
				error.setErrorCode(Errors.GENERAL_ERROR.getErrorCode());
				error.setErrorName(((Exception) returnedObject).getMessage());

				// wrapping the error into errors list
				errors = new ArrayList<com.couponsworld.apiresults.Error>();
				errors.add(error);

				// Creating resultantCategorySubCategoryMapping object
				resultantCategorySubCategoryMapping = new ResultantCategorySubCategoryMapping();

				resultantCategorySubCategoryMapping.setCategorySubCategoryMappings(categorySubCategoryMappings);
				resultantCategorySubCategoryMapping.setErrors(errors);
				resultantCategorySubCategoryMapping.setStatus(Status.FAILURE);
				resultantCategorySubCategoryMapping.setLinks(
						GenerateLinkService.generateCategorySubCategoryMappingLink("deleteCategorySubCategoryMapping"));
			}
		} catch (Exception e) {
			// Creating Error for updating Offer
			com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
			error.setErrorCode(Errors.GENERAL_ERROR.getErrorCode());
			error.setErrorName(e.getMessage());

			// wrapping the error into errors list
			errors = new ArrayList<com.couponsworld.apiresults.Error>();
			errors.add(error);

			// Creating resultantCategorySubCategoryMapping object
			resultantCategorySubCategoryMapping = new ResultantCategorySubCategoryMapping();

			resultantCategorySubCategoryMapping.setCategorySubCategoryMappings(categorySubCategoryMappings);
			resultantCategorySubCategoryMapping.setErrors(errors);
			resultantCategorySubCategoryMapping.setStatus(Status.FAILURE);
			resultantCategorySubCategoryMapping.setLinks(
					GenerateLinkService.generateCategorySubCategoryMappingLink("deleteCategorySubCategoryMapping"));

			categorySubCategoryMappings = null;
			errors = null;
		}
		categorySubCategoryMappings = null;
		errors = null;
		return resultantCategorySubCategoryMapping;
	}

}
