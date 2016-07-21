package com.couponsworld.services;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.couponsworld.apiresults.ResultantCompany;
import com.couponsworld.database.DatabaseService;
import com.couponsworld.dto.Category;
import com.couponsworld.dto.Company;
import com.couponsworld.enums.Errors;
import com.couponsworld.enums.Status;
import com.couponsworld.exceptions.CompanyException;
import com.couponsworld.utilities.GenerateLinkService;

public class CompanyService {

	// declaration of logger
	private static final Logger log = Logger.getLogger(CompanyService.class.getName());

	public static List<Company> companies = null;
	public static List<com.couponsworld.apiresults.Error> errors = null;
	public static ResultantCompany resultantCompany;

	public static ResultantCompany createCompany(Company company) {
		try {
			log.info("=========================== Company Service - Start =================================");
			Object returnedObject = DatabaseService.createCompanyInDatabase(company);
			if (returnedObject instanceof Company) {
				log.info("Company wrapping into resultantCompany ..");
				// company successfully created and returned....
				// wrapping the company into company list
				companies = new ArrayList<Company>();
				companies.add((Company) returnedObject);

				// Creating ResultantCompany object
				resultantCompany = new ResultantCompany();

				resultantCompany.setCompanies(companies);
				resultantCompany.setErrors(errors);
				resultantCompany.setStatus(Status.SUCCESS);
				resultantCompany.setLinks(GenerateLinkService.mapOfLinks.get("createCompany"));
				log.info("ResultantCompany object returned successfully..");
				log.info("=========================== Company Service - End =================================");
			} else {
				// Creating Error for updating company
				com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
				error.setErrorCode(Errors.GENERAL_ERROR.getErrorCode());
				error.setErrorName(((Exception) returnedObject).getMessage());
				log.info("Error Occured while creating the Company..:" + error.getErrorCode() + ":"
						+ error.getErrorName());
				// wrapping the company into company list
				companies = new ArrayList<Company>();
				companies.add(company);

				// wrapping the error into errors list
				errors = new ArrayList<com.couponsworld.apiresults.Error>();
				errors.add(error);

				// company does not created and error returned....
				// Creating ResultantCompany object
				resultantCompany = new ResultantCompany();

				resultantCompany.setCompanies(companies);
				resultantCompany.setErrors(errors);
				resultantCompany.setStatus(Status.FAILURE);
				resultantCompany.setLinks(GenerateLinkService.mapOfLinks.get("createCompany"));
				log.info("ResultantCompany object returned successfully..");
				log.info("=========================== Company Service - End =================================");
			}
		} catch (Exception exception) {
			// Creating Error for updating company
			com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
			error.setErrorCode(Errors.GENERAL_ERROR.getErrorCode());
			error.setErrorName(exception.getMessage());
			log.info("Error Occured while creating the Company..:" + error.getErrorCode() + ":" + error.getErrorName());
			// wrapping the company into company list
			companies = new ArrayList<Company>();
			companies.add(company);

			// wrapping the error into errors list
			errors = new ArrayList<com.couponsworld.apiresults.Error>();
			errors.add(error);

			// company does not created and error returned....
			// Creating ResultantCompany object
			resultantCompany = new ResultantCompany();

			resultantCompany.setCompanies(companies);
			resultantCompany.setErrors(errors);
			resultantCompany.setStatus(Status.FAILURE);
			resultantCompany.setLinks(GenerateLinkService.mapOfLinks.get("createCompany"));
			log.info("ResultantCompany object returned successfully..");
			log.info("=========================== Company Service - End =================================");
		}
		companies = null;
		errors = null;
		return resultantCompany;
	}

	public static ResultantCompany getCompanies() {
		try {
			log.info("=========================== Company Service - Start =================================");
			Object returnedObject = DatabaseService.getCompaniesFromDatabase();
			if (returnedObject instanceof Exception) {
				// Creating Error for updating company
				com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
				error.setErrorCode(Errors.GENERAL_ERROR.getErrorCode());
				error.setErrorName(((Exception) returnedObject).getMessage());
				log.info("Error Occured while fetching all the Companies..:" + error.getErrorCode() + ":"
						+ error.getErrorName());
				// wrapping the error into errors list
				errors = new ArrayList<com.couponsworld.apiresults.Error>();
				errors.add(error);
				// company does not created and error returned....
				// Creating ResultantCompany object
				resultantCompany = new ResultantCompany();
				resultantCompany.setCompanies(companies);
				resultantCompany.setErrors(errors);
				resultantCompany.setStatus(Status.FAILURE);
				resultantCompany.setLinks(GenerateLinkService.mapOfLinks.get("getCompanies"));
				log.info("ResultantCompany object returned successfully..");
				log.info("=========================== Company Service - End =================================");
			} else if (returnedObject instanceof CompanyException) {
				// Creating Error for updating company
				com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
				error.setErrorCode(Errors.COMPANY_ERROR.getErrorCode());
				error.setErrorName(((CompanyException) returnedObject).getMessage());
				log.info("Error Occured while fetching all the Companies..:" + error.getErrorCode() + ":"
						+ error.getErrorName());
				// wrapping the error into errors list
				errors = new ArrayList<com.couponsworld.apiresults.Error>();
				errors.add(error);
				// company does not created and error returned....
				// Creating ResultantCompany object
				resultantCompany = new ResultantCompany();
				resultantCompany.setCompanies(companies);
				resultantCompany.setErrors(errors);
				resultantCompany.setStatus(Status.FAILURE);
				resultantCompany.setLinks(GenerateLinkService.mapOfLinks.get("getCompanies"));
				log.info("ResultantCompany object returned successfully..");
				log.info("=========================== Company Service - End =================================");
			} else {

				if (((List<Category>) returnedObject).size() > 0) {
					log.info("Company wrapping into resultantCompany ..");
					resultantCompany = new ResultantCompany();
					resultantCompany.setCompanies((List<Company>) returnedObject);
					resultantCompany.setErrors(errors);
					resultantCompany.setStatus(Status.SUCCESS);
					resultantCompany.setLinks(GenerateLinkService.mapOfLinks.get("getCompanies"));
					log.info("ResultantCompany object returned successfully..");
					log.info("=========================== Company Service - End =================================");
				} else {
					log.info("Company=null wrapping into resultantCompany ..");
					resultantCompany = new ResultantCompany();
					resultantCompany.setCompanies(companies);
					resultantCompany.setErrors(errors);
					resultantCompany.setStatus(Status.SUCCESS);
					resultantCompany.setLinks(GenerateLinkService.mapOfLinks.get("getCompanies"));
					log.info("ResultantCompany object returned successfully..");
					log.info("=========================== Company Service - End =================================");
				}
			}
		} catch (Exception e) {
			// Creating Error for updating company
			com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
			error.setErrorCode(Errors.GENERAL_ERROR.getErrorCode());
			error.setErrorName(e.getMessage());
			log.info("Error Occured while fetching all the Companies..:" + error.getErrorCode() + ":"
					+ error.getErrorName());
			// wrapping the error into errors list
			errors = new ArrayList<com.couponsworld.apiresults.Error>();
			errors.add(error);
			// company does not created and error returned....
			// Creating ResultantCompany object
			resultantCompany = new ResultantCompany();
			resultantCompany.setCompanies(companies);
			resultantCompany.setErrors(errors);
			resultantCompany.setStatus(Status.FAILURE);
			resultantCompany.setLinks(GenerateLinkService.mapOfLinks.get("getCompanies"));
			log.info("ResultantCompany object returned successfully..");
			log.info("=========================== Company Service - End =================================");
			companies = null;
			errors = null;
		}
		companies = null;
		errors = null;
		return resultantCompany;
	}

	public static ResultantCompany updateCompany(long companyId, Company company) {
		try {
			log.info("=========================== Company Service - Start =================================");
			Object returnedObject = DatabaseService.updateCompanyInDatabase(companyId, company);
			if (returnedObject instanceof Company) {
				log.info("Company wrapping into resultantCompany ..");
				// Company successfully created and returned....
				// Creating ResultantComapny object
				companies = new ArrayList<Company>();
				companies.add((Company) returnedObject);

				resultantCompany = new ResultantCompany();
				resultantCompany.setCompanies(companies);
				resultantCompany.setErrors(errors);
				resultantCompany.setStatus(Status.SUCCESS);
				resultantCompany.setLinks(GenerateLinkService.mapOfLinks.get("updateCompany"));
				log.info("ResultantCompany object returned successfully..");
				log.info("=========================== Company Service - End =================================");
			} else if (returnedObject instanceof CompanyException) {
				// Creating Error for updating company
				com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
				error.setErrorCode(Errors.COMPANY_ERROR.getErrorCode());
				error.setErrorName(((CompanyException) returnedObject).getMessage());
				log.info("Error Occured while updating the Company..:" + error.getErrorCode() + ":"
						+ error.getErrorName());
				// wrapping the error into errors list
				errors = new ArrayList<com.couponsworld.apiresults.Error>();
				errors.add(error);

				// wrapping the company into company list
				companies = new ArrayList<Company>();
				companies.add(company);

				// company does not created and error returned....
				// Creating ResultantCompany object
				resultantCompany = new ResultantCompany();
				resultantCompany.setCompanies(companies);
				resultantCompany.setErrors(errors);
				resultantCompany.setStatus(Status.FAILURE);
				resultantCompany.setLinks(GenerateLinkService.mapOfLinks.get("updateCompany"));
				log.info("ResultantCompany object returned successfully..");
				log.info("=========================== Company Service - End =================================");
			} else {
				// Creating Error for updating company
				com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
				error.setErrorCode(Errors.GENERAL_ERROR.getErrorCode());
				error.setErrorName(((Exception) returnedObject).getMessage());
				log.info("Error Occured while updating the Company..:" + error.getErrorCode() + ":"
						+ error.getErrorName());
				// wrapping the error into errors list
				errors = new ArrayList<com.couponsworld.apiresults.Error>();
				errors.add(error);

				// wrapping the company into company list
				companies = new ArrayList<Company>();
				companies.add(company);

				// company does not created and error returned....
				// Creating ResultantCompany object
				resultantCompany = new ResultantCompany();
				resultantCompany.setCompanies(companies);
				resultantCompany.setErrors(errors);
				resultantCompany.setStatus(Status.FAILURE);
				resultantCompany.setLinks(GenerateLinkService.mapOfLinks.get("updateCompany"));
				log.info("ResultantCompany object returned successfully..");
				log.info("=========================== Company Service - End =================================");
			}
		} catch (Exception e) {
			// Creating Error for updating company
			com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
			error.setErrorCode(Errors.GENERAL_ERROR.getErrorCode());
			error.setErrorName(((Exception) e).getMessage());
			log.info("Error Occured while updating the Company..:" + error.getErrorCode() + ":" + error.getErrorName());
			// wrapping the error into errors list
			errors = new ArrayList<com.couponsworld.apiresults.Error>();
			errors.add(error);

			// wrapping the company into company list
			companies = new ArrayList<Company>();
			companies.add(company);

			// company does not created and error returned....
			// Creating ResultantCompany object
			resultantCompany = new ResultantCompany();
			resultantCompany.setCompanies(companies);
			resultantCompany.setErrors(errors);
			resultantCompany.setStatus(Status.FAILURE);
			resultantCompany.setLinks(GenerateLinkService.mapOfLinks.get("updateCompany"));
			log.info("ResultantCompany object returned successfully..");
			log.info("=========================== Company Service - End =================================");
			companies = null;
			errors = null;
		}
		companies = null;
		errors = null;
		return resultantCompany;
	}

	public static ResultantCompany deleteCompany(long companyId) {
		try {
			log.info("=========================== Company Service - Start =================================");
			Object returnedObject = DatabaseService.deleteCompanyFromDatabase(companyId);
			if (returnedObject instanceof Company) {
				log.info("Company wrapping into resultantCompany ..");
				companies = new ArrayList<Company>();
				companies.add((Company) returnedObject);

				resultantCompany = new ResultantCompany();
				resultantCompany.setCompanies(companies);
				resultantCompany.setErrors(errors);
				resultantCompany.setStatus(Status.SUCCESS);
				resultantCompany.setLinks(GenerateLinkService.mapOfLinks.get("deleteCompany"));
				log.info("ResultantCompany object returned successfully..");
				log.info("=========================== Company Service - End =================================");
			} else if (returnedObject instanceof CompanyException) {
				// Creating Error for updating company
				com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
				error.setErrorCode(Errors.COMPANY_ERROR.getErrorCode());
				error.setErrorName(((CompanyException) returnedObject).getMessage());
				log.info("Error Occured while deleting the Company..:" + error.getErrorCode() + ":"
						+ error.getErrorName());
				// wrapping the error into errors list
				errors = new ArrayList<com.couponsworld.apiresults.Error>();
				errors.add(error);

				// company does not created and error returned....
				// Creating ResultantCompany object
				resultantCompany = new ResultantCompany();
				resultantCompany.setCompanies(companies);
				resultantCompany.setErrors(errors);
				resultantCompany.setStatus(Status.FAILURE);
				resultantCompany.setLinks(GenerateLinkService.mapOfLinks.get("deleteCompany"));
				log.info("ResultantCompany object returned successfully..");
				log.info("=========================== Company Service - End =================================");
			} else {
				// Creating Error for updating company
				com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
				error.setErrorCode(Errors.GENERAL_ERROR.getErrorCode());
				error.setErrorName(((Exception) returnedObject).getMessage());
				log.info("Error Occured while deleting the Company..:" + error.getErrorCode() + ":"
						+ error.getErrorName());
				// wrapping the error into errors list
				errors = new ArrayList<com.couponsworld.apiresults.Error>();
				errors.add(error);

				// company does not created and error returned....
				// Creating ResultantCompany object
				resultantCompany = new ResultantCompany();
				resultantCompany.setCompanies(companies);
				resultantCompany.setErrors(errors);
				resultantCompany.setStatus(Status.FAILURE);
				resultantCompany.setLinks(GenerateLinkService.mapOfLinks.get("deleteCompany"));
				log.info("ResultantCompany object returned successfully..");
				log.info("=========================== Company Service - End =================================");
			}
		} catch (Exception e) {
			// Creating Error for updating company
			com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
			error.setErrorCode(Errors.GENERAL_ERROR.getErrorCode());
			error.setErrorName(((Exception) e).getMessage());
			log.info("Error Occured while deleting the Company..:" + error.getErrorCode() + ":" + error.getErrorName());
			// wrapping the error into errors list
			errors = new ArrayList<com.couponsworld.apiresults.Error>();
			errors.add(error);

			// company does not created and error returned....
			// Creating ResultantCompany object
			resultantCompany = new ResultantCompany();
			resultantCompany.setCompanies(companies);
			resultantCompany.setErrors(errors);
			resultantCompany.setStatus(Status.FAILURE);
			resultantCompany.setLinks(GenerateLinkService.mapOfLinks.get("deleteCompany"));
			log.info("ResultantCompany object returned successfully..");
			log.info("=========================== Company Service - End =================================");
			companies = null;
			errors = null;
		}
		companies = null;
		errors = null;
		return resultantCompany;
	}

}
