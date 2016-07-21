package com.couponsworld.services;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.couponsworld.apiresults.ResultantCategory;
import com.couponsworld.database.DatabaseService;
import com.couponsworld.dto.Category;
import com.couponsworld.enums.Errors;
import com.couponsworld.enums.Status;
import com.couponsworld.exceptions.CategoryException;
import com.couponsworld.resources.UserTypeResource;
import com.couponsworld.utilities.GenerateLinkService;

public class CategoryService {

	// declaration of logger
	private static final Logger log = Logger.getLogger(CategoryService.class.getName());

	public static List<Category> categories = null;
	public static List<com.couponsworld.apiresults.Error> errors = null;
	public static ResultantCategory resultantCategory;

	public static ResultantCategory createCategory(Category category) {
		try {
			log.info("=========================== Category Service - Start =================================");
			Object returnedObject = DatabaseService.createCategoryInDatabase(category);
			if (returnedObject instanceof Category) {
				// category successfully created and returned....
				// wrapping the category into offer list
				log.info("Category wrapping into resultantCategory ..");
				categories = new ArrayList<Category>();
				categories.add((Category) returnedObject);

				// Creating ResultantCategory object
				resultantCategory = new ResultantCategory();

				resultantCategory.setCategories(categories);
				resultantCategory.setErrors(errors);
				resultantCategory.setStatus(Status.SUCCESS);
				resultantCategory.setLinks(GenerateLinkService.generateOfferLink("createCategory"));
				log.info("ResultantCategory object returned successfully..");
				log.info("=============================== Category Service - End ===========================");
			} else {

				// Creating Error for updating Offer
				com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
				error.setErrorCode(Errors.GENERAL_ERROR.getErrorCode());
				error.setErrorName(((Exception) returnedObject).getMessage());
				log.info("Error Occured while creating the category..:" + error.getErrorCode() + ":"
						+ error.getErrorName());

				// wrapping the category into category list
				categories = new ArrayList<Category>();
				categories.add(category);

				// wrapping the error into errors list
				errors = new ArrayList<com.couponsworld.apiresults.Error>();
				errors.add(error);

				// category does not created and error returned....
				// Creating ResultantCategory object
				resultantCategory = new ResultantCategory();
				resultantCategory.setCategories(categories);
				resultantCategory.setErrors(errors);
				resultantCategory.setStatus(Status.FAILURE);
				resultantCategory.setLinks(GenerateLinkService.generateOfferLink("createCategory"));
				log.info("ResultantCategory object returned successfully..");
				log.info("=============================== Category Service - End ===========================");
			}
		} catch (Exception exception) {
			// Creating Error for updating Offer
			com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
			error.setErrorCode(Errors.GENERAL_ERROR.getErrorCode());
			error.setErrorName(exception.getMessage());

			log.info(
					"Error Occured while creating the category..:" + error.getErrorCode() + ":" + error.getErrorName());
			// wrapping the category into category list
			categories = new ArrayList<Category>();
			categories.add(category);

			// wrapping the error into errors list
			errors = new ArrayList<com.couponsworld.apiresults.Error>();
			errors.add(error);

			// offer does not created and error returned....
			// Creating ResultantCategory object
			resultantCategory = new ResultantCategory();
			resultantCategory.setCategories(categories);
			resultantCategory.setErrors(errors);
			resultantCategory.setStatus(Status.FAILURE);
			resultantCategory.setLinks(GenerateLinkService.generateOfferLink("createCategory"));
			log.info("ResultantCategory object returned successfully..");
			log.info("=============================== Category Service - End ===========================");
		}
		categories = null;
		errors = null;
		return resultantCategory;
	}

	public static ResultantCategory getCategories() {
		try {
			log.info("=============================== Category Service - Start ===========================");
			Object returnedObject = DatabaseService.getCategoriesFromDatabase();
			if (returnedObject instanceof Exception) {
				log.info("Error Occured while getting the categories..:" + ((Exception) returnedObject).getMessage());

				// Creating Error for updating Offer
				com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
				error.setErrorCode(Errors.GENERAL_ERROR.getErrorCode());
				error.setErrorName(((Exception) returnedObject).getMessage());

				// wrapping the error into errors list
				errors = new ArrayList<com.couponsworld.apiresults.Error>();
				errors.add(error);

				// offer does not created and error returned....
				// Creating ResultantOffer object
				resultantCategory = new ResultantCategory();
				resultantCategory.setCategories(categories);
				resultantCategory.setErrors(errors);
				resultantCategory.setStatus(Status.FAILURE);
				resultantCategory.setLinks(GenerateLinkService.mapOfLinks.get("getCategories"));
				log.info("ResultantCategory object returned successfully..");
				log.info("=============================== Category Service - End ===========================");
			} else if (returnedObject instanceof CategoryException) {

				// Creating Error for updating Offer
				com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
				error.setErrorCode(Errors.CATEGORY_ERROR.getErrorCode());
				error.setErrorName(((CategoryException) returnedObject).getMessage());
				log.info("Error Occured while getting the categories..:" + error.getErrorCode() + ":"
						+ error.getErrorName());
				// wrapping the error into errors list
				errors = new ArrayList<com.couponsworld.apiresults.Error>();
				errors.add(error);

				// offer does not created and error returned....
				// Creating ResultantOffer object
				resultantCategory = new ResultantCategory();
				resultantCategory.setCategories(categories);
				resultantCategory.setErrors(errors);
				resultantCategory.setStatus(Status.FAILURE);
				resultantCategory.setLinks(GenerateLinkService.mapOfLinks.get("getCategories"));
				log.info("ResultantCategory object returned successfully..");
				log.info("=============================== Category Service - End ===========================");
			} else {
				if (((List<Category>) returnedObject).size() > 0) {
					log.info("Wrapping the List of category received from database to resultant category...");
					resultantCategory = new ResultantCategory();
					resultantCategory.setCategories((List<Category>) returnedObject);
					resultantCategory.setErrors(errors);
					resultantCategory.setStatus(Status.SUCCESS);
					resultantCategory.setLinks(GenerateLinkService.mapOfLinks.get("getCategories"));
				} else {
					log.info("Wrapping categories=null to resultant category...");
					resultantCategory = new ResultantCategory();
					resultantCategory.setCategories(categories);
					resultantCategory.setErrors(errors);
					resultantCategory.setStatus(Status.SUCCESS);
					resultantCategory.setLinks(GenerateLinkService.mapOfLinks.get("getCategories"));
				}
				log.info("ResultantCategory object returned successfully..");
				log.info("=============================== Category Service - End ===========================");
			}
		} catch (Exception e) {
			// Creating Error for updating Offer
			com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
			error.setErrorCode(Errors.GENERAL_ERROR.getErrorCode());
			error.setErrorName(e.getMessage());
			log.info("Error Occured while getting the categories..:" + error.getErrorCode() + ":"
					+ error.getErrorName());
			// wrapping the error into errors list
			errors = new ArrayList<com.couponsworld.apiresults.Error>();
			errors.add(error);

			resultantCategory = new ResultantCategory();

			resultantCategory.setCategories(categories);
			resultantCategory.setErrors(errors);
			resultantCategory.setStatus(Status.FAILURE);
			resultantCategory.setLinks(GenerateLinkService.mapOfLinks.get("getCategories"));
			log.info("ResultantCategory object returned successfully..");
			log.info("=============================== Category Service - End ===========================");
			categories = null;
			errors = null;
		}
		categories = null;
		errors = null;
		return resultantCategory;
	}

	public static ResultantCategory updateCategory(long categoryId, Category category) {
		try {
			log.info("=========================== Category Service - Start =================================");
			Object returnedObject = DatabaseService.updateCategoryInDatabase(categoryId, category);
			if (returnedObject instanceof Category) {
				log.info("Category wrapping into resultantCategory ..");
				// offer successfully created and returned....
				// Creating ResultantCategory object
				categories = new ArrayList<Category>();
				categories.add((Category) returnedObject);

				resultantCategory = new ResultantCategory();
				resultantCategory.setCategories(categories);
				resultantCategory.setErrors(errors);
				resultantCategory.setStatus(Status.SUCCESS);
				resultantCategory.setLinks(GenerateLinkService.mapOfLinks.get("updateCategory"));
				log.info("ResultantCategory object returned successfully..");
				log.info("=============================== Category Service - End ===========================");
			} else if (returnedObject instanceof CategoryException) {
				// Creating Error for updating Offer
				com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
				error.setErrorCode(Errors.CATEGORY_ERROR.getErrorCode());
				error.setErrorName(((CategoryException) returnedObject).getMessage());

				// wrapping the error into errors list
				errors = new ArrayList<com.couponsworld.apiresults.Error>();
				errors.add(error);
				log.info("Error Occured while updating the category..:" + error.getErrorCode() + ":"
						+ error.getErrorName());
				// wrapping the category into category list
				categories = new ArrayList<Category>();
				categories.add(category);

				// category does not created and error returned....
				// Creating ResultantOffer object
				resultantCategory = new ResultantCategory();
				resultantCategory.setCategories(categories);
				resultantCategory.setErrors(errors);
				resultantCategory.setStatus(Status.FAILURE);
				resultantCategory.setLinks(GenerateLinkService.mapOfLinks.get("updateCategory"));
				log.info("ResultantCategory object returned successfully..");
				log.info("=============================== Category Service - End ===========================");
			} else {
				// Creating Error for updating Offer
				com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
				error.setErrorCode(Errors.GENERAL_ERROR.getErrorCode());
				error.setErrorName(((Exception) returnedObject).getMessage());
				log.info("Error Occured while updating the category..:" + error.getErrorCode() + ":"
						+ error.getErrorName());
				// wrapping the error into errors list
				errors = new ArrayList<com.couponsworld.apiresults.Error>();
				errors.add(error);

				// wrapping the category into category list
				categories = new ArrayList<Category>();
				categories.add(category);

				// category does not created and error returned....
				// Creating ResultantOffer object
				resultantCategory = new ResultantCategory();
				resultantCategory.setCategories(categories);
				resultantCategory.setErrors(errors);
				resultantCategory.setStatus(Status.FAILURE);
				resultantCategory.setLinks(GenerateLinkService.mapOfLinks.get("updateCategory"));
				log.info("ResultantCategory object returned successfully..");
				log.info("=============================== Category Service - End ===========================");
			}
		} catch (Exception e) {
			// Creating Error for updating Offer
			com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
			error.setErrorCode(Errors.GENERAL_ERROR.getErrorCode());
			error.setErrorName(e.getMessage());
			log.info(
					"Error Occured while updating the category..:" + error.getErrorCode() + ":" + error.getErrorName());
			// wrapping the category into category list
			categories = new ArrayList<Category>();
			categories.add(category);

			// wrapping the error into errors list
			errors = new ArrayList<com.couponsworld.apiresults.Error>();
			errors.add(error);

			// category does not created and error returned....
			// Creating ResultantOffer object
			resultantCategory = new ResultantCategory();
			resultantCategory.setCategories(categories);
			resultantCategory.setErrors(errors);
			resultantCategory.setStatus(Status.FAILURE);
			resultantCategory.setLinks(GenerateLinkService.mapOfLinks.get("updateCategory"));
			log.info("ResultantCategory object returned successfully..");
			log.info("=============================== Category Service - End ===========================");
			categories = null;
			errors = null;
		}
		categories = null;
		errors = null;
		return resultantCategory;
	}

	public static ResultantCategory deleteCategory(long categoryId) {
		try {
			log.info("=========================== Category Service - Start =================================");
			Object returnedObject = DatabaseService.deleteCategoryFromDatabase(categoryId);
			if (returnedObject instanceof Category) {
				log.info("Category wrapping into resultantCategory ..");
				// category successfully created and returned....
				// Creating ResultantCategory object
				categories = new ArrayList<Category>();
				categories.add((Category) returnedObject);

				resultantCategory = new ResultantCategory();
				resultantCategory.setCategories(categories);
				resultantCategory.setErrors(errors);
				resultantCategory.setStatus(Status.SUCCESS);
				resultantCategory.setLinks(GenerateLinkService.mapOfLinks.get("deleteCategory"));
				log.info("ResultantCategory object returned successfully..");
				log.info("=============================== Category Service - End ===========================");
			} else if (returnedObject instanceof CategoryException) {
				// Creating Error for updating Offer
				com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
				error.setErrorCode(Errors.CATEGORY_ERROR.getErrorCode());
				error.setErrorName(((CategoryException) returnedObject).getMessage());
				log.info("Error Occured while deleting the category..:" + error.getErrorCode() + ":"
						+ error.getErrorName());
				// wrapping the error into errors list
				errors = new ArrayList<com.couponsworld.apiresults.Error>();
				errors.add(error);

				resultantCategory = new ResultantCategory();
				resultantCategory.setCategories(categories);
				resultantCategory.setErrors(errors);
				resultantCategory.setStatus(Status.FAILURE);
				resultantCategory.setLinks(GenerateLinkService.mapOfLinks.get("deleteCategory"));
				log.info("ResultantCategory object returned successfully..");
				log.info("=============================== Category Service - End ===========================");
			} else {
				// Creating Error for updating Offer
				com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
				error.setErrorCode(Errors.GENERAL_ERROR.getErrorCode());
				error.setErrorName(((Exception) returnedObject).getMessage());
				log.info("Error Occured while deleting the category..:" + error.getErrorCode() + ":"
						+ error.getErrorName());
				// wrapping the error into errors list
				errors = new ArrayList<com.couponsworld.apiresults.Error>();
				errors.add(error);

				// category does not created and error returned....
				// Creating ResultantCategory object
				resultantCategory = new ResultantCategory();
				resultantCategory.setCategories(categories);
				resultantCategory.setErrors(errors);
				resultantCategory.setStatus(Status.FAILURE);
				resultantCategory.setLinks(GenerateLinkService.mapOfLinks.get("deleteCategory"));
				log.info("ResultantCategory object returned successfully..");
				log.info("=============================== Category Service - End ===========================");
			}
		} catch (Exception e) {
			// Creating Error for updating Offer
			com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
			error.setErrorCode(Errors.GENERAL_ERROR.getErrorCode());
			error.setErrorName(e.getMessage());
			log.info(
					"Error Occured while deleting the category..:" + error.getErrorCode() + ":" + error.getErrorName());
			// wrapping the error into errors list
			errors = new ArrayList<com.couponsworld.apiresults.Error>();
			errors.add(error);

			resultantCategory = new ResultantCategory();
			resultantCategory.setCategories(categories);
			resultantCategory.setErrors(errors);
			resultantCategory.setStatus(Status.FAILURE);
			resultantCategory.setLinks(GenerateLinkService.mapOfLinks.get("deleteCategory"));
			log.info("ResultantCategory object returned successfully..");
			log.info("=============================== Category Service - End ===========================");
			categories = null;
			errors = null;
		}
		categories = null;
		errors = null;
		return resultantCategory;
	}

}
