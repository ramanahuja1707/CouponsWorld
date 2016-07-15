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
import com.couponsworld.dto.Offer;
import com.couponsworld.dto.UserType;
import com.couponsworld.enums.Errors;
import com.couponsworld.enums.Status;
import com.couponsworld.exceptions.MissingMandatoryParametersException;
import com.couponsworld.utilities.ApiAuthenticationService;
import com.couponsworld.utilities.Constants;
import com.couponsworld.utilities.HttpUrlService;
import com.couponsworld.utilities.ValidationService;
import com.google.gson.Gson;

public class OfferServlet extends HttpServlet {

	// declaration of logger
	private static final Logger log = Logger.getLogger(OfferServlet.class.getName());

	private final String CONTENT_TYPE_JSON = "application/json";

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			HttpSession session = req.getSession(false);
			String username = (String) session.getAttribute("username");
			String password = (String) session.getAttribute("password");
			if (Constants.AUTH_PASSWORD.equals(password) && Constants.AUTH_USERNAME.equals(username)) {

				log.info(
						"############################################# POST-OfferServlet-START #######################################################");
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

				log.info("Wrapping the Offer parameter to Offer object...");
				// Wrapping the Offer parameter to Offer object...
				Offer offer = new Offer();

				if (req.getParameter("cashBack") == null || req.getParameter("cashBack").equals("")) {
					offer.setCashBack(0L);
				} else {
					offer.setCashBack(Long.parseLong(req.getParameter("cashBack").toString()));
				}
				if (req.getParameter("minimumAmount") == null || req.getParameter("minimumAmount").equals("")) {
					offer.setMinimumAmount(0L);
				} else {

					offer.setMinimumAmount(Long.parseLong(req.getParameter("minimumAmount")));
				}
				offer.setCashBackMode(req.getParameter("cashBackMode"));
				offer.setCatchyHeading(req.getParameter("catchyHeading"));
				offer.setCategory(req.getParameter("category"));
				offer.setCompany(req.getParameter("company"));
				offer.setDescription(req.getParameter("description"));
				offer.setExpiryDate(req.getParameter("expiryDate"));
				offer.setExtraOfferReference(req.getParameter("extraOfferReference"));
				offer.setOfferType(req.getParameter("offerType"));
				if (req.getParameter("maximumCashBack") == null || req.getParameter("maximumCashBack").equals("")) {
					offer.setMaximumCashBack(0L);
				} else {
					offer.setMaximumCashBack(Long.parseLong(req.getParameter("maximumCashBack").toString()));
				}
				offer.setPromoCode(req.getParameter("promoCode"));
				offer.setRestrictions(req.getParameter("restrictions"));
				offer.setStartDate(req.getParameter("startDate"));
				offer.setSubCategory(req.getParameter("subCategory"));
				offer.setTermsAndConditions(req.getParameter("termsAndConditions"));

				if (req.getParameter("totalExtraOffers") == null || req.getParameter("totalExtraOffers").equals("")) {
					offer.setTotalExtraOffers(0L);
				} else {
					offer.setTotalExtraOffers(Long.parseLong(req.getParameter("totalExtraOffers").toString()));
				}
				offer.setUsabilityStatus(req.getParameter("usabilityStatus"));
				offer.setUserPlatform(req.getParameter("userPlatform"));
				offer.setUserType(req.getParameter("userType"));

				// VAlidating the Offer parameter
				List<Error> errors = ValidationService.validateOfferForPostMethod(offer);

				log.info("Validating the Offer wrapped object...");

				if (errors == null) {
					log.info("Validation of the Wrapped Object is successfull...");

					log.info("Converting the Wrapped object into Json String...");
					// converting the Object Of UserType to Json Format
					Gson gson = new Gson();
					String offerJsonString = gson.toJson(offer);

					log.info("Establishing URL Connection with the passed request parameters....");

					HttpURLConnection httpUrlConnection = HttpUrlService
							.getHttpURLConnection(Constants.OFFER_URL, req.getMethod(), CONTENT_TYPE_JSON,
									"Basic " + new ApiAuthenticationService().generateAuthorizationKey(username,
											password),
									Constants.DO_OUTPUT_FLAG_TRUE, offerJsonString, username, password,
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
					RequestDispatcher requestDispatcher = req.getRequestDispatcher("/addOfferSuccessFailure.jsp");
					requestDispatcher.forward(req, resp);

					log.info("Redirecting The control to the addOfferSuccessFailure.jsp Page successfully");

					log.info(
							"#############################################POST-OfferServlet-END#############################################");
				} else {
					log.info("Validation of the Wrapped Object is failed...");

					// setting the Status of POST method execution - FAILED
					req.setAttribute("errors", errors);
					req.setAttribute("status", Status.FAILURE);

					log.info("Execution Status Parameters attaching with the request :");
					log.info("errors :" + errors.toString());
					log.info("status : " + Status.SUCCESS);

					// Redirecting The control to the JSP Page successfully
					RequestDispatcher requestDispatcher = req.getRequestDispatcher("/addOfferSuccessFailure.jsp");
					requestDispatcher.forward(req, resp);

					log.info("Redirecting The control to the addOfferSuccessFailure.jsp Page successfully");
					log.info(
							"############################################# POST-OfferServlet-END #############################################");
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
						"#############################################POST-OfferServlet-END#############################################");
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

			RequestDispatcher requestDispatcher = req.getRequestDispatcher("/addOfferSuccessFailure.jsp");
			requestDispatcher.forward(req, resp);

			log.info("Redirecting The control to the addOfferSuccessFailure.jsp Page unsuccessfully");

			log.info(
					"#############################################POST-OfferServlet-END#############################################");
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

			RequestDispatcher requestDispatcher = req.getRequestDispatcher("/addOfferSuccessFailure.jsp");
			requestDispatcher.forward(req, resp);

			log.info("Redirecting The control to the addOfferSuccessFailure.jsp Page unsuccessfully");

			log.info(
					"#############################################POST-OfferServlet-END#############################################");
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

			RequestDispatcher requestDispatcher = req.getRequestDispatcher("/addOfferSuccessFailure.jsp");
			requestDispatcher.forward(req, resp);

			log.info("Redirecting The control to the addOfferSuccessFailure.jsp Page unsuccessfully");

			log.info(
					"#############################################POST-OfferServlet-END#############################################");
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			HttpSession session = req.getSession(false);
			String username = (String) session.getAttribute("username");
			String password = (String) session.getAttribute("password");
			if (Constants.AUTH_PASSWORD.equals(password) && Constants.AUTH_USERNAME.equals(username)) {
				// configuration for PUT Request
				if (req.getParameter("_method").equals("PUT")) {
					// log.info("##########################----PUT----######################");
					doPut(req, resp);
				} else if (req.getParameter("_method").equals("DELETE")) {
					doDelete(req, resp);
				} else if (req.getParameter("_method").equals("GET")) {
					String contentType = "", objectJson = "";
					String company = req.getParameter("company").toString().replace(" ", "+");
					String category = req.getParameter("category").toString().replace(" ", "+");
					String userType = req.getParameter("userType").toString().replace(" ", "+");
					String userPlatform = req.getParameter("userPlatform").toString().replace(" ", "+");
					String subCategory = req.getParameter("subCategory").toString().replace(" ", "+");
					String usabilityStatus = req.getParameter("usabilityStatus").toString().replace(" ", "+");
					String cashBackMode = req.getParameter("cashBackMode").toString().replace(" ", "+");
					String offerType = req.getParameter("offerType").toString().replace(" ", "+");
					log.info(
							"################################################ GET-UserTypeServlet-START ################################################");
					System.out.println(company);
					System.out.println(category);
					System.out.println(userType);
					System.out.println(userPlatform);
					System.out.println(usabilityStatus);
					System.out.println(subCategory);
					System.out.println(cashBackMode);

					// log.info(req.getContentType());
					log.info("Establishing URL Connection with the passed request parameters....");

					// Creating Connection from the URL passed
					HttpURLConnection httpUrlConnection = HttpUrlService.getHttpURLConnection(
							Constants.OFFER_URL + "?company=" + company + "&category=" + category + "&userPlatform="
									+ userPlatform + "&userType=" + userType + "&usabilityStatus="
									+ usabilityStatus + "&subCategory=" + subCategory + "&cashBackMode=" + cashBackMode
									+ "&offerType=" + offerType,
							req.getMethod(), contentType,
							"Basic " + new ApiAuthenticationService().generateAuthorizationKey(username, password),
							Constants.DO_OUTPUT_FLAG_TRUE, objectJson, username, password,
							session.getAttribute("accessId").toString(),
							session.getAttribute("accessPlatform").toString());

					log.info("URL Connection with the passed request parameters Successfully created....");
					log.info("Reading the response from the URL connection........");

					// Getting Response from the URL
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
					RequestDispatcher requestDispatcher = req.getRequestDispatcher("/showOffer.jsp");
					requestDispatcher.forward(req, resp);

					log.info("Redirecting The control to the showOffer.jsp Page successfully");

					log.info(
							"################################################GET-OfferServlet-END################################################");
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

				// setting the Status of Get method execution - Failure
				req.setAttribute("status", Status.FAILURE);
				req.setAttribute("errors", errors);

				log.info("Execution Status Parameters attaching with the request :");
				log.info("errors :" + errors.toString());
				log.info("status : " + Status.FAILURE);

				RequestDispatcher requestDispatcher = req.getRequestDispatcher("/notInSession.jsp");
				requestDispatcher.forward(req, resp);

				log.info("Redirecting The control to the notInSession.jsp Page unsuccessfully");

				log.info(
						"################################################GET-OfferServlet-END################################################");

			}
		} catch (IOException ioe) {

			log.info("IO Exception Caught in executing GET method :" + ioe.getMessage());
			// creation of Error
			Error error = new Error();
			error.setErrorCode(Errors.GENERAL_ERROR.getErrorCode());
			error.setErrorName(ioe.getMessage());

			// creation of Error List and embedding error into it
			List<Error> errors = new ArrayList<>();
			errors.add(error);

			log.info("Attatching the errors list with the request object...");

			// setting the Status of Get method execution - Failure
			req.setAttribute("status", Status.FAILURE);
			req.setAttribute("errors", errors);

			log.info("Execution Status Parameters attaching with the request :");
			log.info("errors :" + errors.toString());
			log.info("status : " + Status.FAILURE);

			RequestDispatcher requestDispatcher = req.getRequestDispatcher("/showOffer.jsp");
			requestDispatcher.forward(req, resp);

			log.info("Redirecting The control to the showOffer.jsp Page unsuccessfully");

			log.info(
					"################################################GET-OfferServlet-END################################################");
		} catch (MissingMandatoryParametersException mmpe) {

			log.info("Missing Manadatory Parameters Exception Caught in executing GET method :" + mmpe.getMessage());

			// creation of Error
			Error error = new Error();
			error.setErrorCode(Errors.MISSING_MANDATORY_PARAMETERS.getErrorCode());
			error.setErrorName(mmpe.getMessage());

			// creation of Error List and embedding error into it
			List<Error> errors = new ArrayList<>();
			errors.add(error);

			log.info("Attatching the errors list with the request object...");

			// setting the Status of Get method execution - Failure
			req.setAttribute("status", Status.FAILURE);
			req.setAttribute("errors", errors);

			log.info("Execution Status Parameters attaching with the request :");
			log.info("errors :" + errors.toString());
			log.info("status : " + Status.FAILURE);

			RequestDispatcher requestDispatcher = req.getRequestDispatcher("/showOffer.jsp");
			requestDispatcher.forward(req, resp);

			log.info("Redirecting The control to the showOffer.jsp Page unsuccessfully");

			log.info(
					"################################################GET-OfferServlet-END################################################");
		} catch (Exception e) {
			log.info("Exception Caught in executing GET method :" + e.getMessage());

			// creation of Error
			Error error = new Error();
			error.setErrorCode(Errors.GENERAL_ERROR.getErrorCode());
			error.setErrorName(e.getMessage());

			// creation of Error List and embedding error into it
			List<Error> errors = new ArrayList<>();
			errors.add(error);

			log.info("Attatching the errors list with the request object...");

			// setting the Status of Get method execution - Failure
			req.setAttribute("status", Status.FAILURE);
			req.setAttribute("errors", errors);

			log.info("Execution Status Parameters attaching with the request :");
			log.info("errors :" + errors.toString());
			log.info("status : " + Status.FAILURE);

			RequestDispatcher requestDispatcher = req.getRequestDispatcher("/showOffer.jsp");
			requestDispatcher.forward(req, resp);

			log.info("Redirecting The control to the showOffer.jsp Page unsuccessfully");

			log.info(
					"################################################GET-OfferServlet-END################################################");
		}

	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			HttpSession session = req.getSession(false);
			String username = (String) session.getAttribute("username");
			String password = (String) session.getAttribute("password");
			if (Constants.AUTH_PASSWORD.equals(password) && Constants.AUTH_USERNAME.equals(username)) {

				String[] offerSelected = req.getParameter("offerSelected").split("-");
				String offerSelectedId = offerSelected[0];
				String contentType = "";
				log.info(
						"#############################################DELETE-OfferServlet-START################################################");

				if (ValidationService.validateOfferForDeleteMethod(offerSelectedId) == null) {

					log.info("Wrapping the Offer parameter to Offer object...");

					log.info("Validation of the Wrapped Object is successfull...");

					log.info("Establishing URL Connection with the passed request parameters....");

					String offerJsonString = "";

					HttpURLConnection httpUrlConnection = HttpUrlService
							.getHttpURLConnection(Constants.OFFER_URL + "/" + offerSelectedId, "DELETE", contentType,
									"Basic " + new ApiAuthenticationService().generateAuthorizationKey(username,
											password),
									Constants.DO_OUTPUT_FLAG_TRUE, offerJsonString, username, password,
									session.getAttribute("accessId").toString(),
									session.getAttribute("accessPlatform").toString());
					String urlResponse = HttpUrlService.readHttpUrlResponse(httpUrlConnection.getInputStream());

					log.info("Successfully got the response read from URL connection.....");
					log.info("Response : " + urlResponse);

					// setting the Status of DELETE method execution - Success
					req.setAttribute("response", urlResponse.toString());
					req.setAttribute("status", Status.SUCCESS);

					log.info("Execution Status Parameters attaching with the request :");
					log.info("response :" + urlResponse.toString());
					log.info("status : " + Status.SUCCESS);

					// Redirecting The control to the JSP Page successfully
					RequestDispatcher requestDispatcher = req.getRequestDispatcher("/deleteOfferSuccessFailure.jsp");
					requestDispatcher.forward(req, resp);

					log.info("Redirecting The control to the deleteOfferSuccessFailure.jsp Page successfully");

					log.info(
							"#############################################DELETE-OfferServlet-END#############################################");
				} else {

					// creation of Error
					Error error = new Error();
					error.setErrorCode(Errors.GENERAL_ERROR.getErrorCode());
					error.setErrorName("Missing the Updated Value of Offer Or Offer Id..");

					// creation of Error List and embedding error into it
					List<Error> errors = new ArrayList<>();
					errors.add(error);

					log.info("Attatching the errors list with the request object...");

					// setting the Status of DELETE method execution - Failure
					req.setAttribute("status", Status.FAILURE);
					req.setAttribute("errors", errors);

					log.info("Execution Status Parameters attaching with the request :");
					log.info("errors :" + errors.toString());
					log.info("status : " + Status.FAILURE);

					RequestDispatcher requestDispatcher = req.getRequestDispatcher("/deleteOfferSuccessFailure.jsp");
					requestDispatcher.forward(req, resp);

					log.info("Redirecting The control to the deleteOfferSuccessFailure.jsp Page unsuccessfully");

					log.info(
							"#############################################DELETE-OfferServlet-END#############################################");
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

				// setting the Status of DELETE method execution - Failure
				req.setAttribute("status", Status.FAILURE);
				req.setAttribute("errors", errors);

				log.info("Execution Status Parameters attaching with the request :");
				log.info("errors :" + errors.toString());
				log.info("status : " + Status.FAILURE);

				RequestDispatcher requestDispatcher = req.getRequestDispatcher("/notInSession.jsp");
				requestDispatcher.forward(req, resp);

				log.info("Redirecting The control to the notInSession.jsp Page unsuccessfully");

				log.info(
						"#############################################DELETE-OfferServlet-END#############################################");
			}
		} catch (IOException ioe) {

			log.info("IO Exception Caught in executing DELETE method :" + ioe.getMessage());
			// creation of Error
			Error error = new Error();
			error.setErrorCode(Errors.GENERAL_ERROR.getErrorCode());
			error.setErrorName(ioe.getMessage());

			// creation of Error List and embedding error into it
			List<Error> errors = new ArrayList<>();
			errors.add(error);

			log.info("Attatching the errors list with the request object...");

			// setting the Status of DELETE method execution - Failure
			req.setAttribute("status", Status.FAILURE);
			req.setAttribute("errors", errors);

			log.info("Execution Status Parameters attaching with the request :");
			log.info("errors :" + errors.toString());
			log.info("status : " + Status.FAILURE);

			RequestDispatcher requestDispatcher = req.getRequestDispatcher("/deleteOfferSuccessFailure.jsp");
			requestDispatcher.forward(req, resp);

			log.info("Redirecting The control to the deleteOfferSuccessFailure.jsp Page unsuccessfully");

			log.info(
					"#############################################DELETE-OfferServlet-END#############################################");

		} catch (MissingMandatoryParametersException mmpe) {

			log.info("Missing Manadatory Parameters Exception Caught in executing DELETE method :" + mmpe.getMessage());

			// creation of Error
			Error error = new Error();
			error.setErrorCode(Errors.MISSING_MANDATORY_PARAMETERS.getErrorCode());
			error.setErrorName(mmpe.getMessage());

			// creation of Error List and embedding error into it
			List<Error> errors = new ArrayList<>();
			errors.add(error);

			log.info("Attatching the errors list with the request object...");

			// setting the Status of DELETE method execution - Failure
			req.setAttribute("status", Status.FAILURE);
			req.setAttribute("errors", errors);

			log.info("Execution Status Parameters attaching with the request :");
			log.info("errors :" + errors.toString());
			log.info("status : " + Status.FAILURE);

			RequestDispatcher requestDispatcher = req.getRequestDispatcher("/deleteOfferSuccessFailure.jsp");
			requestDispatcher.forward(req, resp);

			log.info("Redirecting The control to the deleteOfferSuccessFailure.jsp Page unsuccessfully");

			log.info(
					"#############################################DELETE-OfferServlet-END#############################################");
		} catch (Exception e) {
			log.info("Exception Caught in executing DELETE method :" + e.getMessage());

			// creation of Error
			Error error = new Error();
			error.setErrorCode(Errors.GENERAL_ERROR.getErrorCode());
			error.setErrorName(e.getMessage());

			// creation of Error List and embedding error into it
			List<Error> errors = new ArrayList<>();
			errors.add(error);

			log.info("Attatching the errors list with the request object...");

			// setting the Status of DELETE method execution - Failure
			req.setAttribute("status", Status.FAILURE);
			req.setAttribute("errors", errors);

			log.info("Execution Status Parameters attaching with the request :");
			log.info("errors :" + errors.toString());
			log.info("status : " + Status.FAILURE);

			RequestDispatcher requestDispatcher = req.getRequestDispatcher("/deleteOfferSuccessFailure.jsp");
			requestDispatcher.forward(req, resp);

			log.info("Redirecting The control to the deleteOfferSuccessFailure.jsp Page unsuccessfully");

			log.info(
					"#############################################DELETE-OfferServlet-END#############################################");
		}

	}
}
