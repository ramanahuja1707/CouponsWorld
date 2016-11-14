package com.couponsworld.servlets.webclient;

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
import com.couponsworld.servlets.GenerateOfferPageServlet;
import com.couponsworld.utilities.Constants;
import com.couponsworld.utilities.GenerateOfferPageDataService;
import com.couponsworld.utilities.HttpUrlService;

/**
 * Servlet implementation class GenerateIndexPageWeb
 */
public class GenerateIndexPageWeb extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// declaration of logger
	private static final Logger log = Logger.getLogger(GenerateIndexPageWeb.class.getName());

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GenerateIndexPageWeb() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			log.info("-------------------------START-----------------------------");
			HttpSession session = req.getSession(false);
			String username = (String) Constants.AUTH_USERNAME;
			String password = (String) Constants.AUTH_PASSWORD;
			if (Constants.AUTH_PASSWORD.equals(password) && Constants.AUTH_USERNAME.equals(username)) {

				log.info("-------------------------START-----------------------------");
				log.info("Fetching all Data required to load index.jsp Page ...");
				// String accessId =
				// session.getAttribute("accessId").toString();
				// String accessPlatform =
				// session.getAttribute("accessPlatform").toString();
				String accessId = Constants.ADMIN_ACCESS_ID;
				String accessPlatform = Constants.WEB_ACCESS_PLATFORM;

				String allCategorySubCategoryMappings = GenerateOfferPageDataService
						.getAllCategoriesSubCategoriesMapping(username, password, accessId, accessPlatform);

				String allUserTypes = GenerateOfferPageDataService.getAllUserTypes(username, password, accessId,
						accessPlatform);
				String allUserPlatforms = GenerateOfferPageDataService.getAllUserPlatforms(username, password, accessId,
						accessPlatform);

				if (allUserPlatforms.equals("")) {
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
					// req.setAttribute("allCompanies", allCompanies);
					// log.info("all Categories : " + allCategories);
					log.info("all Category mappings : " + allCategorySubCategoryMappings);
					log.info("all USer types : " + allUserTypes);
					log.info("all UserPlatforms : " + allUserPlatforms);

					// req.setAttribute("allCategories", allCategories);
					// req.setAttribute("allSubCategories", allSubCategories);
					// req.setAttribute("allUsabilityStatuses",
					// allUsabilityStatuses);
					req.setAttribute("allUserPlatforms", allUserPlatforms);
					req.setAttribute("allUserTypes", allUserTypes);
					req.setAttribute("allCategorySubCategoryMappings", allCategorySubCategoryMappings);
					log.info("Redirecting the control to load the index.jsp Page...");
					RequestDispatcher requestDispatcher = req.getRequestDispatcher("/index.jsp");
					requestDispatcher.forward(req, resp);

				}

			}
		} catch (Exception e) {
			log.info("Error Occured :" + e.getMessage());
			// setting the Status of Get method execution - Failure
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
