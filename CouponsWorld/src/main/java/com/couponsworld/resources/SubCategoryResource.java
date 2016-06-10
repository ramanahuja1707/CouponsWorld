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
import com.couponsworld.apiresults.ResultantSubCategory;
import com.couponsworld.dto.Category;
import com.couponsworld.dto.SubCategory;
import com.couponsworld.enums.Errors;
import com.couponsworld.enums.Status;
import com.couponsworld.services.CategoryService;
import com.couponsworld.services.SubCategoryService;
import com.couponsworld.utilities.GenerateLinkService;

@Path("/subcategories")
public class SubCategoryResource {

	private List<SubCategory> subCategories = null;
	private List<com.couponsworld.apiresults.Error> errors = null;
	private ResultantSubCategory resultantSubCategory;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public ResultantSubCategory getSubCategories() {
		try {

			return SubCategoryService.getSubCategories();

		} catch (NullPointerException nle) {

			// creating resultantSubCategory Object
			resultantSubCategory = new ResultantSubCategory();

			// creating the error getting
			com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
			error.setErrorCode(Errors.NULL_POINTER_ERROR.getErrorCode());
			error.setErrorName("Provide correct credentials to access api");

			// wrapping the error to a list of errors
			errors = new ArrayList<com.couponsworld.apiresults.Error>();
			errors.add(error);

			resultantSubCategory.setErrors(errors);
			resultantSubCategory.setLinks(GenerateLinkService.generateSubcategoryLink("getCategories"));
			resultantSubCategory.setStatus(Status.FAILURE);
			resultantSubCategory.setSubCategories(subCategories);
			errors = null;
			subCategories = null;
			return resultantSubCategory;
		} catch (Exception exception) {

			// creating resultantSubCategory Object
			resultantSubCategory = new ResultantSubCategory();

			// creating the error getting
			com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
			error.setErrorCode(Errors.GENERAL_ERROR.getErrorCode());
			error.setErrorName(exception.getMessage());

			// wrapping the error to a list of errors
			errors = new ArrayList<com.couponsworld.apiresults.Error>();
			errors.add(error);

			resultantSubCategory.setErrors(errors);
			resultantSubCategory.setLinks(GenerateLinkService.generateSubcategoryLink("getCategories"));
			resultantSubCategory.setStatus(Status.FAILURE);
			resultantSubCategory.setSubCategories(subCategories);
			errors = null;
			subCategories = null;
			return resultantSubCategory;
		}

	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ResultantSubCategory createSubCategory(SubCategory subCategory) {
		try {

			return SubCategoryService.createSubCategory(subCategory);

		} catch (NullPointerException npe) {
			// creating resultantSubCategory Object
			resultantSubCategory = new ResultantSubCategory();

			// creating the error getting
			com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
			error.setErrorCode(Errors.NULL_POINTER_ERROR.getErrorCode());
			error.setErrorName("Provide correct credentials to access api");

			subCategories = new ArrayList<SubCategory>();
			subCategories.add(subCategory);

			// wrapping the error to a list of errors
			errors = new ArrayList<com.couponsworld.apiresults.Error>();
			errors.add(error);

			resultantSubCategory.setErrors(errors);
			resultantSubCategory.setLinks(GenerateLinkService.generateSubcategoryLink("createSubCategory"));
			resultantSubCategory.setStatus(Status.FAILURE);
			resultantSubCategory.setSubCategories(subCategories);

			subCategories = null;
			errors = null;
			return resultantSubCategory;
		} catch (Exception exception) {

			subCategories = new ArrayList<SubCategory>();
			subCategories.add(subCategory);

			// creating resultantSubCategory Object
			resultantSubCategory = new ResultantSubCategory();

			// creating the error getting
			com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
			error.setErrorCode(Errors.GENERAL_ERROR.getErrorCode());
			error.setErrorName(exception.getMessage().toString());

			// wrapping the error to a list of errors
			errors = new ArrayList<com.couponsworld.apiresults.Error>();
			errors.add(error);

			resultantSubCategory.setErrors(errors);
			resultantSubCategory.setLinks(GenerateLinkService.generateSubcategoryLink("createSubCategory"));
			resultantSubCategory.setStatus(Status.FAILURE);
			resultantSubCategory.setSubCategories(subCategories);
			subCategories = null;
			errors = null;
			return resultantSubCategory;
		}
	}

	@PUT
	@Path("/{subCategoryId}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ResultantSubCategory updateSubCategory(@PathParam("subCategoryId") long subCategoryId,
			SubCategory subCategory) {
		try {

			return SubCategoryService.updateSubCategory(subCategoryId, subCategory);
		} catch (NullPointerException npe) {
			// creating resultantSubCategory Object
			resultantSubCategory = new ResultantSubCategory();

			subCategories = new ArrayList<SubCategory>();
			subCategories.add(subCategory);

			// creating the error getting
			com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
			error.setErrorCode(Errors.NULL_POINTER_ERROR.getErrorCode());
			error.setErrorName("Provide correct credentials to access api");

			// wrapping the error to a list of errors
			errors = new ArrayList<com.couponsworld.apiresults.Error>();
			errors.add(error);

			resultantSubCategory.setErrors(errors);
			resultantSubCategory.setLinks(GenerateLinkService.generateSubcategoryLink("updateSubCategory"));
			resultantSubCategory.setStatus(Status.FAILURE);
			resultantSubCategory.setSubCategories(subCategories);

			subCategories = null;
			errors = null;
			return resultantSubCategory;
		} catch (Exception exception) {

			// creating resultantSubCategory Object
			resultantSubCategory = new ResultantSubCategory();

			subCategories = new ArrayList<SubCategory>();
			subCategories.add(subCategory);

			// creating the error getting
			com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
			error.setErrorCode(Errors.GENERAL_ERROR.getErrorCode());
			error.setErrorName("Provide correct credentials to access api");

			// wrapping the error to a list of errors
			errors = new ArrayList<com.couponsworld.apiresults.Error>();
			errors.add(error);

			resultantSubCategory.setErrors(errors);
			resultantSubCategory.setLinks(GenerateLinkService.generateSubcategoryLink("updateSubCategory"));
			resultantSubCategory.setStatus(Status.FAILURE);
			resultantSubCategory.setSubCategories(subCategories);

			subCategories = null;
			errors = null;
			return resultantSubCategory;
		}
	}

	@DELETE
	@Path("/{subCategoryId}")
	@Produces(MediaType.APPLICATION_JSON)
	public ResultantSubCategory deleteSubCategory(@PathParam("subCategoryId") long subCategoryId) {
		try {

			return SubCategoryService.deleteSubCategory(subCategoryId);

		} catch (NullPointerException npe) {
			// creating resultantSubCategory Object
			resultantSubCategory = new ResultantSubCategory();
			// creating the error getting
			com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
			error.setErrorCode(Errors.NULL_POINTER_ERROR.getErrorCode());
			error.setErrorName("Provide correct credentials to access api");

			// wrapping the error to a list of errors
			errors = new ArrayList<com.couponsworld.apiresults.Error>();
			errors.add(error);

			resultantSubCategory.setErrors(errors);
			resultantSubCategory.setLinks(GenerateLinkService.generateSubcategoryLink("deleteSubCategory"));
			resultantSubCategory.setStatus(Status.FAILURE);
			resultantSubCategory.setSubCategories(subCategories);
			subCategories = null;
			errors = null;
			return resultantSubCategory;
		} catch (Exception exception) {

			// creating resultantSubCategory Object
			resultantSubCategory = new ResultantSubCategory();
			// creating the error getting
			com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
			error.setErrorCode(Errors.GENERAL_ERROR.getErrorCode());
			error.setErrorName("Provide correct credentials to access api");

			// wrapping the error to a list of errors
			errors = new ArrayList<com.couponsworld.apiresults.Error>();
			errors.add(error);

			resultantSubCategory.setErrors(errors);
			resultantSubCategory.setLinks(GenerateLinkService.generateSubcategoryLink("deleteSubCategory"));
			resultantSubCategory.setStatus(Status.FAILURE);
			resultantSubCategory.setSubCategories(subCategories);
			subCategories = null;
			errors = null;
			return resultantSubCategory;
		}

	}
}
