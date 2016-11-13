package com.couponsworld.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.couponsworld.apiresults.Error;
import com.couponsworld.enums.Errors;
import com.couponsworld.enums.Status;
import com.couponsworld.utilities.Constants;
import com.couponsworld.utilities.GenerateOfferPageDataService;
import com.couponsworld.utilities.HttpUrlService;

@SuppressWarnings("serial")
public class GenerateOfferPageServletWeb extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// declaration of logger
	private static final Logger log = Logger.getLogger(GenerateOfferPageServletWeb.class.getName());

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			log.info("-------------------------START-----------------------------");
			HttpSession session = req.getSession(false);
			String username = (String) Constants.AUTH_PASSWORD;
			String password = (String) Constants.AUTH_USERNAME;
			if (Constants.AUTH_PASSWORD.equals(password) && Constants.AUTH_USERNAME.equals(username)) {

				log.info("-------------------------START-----------------------------");
				log.info("Fetching all Data required to load searchYourBestOfferWeb.jsp Page ...");

				String allCategorySubCategoryMappings = HttpUrlService.readHttpUrlResponseWeb("");
				;

				String allCompanies = HttpUrlService.readHttpUrlResponseWeb("");
				;
				String allCategories = HttpUrlService.readHttpUrlResponseWeb("");
				;
				String allSubCategories = HttpUrlService.readHttpUrlResponseWeb("");
				;
				String allUserTypes = HttpUrlService.readHttpUrlResponseWeb("");
				String allUserPlatforms = HttpUrlService.readHttpUrlResponseWeb("");
				;
				String allUsabilityStatuses = HttpUrlService.readHttpUrlResponseWeb("");
				;

				if (allCategories.equals("")) {
					log.info("Required Data Categories Fetching Error Occured...FAILURE");

					// setting the Status of Get method execution - Failure
					req.setAttribute("status", Status.FAILURE);
					req.setAttribute("errors",
							getErrorsList("Error in Fetching all categories...", Errors.GENERAL_ERROR.getErrorCode()));

					RequestDispatcher requestDispatcher = req.getRequestDispatcher("/errorPageWeb.jsp");
					requestDispatcher.forward(req, resp);

				} else if (allCompanies.equals("")) {
					log.info("Required Data Companies Fetching Error Occured...FAILURE");
					// setting the Status of Get method execution - Failure
					req.setAttribute("status", Status.FAILURE);
					req.setAttribute("errors",
							getErrorsList("Error in Fetching all companies...", Errors.GENERAL_ERROR.getErrorCode()));

					RequestDispatcher requestDispatcher = req.getRequestDispatcher("/errorPageWeb.jsp");
					requestDispatcher.forward(req, resp);

				} else if (allSubCategories.equals("")) {
					log.info("Required Data SubCategories Fetching Error Occured...FAILURE");
					// setting the Status of Get method execution - Failure
					req.setAttribute("status", Status.FAILURE);
					req.setAttribute("errors", getErrorsList("Error in Fetching all Sub Categories...",
							Errors.GENERAL_ERROR.getErrorCode()));

					RequestDispatcher requestDispatcher = req.getRequestDispatcher("/errorPageWeb.jsp");
					requestDispatcher.forward(req, resp);

				} else if (allUsabilityStatuses.equals("")) {
					log.info("Required Data UsabilityStatuses Fetching Error Occured...FAILURE");
					// setting the Status of Get method execution - Failure
					req.setAttribute("status", Status.FAILURE);
					req.setAttribute("errors", getErrorsList("Error in Fetching all Usability Status...",
							Errors.GENERAL_ERROR.getErrorCode()));

					RequestDispatcher requestDispatcher = req.getRequestDispatcher("/errorPageWeb.jsp");
					requestDispatcher.forward(req, resp);

				} else if (allUserPlatforms.equals("")) {
					log.info("Required Data UserPlatforms Fetching Error Occured...FAILURE");
					// setting the Status of Get method execution - Failure
					req.setAttribute("status", Status.FAILURE);
					req.setAttribute("errors", getErrorsList("Error in Fetching all User Platforms...",
							Errors.GENERAL_ERROR.getErrorCode()));

					RequestDispatcher requestDispatcher = req.getRequestDispatcher("/errorPageWeb.jsp");
					requestDispatcher.forward(req, resp);

				} else if (allUserTypes.equals("")) {
					log.info("Required Data UserTypes Fetching Error Occured...FAILURE");
					// setting the Status of Get method execution - Failure
					req.setAttribute("status", Status.FAILURE);
					req.setAttribute("errors",
							getErrorsList("Error in Fetching all User Types...", Errors.GENERAL_ERROR.getErrorCode()));

					RequestDispatcher requestDispatcher = req.getRequestDispatcher("/errorPageWeb.jsp");
					requestDispatcher.forward(req, resp);

				} else if (allCategorySubCategoryMappings.equals("")) {
					log.info("Required Data category sub category mappings Fetching Error Occured...FAILURE");
					// setting the Status of Get method execution - Failure
					req.setAttribute("status", Status.FAILURE);
					req.setAttribute("errors", getErrorsList("Error in Fetching all category sub category mappings...",
							Errors.GENERAL_ERROR.getErrorCode()));

					RequestDispatcher requestDispatcher = req.getRequestDispatcher("/errorPageWeb.jsp");
					requestDispatcher.forward(req, resp);

				} else {
					log.info("Required Data Fetched Successfully...");

					log.info("Adding all the data to the response object...");
					req.setAttribute("status", Status.SUCCESS);
					req.setAttribute("allCompanies", allCompanies);
					req.setAttribute("allCategories", allCategories);
					req.setAttribute("allSubCategories", allSubCategories);
					req.setAttribute("allUsabilityStatuses", allUsabilityStatuses);
					req.setAttribute("allUserPlatforms", allUserPlatforms);
					req.setAttribute("allUserTypes", allUserTypes);
					req.setAttribute("allCategorySubCategoryMappings", allCategorySubCategoryMappings);

					log.info("Redirecting the control to load the searchYourBestOfferWeb.jsp Page...");
					RequestDispatcher requestDispatcher = req.getRequestDispatcher("/searchYourBestOfferWeb.jsp");
					requestDispatcher.forward(req, resp);

				}

			}
		} catch (Exception e) {
			log.info("Error Occured :" + e.getMessage());
			// Setting the Status of Get method execution - Failure
			req.setAttribute("status", Status.FAILURE);
			req.setAttribute("errors", getErrorsList(e.getMessage(), Errors.GENERAL_ERROR.getErrorCode()));

			RequestDispatcher requestDispatcher = req.getRequestDispatcher("/errorPageWeb.jsp");
			requestDispatcher.forward(req, resp);

		}
	}

	private List<Error> getErrorsList(String errorName, long errorCode) {

		// creating error object
		Error error = new Error();
		error.setErrorCode(errorCode);
		error.setErrorName(errorName);

		// wrapping the error into errors list
		List<Error> errors = new ArrayList<>();
		errors.add(error);

		// returning errors list
		return errors;

	}

}
