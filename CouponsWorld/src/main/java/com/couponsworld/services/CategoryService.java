package com.couponsworld.services;

import java.util.ArrayList;
import java.util.List;

import com.couponsworld.apiresults.ResultantCategory;
import com.couponsworld.database.DatabaseService;
import com.couponsworld.dto.Category;
import com.couponsworld.enums.Errors;
import com.couponsworld.enums.Status;
import com.couponsworld.exceptions.CategoryException;
import com.couponsworld.utilities.GenerateLinkService;

public class CategoryService {

	public static List<Category> categories = null;
	public static List<com.couponsworld.apiresults.Error> errors = null;
	public static ResultantCategory resultantCategory;

	public static ResultantCategory createCategory(Category category) {
		try {
			Object returnedObject = DatabaseService.createCategoryInDatabase(category);
			if (returnedObject instanceof Category) {
				// category successfully created and returned....
				// wrapping the category into offer list
				categories = new ArrayList<Category>();
				categories.add((Category) returnedObject);

				// Creating ResultantCategory object
				resultantCategory = new ResultantCategory();

				resultantCategory.setCategories(categories);
				resultantCategory.setErrors(errors);
				resultantCategory.setStatus(Status.SUCCESS);
				resultantCategory.setLinks(GenerateLinkService.generateOfferLink("createCategory"));
			} else {
				// Creating Error for updating Offer
				com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
				error.setErrorCode(Errors.GENERAL_ERROR.getErrorCode());
				error.setErrorName(((Exception) returnedObject).getMessage());

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
			}
		} catch (Exception exception) {
			// Creating Error for updating Offer
			com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
			error.setErrorCode(Errors.GENERAL_ERROR.getErrorCode());
			error.setErrorName(exception.getMessage());

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
		}
		categories = null;
		errors = null;
		return resultantCategory;
	}

	public static ResultantCategory getCategories() {
		try {
			Object returnedObject = DatabaseService.getCategoriesFromDatabase();
			if (returnedObject instanceof Exception) {
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
			} else if (returnedObject instanceof CategoryException) {
				// Creating Error for updating Offer
				com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
				error.setErrorCode(Errors.CATEGORY_ERROR.getErrorCode());
				error.setErrorName(((CategoryException) returnedObject).getMessage());

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
			} else {

				if (((List<Category>) returnedObject).size() > 0) {

					resultantCategory = new ResultantCategory();
					resultantCategory.setCategories((List<Category>) returnedObject);
					resultantCategory.setErrors(errors);
					resultantCategory.setStatus(Status.SUCCESS);
					resultantCategory.setLinks(GenerateLinkService.mapOfLinks.get("getCategories"));
				} else {
					resultantCategory = new ResultantCategory();
					resultantCategory.setCategories(categories);
					resultantCategory.setErrors(errors);
					resultantCategory.setStatus(Status.SUCCESS);
					resultantCategory.setLinks(GenerateLinkService.mapOfLinks.get("getCategories"));
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

			resultantCategory = new ResultantCategory();

			resultantCategory.setCategories(categories);
			resultantCategory.setErrors(errors);
			resultantCategory.setStatus(Status.FAILURE);
			resultantCategory.setLinks(GenerateLinkService.mapOfLinks.get("getCategories"));
			categories = null;
			errors = null;
		}
		categories = null;
		errors = null;
		return resultantCategory;
	}

	public static ResultantCategory updateCategory(long categoryId, Category category) {
		try {
			Object returnedObject = DatabaseService.updateCategoryInDatabase(categoryId, category);
			if (returnedObject instanceof Category) {
				// offer successfully created and returned....
				// Creating ResultantCategory object
				categories = new ArrayList<Category>();
				categories.add((Category) returnedObject);

				resultantCategory = new ResultantCategory();
				resultantCategory.setCategories(categories);
				resultantCategory.setErrors(errors);
				resultantCategory.setStatus(Status.SUCCESS);
				resultantCategory.setLinks(GenerateLinkService.mapOfLinks.get("updateCategory"));
			} else if (returnedObject instanceof CategoryException) {
				// Creating Error for updating Offer
				com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
				error.setErrorCode(Errors.CATEGORY_ERROR.getErrorCode());
				error.setErrorName(((CategoryException) returnedObject).getMessage());

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
			} else {
				// Creating Error for updating Offer
				com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
				error.setErrorCode(Errors.GENERAL_ERROR.getErrorCode());
				error.setErrorName(((Exception) returnedObject).getMessage());

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
			}
		} catch (Exception e) {
			// Creating Error for updating Offer
			com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
			error.setErrorCode(Errors.GENERAL_ERROR.getErrorCode());
			error.setErrorName(e.getMessage());

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
			categories = null;
			errors = null;
		}
		categories = null;
		errors = null;
		return resultantCategory;
	}

	public static ResultantCategory deleteCategory(long categoryId) {
		try {
			Object returnedObject = DatabaseService.deleteCategoryFromDatabase(categoryId);
			if (returnedObject instanceof Category) {
				// category successfully created and returned....
				// Creating ResultantCategory object
				categories = new ArrayList<Category>();
				categories.add((Category) returnedObject);

				resultantCategory = new ResultantCategory();
				resultantCategory.setCategories(categories);
				resultantCategory.setErrors(errors);
				resultantCategory.setStatus(Status.SUCCESS);
				resultantCategory.setLinks(GenerateLinkService.mapOfLinks.get("deleteCategory"));
			} else if (returnedObject instanceof CategoryException) {
				// Creating Error for updating Offer
				com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
				error.setErrorCode(Errors.CATEGORY_ERROR.getErrorCode());
				error.setErrorName(((CategoryException) returnedObject).getMessage());

				// wrapping the error into errors list
				errors = new ArrayList<com.couponsworld.apiresults.Error>();
				errors.add(error);

				resultantCategory = new ResultantCategory();
				resultantCategory.setCategories(categories);
				resultantCategory.setErrors(errors);
				resultantCategory.setStatus(Status.FAILURE);
				resultantCategory.setLinks(GenerateLinkService.mapOfLinks.get("deleteCategory"));
			} else {
				// Creating Error for updating Offer
				com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
				error.setErrorCode(Errors.GENERAL_ERROR.getErrorCode());
				error.setErrorName(((Exception) returnedObject).getMessage());

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
			}
		} catch (Exception e) {
			// Creating Error for updating Offer
			com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
			error.setErrorCode(Errors.GENERAL_ERROR.getErrorCode());
			error.setErrorName(e.getMessage());

			// wrapping the error into errors list
			errors = new ArrayList<com.couponsworld.apiresults.Error>();
			errors.add(error);

			resultantCategory = new ResultantCategory();
			resultantCategory.setCategories(categories);
			resultantCategory.setErrors(errors);
			resultantCategory.setStatus(Status.FAILURE);
			resultantCategory.setLinks(GenerateLinkService.mapOfLinks.get("deleteCategory"));
			categories = null;
			errors = null;
		}
		categories = null;
		errors = null;
		return resultantCategory;
	}

}
