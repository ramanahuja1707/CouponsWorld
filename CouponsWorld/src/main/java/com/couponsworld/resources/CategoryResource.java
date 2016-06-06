package com.couponsworld.resources;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
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
import com.couponsworld.services.GenerateLinkService;
import com.couponsworld.utilities.AuthenticationKeyValidator;
import com.couponsworld.utilities.Constants;

@Path("/categories")
public class CategoryResource {
	private List<Category> categories = null;
	private List<com.couponsworld.apiresults.Error> errors = null;
	private ResultantCategory resultantCategory;

	@GET
	@Path("/{authLoginId}")
	@Produces(MediaType.APPLICATION_JSON)
	public ResultantCategory getCategories(@PathParam("authLoginId") String authLoginId,
			@HeaderParam("authKey") String authKey) {
		try {
			if (authLoginId.equals(Constants.AUTH_LOGIN_ID) && AuthenticationKeyValidator.validate(authKey)) {
				return CategoryService.getcategories();
			} else {
				// creating resultantOffer Object
				resultantCategory = new ResultantCategory();

				// creatin g the error getting
				com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
				error.setErrorCode(Errors.API_AUTHENTICATION_ERROR.getErrorCode());
				error.setErrorName("Invalid Login Credentials....");

				// wrapping the error to a list of errors
				errors = new ArrayList<com.couponsworld.apiresults.Error>();
				errors.add(error);

				resultantCategory.setErrors(errors);
				resultantCategory.setLinks(GenerateLinkService.generateCategoryLink("getOffers"));
				resultantCategory.setStatus(Status.FAILURE);
				resultantCategory.setCategories(categories);
				errors = null;
				categories = null;
				return resultantCategory;
			}
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
			resultantCategory.setLinks(GenerateLinkService.generateCategoryLink("getOffers"));
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
			resultantCategory.setLinks(GenerateLinkService.generateCategoryLink("getOffers"));
			resultantCategory.setStatus(Status.FAILURE);
			resultantCategory.setCategories(categories);
			errors = null;
			categories = null;
			return resultantCategory;
		}

	}

	@POST
	@Path("/{authLoginId}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ResultantCategory createCategory(Category category, @PathParam("authLoginId") String authLoginId,
			@HeaderParam("authKey") String authKey) {
		try {
			if (authLoginId.equals(Constants.AUTH_LOGIN_ID) && AuthenticationKeyValidator.validate(authKey)) {
				return CategoryService.createCategory(category);
			} else {
				// creating resultantOffer Object
				resultantCategory = new ResultantCategory();

				// creatin g the error getting
				com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
				error.setErrorCode(Errors.API_AUTHENTICATION_ERROR.getErrorCode());
				error.setErrorName("Invalid Login Credentials....");

				// wrapping the error to a list of errors
				errors = new ArrayList<com.couponsworld.apiresults.Error>();
				errors.add(error);

				resultantCategory.setErrors(errors);
				resultantCategory.setLinks(GenerateLinkService.generateCategoryLink("createOffer"));
				resultantCategory.setStatus(Status.FAILURE);
				resultantCategory.setCategories(categories);
				categories = null;
				errors = null;
				return resultantCategory;
			}
		} catch (NullPointerException npe) {
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
			resultantCategory.setLinks(GenerateLinkService.generateCategoryLink("createOffer"));
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
			resultantCategory.setLinks(GenerateLinkService.generateOfferLink("createOffer"));
			resultantCategory.setStatus(Status.FAILURE);
			resultantCategory.setCategories(categories);
			categories = null;
			errors = null;
			return resultantCategory;
		}
	}

	@PUT
	@Path("/{categoryId}/{authLoginId}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ResultantCategory updateOffer(@PathParam("categoryId") long categoryId, Category category,
			@PathParam("authLoginId") String authLoginId, @HeaderParam("authKey") String authKey) {
		try {
			if (authLoginId.equals(Constants.AUTH_LOGIN_ID) && AuthenticationKeyValidator.validate(authKey)) {
				return CategoryService.updateCategory(categoryId, category);
			} else {
				// creating resultantCategory Object
				resultantCategory = new ResultantCategory();

				// creating the error getting
				com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
				error.setErrorCode(Errors.API_AUTHENTICATION_ERROR.getErrorCode());
				error.setErrorName("Invalid Login Credentials....");

				// wrapping the error to a list of errors
				errors = new ArrayList<com.couponsworld.apiresults.Error>();
				errors.add(error);

				resultantCategory.setErrors(errors);
				resultantCategory.setLinks(GenerateLinkService.generateCategoryLink("updateCategory"));
				resultantCategory.setStatus(Status.FAILURE);
				resultantCategory.setCategories(categories);

				categories = null;
				errors = null;
				return resultantCategory;
			}
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
			resultantCategory.setLinks(GenerateLinkService.generateCategoryLink("updateCategory"));
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
			resultantCategory.setLinks(GenerateLinkService.generateCategoryLink("updateCategory"));
			resultantCategory.setStatus(Status.FAILURE);
			resultantCategory.setCategories(categories);
			categories = null;
			errors = null;
			return resultantCategory;
		}
	}

	@DELETE
	@Path("/{categoryId}/{authLoginId}")
	@Produces(MediaType.APPLICATION_JSON)
	public ResultantCategory deleteOffer(@PathParam("categoryId") long categoryId,
			@PathParam("authLoginId") String authLoginId, @HeaderParam("authKey") String authKey) {
		try {
			if (authLoginId.equals(Constants.AUTH_LOGIN_ID) && AuthenticationKeyValidator.validate(authKey)) {
				return CategoryService.deleteCategory(categoryId);
			} else {
				// creating resultantCategory Object
				resultantCategory = new ResultantCategory();

				// creatin g the error getting
				com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
				error.setErrorCode(Errors.API_AUTHENTICATION_ERROR.getErrorCode());
				error.setErrorName("Invalid Login Credentials....");

				// wrapping the error to a list of errors
				errors = new ArrayList<com.couponsworld.apiresults.Error>();
				errors.add(error);

				resultantCategory.setErrors(errors);
				resultantCategory.setLinks(GenerateLinkService.generateCategoryLink("deleteCategory"));
				resultantCategory.setStatus(Status.FAILURE);
				resultantCategory.setCategories(categories);

				categories = null;
				errors = null;
				return resultantCategory;
			}
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
			resultantCategory.setLinks(GenerateLinkService.generateCategoryLink("deleteCategory"));
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
			resultantCategory.setLinks(GenerateLinkService.generateCategoryLink("deleteCategory"));
			resultantCategory.setStatus(Status.FAILURE);
			resultantCategory.setCategories(categories);
			categories = null;
			errors = null;
			return resultantCategory;
		}

	}
}
