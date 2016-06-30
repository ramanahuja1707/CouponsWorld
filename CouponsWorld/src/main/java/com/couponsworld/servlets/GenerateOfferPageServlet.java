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

/**
 * Servlet implementation class GenerateDataForOfferPage
 */
public class GenerateOfferPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// declaration of logger
	private static final Logger log = Logger.getLogger(GenerateOfferPageServlet.class.getName());

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GenerateOfferPageServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			log.info("-------------------------START-----------------------------");
			HttpSession session = req.getSession(false);
			String username = (String) session.getAttribute("username");
			String password = (String) session.getAttribute("password");
			if (Constants.AUTH_PASSWORD.equals(password) && Constants.AUTH_USERNAME.equals(username)) {

				log.info("-------------------------START-----------------------------");
				log.info("Fetching all Data required to load addOffer.jsp Page ...");
				String accessId = session.getAttribute("accessId").toString();
				String accessPlatform = session.getAttribute("accessPlatform").toString();
				String allCompanies = GenerateOfferPageDataService.getAllCompanies(username, password, accessId,
						accessPlatform);
				String allCategories = GenerateOfferPageDataService.getAllCategories(username, password, accessId,
						accessPlatform);
				String allSubCategories = GenerateOfferPageDataService.getAllSubCategories(username, password, accessId,
						accessPlatform);
				String allUserTypes = GenerateOfferPageDataService.getAllUserTypes(username, password, accessId,
						accessPlatform);
				String allUserPlatforms = GenerateOfferPageDataService.getAllUserPlatforms(username, password, accessId,
						accessPlatform);
				String allUsabilityStatuses = GenerateOfferPageDataService.getAllUsabilityStatuses(username, password,
						accessId, accessPlatform);

				if (allCategories.equals("")) {
					log.info("Required Data Categories Fetching Error Occured...FAILURE");

					// setting the Status of Get method execution - Failure
					req.setAttribute("status", Status.FAILURE);
					req.setAttribute("errors",
							getErrorsList("Error in Fetching all categories...", Errors.GENERAL_ERROR.getErrorCode()));

					RequestDispatcher requestDispatcher = req.getRequestDispatcher("/errorInLoadingOfferPage.jsp");
					requestDispatcher.forward(req, resp);

				} else if (allCompanies.equals("")) {
					log.info("Required Data Companies Fetching Error Occured...FAILURE");
					// setting the Status of Get method execution - Failure
					req.setAttribute("status", Status.FAILURE);
					req.setAttribute("errors",
							getErrorsList("Error in Fetching all companies...", Errors.GENERAL_ERROR.getErrorCode()));

					RequestDispatcher requestDispatcher = req.getRequestDispatcher("/errorInLoadingOfferPage.jsp");
					requestDispatcher.forward(req, resp);

				} else if (allSubCategories.equals("")) {
					log.info("Required Data SubCategories Fetching Error Occured...FAILURE");
					// setting the Status of Get method execution - Failure
					req.setAttribute("status", Status.FAILURE);
					req.setAttribute("errors", getErrorsList("Error in Fetching all Sub Categories...",
							Errors.GENERAL_ERROR.getErrorCode()));

					RequestDispatcher requestDispatcher = req.getRequestDispatcher("/errorInLoadingOfferPage.jsp");
					requestDispatcher.forward(req, resp);

				} else if (allUsabilityStatuses.equals("")) {
					log.info("Required Data UsabilityStatuses Fetching Error Occured...FAILURE");
					// setting the Status of Get method execution - Failure
					req.setAttribute("status", Status.FAILURE);
					req.setAttribute("errors", getErrorsList("Error in Fetching all Usability Status...",
							Errors.GENERAL_ERROR.getErrorCode()));

					RequestDispatcher requestDispatcher = req.getRequestDispatcher("/errorInLoadingOfferPage.jsp");
					requestDispatcher.forward(req, resp);

				} else if (allUserPlatforms.equals("")) {
					log.info("Required Data UserPlatforms Fetching Error Occured...FAILURE");
					// setting the Status of Get method execution - Failure
					req.setAttribute("status", Status.FAILURE);
					req.setAttribute("errors", getErrorsList("Error in Fetching all User Platforms...",
							Errors.GENERAL_ERROR.getErrorCode()));

					RequestDispatcher requestDispatcher = req.getRequestDispatcher("/errorInLoadingOfferPage.jsp");
					requestDispatcher.forward(req, resp);

				} else if (allUserTypes.equals("")) {
					log.info("Required Data UserTypes Fetching Error Occured...FAILURE");
					// setting the Status of Get method execution - Failure
					req.setAttribute("status", Status.FAILURE);
					req.setAttribute("errors",
							getErrorsList("Error in Fetching all User Types...", Errors.GENERAL_ERROR.getErrorCode()));

					RequestDispatcher requestDispatcher = req.getRequestDispatcher("/errorInLoadingOfferPage.jsp");
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

					log.info("Redirecting the control to load the addOffer.jsp Page...");
					RequestDispatcher requestDispatcher = req.getRequestDispatcher("/addOffer.jsp");
					requestDispatcher.forward(req, resp);

				}

			}
		} catch (Exception e) {
			log.info("Error Occured :" + e.getMessage());
			// setting the Status of Get method execution - Failure
			req.setAttribute("status", Status.FAILURE);
			req.setAttribute("errors", getErrorsList(e.getMessage(), Errors.GENERAL_ERROR.getErrorCode()));

			RequestDispatcher requestDispatcher = req.getRequestDispatcher("/errorInLoadingOfferPage.jsp");
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
