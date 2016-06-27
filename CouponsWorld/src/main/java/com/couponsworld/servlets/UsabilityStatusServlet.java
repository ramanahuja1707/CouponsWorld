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
import com.couponsworld.dto.UsabilityStatus;
import com.couponsworld.enums.Errors;
import com.couponsworld.enums.Status;
import com.couponsworld.exceptions.MissingMandatoryParametersException;
import com.couponsworld.utilities.ApiAuthenticationService;
import com.couponsworld.utilities.Constants;
import com.couponsworld.utilities.HttpUrlService;
import com.couponsworld.utilities.ValidationService;
import com.google.gson.Gson;

public class UsabilityStatusServlet extends HttpServlet {

	// declaration of logger
	private static final Logger log = Logger.getLogger(UsabilityStatusServlet.class.getName());

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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

					log.info(
							"################################################GET-UsabilityStatusServlet-START################################################");
					// log.info(req.getContentType());
					log.info("Establishing URL Connection with the passed request parameters....");

					// Creating Connection from the URL passed
					HttpURLConnection httpUrlConnection = HttpUrlService
							.getHttpURLConnection(Constants.USABILITYSTATUS_URL, req.getMethod(),
									contentType, "Basic " + new ApiAuthenticationService()
											.generateAuthorizationKey(username, password),
									Constants.DO_OUTPUT_FLAG_TRUE, objectJson);

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
					RequestDispatcher requestDispatcher = req.getRequestDispatcher("/showUsabilityStatus.jsp");
					requestDispatcher.forward(req, resp);

					log.info("Redirecting The control to the showUsabilityStatus.jsp Page successfully");

					log.info(
							"################################################GET-UsabilityStatusServlet-END################################################");
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
						"################################################GET-UsabilityStatusServlet-END################################################");

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

			RequestDispatcher requestDispatcher = req.getRequestDispatcher("/showUsabilityStatus.jsp");
			requestDispatcher.forward(req, resp);

			log.info("Redirecting The control to the showUsabilityStatus.jsp Page unsuccessfully");

			log.info(
					"################################################GET-UsabilityStatusServlet-END################################################");
		} catch (MissingMandatoryParametersException mmpe) {

			log.info(
					"Missing Manadatory Parameters Exception Caught in executing GET method :" + mmpe.getMessage());

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

			RequestDispatcher requestDispatcher = req.getRequestDispatcher("/showUsabilityStatus.jsp");
			requestDispatcher.forward(req, resp);

			log.info("Redirecting The control to the showUsabilityStatus.jsp Page unsuccessfully");

			log.info(
					"################################################GET-UsabilityStatusServlet-END################################################");
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

			RequestDispatcher requestDispatcher = req.getRequestDispatcher("/showUsabilityStatus.jsp");
			requestDispatcher.forward(req, resp);

			log.info("Redirecting The control to the showUsabilityStatus.jsp Page unsuccessfully");

			log.info(
					"################################################GET-UsabilityStatusServlet-END################################################");
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {

			HttpSession session = req.getSession(false);
			String username = (String) session.getAttribute("username");
			String password = (String) session.getAttribute("password");
			if (Constants.AUTH_PASSWORD.equals(password) && Constants.AUTH_USERNAME.equals(username)) {
				String CONTENT_TYPE_JSON = "application/json";
				log.info(
						"#############################################POST-UsabilityStatusServlet-START#######################################################");

				// Wrapping the Usertype parameter to USerType object...
				UsabilityStatus usabilityStatus = new UsabilityStatus();
				usabilityStatus.setUsabilityStatusName(req.getParameter("usabilityStatus"));

				log.info("Wrapping the UsabilityStatus parameter to UsabilityStatus object...");

				// VAlidating the USerTYpe parameter
				List<Error> errors = ValidationService.validateUsabilityStatusForPostMethod(usabilityStatus);

				log.info("Validating the UsabilityStatus wrapped object...");

				if (errors == null) {
					log.info("Validation of the Wrapped Object is successfull...");

					log.info("Converting the Wrapped object into Json String...");
					// converting the Object Of UserType to Json Format
					Gson gson = new Gson();
					String usabilityStatusJsonString = gson.toJson(usabilityStatus);

					log.info("Establishing URL Connection with the passed request parameters....");

					HttpURLConnection httpUrlConnection = HttpUrlService
							.getHttpURLConnection(Constants.USABILITYSTATUS_URL, req.getMethod(),
									CONTENT_TYPE_JSON, "Basic " + new ApiAuthenticationService()
											.generateAuthorizationKey(username, password),
									Constants.DO_OUTPUT_FLAG_TRUE, usabilityStatusJsonString);
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
					RequestDispatcher requestDispatcher = req.getRequestDispatcher("/addUsabilityStatus.jsp");
					requestDispatcher.forward(req, resp);

					log.info("Redirecting The control to the addUsabilityStatus.jsp Page successfully");

					log.info(
							"#############################################POST-UsabilityStatusServlet-END#############################################");
				} else {
					log.info("Validation of the Wrapped Object is failed...");

					// setting the Status of POST method execution - FAILED
					req.setAttribute("errors", errors);
					req.setAttribute("status", Status.FAILURE);

					log.info("Execution Status Parameters attaching with the request :");
					log.info("errors :" + errors.toString());
					log.info("status : " + Status.SUCCESS);

					// Redirecting The control to the JSP Page successfully
					RequestDispatcher requestDispatcher = req.getRequestDispatcher("/addUsabilityStatus.jsp");
					requestDispatcher.forward(req, resp);

					log.info("Redirecting The control to the addUsabilityStatus.jsp Page successfully");
					log.info(
							"#############################################POST-UsabilityStatusServlet-END#############################################");
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
						"#############################################POST-UsabilityStatusServlet-END#############################################");
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

			RequestDispatcher requestDispatcher = req.getRequestDispatcher("/addUsabilityStatus.jsp");
			requestDispatcher.forward(req, resp);

			log.info("Redirecting The control to the addUsabilityStatus.jsp Page unsuccessfully");

			log.info(
					"#############################################POST-UsabilityStatusServlet-END#############################################");
		} catch (MissingMandatoryParametersException mmpe) {

			log.info(
					"Missing Manadatory Parameters Exception Caught in executing POST method :" + mmpe.getMessage());

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

			RequestDispatcher requestDispatcher = req.getRequestDispatcher("/addUsabilityStatus.jsp");
			requestDispatcher.forward(req, resp);

			log.info("Redirecting The control to the addUsabilityStatus.jsp Page unsuccessfully");

			log.info(
					"#############################################POST-UsabilityStatusServlet-END#############################################");
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

			RequestDispatcher requestDispatcher = req.getRequestDispatcher("/addUsabilityStatus.jsp");
			requestDispatcher.forward(req, resp);

			log.info("Redirecting The control to the addUsabilityStatus.jsp Page unsuccessfully");

			log.info(
					"#############################################POST-UsabilityStatusServlet-END#############################################");
		}
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			HttpSession session = req.getSession(false);
			String username = (String) session.getAttribute("username");
			String password = (String) session.getAttribute("password");
			if (Constants.AUTH_PASSWORD.equals(password) && Constants.AUTH_USERNAME.equals(username)) {
				String CONTENT_TYPE_JSON = "application/json";
				String[] usabilityStatusArray = req.getParameter("usabilityStatusSelected").split("-");
				String usabilityStatusSelectedId = usabilityStatusArray[1];
				String updatedUsabilityStatusSelected = req.getParameter("updatedUsabilityStatusSelected");
				log.info(
						"#############################################PUT-UsabilityStatusServlet-START################################################");
				// Wrapping the usabiltiyStatus parameter to usabiltiyStatus
				// object...
				UsabilityStatus usabilityStatus = new UsabilityStatus();
				usabilityStatus.setUsabilityStatusName(updatedUsabilityStatusSelected);

				if (ValidationService.validateUsabilityStatusForPutMethod(usabilityStatus,
						usabilityStatusSelectedId) == null) {

					log.info("Wrapping the usabilityStatus parameter to usabilityStatus object...");

					log.info("Validation of the Wrapped Object is successfull...");

					log.info("Converting the Wrapped object into Json String...");
					// converting the Object Of UserType to Json Format
					Gson gson = new Gson();
					String usabilityStatusJsonString = gson.toJson(usabilityStatus);

					log.info("Establishing URL Connection with the passed request parameters....");

					HttpURLConnection httpUrlConnection = HttpUrlService.getHttpURLConnection(
							Constants.USABILITYSTATUS_URL + "/" + usabilityStatusSelectedId, "PUT", CONTENT_TYPE_JSON,
							"Basic " + new ApiAuthenticationService().generateAuthorizationKey(username, password),
							Constants.DO_OUTPUT_FLAG_TRUE, usabilityStatusJsonString);
					String urlResponse = HttpUrlService.readHttpUrlResponse(httpUrlConnection.getInputStream());

					log.info("Successfully got the response read from URL connection.....");
					log.info("Response : " + urlResponse);

					// setting the Status of PUT method execution - Success
					req.setAttribute("response", urlResponse.toString());
					req.setAttribute("status", Status.SUCCESS);

					log.info("Execution Status Parameters attaching with the request :");
					log.info("response :" + urlResponse.toString());
					log.info("status : " + Status.SUCCESS);

					// Redirecting The control to the JSP Page successfully
					RequestDispatcher requestDispatcher = req
							.getRequestDispatcher("/updateUsabilityStatusSuccessFailure.jsp");
					requestDispatcher.forward(req, resp);

					log.info(
							"Redirecting The control to the updateUsabilityStatusSuccessFailure.jsp Page successfully");

					log.info(
							"#############################################PUT-UsabilityStatusServlet-END#############################################");
				} else {

					// creation of Error
					Error error = new Error();
					error.setErrorCode(Errors.GENERAL_ERROR.getErrorCode());
					error.setErrorName("Missing the Updated Value of User Type Or USer Type Id..");

					// creation of Error List and embedding error into it
					List<Error> errors = new ArrayList<>();
					errors.add(error);

					log.info("Attatching the errors list with the request object...");

					// setting the Status of PUT method execution - Failure
					req.setAttribute("status", Status.FAILURE);
					req.setAttribute("errors", errors);

					log.info("Execution Status Parameters attaching with the request :");
					log.info("errors :" + errors.toString());
					log.info("status : " + Status.FAILURE);

					RequestDispatcher requestDispatcher = req
							.getRequestDispatcher("updateUsabilityStatusSuccessFailure.jsp");
					requestDispatcher.forward(req, resp);

					log.info(
							"Redirecting The control to the updateUsabilityStatusSuccessFailure.jsp Page unsuccessfully");

					log.info(
							"#############################################PUT-UsabilityStatusServlet-END#############################################");
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

				// setting the Status of PUT method execution - Failure
				req.setAttribute("status", Status.FAILURE);
				req.setAttribute("errors", errors);

				log.info("Execution Status Parameters attaching with the request :");
				log.info("errors :" + errors.toString());
				log.info("status : " + Status.FAILURE);

				RequestDispatcher requestDispatcher = req.getRequestDispatcher("/notInSession.jsp");
				requestDispatcher.forward(req, resp);

				log.info("Redirecting The control to the notInSession.jsp Page unsuccessfully");

				log.info(
						"#############################################PUT-UsabilityStatusServlet-END#############################################");
			}

		} catch (IOException ioe) {

			log.info("IO Exception Caught in executing PUT method :" + ioe.getMessage());
			// creation of Error
			Error error = new Error();
			error.setErrorCode(Errors.GENERAL_ERROR.getErrorCode());
			error.setErrorName(ioe.getMessage());

			// creation of Error List and embedding error into it
			List<Error> errors = new ArrayList<>();
			errors.add(error);

			log.info("Attatching the errors list with the request object...");

			// setting the Status of PUT method execution - Failure
			req.setAttribute("status", Status.FAILURE);
			req.setAttribute("errors", errors);

			log.info("Execution Status Parameters attaching with the request :");
			log.info("errors :" + errors.toString());
			log.info("status : " + Status.FAILURE);

			RequestDispatcher requestDispatcher = req.getRequestDispatcher("/updateUsabilityStatusSuccessFailure.jsp");
			requestDispatcher.forward(req, resp);

			log.info(
					"Redirecting The control to the updateUsabilityStatusSuccessFailure.jsp Page unsuccessfully");

			log.info(
					"#############################################PUT-UsabilityStatusServlet-END#############################################");

		} catch (MissingMandatoryParametersException mmpe) {

			log.info(
					"Missing Manadatory Parameters Exception Caught in executing PUT method :" + mmpe.getMessage());

			// creation of Error
			Error error = new Error();
			error.setErrorCode(Errors.MISSING_MANDATORY_PARAMETERS.getErrorCode());
			error.setErrorName(mmpe.getMessage());

			// creation of Error List and embedding error into it
			List<Error> errors = new ArrayList<>();
			errors.add(error);

			log.info("Attatching the errors list with the request object...");

			// setting the Status of PUT method execution - Failure
			req.setAttribute("status", Status.FAILURE);
			req.setAttribute("errors", errors);

			log.info("Execution Status Parameters attaching with the request :");
			log.info("errors :" + errors.toString());
			log.info("status : " + Status.FAILURE);

			RequestDispatcher requestDispatcher = req.getRequestDispatcher("/updateUsabilityStatusSuccessFailure.jsp");
			requestDispatcher.forward(req, resp);

			log.info(
					"Redirecting The control to the updateUsabilityStatusSuccessFailure.jsp Page unsuccessfully");

			log.info(
					"#############################################PUT-UsabilityStatusServlet-END#############################################");
		} catch (Exception e) {
			log.info("Exception Caught in executing PUT method :" + e.getMessage());

			// creation of Error
			Error error = new Error();
			error.setErrorCode(Errors.GENERAL_ERROR.getErrorCode());
			error.setErrorName(e.getMessage());

			// creation of Error List and embedding error into it
			List<Error> errors = new ArrayList<>();
			errors.add(error);

			log.info("Attatching the errors list with the request object...");

			// setting the Status of PUT method execution - Failure
			req.setAttribute("status", Status.FAILURE);
			req.setAttribute("errors", errors);

			log.info("Execution Status Parameters attaching with the request :");
			log.info("errors :" + errors.toString());
			log.info("status : " + Status.FAILURE);

			RequestDispatcher requestDispatcher = req.getRequestDispatcher("/updateUsabilityStatusSuccessFailure.jsp");
			requestDispatcher.forward(req, resp);

			log.info(
					"Redirecting The control to the updateUsabilityStatusSuccessFailure.jsp Page unsuccessfully");

			log.info(
					"#############################################PUT-UsabilityStatus-END#############################################");
		}
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			HttpSession session = req.getSession(false);
			String username = (String) session.getAttribute("username");
			String password = (String) session.getAttribute("password");
			if (Constants.AUTH_PASSWORD.equals(password) && Constants.AUTH_USERNAME.equals(username)) {

				String[] usabilityStatusArray = req.getParameter("usabilityStatusSelected").split("-");
				String usabilityStatusSelectedId = usabilityStatusArray[1];
				String contentType = "";
				log.info(
						"#############################################DELETE-UsabilityStatusServlet-START################################################");

				if (ValidationService.validateUsabilityStatusForDeleteMethod(usabilityStatusSelectedId) == null) {

					log.info("Wrapping the UsabilityStatus parameter to UsabilityStatus object...");

					log.info("Validation of the Wrapped Object is successfull...");

					log.info("Establishing URL Connection with the passed request parameters....");

					String usabilityStatusJsonString = "";

					HttpURLConnection httpUrlConnection = HttpUrlService.getHttpURLConnection(
							Constants.USABILITYSTATUS_URL + "/" + usabilityStatusSelectedId, "DELETE", contentType,
							"Basic " + new ApiAuthenticationService().generateAuthorizationKey(username, password),
							Constants.DO_OUTPUT_FLAG_TRUE, usabilityStatusJsonString);
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
					RequestDispatcher requestDispatcher = req
							.getRequestDispatcher("/deleteUsabilityStatusSuccessFailure.jsp");
					requestDispatcher.forward(req, resp);

					log.info(
							"Redirecting The control to the deleteUsabilityStatusSuccessFailure.jsp Page successfully");

					log.info(
							"#############################################DELETE-UsabilityStatusServlet-END#############################################");
				} else {

					// creation of Error
					Error error = new Error();
					error.setErrorCode(Errors.GENERAL_ERROR.getErrorCode());
					error.setErrorName("Missing the Updated Value of User Type Or USer Type Id..");

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

					RequestDispatcher requestDispatcher = req
							.getRequestDispatcher("/deleteUsabilityStatusSuccessFailure.jsp");
					requestDispatcher.forward(req, resp);

					log.info(
							"Redirecting The control to the deleteUsabilityStatusSuccessFailure.jsp Page unsuccessfully");

					log.info(
							"#############################################DELETE-UsabilityStatusServlet-END#############################################");
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
						"#############################################DELETE-UsabilityStatusServlet-END#############################################");
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

			RequestDispatcher requestDispatcher = req.getRequestDispatcher("/deleteUsabilityStatusSuccessFailure.jsp");
			requestDispatcher.forward(req, resp);

			log.info(
					"Redirecting The control to the deleteUsabilityStatusSuccessFailure.jsp Page unsuccessfully");

			log.info(
					"#############################################DELETE-UsabilityStatusServlet-END#############################################");

		} catch (MissingMandatoryParametersException mmpe) {

			log.info(
					"Missing Manadatory Parameters Exception Caught in executing DELETE method :" + mmpe.getMessage());

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

			RequestDispatcher requestDispatcher = req.getRequestDispatcher("/deleteUsabilityStatusSuccessFailure.jsp");
			requestDispatcher.forward(req, resp);

			log.info(
					"Redirecting The control to the deleteUsabilityStatusSuccessFailure.jsp Page unsuccessfully");

			log.info(
					"#############################################DELETE-UsabilityStatusServlet-END#############################################");
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

			RequestDispatcher requestDispatcher = req.getRequestDispatcher("/deleteUsabilityStatusSuccessFailure.jsp");
			requestDispatcher.forward(req, resp);

			log.info(
					"Redirecting The control to the deleteUsabilityStatusSuccessFailure.jsp Page unsuccessfully");

			log.info(
					"#############################################DELETE-UsabilityStatusServlet-END#############################################");
		}

	}
}
