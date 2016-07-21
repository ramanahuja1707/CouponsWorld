package com.couponsworld.services;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

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

	// declaration of logger
	private static final Logger log = Logger.getLogger(SubCategoryService.class.getName());

	public static List<SubCategory> subCategories = null;
	public static List<com.couponsworld.apiresults.Error> errors = null;
	public static ResultantSubCategory resultantSubCategory;

	public static ResultantSubCategory createSubCategory(SubCategory subCategory) {
		try {
			log.info("=========================== Sub Category Service - Start =================================");
			Object returnedObject = DatabaseService.createSubCategoryInDatabase(subCategory);
			if (returnedObject instanceof SubCategory) {
				log.info("sub Category wrapping into resultantSubCategory ..");
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
				log.info("ResultantSubCategory object returned successfully..");
				log.info("=============================== Sub Category Service - End ===========================");
			} else {
				// Creating Error for updating Subcategory
				com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
				error.setErrorCode(Errors.GENERAL_ERROR.getErrorCode());
				error.setErrorName(((Exception) returnedObject).getMessage());
				log.info("Error Occured while creating the sub category..:" + error.getErrorCode() + ":"
						+ error.getErrorName());
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
				log.info("ResultantSubCategory object returned successfully..");
				log.info("=============================== Sub Category Service - End ===========================");
			}
		} catch (Exception exception) {
			// Creating Error for updating Offer
			com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
			error.setErrorCode(Errors.GENERAL_ERROR.getErrorCode());
			error.setErrorName(exception.getMessage());
			log.info("Error Occured while creating the sub category..:" + error.getErrorCode() + ":"
					+ error.getErrorName());
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
			log.info("ResultantSubCategory object returned successfully..");
			log.info("=============================== Sub Category Service - End ===========================");
		}
		subCategories = null;
		errors = null;
		return resultantSubCategory;
	}

	public static ResultantSubCategory getSubCategories() {
		try {
			log.info("=========================== Sub Category Service - Start =================================");
			Object returnedObject = DatabaseService.getSubCategoriesFromDatabase();
			if (returnedObject instanceof Exception) {
				// Creating Error for updating Subcategory
				com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
				error.setErrorCode(Errors.GENERAL_ERROR.getErrorCode());
				error.setErrorName(((Exception) returnedObject).getMessage());
				log.info("Error Occured while fetching all the sub categories..:" + error.getErrorCode() + ":"
						+ error.getErrorName());
				// wrapping the error into errors list
				errors = new ArrayList<com.couponsworld.apiresults.Error>();
				errors.add(error);

				// Creating ResultantCategory object
				resultantSubCategory = new ResultantSubCategory();
				resultantSubCategory.setSubCategories(subCategories);
				resultantSubCategory.setErrors(errors);
				resultantSubCategory.setStatus(Status.FAILURE);
				resultantSubCategory.setLinks(GenerateLinkService.mapOfLinks.get("getSubCategories"));
				log.info("ResultantSubCategory object returned successfully..");
				log.info("=============================== Sub Category Service - End ===========================");
			} else if (returnedObject instanceof CategoryException) {
				// Creating Error for updating subcategory
				com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
				error.setErrorCode(Errors.SUBCATEGORY_ERROR.getErrorCode());
				error.setErrorName(((CategoryException) returnedObject).getMessage());
				log.info("Error Occured while fetching all the sub categories..:" + error.getErrorCode() + ":"
						+ error.getErrorName());
				// wrapping the error into errors list
				errors = new ArrayList<com.couponsworld.apiresults.Error>();
				errors.add(error);
				// Creating ResultantCategory object
				resultantSubCategory = new ResultantSubCategory();

				resultantSubCategory.setSubCategories(subCategories);
				resultantSubCategory.setErrors(errors);
				resultantSubCategory.setStatus(Status.FAILURE);
				resultantSubCategory.setLinks(GenerateLinkService.mapOfLinks.get("getSubCategories"));
				log.info("ResultantSubCategory object returned successfully..");
				log.info("=============================== Sub Category Service - End ===========================");
			} else {

				if (((List<Category>) returnedObject).size() > 0) {
					log.info("sub Category wrapping into resultantSubCategory ..");
					// Creating ResultantCategory object
					resultantSubCategory = new ResultantSubCategory();

					resultantSubCategory.setSubCategories((List<SubCategory>) returnedObject);
					resultantSubCategory.setErrors(errors);
					resultantSubCategory.setStatus(Status.SUCCESS);
					resultantSubCategory.setLinks(GenerateLinkService.mapOfLinks.get("getSubCategories"));
				} else {
					log.info("subCategory=null wrapping into resultantSubCategory ..");
					// Creating ResultantCategory object
					resultantSubCategory = new ResultantSubCategory();

					resultantSubCategory.setSubCategories(subCategories);
					resultantSubCategory.setErrors(errors);
					resultantSubCategory.setStatus(Status.SUCCESS);
					resultantSubCategory.setLinks(GenerateLinkService.mapOfLinks.get("getSubCategories"));
				}
				log.info("ResultantSubCategory object returned successfully..");
				log.info("=============================== Sub Category Service - End ===========================");
			}
		} catch (Exception e) {
			// Creating Error for updating subcategory
			com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
			error.setErrorCode(Errors.SUBCATEGORY_ERROR.getErrorCode());
			error.setErrorName(((Exception) e).getMessage());
			log.info("Error Occured while fetching all the sub categories..:" + error.getErrorCode() + ":"
					+ error.getErrorName());
			// wrapping the error into errors list
			errors = new ArrayList<com.couponsworld.apiresults.Error>();
			errors.add(error);
			// Creating ResultantCategory object
			resultantSubCategory = new ResultantSubCategory();

			resultantSubCategory.setSubCategories(subCategories);
			resultantSubCategory.setErrors(errors);
			resultantSubCategory.setStatus(Status.FAILURE);
			resultantSubCategory.setLinks(GenerateLinkService.mapOfLinks.get("getSubCategories"));
			log.info("ResultantSubCategory object returned successfully..");
			log.info("=============================== Sub Category Service - End ===========================");
			subCategories = null;
			errors = null;
		}
		subCategories = null;
		errors = null;
		return resultantSubCategory;
	}

	public static ResultantSubCategory updateSubCategory(long subCategoryId, SubCategory subCategory) {
		try {
			log.info("=========================== Sub Category Service - Start =================================");
			Object returnedObject = DatabaseService.updateSubCategoryInDatabase(subCategoryId, subCategory);
			if (returnedObject instanceof SubCategory) {
				log.info("sub Category wrapping into resultantSubCategory ..");
				// wrapping the SubCategory into SubCategory list
				subCategories = new ArrayList<SubCategory>();
				subCategories.add((SubCategory) returnedObject);

				resultantSubCategory = new ResultantSubCategory();
				resultantSubCategory.setSubCategories(subCategories);
				resultantSubCategory.setErrors(errors);
				resultantSubCategory.setStatus(Status.SUCCESS);
				resultantSubCategory.setLinks(GenerateLinkService.mapOfLinks.get("updateSubCategory"));
				log.info("ResultantSubCategory object returned successfully..");
				log.info("=============================== Sub Category Service - End ===========================");
			} else if (returnedObject instanceof SubCategoryException) {
				// Creating Error for updating Offer
				com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
				error.setErrorCode(Errors.SUBCATEGORY_ERROR.getErrorCode());
				error.setErrorName(((SubCategoryException) returnedObject).getMessage());
				log.info("Error Occured while updating the sub category..:" + error.getErrorCode() + ":"
						+ error.getErrorName());
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
				log.info("ResultantSubCategory object returned successfully..");
				log.info("=============================== Sub Category Service - End ===========================");
			} else {
				// Creating Error for updating Offer
				com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
				error.setErrorCode(Errors.GENERAL_ERROR.getErrorCode());
				error.setErrorName(((Exception) returnedObject).getMessage());
				log.info("Error Occured while updating the sub category..:" + error.getErrorCode() + ":"
						+ error.getErrorName());
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
				log.info("ResultantSubCategory object returned successfully..");
				log.info("=============================== Sub Category Service - End ===========================");
			}
		} catch (Exception e) {
			// Creating Error for updating Offer
			com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
			error.setErrorCode(Errors.GENERAL_ERROR.getErrorCode());
			error.setErrorName(e.getMessage());
			log.info("Error Occured while updating the sub category..:" + error.getErrorCode() + ":"
					+ error.getErrorName());
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
			log.info("ResultantSubCategory object returned successfully..");
			log.info("=============================== Sub Category Service - End ===========================");
			subCategories = null;
			errors = null;
		}
		subCategories = null;
		errors = null;
		return resultantSubCategory;
	}

	public static ResultantSubCategory deleteSubCategory(long subCategoryId) {
		try {
			log.info("=========================== Sub Category Service - Start =================================");
			Object returnedObject = DatabaseService.deleteSubCategoryFromDatabase(subCategoryId);
			if (returnedObject instanceof SubCategory) {
				log.info("sub Category wrapping into resultantSubCategory ..");
				// wrapping the SubCategory into SubCategory list
				subCategories = new ArrayList<SubCategory>();
				subCategories.add((SubCategory) returnedObject);

				resultantSubCategory = new ResultantSubCategory();
				resultantSubCategory.setSubCategories(subCategories);
				resultantSubCategory.setErrors(errors);
				resultantSubCategory.setStatus(Status.SUCCESS);
				resultantSubCategory.setLinks(GenerateLinkService.mapOfLinks.get("deleteSubCategory"));
				log.info("ResultantSubCategory object returned successfully..");
				log.info("=============================== Sub Category Service - End ===========================");
			} else if (returnedObject instanceof SubCategoryException) {
				// Creating Error for updating Offer
				com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
				error.setErrorCode(Errors.SUBCATEGORY_ERROR.getErrorCode());
				error.setErrorName(((SubCategoryException) returnedObject).getMessage());
				log.info("Error Occured while deleting the sub category..:" + error.getErrorCode() + ":"
						+ error.getErrorName());
				// wrapping the error into errors list
				errors = new ArrayList<com.couponsworld.apiresults.Error>();
				errors.add(error);

				resultantSubCategory = new ResultantSubCategory();
				resultantSubCategory.setSubCategories(subCategories);
				resultantSubCategory.setErrors(errors);
				resultantSubCategory.setStatus(Status.FAILURE);
				resultantSubCategory.setLinks(GenerateLinkService.mapOfLinks.get("deleteSubCategory"));
				log.info("ResultantSubCategory object returned successfully..");
				log.info("=============================== Sub Category Service - End ===========================");
			} else {
				// Creating Error for updating Offer
				com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
				error.setErrorCode(Errors.GENERAL_ERROR.getErrorCode());
				error.setErrorName(((Exception) returnedObject).getMessage());
				log.info("Error Occured while deleting the sub category..:" + error.getErrorCode() + ":"
						+ error.getErrorName());
				// wrapping the error into errors list
				errors = new ArrayList<com.couponsworld.apiresults.Error>();
				errors.add(error);

				resultantSubCategory = new ResultantSubCategory();
				resultantSubCategory.setSubCategories(subCategories);
				resultantSubCategory.setErrors(errors);
				resultantSubCategory.setStatus(Status.FAILURE);
				resultantSubCategory.setLinks(GenerateLinkService.mapOfLinks.get("deleteSubCategory"));
				log.info("ResultantSubCategory object returned successfully..");
				log.info("=============================== Sub Category Service - End ===========================");
			}
		} catch (Exception e) {
			// Creating Error for updating Offer
			com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
			error.setErrorCode(Errors.GENERAL_ERROR.getErrorCode());
			error.setErrorName(((Exception) e).getMessage());
			log.info("Error Occured while deleting the sub category..:" + error.getErrorCode() + ":"
					+ error.getErrorName());
			// wrapping the error into errors list
			errors = new ArrayList<com.couponsworld.apiresults.Error>();
			errors.add(error);

			resultantSubCategory = new ResultantSubCategory();
			resultantSubCategory.setSubCategories(subCategories);
			resultantSubCategory.setErrors(errors);
			resultantSubCategory.setStatus(Status.FAILURE);
			resultantSubCategory.setLinks(GenerateLinkService.mapOfLinks.get("deleteSubCategory"));
			log.info("ResultantSubCategory object returned successfully..");
			log.info("=============================== Sub Category Service - End ===========================");
			subCategories = null;
			errors = null;
		}
		subCategories = null;
		errors = null;
		return resultantSubCategory;
	}

}
