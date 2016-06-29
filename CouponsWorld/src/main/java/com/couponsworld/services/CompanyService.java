package com.couponsworld.services;

import java.util.ArrayList;
import java.util.List;

import com.couponsworld.apiresults.ResultantCompany;
import com.couponsworld.database.DatabaseService;
import com.couponsworld.dto.Category;
import com.couponsworld.dto.Company;
import com.couponsworld.enums.Errors;
import com.couponsworld.enums.Status;
import com.couponsworld.exceptions.CompanyException;
import com.couponsworld.utilities.GenerateLinkService;

public class CompanyService {
	public static List<Company> companies = null;
	public static List<com.couponsworld.apiresults.Error> errors = null;
	public static ResultantCompany resultantCompany;

	public static ResultantCompany createCompany(Company company) {
		try {
			Object returnedObject = DatabaseService.createCompanyInDatabase(company);
			if (returnedObject instanceof Company) {
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
			} else {
				// Creating Error for updating company
				com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
				error.setErrorCode(Errors.GENERAL_ERROR.getErrorCode());
				error.setErrorName(((Exception) returnedObject).getMessage());

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
			}
		} catch (Exception exception) {
			// Creating Error for updating company
			com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
			error.setErrorCode(Errors.GENERAL_ERROR.getErrorCode());
			error.setErrorName(exception.getMessage());

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
		}
		companies = null;
		errors = null;
		return resultantCompany;
	}

	public static ResultantCompany getCompanies() {
		try {
			Object returnedObject = DatabaseService.getCompaniesFromDatabase();
			if (returnedObject instanceof Exception) {
				// Creating Error for updating company
				com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
				error.setErrorCode(Errors.GENERAL_ERROR.getErrorCode());
				error.setErrorName(((Exception) returnedObject).getMessage());

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
			} else if (returnedObject instanceof CompanyException) {
				// Creating Error for updating company
				com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
				error.setErrorCode(Errors.COMPANY_ERROR.getErrorCode());
				error.setErrorName(((CompanyException) returnedObject).getMessage());

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
			} else {

				if (((List<Category>) returnedObject).size() > 0) {

					resultantCompany = new ResultantCompany();
					resultantCompany.setCompanies((List<Company>) returnedObject);
					resultantCompany.setErrors(errors);
					resultantCompany.setStatus(Status.SUCCESS);
					resultantCompany.setLinks(GenerateLinkService.mapOfLinks.get("getCompanies"));
				} else {
					resultantCompany = new ResultantCompany();
					resultantCompany.setCompanies(companies);
					resultantCompany.setErrors(errors);
					resultantCompany.setStatus(Status.SUCCESS);
					resultantCompany.setLinks(GenerateLinkService.mapOfLinks.get("getCompanies"));
				}
			}
		} catch (Exception e) {
			// Creating Error for updating company
			com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
			error.setErrorCode(Errors.GENERAL_ERROR.getErrorCode());
			error.setErrorName(e.getMessage());

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
			companies = null;
			errors = null;
		}
		companies = null;
		errors = null;
		return resultantCompany;
	}

	public static ResultantCompany updateCompany(long companyId, Company company) {
		try {
			Object returnedObject = DatabaseService.updateCompanyInDatabase(companyId, company);
			if (returnedObject instanceof Company) {
				// Company successfully created and returned....
				// Creating ResultantComapny object
				companies = new ArrayList<Company>();
				companies.add((Company) returnedObject);

				resultantCompany = new ResultantCompany();
				resultantCompany.setCompanies(companies);
				resultantCompany.setErrors(errors);
				resultantCompany.setStatus(Status.SUCCESS);
				resultantCompany.setLinks(GenerateLinkService.mapOfLinks.get("updateCompany"));
			} else if (returnedObject instanceof CompanyException) {
				// Creating Error for updating company
				com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
				error.setErrorCode(Errors.COMPANY_ERROR.getErrorCode());
				error.setErrorName(((CompanyException) returnedObject).getMessage());

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
			} else {
				// Creating Error for updating company
				com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
				error.setErrorCode(Errors.GENERAL_ERROR.getErrorCode());
				error.setErrorName(((Exception) returnedObject).getMessage());

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
			}
		} catch (Exception e) {
			// Creating Error for updating company
			com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
			error.setErrorCode(Errors.GENERAL_ERROR.getErrorCode());
			error.setErrorName(((Exception) e).getMessage());

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
			companies = null;
			errors = null;
		}
		companies = null;
		errors = null;
		return resultantCompany;
	}

	public static ResultantCompany deleteCompany(long companyId) {
		try {
			Object returnedObject = DatabaseService.deleteCompanyFromDatabase(companyId);
			if (returnedObject instanceof Company) {
				companies = new ArrayList<Company>();
				companies.add((Company) returnedObject);

				resultantCompany = new ResultantCompany();
				resultantCompany.setCompanies(companies);
				resultantCompany.setErrors(errors);
				resultantCompany.setStatus(Status.SUCCESS);
				resultantCompany.setLinks(GenerateLinkService.mapOfLinks.get("deleteCompany"));
			} else if (returnedObject instanceof CompanyException) {
				// Creating Error for updating company
				com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
				error.setErrorCode(Errors.COMPANY_ERROR.getErrorCode());
				error.setErrorName(((CompanyException) returnedObject).getMessage());

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
			} else {
				// Creating Error for updating company
				com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
				error.setErrorCode(Errors.GENERAL_ERROR.getErrorCode());
				error.setErrorName(((Exception) returnedObject).getMessage());

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
			}
		} catch (Exception e) {
			// Creating Error for updating company
			com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
			error.setErrorCode(Errors.GENERAL_ERROR.getErrorCode());
			error.setErrorName(((Exception) e).getMessage());

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
			companies = null;
			errors = null;
		}
		companies = null;
		errors = null;
		return resultantCompany;
	}

}
