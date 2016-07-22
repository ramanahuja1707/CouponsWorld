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

import com.couponsworld.apiresults.ResultantCategorySubCategoryMapping;
import com.couponsworld.dto.CategorySubCategoryMapping;
import com.couponsworld.enums.Errors;
import com.couponsworld.enums.Status;
import com.couponsworld.services.CategorySubCategoryMappingService;
import com.couponsworld.utilities.GenerateLinkService;

@Path("/categorySubCategoryMapping")
public class CategorySubCategoryMappingResource {

	// declaration of logger
	private static final Logger log = Logger.getLogger(CategorySubCategoryMappingResource.class.getName());

	private List<CategorySubCategoryMapping> categorySubCategoryMappings = null;
	private List<com.couponsworld.apiresults.Error> errors = null;
	private ResultantCategorySubCategoryMapping resultantCategorySubCategoryMapping;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public ResultantCategorySubCategoryMapping getCategorySubCategoryMappings() {
		try {
			log.info(
					"@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ CategorySubCategoryMapping Resource - Start @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
			return CategorySubCategoryMappingService.getCategorySubCategoryMappings();

		} catch (NullPointerException nle) {

			// creating resultantCategorySubCategoryMapping Object
			resultantCategorySubCategoryMapping = new ResultantCategorySubCategoryMapping();

			// creating the error getting
			com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
			error.setErrorCode(Errors.NULL_POINTER_ERROR.getErrorCode());
			error.setErrorName("Provide correct credentials to access api");
			log.info("Error Occured in CategorySubCategoryMapping Resource :" + error.getErrorCode() + ":"
					+ error.getErrorName());
			// wrapping the error to a list of errors
			errors = new ArrayList<com.couponsworld.apiresults.Error>();
			errors.add(error);

			resultantCategorySubCategoryMapping.setErrors(errors);
			resultantCategorySubCategoryMapping.setLinks(
					GenerateLinkService.generateCategorySubCategoryMappingLink("getCategorySubCategoryMappings"));
			resultantCategorySubCategoryMapping.setStatus(Status.FAILURE);
			resultantCategorySubCategoryMapping.setCategorySubCategoryMappings(categorySubCategoryMappings);
			errors = null;
			categorySubCategoryMappings = null;
			return resultantCategorySubCategoryMapping;
		} catch (Exception exception) {
			// creating resultantCategorySubCategoryMapping Object
			resultantCategorySubCategoryMapping = new ResultantCategorySubCategoryMapping();

			// creating the error getting
			com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
			error.setErrorCode(Errors.GENERAL_ERROR.getErrorCode());
			error.setErrorName(exception.getMessage());
			log.info("Error Occured in CategorySubCategoryMapping Resource :" + error.getErrorCode() + ":"
					+ error.getErrorName());
			// wrapping the error to a list of errors
			errors = new ArrayList<com.couponsworld.apiresults.Error>();
			errors.add(error);

			// creating resultantCategorySubCategoryMapping Object
			resultantCategorySubCategoryMapping = new ResultantCategorySubCategoryMapping();
			resultantCategorySubCategoryMapping.setErrors(errors);
			resultantCategorySubCategoryMapping.setLinks(
					GenerateLinkService.generateCategorySubCategoryMappingLink("getCategorySubCategoryMappings"));
			resultantCategorySubCategoryMapping.setStatus(Status.FAILURE);
			resultantCategorySubCategoryMapping.setCategorySubCategoryMappings(categorySubCategoryMappings);

			errors = null;
			categorySubCategoryMappings = null;
			return resultantCategorySubCategoryMapping;
		} finally {
			log.info(
					"@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ CategorySubCategoryMapping Resource - End @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		}

	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ResultantCategorySubCategoryMapping createCategorySubCategoryMapping(
			CategorySubCategoryMapping categorySubCategoryMapping) {
		try {
			log.info(
					"@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ CategorySubCategoryMapping Resource - Start @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
			return CategorySubCategoryMappingService.createCategorySubCategoryMapping(categorySubCategoryMapping);

		} catch (NullPointerException npe) {

			// creating resultantCategorySubCategoryMapping Object
			resultantCategorySubCategoryMapping = new ResultantCategorySubCategoryMapping();

			// creating CategorySubCategoryMapping List to wrap the input
			// CategorySubCategoryMapping Object into it
			categorySubCategoryMappings = new ArrayList<CategorySubCategoryMapping>();
			categorySubCategoryMappings.add(categorySubCategoryMapping);

			// creating the error getting
			com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
			error.setErrorCode(Errors.NULL_POINTER_ERROR.getErrorCode());
			error.setErrorName("Provide correct credentials to access api");
			log.info("Error Occured in CategorySubCategoryMapping Resource :" + error.getErrorCode() + ":"
					+ error.getErrorName());
			// wrapping the error to a list of errors
			errors = new ArrayList<com.couponsworld.apiresults.Error>();
			errors.add(error);

			resultantCategorySubCategoryMapping.setErrors(errors);
			resultantCategorySubCategoryMapping.setLinks(
					GenerateLinkService.generateCategorySubCategoryMappingLink("createCategorySubCategoryMapping"));
			resultantCategorySubCategoryMapping.setStatus(Status.FAILURE);
			resultantCategorySubCategoryMapping.setCategorySubCategoryMappings(categorySubCategoryMappings);
			errors = null;
			categorySubCategoryMappings = null;
			return resultantCategorySubCategoryMapping;
		} catch (Exception exception) {

			// creating ResultantCategorySubCategoryMapping Object
			resultantCategorySubCategoryMapping = new ResultantCategorySubCategoryMapping();

			// creating categorySubCategoryMappings List to wrap the input
			// categorySubCategoryMapping Object into it
			categorySubCategoryMappings = new ArrayList<CategorySubCategoryMapping>();
			categorySubCategoryMappings.add(categorySubCategoryMapping);

			// creating the error getting
			com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
			error.setErrorCode(Errors.GENERAL_ERROR.getErrorCode());
			error.setErrorName(exception.getMessage());
			log.info("Error Occured in CategorySubCategoryMapping Resource :" + error.getErrorCode() + ":"
					+ error.getErrorName());
			// wrapping the error to a list of errors
			errors = new ArrayList<com.couponsworld.apiresults.Error>();
			errors.add(error);

			// creating resultantCompany Object
			resultantCategorySubCategoryMapping = new ResultantCategorySubCategoryMapping();
			resultantCategorySubCategoryMapping.setErrors(errors);
			resultantCategorySubCategoryMapping.setLinks(
					GenerateLinkService.generateCategorySubCategoryMappingLink("createCategorySubCategoryMapping"));
			resultantCategorySubCategoryMapping.setStatus(Status.FAILURE);
			resultantCategorySubCategoryMapping.setCategorySubCategoryMappings(categorySubCategoryMappings);
			errors = null;
			categorySubCategoryMappings = null;
			return resultantCategorySubCategoryMapping;
		} finally {
			log.info(
					"@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ CategorySubCategoryMapping Resource - End @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		}
	}

	@PUT
	@Path("/{categorySubCategoryMappingId}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ResultantCategorySubCategoryMapping updateCategorySubCategoryMapping(
			@PathParam("categorySubCategoryMappingId") long categorySubCategoryMappingId,
			CategorySubCategoryMapping categorySubCategoryMapping) {
		try {
			log.info(
					"@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ CategorySubCategoryMapping Resource - Start @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
			return CategorySubCategoryMappingService.updateCategorySubCategoryMapping(categorySubCategoryMappingId,
					categorySubCategoryMapping);

		} catch (NullPointerException npe) {
			// creating resultantCategorySubCategoryMapping Object
			resultantCategorySubCategoryMapping = new ResultantCategorySubCategoryMapping();

			// creating categorySubCategoryMapping List to wrap the input
			// categorySubCategoryMapping Object into it
			categorySubCategoryMappings = new ArrayList<CategorySubCategoryMapping>();
			categorySubCategoryMappings.add(categorySubCategoryMapping);

			// creating the error getting
			com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
			error.setErrorCode(Errors.NULL_POINTER_ERROR.getErrorCode());
			error.setErrorName("Provide correct credentials to access api");
			log.info("Error Occured in CategorySubCategoryMapping Resource :" + error.getErrorCode() + ":"
					+ error.getErrorName());
			// wrapping the error to a list of errors
			errors = new ArrayList<com.couponsworld.apiresults.Error>();
			errors.add(error);

			resultantCategorySubCategoryMapping.setErrors(errors);
			resultantCategorySubCategoryMapping.setLinks(
					GenerateLinkService.generateCategorySubCategoryMappingLink("updateCategorySubCategoryMapping"));
			resultantCategorySubCategoryMapping.setStatus(Status.FAILURE);
			resultantCategorySubCategoryMapping.setCategorySubCategoryMappings(categorySubCategoryMappings);
			errors = null;
			categorySubCategoryMappings = null;
			return resultantCategorySubCategoryMapping;
		} catch (Exception exception) {

			// creating resultantCategorySubCategoryMapping Object
			resultantCategorySubCategoryMapping = new ResultantCategorySubCategoryMapping();

			// creating categorySubCategoryMapping List to wrap the input
			// categorySubCategoryMapping Object into it
			categorySubCategoryMappings = new ArrayList<CategorySubCategoryMapping>();
			categorySubCategoryMappings.add(categorySubCategoryMapping);

			// creating the error getting
			com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
			error.setErrorCode(Errors.GENERAL_ERROR.getErrorCode());
			error.setErrorName(exception.getMessage());
			log.info("Error Occured in CategorySubCategoryMapping Resource :" + error.getErrorCode() + ":"
					+ error.getErrorName());
			// wrapping the error to a list of errors
			errors = new ArrayList<com.couponsworld.apiresults.Error>();
			errors.add(error);

			// creating resultantCategorySubCategoryMapping Object
			resultantCategorySubCategoryMapping = new ResultantCategorySubCategoryMapping();
			resultantCategorySubCategoryMapping.setErrors(errors);
			resultantCategorySubCategoryMapping.setLinks(
					GenerateLinkService.generateCategorySubCategoryMappingLink("updateCategorySubCategoryMapping"));
			resultantCategorySubCategoryMapping.setStatus(Status.FAILURE);
			resultantCategorySubCategoryMapping.setCategorySubCategoryMappings(categorySubCategoryMappings);

			errors = null;
			categorySubCategoryMappings = null;
			return resultantCategorySubCategoryMapping;
		} finally {
			log.info(
					"@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ CategorySubCategoryMapping Resource - End @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		}
	}

	@DELETE
	@Path("/{categorySubCategoryMappingId}")
	@Produces(MediaType.APPLICATION_JSON)
	public ResultantCategorySubCategoryMapping deleteCategorySubCategoryMappings(
			@PathParam("categorySubCategoryMappingId") long categorySubCategoryMappingId) {
		try {
			log.info(
					"@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ CategorySubCategoryMapping Resource - Start @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
			return CategorySubCategoryMappingService.deleteCategorySubCategoryMapping(categorySubCategoryMappingId);
		} catch (NullPointerException npe) {
			// creating resultantCategorySubCategoryMapping Object
			resultantCategorySubCategoryMapping = new ResultantCategorySubCategoryMapping();

			// creating the error getting
			com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
			error.setErrorCode(Errors.NULL_POINTER_ERROR.getErrorCode());
			error.setErrorName("Provide correct credentials to access api");
			log.info("Error Occured in CategorySubCategoryMapping Resource :" + error.getErrorCode() + ":"
					+ error.getErrorName());
			// wrapping the error to a list of errors
			errors = new ArrayList<com.couponsworld.apiresults.Error>();
			errors.add(error);

			resultantCategorySubCategoryMapping.setErrors(errors);
			resultantCategorySubCategoryMapping.setLinks(
					GenerateLinkService.generateCategorySubCategoryMappingLink("deleteCategorySubCategoryMappings"));
			resultantCategorySubCategoryMapping.setStatus(Status.FAILURE);
			resultantCategorySubCategoryMapping.setCategorySubCategoryMappings(categorySubCategoryMappings);
			errors = null;
			categorySubCategoryMappings = null;
			return resultantCategorySubCategoryMapping;
		} catch (Exception exception) {

			// creating resultantCategorySubCategoryMapping Object
			resultantCategorySubCategoryMapping = new ResultantCategorySubCategoryMapping();

			// creating the error getting
			com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
			error.setErrorCode(Errors.GENERAL_ERROR.getErrorCode());
			error.setErrorName(exception.getMessage());
			log.info("Error Occured in CategorySubCategoryMapping Resource :" + error.getErrorCode() + ":"
					+ error.getErrorName());
			// wrapping the error to a list of errors
			errors = new ArrayList<com.couponsworld.apiresults.Error>();
			errors.add(error);

			// creating resultantCompany Object
			resultantCategorySubCategoryMapping = new ResultantCategorySubCategoryMapping();
			resultantCategorySubCategoryMapping.setErrors(errors);
			resultantCategorySubCategoryMapping.setLinks(
					GenerateLinkService.generateCategorySubCategoryMappingLink("deleteCategorySubCategoryMappings"));
			resultantCategorySubCategoryMapping.setStatus(Status.FAILURE);
			resultantCategorySubCategoryMapping.setCategorySubCategoryMappings(categorySubCategoryMappings);
			errors = null;
			categorySubCategoryMappings = null;
			return resultantCategorySubCategoryMapping;
		} finally {
			log.info(
					"@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ CategorySubCategoryMapping Resource - End @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		}

	}

}
