package com.couponsworld.resources;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.couponsworld.apiresults.ResultantCategory;
import com.couponsworld.dto.Category;
import com.couponsworld.enums.Errors;
import com.couponsworld.enums.Status;
import com.couponsworld.services.CategoryService;
import com.couponsworld.utilities.GenerateLinkService;

@Path("/categories")
public class CategoryResource {
	private List<Category> categories = null;
	private List<com.couponsworld.apiresults.Error> errors = null;
	private ResultantCategory resultantCategory;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public ResultantCategory getCategories() {
		try {

			return CategoryService.getCategories();

		} catch (NullPointerException nle) {

			// creating resultantOffer Object
			resultantCategory = new ResultantCategory();

			// creating the error getting
			com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
			error.setErrorCode(Errors.NULL_POINTER_ERROR.getErrorCode());
			error.setErrorName("Provide correct credentials to access api");

			// wrapping the error to a list of errors
			errors = new ArrayList<com.couponsworld.apiresults.Error>();
			errors.add(error);

			resultantCategory.setErrors(errors);
			resultantCategory.setLinks(GenerateLinkService.mapOfLinks.get("getCategories"));
			resultantCategory.setStatus(Status.FAILURE);
			resultantCategory.setCategories(categories);
			errors = null;
			categories = null;
			return resultantCategory;
		} catch (Exception exception) {
			// creating resultantOffer Object
			resultantCategory = new ResultantCategory();

			// creating the error getting
			com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
			error.setErrorCode(Errors.GENERAL_ERROR.getErrorCode());
			error.setErrorName(exception.getMessage());

			// wrapping the error to a list of errors
			errors = new ArrayList<com.couponsworld.apiresults.Error>();
			errors.add(error);

			resultantCategory.setErrors(errors);
			resultantCategory.setLinks(GenerateLinkService.mapOfLinks.get("getCategories"));
			resultantCategory.setStatus(Status.FAILURE);
			resultantCategory.setCategories(categories);
			errors = null;
			categories = null;
			return resultantCategory;
		}

	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ResultantCategory createCategory(Category category) {
		try {

			return CategoryService.createCategory(category);

		} catch (NullPointerException npe) {
			// creating resultantOffer Object
			resultantCategory = new ResultantCategory();

			// creating the error getting
			com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
			error.setErrorCode(Errors.NULL_POINTER_ERROR.getErrorCode());
			error.setErrorName("Provide correct credentials to access api");

			// creating Category List to wrap the input Offer Object into it
			categories = new ArrayList<Category>();
			categories.add(category);

			// wrapping the error to a list of errors
			errors = new ArrayList<com.couponsworld.apiresults.Error>();
			errors.add(error);

			resultantCategory.setErrors(errors);
			resultantCategory.setLinks(GenerateLinkService.mapOfLinks.get("createCategory"));
			resultantCategory.setStatus(Status.FAILURE);
			resultantCategory.setCategories(categories);

			categories = null;
			errors = null;
			return resultantCategory;
		} catch (Exception exception) {

			// creating Category List to wrap the input Offer Object into it
			categories = new ArrayList<Category>();
			categories.add(category);

			// creating resultantOffer Object
			resultantCategory = new ResultantCategory();

			// creating the error getting
			com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
			error.setErrorCode(Errors.GENERAL_ERROR.getErrorCode());
			error.setErrorName(exception.getMessage().toString());

			// wrapping the error to a list of errors
			errors = new ArrayList<com.couponsworld.apiresults.Error>();
			errors.add(error);

			resultantCategory.setErrors(errors);
			resultantCategory.setLinks(GenerateLinkService.generateOfferLink("createCategory"));
			resultantCategory.setStatus(Status.FAILURE);
			resultantCategory.setCategories(categories);
			categories = null;
			errors = null;
			return resultantCategory;
		}
	}

	@PUT
	@Path("/{categoryId}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ResultantCategory updateCategory(@PathParam("categoryId") long categoryId, Category category) {
		try {

			return CategoryService.updateCategory(categoryId, category);

		} catch (NullPointerException npe) {
			// creating resultantCategory Object
			resultantCategory = new ResultantCategory();

			// creating Category List to wrap the input Offer Object into it
			categories = new ArrayList<Category>();
			categories.add(category);

			// creating the error getting
			com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
			error.setErrorCode(Errors.NULL_POINTER_ERROR.getErrorCode());
			error.setErrorName("Provide correct credentials to access api");

			// wrapping the error to a list of errors
			errors = new ArrayList<com.couponsworld.apiresults.Error>();
			errors.add(error);

			resultantCategory.setErrors(errors);
			resultantCategory.setLinks(GenerateLinkService.mapOfLinks.get("updateCategory"));
			resultantCategory.setStatus(Status.FAILURE);
			resultantCategory.setCategories(categories);

			errors = null;
			return resultantCategory;
		} catch (Exception exception) {

			// creating category List to wrap the input category Object into it
			categories = new ArrayList<Category>();
			categories.add(category);

			// creating resultantCategory Object
			resultantCategory = new ResultantCategory();

			// creating the error getting
			com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
			error.setErrorCode(Errors.GENERAL_ERROR.getErrorCode());
			error.setErrorName(exception.getMessage().toString());

			// wrapping the error to a list of errors
			errors = new ArrayList<com.couponsworld.apiresults.Error>();
			errors.add(error);

			resultantCategory.setErrors(errors);
			resultantCategory.setLinks(GenerateLinkService.mapOfLinks.get("updateCategory"));
			resultantCategory.setStatus(Status.FAILURE);
			resultantCategory.setCategories(categories);
			categories = null;
			errors = null;
			return resultantCategory;
		}
	}

	@DELETE
	@Path("/{categoryId}")
	@Produces(MediaType.APPLICATION_JSON)
	public ResultantCategory deleteCategory(@PathParam("categoryId") long categoryId) {
		try {

			return CategoryService.deleteCategory(categoryId);

		} catch (NullPointerException npe) {
			// creating resultantCategory Object
			resultantCategory = new ResultantCategory();

			// creating the error getting
			com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
			error.setErrorCode(Errors.NULL_POINTER_ERROR.getErrorCode());
			error.setErrorName("Provide correct credentials to access api");

			// wrapping the error to a list of errors
			errors = new ArrayList<com.couponsworld.apiresults.Error>();
			errors.add(error);

			resultantCategory.setErrors(errors);
			resultantCategory.setLinks(GenerateLinkService.mapOfLinks.get("deleteCategory"));
			resultantCategory.setStatus(Status.FAILURE);
			resultantCategory.setCategories(categories);
			categories = null;
			errors = null;
			return resultantCategory;
		} catch (Exception exception) {

			// creating resultantCategory Object
			resultantCategory = new ResultantCategory();

			// creating the error getting
			com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
			error.setErrorCode(Errors.GENERAL_ERROR.getErrorCode());
			error.setErrorName(exception.getMessage().toString());

			// wrapping the error to a list of errors
			errors = new ArrayList<com.couponsworld.apiresults.Error>();
			errors.add(error);

			resultantCategory.setErrors(errors);
			resultantCategory.setLinks(GenerateLinkService.mapOfLinks.get("deleteCategory"));
			resultantCategory.setStatus(Status.FAILURE);
			resultantCategory.setCategories(categories);
			categories = null;
			errors = null;
			return resultantCategory;
		}

	}
}
