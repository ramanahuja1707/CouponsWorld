package com.couponsworld.resources;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

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

	// declaration of logger
	private static final Logger log = Logger.getLogger(SubCategoryResource.class.getName());

	private List<SubCategory> subCategories = null;
	private List<com.couponsworld.apiresults.Error> errors = null;
	private ResultantSubCategory resultantSubCategory;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public ResultantSubCategory getSubCategories() {
		try {
			log.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ SubCategory Resource - Start @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
			return SubCategoryService.getSubCategories();

		} catch (NullPointerException nle) {

			// creating resultantSubCategory Object
			resultantSubCategory = new ResultantSubCategory();

			// creating the error getting
			com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
			error.setErrorCode(Errors.NULL_POINTER_ERROR.getErrorCode());
			error.setErrorName("Provide correct credentials to access api");
			log.info("Error Occured in SubCategory Resource :" + error.getErrorCode() + ":" + error.getErrorName());
			// wrapping the error to a list of errors
			errors = new ArrayList<com.couponsworld.apiresults.Error>();
			errors.add(error);

			resultantSubCategory.setErrors(errors);
			resultantSubCategory.setLinks(GenerateLinkService.mapOfLinks.get("getCategories"));
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
			log.info("Error Occured in SubCategory Resource :" + error.getErrorCode() + ":" + error.getErrorName());
			// wrapping the error to a list of errors
			errors = new ArrayList<com.couponsworld.apiresults.Error>();
			errors.add(error);

			resultantSubCategory.setErrors(errors);
			resultantSubCategory.setLinks(GenerateLinkService.mapOfLinks.get("getCategories"));
			resultantSubCategory.setStatus(Status.FAILURE);
			resultantSubCategory.setSubCategories(subCategories);
			errors = null;
			subCategories = null;
			return resultantSubCategory;
		} finally {
			log.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ SubCategory Resource - End @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		}

	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ResultantSubCategory createSubCategory(SubCategory subCategory) {
		try {
			log.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ SubCategory Resource - Start @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
			return SubCategoryService.createSubCategory(subCategory);

		} catch (NullPointerException npe) {
			// creating resultantSubCategory Object
			resultantSubCategory = new ResultantSubCategory();

			// creating the error getting
			com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
			error.setErrorCode(Errors.NULL_POINTER_ERROR.getErrorCode());
			error.setErrorName("Provide correct credentials to access api");
			log.info("Error Occured in SubCategory Resource :" + error.getErrorCode() + ":" + error.getErrorName());
			subCategories = new ArrayList<SubCategory>();
			subCategories.add(subCategory);

			// wrapping the error to a list of errors
			errors = new ArrayList<com.couponsworld.apiresults.Error>();
			errors.add(error);

			resultantSubCategory.setErrors(errors);
			resultantSubCategory.setLinks(GenerateLinkService.mapOfLinks.get("createSubCategory"));
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
			log.info("Error Occured in SubCategory Resource :" + error.getErrorCode() + ":" + error.getErrorName());
			// wrapping the error to a list of errors
			errors = new ArrayList<com.couponsworld.apiresults.Error>();
			errors.add(error);

			resultantSubCategory.setErrors(errors);
			resultantSubCategory.setLinks(GenerateLinkService.mapOfLinks.get("createSubCategory"));
			resultantSubCategory.setStatus(Status.FAILURE);
			resultantSubCategory.setSubCategories(subCategories);
			subCategories = null;
			errors = null;
			return resultantSubCategory;
		} finally {
			log.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ SubCategory Resource - End @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		}
	}

	@PUT
	@Path("/{subCategoryId}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ResultantSubCategory updateSubCategory(@PathParam("subCategoryId") long subCategoryId,
			SubCategory subCategory) {
		try {
			log.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ SubCategory Resource - start @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
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
			log.info("Error Occured in SubCategory Resource :" + error.getErrorCode() + ":" + error.getErrorName());
			// wrapping the error to a list of errors
			errors = new ArrayList<com.couponsworld.apiresults.Error>();
			errors.add(error);

			resultantSubCategory.setErrors(errors);
			resultantSubCategory.setLinks(GenerateLinkService.mapOfLinks.get("updateSubCategory"));
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
			log.info("Error Occured in SubCategory Resource :" + error.getErrorCode() + ":" + error.getErrorName());
			// wrapping the error to a list of errors
			errors = new ArrayList<com.couponsworld.apiresults.Error>();
			errors.add(error);

			resultantSubCategory.setErrors(errors);
			resultantSubCategory.setLinks(GenerateLinkService.mapOfLinks.get("updateSubCategory"));
			resultantSubCategory.setStatus(Status.FAILURE);
			resultantSubCategory.setSubCategories(subCategories);

			subCategories = null;
			errors = null;
			return resultantSubCategory;
		} finally {
			log.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ SubCategory Resource - End @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		}
	}

	@DELETE
	@Path("/{subCategoryId}")
	@Produces(MediaType.APPLICATION_JSON)
	public ResultantSubCategory deleteSubCategory(@PathParam("subCategoryId") long subCategoryId) {
		try {
			log.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ SubCategory Resource - start @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
			return SubCategoryService.deleteSubCategory(subCategoryId);

		} catch (NullPointerException npe) {
			// creating resultantSubCategory Object
			resultantSubCategory = new ResultantSubCategory();
			// creating the error getting
			com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
			error.setErrorCode(Errors.NULL_POINTER_ERROR.getErrorCode());
			error.setErrorName("Provide correct credentials to access api");
			log.info("Error Occured in SubCategory Resource :" + error.getErrorCode() + ":" + error.getErrorName());
			// wrapping the error to a list of errors
			errors = new ArrayList<com.couponsworld.apiresults.Error>();
			errors.add(error);

			resultantSubCategory.setErrors(errors);
			resultantSubCategory.setLinks(GenerateLinkService.mapOfLinks.get("deleteSubCategory"));
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
			log.info("Error Occured in SubCategory Resource :" + error.getErrorCode() + ":" + error.getErrorName());
			// wrapping the error to a list of errors
			errors = new ArrayList<com.couponsworld.apiresults.Error>();
			errors.add(error);

			resultantSubCategory.setErrors(errors);
			resultantSubCategory.setLinks(GenerateLinkService.mapOfLinks.get("deleteSubCategory"));
			resultantSubCategory.setStatus(Status.FAILURE);
			resultantSubCategory.setSubCategories(subCategories);
			subCategories = null;
			errors = null;
			return resultantSubCategory;
		} finally {
			log.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ SubCategory Resource - End @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		}

	}
}
