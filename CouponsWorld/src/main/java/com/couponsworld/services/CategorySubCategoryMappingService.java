package com.couponsworld.services;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

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

	// declaration of logger
	private static final Logger log = Logger.getLogger(CategorySubCategoryMappingService.class.getName());

	public static List<CategorySubCategoryMapping> categorySubCategoryMappings = null;
	public static List<com.couponsworld.apiresults.Error> errors = null;
	public static ResultantCategorySubCategoryMapping resultantCategorySubCategoryMapping;

	public static ResultantCategorySubCategoryMapping createCategorySubCategoryMapping(
			CategorySubCategoryMapping categorySubCategoryMapping) {
		try {
			log.info(
					"=========================== Category Sub Category Mapping Service - Start =================================");
			Object returnedObject = DatabaseService
					.createCategorySubCategoryMappingInDatabase(categorySubCategoryMapping);
			if (returnedObject instanceof CategorySubCategoryMapping) {
				log.info("Category Sub Category Mapping Service wrapping into resultantCategorySubCategoryMapping ..");
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
				log.info("ResultantCategorySubCategoryMapping object returned successfully..");
				log.info(
						"=========================== Category Sub Category Mapping Service - End =================================");
			} else {
				// Creating Error for updating Offer
				com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
				error.setErrorCode(Errors.GENERAL_ERROR.getErrorCode());
				error.setErrorName(((Exception) returnedObject).getMessage());
				log.info("Error Occured while creating the categorySubCategoryMappings..:" + error.getErrorCode() + ":"
						+ error.getErrorName());
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
				log.info("ResultantCategorySubCategoryMapping object returned successfully..");
				log.info(
						"=========================== Category Sub Category Mapping Service - End =================================");
			}
		} catch (Exception exception) {
			// Creating Error for updating Offer
			com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
			error.setErrorCode(Errors.GENERAL_ERROR.getErrorCode());
			error.setErrorName(exception.getMessage());
			log.info("Error Occured while creating the categorySubCategoryMappings..:" + error.getErrorCode() + ":"
					+ error.getErrorName());
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
			log.info("ResultantCategorySubCategoryMapping object returned successfully..");
			log.info(
					"=========================== Category Sub Category Mapping Service - End =================================");
		}
		categorySubCategoryMappings = null;
		errors = null;
		return resultantCategorySubCategoryMapping;
	}

	public static ResultantCategorySubCategoryMapping getCategorySubCategoryMappings() {
		try {
			log.info(
					"=========================== Category Sub Category Mapping Service - Start =================================");
			Object returnedObject = DatabaseService.getCategorySubCategoryMappingsFromDatabase();
			if (returnedObject instanceof Exception) {

				// Creating Error for updating Offer
				com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
				error.setErrorCode(Errors.GENERAL_ERROR.getErrorCode());
				error.setErrorName(((Exception) returnedObject).getMessage());
				log.info("Error Occured while fetching all the categorySubCategoryMappings..:" + error.getErrorCode()
						+ ":" + error.getErrorName());
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
				log.info("ResultantCategorySubCategoryMapping object returned successfully..");
				log.info(
						"=========================== Category Sub Category Mapping Service - End =================================");

			} else if (returnedObject instanceof CategorySubCategoryMappingException) {
				// Creating Error for updating Offer
				com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
				error.setErrorCode(Errors.CATEGORY_SUBCATEGORY_MAPPING_ERROR.getErrorCode());
				error.setErrorName(((CategorySubCategoryMappingException) returnedObject).getMessage());
				log.info("Error Occured while fetching all categorySubCategoryMappings..:" + error.getErrorCode() + ":"
						+ error.getErrorName());
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
				log.info("ResultantCategorySubCategoryMapping object returned successfully..");
				log.info(
						"=========================== Category Sub Category Mapping Service - End =================================");

			} else {

				if (((List<Category>) returnedObject).size() > 0) {
					log.info("Wrapping the list of CategorySubCategoryMapping to resultantCategorySubCategoryMapping");
					// Creating resultantCategorySubCategoryMapping object
					resultantCategorySubCategoryMapping = new ResultantCategorySubCategoryMapping();

					resultantCategorySubCategoryMapping
							.setCategorySubCategoryMappings((List<CategorySubCategoryMapping>) returnedObject);
					resultantCategorySubCategoryMapping.setErrors(errors);
					resultantCategorySubCategoryMapping.setStatus(Status.SUCCESS);
					resultantCategorySubCategoryMapping.setLinks(GenerateLinkService
							.generateCategorySubCategoryMappingLink("getCategorySubCategoryMappings"));

				} else {
					log.info("Wrapping the CategorySubCategoryMapping=null to resultantCategorySubCategoryMapping");
					// Creating resultantCategorySubCategoryMapping object
					resultantCategorySubCategoryMapping = new ResultantCategorySubCategoryMapping();

					resultantCategorySubCategoryMapping.setCategorySubCategoryMappings(categorySubCategoryMappings);
					resultantCategorySubCategoryMapping.setErrors(errors);
					resultantCategorySubCategoryMapping.setStatus(Status.SUCCESS);
					resultantCategorySubCategoryMapping.setLinks(GenerateLinkService
							.generateCategorySubCategoryMappingLink("getCategorySubCategoryMappings"));
				}
				log.info("ResultantCategorySubCategoryMapping object returned successfully..");
				log.info(
						"=========================== Category Sub Category Mapping Service - End =================================");

			}
		} catch (Exception e) {
			// Creating Error for updating Offer
			com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
			error.setErrorCode(Errors.GENERAL_ERROR.getErrorCode());
			error.setErrorName(e.getMessage());
			log.info("Error Occured while fetching all categorySubCategoryMappings..:" + error.getErrorCode() + ":"
					+ error.getErrorName());
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
			log.info("ResultantCategorySubCategoryMapping object returned successfully..");
			log.info(
					"=========================== Category Sub Category Mapping Service - End =================================");

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
			log.info(
					"=========================== Category Sub Category Mapping Service - Start =================================");
			Object returnedObject = DatabaseService.updateCategorySubCategoryMappingInDatabase(
					categorySubCategoryMappingId, categorySubCategoryMapping);
			if (returnedObject instanceof CategorySubCategoryMapping) {
				log.info("Wrapping the list of CategorySubCategoryMapping to resultantCategorySubCategoryMapping");
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
				log.info("ResultantCategorySubCategoryMapping object returned successfully..");
				log.info(
						"=========================== Category Sub Category Mapping Service - End =================================");
			} else if (returnedObject instanceof CategorySubCategoryMappingException) {

				// Creating Error for updating Offer
				com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
				error.setErrorCode(Errors.CATEGORY_SUBCATEGORY_MAPPING_ERROR.getErrorCode());
				error.setErrorName(((CategorySubCategoryMappingException) returnedObject).getMessage());
				log.info("Error Occured while updating the categorySubCategoryMappings..:" + error.getErrorCode() + ":"
						+ error.getErrorName());
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
				log.info("ResultantCategorySubCategoryMapping object returned successfully..");
				log.info(
						"=========================== Category Sub Category Mapping Service - End =================================");
			} else {
				// Creating Error for updating Offer
				com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
				error.setErrorCode(Errors.GENERAL_ERROR.getErrorCode());
				error.setErrorName(((Exception) returnedObject).getMessage());
				log.info("Error Occured while updating the categorySubCategoryMappings..:" + error.getErrorCode() + ":"
						+ error.getErrorName());
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
				log.info("ResultantCategorySubCategoryMapping object returned successfully..");
				log.info(
						"=========================== Category Sub Category Mapping Service - End =================================");
			}
		} catch (Exception e) {
			// Creating Error for updating Offer
			com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
			error.setErrorCode(Errors.GENERAL_ERROR.getErrorCode());
			error.setErrorName(e.getMessage());
			log.info("Error Occured while updating the categorySubCategoryMappings..:" + error.getErrorCode() + ":"
					+ error.getErrorName());
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
			log.info("ResultantCategorySubCategoryMapping object returned successfully..");
			log.info(
					"=========================== Category Sub Category Mapping Service - End =================================");
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
			log.info(
					"=========================== Category Sub Category Mapping Service - Start =================================");
			Object returnedObject = DatabaseService
					.deleteCategorySubCategoryMappingFromDatabase(categorySubCategoryMappingId);
			if (returnedObject instanceof CategorySubCategoryMapping) {
				log.info("Wrapping the list of CategorySubCategoryMapping to resultantCategorySubCategoryMapping");
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
				log.info("ResultantCategorySubCategoryMapping object returned successfully..");
				log.info(
						"=========================== Category Sub Category Mapping Service - End =================================");
			} else if (returnedObject instanceof CategorySubCategoryMappingException) {
				// Creating Error for updating Offer
				com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
				error.setErrorCode(Errors.CATEGORY_SUBCATEGORY_MAPPING_ERROR.getErrorCode());
				error.setErrorName(((CategorySubCategoryMappingException) returnedObject).getMessage());
				log.info("Error Occured while deleting the categorySubCategoryMappings..:" + error.getErrorCode() + ":"
						+ error.getErrorName());
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
				log.info("ResultantCategorySubCategoryMapping object returned successfully..");
				log.info(
						"=========================== Category Sub Category Mapping Service - End =================================");
			} else {
				// Creating Error for updating Offer
				com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
				error.setErrorCode(Errors.GENERAL_ERROR.getErrorCode());
				error.setErrorName(((Exception) returnedObject).getMessage());
				log.info("Error Occured while deleting the categorySubCategoryMappings..:" + error.getErrorCode() + ":"
						+ error.getErrorName());
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
				log.info("ResultantCategorySubCategoryMapping object returned successfully..");
				log.info(
						"=========================== Category Sub Category Mapping Service - End =================================");
			}
		} catch (Exception e) {
			// Creating Error for updating Offer
			com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
			error.setErrorCode(Errors.GENERAL_ERROR.getErrorCode());
			error.setErrorName(e.getMessage());

			// wrapping the error into errors list
			errors = new ArrayList<com.couponsworld.apiresults.Error>();
			errors.add(error);
			log.info("Error Occured while deleting the categorySubCategoryMappings..:" + error.getErrorCode() + ":"
					+ error.getErrorName());
			// Creating resultantCategorySubCategoryMapping object
			resultantCategorySubCategoryMapping = new ResultantCategorySubCategoryMapping();

			resultantCategorySubCategoryMapping.setCategorySubCategoryMappings(categorySubCategoryMappings);
			resultantCategorySubCategoryMapping.setErrors(errors);
			resultantCategorySubCategoryMapping.setStatus(Status.FAILURE);
			resultantCategorySubCategoryMapping.setLinks(
					GenerateLinkService.generateCategorySubCategoryMappingLink("deleteCategorySubCategoryMapping"));
			log.info("ResultantCategorySubCategoryMapping object returned successfully..");
			log.info(
					"=========================== Category Sub Category Mapping Service - End =================================");
			categorySubCategoryMappings = null;
			errors = null;
		}
		categorySubCategoryMappings = null;
		errors = null;
		return resultantCategorySubCategoryMapping;
	}

}
