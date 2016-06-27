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
import com.couponsworld.dto.UserType;
import com.couponsworld.enums.Errors;
import com.couponsworld.enums.Status;
import com.couponsworld.exceptions.MissingMandatoryParametersException;
import com.couponsworld.resources.UserTypeResource;
import com.couponsworld.utilities.ApiAuthenticationService;
import com.couponsworld.utilities.Constants;
import com.couponsworld.utilities.HttpUrlService;
import com.couponsworld.utilities.ValidationService;
import com.google.gson.Gson;

@SuppressWarnings("serial")
public class UserTypeServlet extends HttpServlet {

	// declaration of logger
	private static final Logger log = Logger.getLogger(UserTypeServlet.class.getName());

	private final String CONTENT_TYPE_JSON = "application/json";

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {

			HttpSession session = req.getSession(false);
			String username = (String) session.getAttribute("username");
			String password = (String) session.getAttribute("password");
			if (Constants.AUTH_PASSWORD.equals(password) && Constants.AUTH_USERNAME.equals(username)) {

				log.info(
						"#############################################POST-UserTypeServlet-START#######################################################");

				log.info(
						"#############################################POST-UserTypeServlet-START#######################################################");

				// Wrapping the Usertype parameter to USerType object...
				UserType userType = new UserType();
				userType.setUserTypeName(req.getParameter("userType"));

				log.info("Wrapping the Usertype parameter to USerType object...");

				// VAlidating the USerTYpe parameter
				List<Error> errors = ValidationService.validateUserTypeForPostMethod(userType);

				log.info("Validating the UserType wrapped object...");

				if (errors == null) {
					log.info("Validation of the Wrapped Object is successfull...");

					log.info("Converting the Wrapped object into Json String...");
					// converting the Object Of UserType to Json Format
					Gson gson = new Gson();
					String userTypeJsonString = gson.toJson(userType);

					log.info("Establishing URL Connection with the passed request parameters....");

					HttpURLConnection httpUrlConnection = HttpUrlService
							.getHttpURLConnection(Constants.USERTYPE_URL, req.getMethod(),
									CONTENT_TYPE_JSON, "Basic " + new ApiAuthenticationService()
											.generateAuthorizationKey(username, password),
									Constants.DO_OUTPUT_FLAG_TRUE, userTypeJsonString);
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
					RequestDispatcher requestDispatcher = req.getRequestDispatcher("/addUserType.jsp");
					requestDispatcher.forward(req, resp);

					log.info("Redirecting The control to the addUserType.jsp Page successfully");

					log.info(
							"#############################################POST-UserTypeServlet-END#############################################");
				} else {
					log.info("Validation of the Wrapped Object is failed...");

					// setting the Status of POST method execution - FAILED
					req.setAttribute("errors", errors);
					req.setAttribute("status", Status.FAILURE);

					log.info("Execution Status Parameters attaching with the request :");
					log.info("errors :" + errors.toString());
					log.info("status : " + Status.SUCCESS);

					// Redirecting The control to the JSP Page successfully
					RequestDispatcher requestDispatcher = req.getRequestDispatcher("/addUserType.jsp");
					requestDispatcher.forward(req, resp);

					log.info("Redirecting The control to the addUserType.jsp Page successfully");
					log.info(
							"#############################################POST-UserTypeServlet-END#############################################");
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
						"#############################################POST-UserTypeServlet-END#############################################");
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

			RequestDispatcher requestDispatcher = req.getRequestDispatcher("/addUserType.jsp");
			requestDispatcher.forward(req, resp);

			log.info("Redirecting The control to the addUserType.jsp Page unsuccessfully");

			log.info(
					"#############################################POST-UserTypeServlet-END#############################################");
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

			RequestDispatcher requestDispatcher = req.getRequestDispatcher("/addUserType.jsp");
			requestDispatcher.forward(req, resp);

			log.info("Redirecting The control to the addUserType.jsp Page unsuccessfully");

			log.info(
					"#############################################POST-UserTypeServlet-END#############################################");
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

			RequestDispatcher requestDispatcher = req.getRequestDispatcher("/addUserType.jsp");
			requestDispatcher.forward(req, resp);

			log.info("Redirecting The control to the addUserType.jsp Page unsuccessfully");

			log.info(
					"#############################################POST-UserTypeServlet-END#############################################");
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

					log.info(
							"################################################GET-UserTypeServlet-START################################################");
					// log.info(req.getContentType());
					log.info("Establishing URL Connection with the passed request parameters....");

					// Creating Connection from the URL passed
					HttpURLConnection httpUrlConnection = HttpUrlService
							.getHttpURLConnection(Constants.USERTYPE_URL, req.getMethod(),
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
					RequestDispatcher requestDispatcher = req.getRequestDispatcher("/showUserType.jsp");
					requestDispatcher.forward(req, resp);

					log.info("Redirecting The control to the showUserType.jsp Page successfully");

					log.info(
							"################################################GET-UserTypeServlet-END################################################");
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
						"################################################GET-UserTypeServlet-END################################################");

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

			RequestDispatcher requestDispatcher = req.getRequestDispatcher("/showUserType.jsp");
			requestDispatcher.forward(req, resp);

			log.info("Redirecting The control to the showUserType.jsp Page unsuccessfully");

			log.info(
					"################################################GET-UserTypeServlet-END################################################");
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

			RequestDispatcher requestDispatcher = req.getRequestDispatcher("/showUserType.jsp");
			requestDispatcher.forward(req, resp);

			log.info("Redirecting The control to the showUserType.jsp Page unsuccessfully");

			log.info(
					"################################################GET-UserTypeServlet-END################################################");
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

			RequestDispatcher requestDispatcher = req.getRequestDispatcher("/showUserType.jsp");
			requestDispatcher.forward(req, resp);

			log.info("Redirecting The control to the showUserType.jsp Page unsuccessfully");

			log.info(
					"################################################GET-UserTypeServlet-END################################################");
		}

	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			HttpSession session = req.getSession(false);
			String username = (String) session.getAttribute("username");
			String password = (String) session.getAttribute("password");
			if (Constants.AUTH_PASSWORD.equals(password) && Constants.AUTH_USERNAME.equals(username)) {
				String[] userTypeArray = req.getParameter("userTypeSelected").split("-");
				String userTypeSelectedId = userTypeArray[1];
				String updatedUserTypeSelected = req.getParameter("updatedUserTypeSelected");
				log.info(
						"#############################################PUT-UserTypeServlet-START################################################");
				// Wrapping the Usertype parameter to USerType object...
				UserType userType = new UserType();
				userType.setUserTypeName(updatedUserTypeSelected);

				if (ValidationService.validateUserTypeForPutMethod(userType, userTypeSelectedId) == null) {

					log.info("Wrapping the Usertype parameter to USerType object...");

					log.info("Validation of the Wrapped Object is successfull...");

					log.info("Converting the Wrapped object into Json String...");
					// converting the Object Of UserType to Json Format
					Gson gson = new Gson();
					String userTypeJsonString = gson.toJson(userType);

					log.info("Establishing URL Connection with the passed request parameters....");

					HttpURLConnection httpUrlConnection = HttpUrlService
							.getHttpURLConnection(Constants.USERTYPE_URL + "/" + userTypeSelectedId, "PUT",
									CONTENT_TYPE_JSON, "Basic " + new ApiAuthenticationService()
											.generateAuthorizationKey(username, password),
									Constants.DO_OUTPUT_FLAG_TRUE, userTypeJsonString);
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
					RequestDispatcher requestDispatcher = req.getRequestDispatcher("/updateUserTypeSuccessFailure.jsp");
					requestDispatcher.forward(req, resp);

					log.info(
							"Redirecting The control to the updateUserTypeSuccessFailure.jsp Page successfully");

					log.info(
							"#############################################PUT-UserTypeServlet-END#############################################");
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

					RequestDispatcher requestDispatcher = req.getRequestDispatcher("updateUserTypeSuccessFailure.jsp");
					requestDispatcher.forward(req, resp);

					log.info(
							"Redirecting The control to the updateUserTypeSuccessFailure.jsp Page unsuccessfully");

					log.info(
							"#############################################PUT-UserTypeServlet-END#############################################");
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
						"#############################################PUT-UserTypeServlet-END#############################################");
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

			RequestDispatcher requestDispatcher = req.getRequestDispatcher("/updateUserTypeSuccessFailure.jsp");
			requestDispatcher.forward(req, resp);

			log.info("Redirecting The control to the updateUserTypeSuccessFailure.jsp Page unsuccessfully");

			log.info(
					"#############################################PUT-UserTypeServlet-END#############################################");

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

			RequestDispatcher requestDispatcher = req.getRequestDispatcher("/updateUserTypeSuccessFailure.jsp");
			requestDispatcher.forward(req, resp);

			log.info("Redirecting The control to the updateUserTypeSuccessFailure.jsp Page unsuccessfully");

			log.info(
					"#############################################PUT-UserTypeServlet-END#############################################");
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

			RequestDispatcher requestDispatcher = req.getRequestDispatcher("/updateUserTypeSuccessFailure.jsp");
			requestDispatcher.forward(req, resp);

			log.info("Redirecting The control to the updateUserTypeSuccessFailure.jsp Page unsuccessfully");

			log.info(
					"#############################################PUT-UserTypeServlet-END#############################################");
		}
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			HttpSession session = req.getSession(false);
			String username = (String) session.getAttribute("username");
			String password = (String) session.getAttribute("password");
			if (Constants.AUTH_PASSWORD.equals(password) && Constants.AUTH_USERNAME.equals(username)) {

				String[] userTypeArray = req.getParameter("userTypeSelected").split("-");
				String userTypeSelectedId = userTypeArray[1];
				String contentType = "";
				log.info(
						"#############################################DELETE-UserTypeServlet-START################################################");

				if (ValidationService.validateUserTypeForDeleteMethod(userTypeSelectedId) == null) {

					log.info("Wrapping the Usertype parameter to USerType object...");

					log.info("Validation of the Wrapped Object is successfull...");

					log.info("Establishing URL Connection with the passed request parameters....");

					String userTypeJsonString = "";

					HttpURLConnection httpUrlConnection = HttpUrlService
							.getHttpURLConnection(Constants.USERTYPE_URL + "/" + userTypeSelectedId, "DELETE",
									contentType, "Basic " + new ApiAuthenticationService()
											.generateAuthorizationKey(username, password),
									Constants.DO_OUTPUT_FLAG_TRUE, userTypeJsonString);
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
					RequestDispatcher requestDispatcher = req.getRequestDispatcher("/deleteUserTypeSuccessFailure.jsp");
					requestDispatcher.forward(req, resp);

					log.info(
							"Redirecting The control to the deleteUserTypeSuccessFailure.jsp Page successfully");

					log.info(
							"#############################################DELETE-UserTypeServlet-END#############################################");
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

					RequestDispatcher requestDispatcher = req.getRequestDispatcher("/deleteUserTypeSuccessFailure.jsp");
					requestDispatcher.forward(req, resp);

					log.info(
							"Redirecting The control to the deleteUserTypeSuccessFailure.jsp Page unsuccessfully");

					log.info(
							"#############################################DELETE-UserTypeServlet-END#############################################");
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
						"#############################################DELETE-UserTypeServlet-END#############################################");
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

			RequestDispatcher requestDispatcher = req.getRequestDispatcher("/deleteUserTypeSuccessFailure.jsp");
			requestDispatcher.forward(req, resp);

			log.info("Redirecting The control to the deleteUserTypeSuccessFailure.jsp Page unsuccessfully");

			log.info(
					"#############################################DELETE-UserTypeServlet-END#############################################");

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

			RequestDispatcher requestDispatcher = req.getRequestDispatcher("/deleteUserTypeSuccessFailure.jsp");
			requestDispatcher.forward(req, resp);

			log.info("Redirecting The control to the deleteUserTypeSuccessFailure.jsp Page unsuccessfully");

			log.info(
					"#############################################DELETE-UserTypeServlet-END#############################################");
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

			RequestDispatcher requestDispatcher = req.getRequestDispatcher("/deleteUserTypeSuccessFailure.jsp");
			requestDispatcher.forward(req, resp);

			log.info("Redirecting The control to the deleteUserTypeSuccessFailure.jsp Page unsuccessfully");

			log.info(
					"#############################################DELETE-UserTypeServlet-END#############################################");
		}

	}

}
