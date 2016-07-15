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

public class GenerateCategorySubCategoryMappingPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// declaration of logger
	private static final Logger log = Logger.getLogger(GenerateOfferPageServlet.class.getName());

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

				String allCategories = GenerateOfferPageDataService.getAllCategories(username, password, accessId,
						accessPlatform);
				String allSubCategories = GenerateOfferPageDataService.getAllSubCategories(username, password, accessId,
						accessPlatform);

				if (allCategories.equals("")) {
					log.info("Required Data Categories Fetching Error Occured...FAILURE");

					// setting the Status of Get method execution - Failure
					req.setAttribute("status", Status.FAILURE);
					req.setAttribute("errors",
							getErrorsList("Error in Fetching all categories...", Errors.GENERAL_ERROR.getErrorCode()));

					RequestDispatcher requestDispatcher = req
							.getRequestDispatcher("/errorInLoadingCategorySubCategoryMappingPage.jsp");
					requestDispatcher.forward(req, resp);

				} else if (allSubCategories.equals("")) {
					log.info("Required Data SubCategories Fetching Error Occured...FAILURE");
					// setting the Status of Get method execution - Failure
					req.setAttribute("status", Status.FAILURE);
					req.setAttribute("errors", getErrorsList("Error in Fetching all Sub Categories...",
							Errors.GENERAL_ERROR.getErrorCode()));

					RequestDispatcher requestDispatcher = req
							.getRequestDispatcher("/errorInLoadingCategorySubCategoryMappingPage.jsp");
					requestDispatcher.forward(req, resp);

				} else {
					log.info("Required Data Fetched Successfully...");

					log.info("Adding all the data to the response object...");
					req.setAttribute("status", Status.SUCCESS);
					req.setAttribute("allCategories", allCategories);
					req.setAttribute("allSubCategories", allSubCategories);

					if (req.getParameter("_page").equals("addCategorySubCategoryMapping.jsp")) {

						log.info("Redirecting the control to load the addOffer.jsp Page...");
						RequestDispatcher requestDispatcher = req
								.getRequestDispatcher("/addCategorySubCategoryMapping.jsp");
						requestDispatcher.forward(req, resp);
					} else if (req.getParameter("_page").equals("searchCategorySubCategoryMapping.jsp")) {
						log.info("Redirecting the control to load the searchCategorySubCategoryMapping.jsp Page...");
						RequestDispatcher requestDispatcher = req
								.getRequestDispatcher("/searchCategorySubCategoryMapping.jsp");
						requestDispatcher.forward(req, resp);
					}

				}

			}
		} catch (Exception e) {
			log.info("Error Occured :" + e.getMessage());
			// setting the Status of Get method execution - Failure
			req.setAttribute("status", Status.FAILURE);
			req.setAttribute("errors", getErrorsList(e.getMessage(), Errors.GENERAL_ERROR.getErrorCode()));

			RequestDispatcher requestDispatcher = req
					.getRequestDispatcher("/errorInLoadingCategorySubCategoryMappingPage.jsp");
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
