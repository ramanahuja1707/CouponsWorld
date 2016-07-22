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

import com.couponsworld.apiresults.ResultantCompany;
import com.couponsworld.dto.Company;
import com.couponsworld.enums.Errors;
import com.couponsworld.enums.Status;
import com.couponsworld.services.CompanyService;
import com.couponsworld.utilities.GenerateLinkService;

@Path("/companies")
public class CompanyResource {

	// declaration of logger
	private static final Logger log = Logger.getLogger(CompanyResource.class.getName());

	private List<Company> companies = null;
	private List<com.couponsworld.apiresults.Error> errors = null;
	private ResultantCompany resultantCompany;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public ResultantCompany getCompanies() {
		try {
			log.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ Company Resource - Start @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
			return CompanyService.getCompanies();

		} catch (NullPointerException nle) {

			// creating resultantCompany Object
			resultantCompany = new ResultantCompany();

			// creating the error getting
			com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
			error.setErrorCode(Errors.NULL_POINTER_ERROR.getErrorCode());
			error.setErrorName("Provide correct credentials to access api");
			log.info("Error Occured in Company Resource :" + error.getErrorCode() + ":" + error.getErrorName());
			// wrapping the error to a list of errors
			errors = new ArrayList<com.couponsworld.apiresults.Error>();
			errors.add(error);

			resultantCompany.setErrors(errors);
			resultantCompany.setLinks(GenerateLinkService.mapOfLinks.get("getOffers"));
			resultantCompany.setStatus(Status.FAILURE);
			resultantCompany.setCompanies(companies);
			errors = null;
			companies = null;
			return resultantCompany;
		} catch (Exception exception) {
			// creating resultantCompany Object
			resultantCompany = new ResultantCompany();

			// creating the error getting
			com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
			error.setErrorCode(Errors.GENERAL_ERROR.getErrorCode());
			error.setErrorName(exception.getMessage());
			log.info("Error Occured in Company Resource :" + error.getErrorCode() + ":" + error.getErrorName());
			// wrapping the error to a list of errors
			errors = new ArrayList<com.couponsworld.apiresults.Error>();
			errors.add(error);

			// creating resultantCompany Object
			resultantCompany = new ResultantCompany();
			resultantCompany.setErrors(errors);
			resultantCompany.setLinks(GenerateLinkService.mapOfLinks.get("getOffers"));
			resultantCompany.setStatus(Status.FAILURE);
			resultantCompany.setCompanies(companies);

			errors = null;
			companies = null;
			return resultantCompany;
		} finally {
			log.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ Company Resource - End @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		}

	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ResultantCompany createCompany(Company company) {
		try {
			log.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ Company Resource - Start @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
			return CompanyService.createCompany(company);

		} catch (NullPointerException npe) {

			// creating resultantCompany Object
			resultantCompany = new ResultantCompany();

			// creating company List to wrap the input Offer Object into it
			companies = new ArrayList<Company>();
			companies.add(company);

			// creating the error getting
			com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
			error.setErrorCode(Errors.NULL_POINTER_ERROR.getErrorCode());
			error.setErrorName("Provide correct credentials to access api");
			log.info("Error Occured in Company Resource :" + error.getErrorCode() + ":" + error.getErrorName());
			// wrapping the error to a list of errors
			errors = new ArrayList<com.couponsworld.apiresults.Error>();
			errors.add(error);

			resultantCompany.setErrors(errors);
			resultantCompany.setLinks(GenerateLinkService.mapOfLinks.get("getOffers"));
			resultantCompany.setStatus(Status.FAILURE);
			resultantCompany.setCompanies(companies);
			errors = null;
			companies = null;
			return resultantCompany;
		} catch (Exception exception) {

			// creating resultantCompany Object
			resultantCompany = new ResultantCompany();

			// creating company List to wrap the input Offer Object into it
			companies = new ArrayList<Company>();
			companies.add(company);

			// creating the error getting
			com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
			error.setErrorCode(Errors.GENERAL_ERROR.getErrorCode());
			error.setErrorName(exception.getMessage());
			log.info("Error Occured in Company Resource :" + error.getErrorCode() + ":" + error.getErrorName());
			// wrapping the error to a list of errors
			errors = new ArrayList<com.couponsworld.apiresults.Error>();
			errors.add(error);

			// creating resultantCompany Object
			resultantCompany = new ResultantCompany();
			resultantCompany.setErrors(errors);
			resultantCompany.setLinks(GenerateLinkService.mapOfLinks.get("getOffers"));
			resultantCompany.setStatus(Status.FAILURE);
			resultantCompany.setCompanies(companies);
			errors = null;
			companies = null;
			return resultantCompany;
		} finally {
			log.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ Company Resource - End @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		}
	}

	@PUT
	@Path("/{companyId}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ResultantCompany updateCompany(@PathParam("companyId") long companyId, Company company) {
		try {
			log.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ Company Resource - Start @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
			return CompanyService.updateCompany(companyId, company);

		} catch (NullPointerException npe) {
			// creating resultantCompany Object
			resultantCompany = new ResultantCompany();

			// creating company List to wrap the input Offer Object into it
			companies = new ArrayList<Company>();
			companies.add(company);

			// creating the error getting
			com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
			error.setErrorCode(Errors.NULL_POINTER_ERROR.getErrorCode());
			error.setErrorName("Provide correct credentials to access api");
			log.info("Error Occured in Company Resource :" + error.getErrorCode() + ":" + error.getErrorName());
			// wrapping the error to a list of errors
			errors = new ArrayList<com.couponsworld.apiresults.Error>();
			errors.add(error);

			resultantCompany.setErrors(errors);
			resultantCompany.setLinks(GenerateLinkService.mapOfLinks.get("getOffers"));
			resultantCompany.setStatus(Status.FAILURE);
			resultantCompany.setCompanies(companies);
			errors = null;
			companies = null;
			return resultantCompany;
		} catch (Exception exception) {

			// creating resultantCompany Object
			resultantCompany = new ResultantCompany();

			// creating company List to wrap the input Offer Object into it
			companies = new ArrayList<Company>();
			companies.add(company);

			// creating the error getting
			com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
			error.setErrorCode(Errors.GENERAL_ERROR.getErrorCode());
			error.setErrorName(exception.getMessage());
			log.info("Error Occured in Company Resource :" + error.getErrorCode() + ":" + error.getErrorName());
			// wrapping the error to a list of errors
			errors = new ArrayList<com.couponsworld.apiresults.Error>();
			errors.add(error);

			// creating resultantCompany Object
			resultantCompany = new ResultantCompany();
			resultantCompany.setErrors(errors);
			resultantCompany.setLinks(GenerateLinkService.mapOfLinks.get("getOffers"));
			resultantCompany.setStatus(Status.FAILURE);
			resultantCompany.setCompanies(companies);
			errors = null;
			companies = null;
			return resultantCompany;
		} finally {
			log.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ Company Resource - End @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		}
	}

	@DELETE
	@Path("/{companyId}")
	@Produces(MediaType.APPLICATION_JSON)
	public ResultantCompany deleteCompany(@PathParam("companyId") long companyId) {
		try {
			log.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ Company Resource - Start @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
			return CompanyService.deleteCompany(companyId);
		} catch (NullPointerException npe) {
			// creating resultantCompany Object
			resultantCompany = new ResultantCompany();

			// creating the error getting
			com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
			error.setErrorCode(Errors.NULL_POINTER_ERROR.getErrorCode());
			error.setErrorName("Provide correct credentials to access api");
			log.info("Error Occured in Company Resource :" + error.getErrorCode() + ":" + error.getErrorName());
			// wrapping the error to a list of errors
			errors = new ArrayList<com.couponsworld.apiresults.Error>();
			errors.add(error);

			resultantCompany.setErrors(errors);
			resultantCompany.setLinks(GenerateLinkService.mapOfLinks.get("getOffers"));
			resultantCompany.setStatus(Status.FAILURE);
			resultantCompany.setCompanies(companies);
			errors = null;
			companies = null;
			return resultantCompany;
		} catch (Exception exception) {

			// creating resultantCompany Object
			resultantCompany = new ResultantCompany();

			// creating the error getting
			com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
			error.setErrorCode(Errors.GENERAL_ERROR.getErrorCode());
			error.setErrorName(exception.getMessage());
			log.info("Error Occured in Company Resource :" + error.getErrorCode() + ":" + error.getErrorName());
			// wrapping the error to a list of errors
			errors = new ArrayList<com.couponsworld.apiresults.Error>();
			errors.add(error);

			// creating resultantCompany Object
			resultantCompany = new ResultantCompany();
			resultantCompany.setErrors(errors);
			resultantCompany.setLinks(GenerateLinkService.mapOfLinks.get("getOffers"));
			resultantCompany.setStatus(Status.FAILURE);
			resultantCompany.setCompanies(companies);
			errors = null;
			companies = null;
			return resultantCompany;
		} finally {
			log.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ Company Resource - End @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		}

	}
}
