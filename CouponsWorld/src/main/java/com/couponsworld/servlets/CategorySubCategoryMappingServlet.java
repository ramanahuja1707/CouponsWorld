package com.couponsworld.servlets;

import java.io.IOException;
import java.net.HttpURLConnection;
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
import com.couponsworld.dto.CategorySubCategoryMapping;
import com.couponsworld.dto.Offer;
import com.couponsworld.enums.Errors;
import com.couponsworld.enums.Status;
import com.couponsworld.exceptions.MissingMandatoryParametersException;
import com.couponsworld.utilities.ApiAuthenticationService;
import com.couponsworld.utilities.Constants;
import com.couponsworld.utilities.HttpUrlService;
import com.couponsworld.utilities.ValidationService;
import com.google.gson.Gson;

public class CategorySubCategoryMappingServlet extends HttpServlet {
	// declaration of logger
	private static final Logger log = Logger.getLogger(CategorySubCategoryMappingServlet.class.getName());

	private final String CONTENT_TYPE_JSON = "application/json";

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			HttpSession session = req.getSession(false);
			String username = (String) session.getAttribute("username");
			String password = (String) session.getAttribute("password");
			if (Constants.AUTH_PASSWORD.equals(password) && Constants.AUTH_USERNAME.equals(username)) {

				log.info(
						"############################################# POST-CategorySubCategoryMappingServlet-START #######################################################");
				/*
				 * System.out.println(req.getParameter("cashBack"));
				 * System.out.println(req.getParameter("cashBackMode"));
				 * System.out.println(req.getParameter("catchyHeading"));
				 * System.out.println(req.getParameter("category"));
				 * System.out.println(req.getParameter("company"));
				 * System.out.println(req.getParameter("description"));
				 * System.out.println(req.getParameter("expiryDate"));
				 * System.out.println(req.getParameter("extraOfferReference"));
				 * System.out.println(req.getParameter("maximumCashBack"));
				 * System.out.println(req.getParameter("promoCode"));
				 * System.out.println(req.getParameter("restrictions"));
				 * System.out.println(req.getParameter("startDate"));
				 * System.out.println(req.getParameter("subCategory"));
				 * System.out.println(req.getParameter("termsAndConditions"));
				 * System.out.println(req.getParameter("totalExtraOffers"));
				 * System.out.println(req.getParameter("usabilityStatus"));
				 * System.out.println(req.getParameter("userPlatform"));
				 * System.out.println(req.getParameter("userType"));
				 */
				System.out.println(req.getParameter("category"));
				System.out.println(req.getParameter("subCategories"));
				log.info("Wrapping the categorySubCategoryMapping parameter to categorySubCategoryMapping object...");
				// Wrapping the Offer parameter to Offer object...
				CategorySubCategoryMapping categorySubCategoryMapping = new CategorySubCategoryMapping();
				categorySubCategoryMapping.setCategoryName(req.getParameter("category").toString());
				categorySubCategoryMapping.setSubCategoryNames(req.getParameter("subCategories"));

				// VAlidating the CategorySubCategoryMapping parameter
				List<Error> errors = ValidationService
						.validateCategorySubCategoryMappingForPostMethod(categorySubCategoryMapping);

				log.info("Validating the categorySubCategoryMapping wrapped object...");

				if (errors == null) {
					log.info("Validation of the Wrapped Object is successfull...");

					log.info("Converting the Wrapped object into Json String...");
					// converting the Object Of UserType to Json Format
					Gson gson = new Gson();
					String categorySubCategoryMappingJsonString = gson.toJson(categorySubCategoryMapping);

					log.info("Establishing URL Connection with the passed request parameters....");

					HttpURLConnection httpUrlConnection = HttpUrlService.getHttpURLConnection(
							Constants.CATEGORY_SUBCATEGORY_MAPPING_URL, req.getMethod(), CONTENT_TYPE_JSON,
							"Basic " + new ApiAuthenticationService().generateAuthorizationKey(username, password),
							Constants.DO_OUTPUT_FLAG_TRUE, categorySubCategoryMappingJsonString, username, password,
							session.getAttribute("accessId").toString(),
							session.getAttribute("accessPlatform").toString());
					String urlResponse = HttpUrlService.readHttpUrlResponse(httpUrlConnection.getInputStream());

					log.info("Successfully got the response read from URL connection.....");
					log.info("Response : " + urlResponse);

					// setting the Status of Get method execution - Success
					req.setAttribute("response", urlResponse.toString());
					req.setAttribute("status", Status.SUCCESS);

					log.info("Execution Status Parameters attaching with the request :");
					log.info("response :" + urlResponse.toString());
					log.info("status : " + Status.SUCCESS);

					// Redirecting The control to the JSP Page successfully
					RequestDispatcher requestDispatcher = req
							.getRequestDispatcher("/addCategorySubCategoryMappingSuccessFailure.jsp");
					requestDispatcher.forward(req, resp);

					log.info(
							"Redirecting The control to the addCategorySubCategoryMappingSuccessFailure.jsp Page successfully");

					log.info(
							"#############################################POST-CategorySubCategoryMappingServlet-END#############################################");
				} else {
					log.info("Validation of the Wrapped Object is failed...");

					// setting the Status of POST method execution - FAILED
					req.setAttribute("errors", errors);
					req.setAttribute("status", Status.FAILURE);

					log.info("Execution Status Parameters attaching with the request :");
					log.info("errors :" + errors.toString());
					log.info("status : " + Status.SUCCESS);

					// Redirecting The control to the JSP Page successfully
					RequestDispatcher requestDispatcher = req
							.getRequestDispatcher("/addCategorySubCategoryMappingSuccessFailure.jsp");
					requestDispatcher.forward(req, resp);

					log.info(
							"Redirecting The control to the addCategorySubCategoryMappingSuccessFailure.jsp Page successfully");
					log.info(
							"############################################# POST-CategorySubCategoryMappingServlet-END #############################################");
				}
			} else {
				log.info("Username and Password not in session.....Session Expired....");

				// creation of Error
				Error error = new Error();
				error.setErrorCode(Errors.SESSION_EXPIRED_ERROR.getErrorCode());
				error.setErrorName(Errors.SESSION_EXPIRED_ERROR.getErrorName());

				// creation of Error List and embedding error into it
				List<Error> errors = new ArrayList<>();
				errors.add(error);

				log.info("Attatching the errors list with the request object...");

				// setting the Status of POST method execution - Failure
				req.setAttribute("status", Status.FAILURE);
				req.setAttribute("errors", errors);

				log.info("Execution Status Parameters attaching with the request :");
				log.info("errors :" + errors.toString());
				log.info("status : " + Status.FAILURE);

				RequestDispatcher requestDispatcher = req.getRequestDispatcher("/notInSession.jsp");
				requestDispatcher.forward(req, resp);

				log.info("Redirecting The control to the notInSession.jsp Page unsuccessfully");

				log.info(
						"#############################################POST-CategorySubCategoryMappingServlet-END#############################################");
			}
		} catch (IOException ioe) {

			log.info("IO Exception Caught in executing POST method :" + ioe.getMessage());
			// creation of Error
			Error error = new Error();
			error.setErrorCode(Errors.GENERAL_ERROR.getErrorCode());
			error.setErrorName(ioe.getMessage());

			// creation of Error List and embedding error into it
			List<Error> errors = new ArrayList<>();
			errors.add(error);

			log.info("Attatching the errors list with the request object...");

			// setting the Status of POST method execution - Failure
			req.setAttribute("status", Status.FAILURE);
			req.setAttribute("errors", errors);

			log.info("Execution Status Parameters attaching with the request :");
			log.info("errors :" + errors.toString());
			log.info("status : " + Status.FAILURE);

			RequestDispatcher requestDispatcher = req
					.getRequestDispatcher("/addCategorySubCategoryMappingSuccessFailure.jsp");
			requestDispatcher.forward(req, resp);

			log.info(
					"Redirecting The control to the addCategorySubCategoryMappingSuccessFailure.jsp Page unsuccessfully");

			log.info(
					"#############################################POST-CategorySubCategoryMappingServlet-END#############################################");
		} catch (MissingMandatoryParametersException mmpe) {

			log.info("Missing Manadatory Parameters Exception Caught in executing POST method :" + mmpe.getMessage());

			// creation of Error
			Error error = new Error();
			error.setErrorCode(Errors.MISSING_MANDATORY_PARAMETERS.getErrorCode());
			error.setErrorName(mmpe.getMessage());

			// creation of Error List and embedding error into it
			List<Error> errors = new ArrayList<>();
			errors.add(error);

			log.info("Attatching the errors list with the request object...");

			// setting the Status of POST method execution - Failure
			req.setAttribute("status", Status.FAILURE);
			req.setAttribute("errors", errors);

			log.info("Execution Status Parameters attaching with the request :");
			log.info("errors :" + errors.toString());
			log.info("status : " + Status.FAILURE);

			RequestDispatcher requestDispatcher = req
					.getRequestDispatcher("/addCategorySubCategoryMappingSuccessFailure.jsp");
			requestDispatcher.forward(req, resp);

			log.info(
					"Redirecting The control to the addCategorySubCategoryMappingSuccessFailure.jsp Page unsuccessfully");

			log.info(
					"#############################################POST-CategorySubCategoryMappingServlet-END#############################################");
		} catch (Exception e) {
			log.info("Exception Caught in executing POST method :" + e.getMessage());

			// creation of Error
			Error error = new Error();
			error.setErrorCode(Errors.GENERAL_ERROR.getErrorCode());
			error.setErrorName(e.getMessage());

			// creation of Error List and embedding error into it
			List<Error> errors = new ArrayList<>();
			errors.add(error);

			log.info("Attatching the errors list with the request object...");

			// setting the Status of POST method execution - Failure
			req.setAttribute("status", Status.FAILURE);
			req.setAttribute("errors", errors);

			log.info("Execution Status Parameters attaching with the request :");
			log.info("errors :" + errors.toString());
			log.info("status : " + Status.FAILURE);

			RequestDispatcher requestDispatcher = req
					.getRequestDispatcher("/addCategorySubCategoryMappingSuccessFailure.jsp");
			requestDispatcher.forward(req, resp);

			log.info(
					"Redirecting The control to the addCategorySubCategoryMappingSuccessFailure.jsp Page unsuccessfully");

			log.info(
					"#############################################POST-CategorySubCategoryMappingServlet-END#############################################");
		}
	}

}
