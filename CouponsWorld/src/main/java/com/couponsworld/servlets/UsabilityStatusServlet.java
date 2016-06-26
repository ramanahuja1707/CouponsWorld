package com.couponsworld.servlets;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

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
					// System.out.println("##########################----PUT----######################");
					doPut(req, resp);
				} else if (req.getParameter("_method").equals("DELETE")) {
					doDelete(req, resp);
				} else if (req.getParameter("_method").equals("GET")) {
					String contentType = "", objectJson = "";

					System.out.println(
							"################################################GET-UsabilityStatusServlet-START################################################");
					// System.out.println(req.getContentType());
					System.out.println("Establishing URL Connection with the passed request parameters....");

					// Creating Connection from the URL passed
					HttpURLConnection httpUrlConnection = HttpUrlService
							.getHttpURLConnection(Constants.USABILITYSTATUS_URL, req.getMethod(),
									contentType, "Basic " + new ApiAuthenticationService()
											.generateAuthorizationKey(username, password),
									Constants.DO_OUTPUT_FLAG_TRUE, objectJson);

					System.out.println("URL Connection with the passed request parameters Successfully created....");
					System.out.println("Reading the response from the URL connection........");

					// Getting Response from the URL
					String urlResponse = HttpUrlService.readHttpUrlResponse(httpUrlConnection.getInputStream());

					System.out.println("Successfully got the response read from URL connection.....");
					System.out.println("Response : " + urlResponse);

					// setting the Status of Get method execution - Success
					req.setAttribute("response", urlResponse.toString());
					req.setAttribute("status", Status.SUCCESS);

					System.out.println("Execution Status Parameters attaching with the request :");
					System.out.println("response :" + urlResponse.toString());
					System.out.println("status : " + Status.SUCCESS);

					// Redirecting The control to the JSP Page successfully
					RequestDispatcher requestDispatcher = req.getRequestDispatcher("/showUsabilityStatus.jsp");
					requestDispatcher.forward(req, resp);

					System.out.println("Redirecting The control to the showUsabilityStatus.jsp Page successfully");

					System.out.println(
							"################################################GET-UsabilityStatusServlet-END################################################");
				}
			} else {

				System.out.println("Username and Password not in session.....Session Expired....");

				// creation of Error
				Error error = new Error();
				error.setErrorCode(Errors.SESSION_EXPIRED_ERROR.getErrorCode());
				error.setErrorName(Errors.SESSION_EXPIRED_ERROR.getErrorName());

				// creation of Error List and embedding error into it
				List<Error> errors = new ArrayList<>();
				errors.add(error);

				System.out.println("Attatching the errors list with the request object...");

				// setting the Status of Get method execution - Failure
				req.setAttribute("status", Status.FAILURE);
				req.setAttribute("errors", errors);

				System.out.println("Execution Status Parameters attaching with the request :");
				System.out.println("errors :" + errors.toString());
				System.out.println("status : " + Status.FAILURE);

				RequestDispatcher requestDispatcher = req.getRequestDispatcher("/notInSession.jsp");
				requestDispatcher.forward(req, resp);

				System.out.println("Redirecting The control to the notInSession.jsp Page unsuccessfully");

				System.out.println(
						"################################################GET-UsabilityStatusServlet-END################################################");

			}
		} catch (IOException ioe) {

			System.out.println("IO Exception Caught in executing GET method :" + ioe.getMessage());
			// creation of Error
			Error error = new Error();
			error.setErrorCode(Errors.GENERAL_ERROR.getErrorCode());
			error.setErrorName(ioe.getMessage());

			// creation of Error List and embedding error into it
			List<Error> errors = new ArrayList<>();
			errors.add(error);

			System.out.println("Attatching the errors list with the request object...");

			// setting the Status of Get method execution - Failure
			req.setAttribute("status", Status.FAILURE);
			req.setAttribute("errors", errors);

			System.out.println("Execution Status Parameters attaching with the request :");
			System.out.println("errors :" + errors.toString());
			System.out.println("status : " + Status.FAILURE);

			RequestDispatcher requestDispatcher = req.getRequestDispatcher("/showUsabilityStatus.jsp");
			requestDispatcher.forward(req, resp);

			System.out.println("Redirecting The control to the showUsabilityStatus.jsp Page unsuccessfully");

			System.out.println(
					"################################################GET-UsabilityStatusServlet-END################################################");
		} catch (MissingMandatoryParametersException mmpe) {

			System.out.println(
					"Missing Manadatory Parameters Exception Caught in executing GET method :" + mmpe.getMessage());

			// creation of Error
			Error error = new Error();
			error.setErrorCode(Errors.MISSING_MANDATORY_PARAMETERS.getErrorCode());
			error.setErrorName(mmpe.getMessage());

			// creation of Error List and embedding error into it
			List<Error> errors = new ArrayList<>();
			errors.add(error);

			System.out.println("Attatching the errors list with the request object...");

			// setting the Status of Get method execution - Failure
			req.setAttribute("status", Status.FAILURE);
			req.setAttribute("errors", errors);

			System.out.println("Execution Status Parameters attaching with the request :");
			System.out.println("errors :" + errors.toString());
			System.out.println("status : " + Status.FAILURE);

			RequestDispatcher requestDispatcher = req.getRequestDispatcher("/showUsabilityStatus.jsp");
			requestDispatcher.forward(req, resp);

			System.out.println("Redirecting The control to the showUsabilityStatus.jsp Page unsuccessfully");

			System.out.println(
					"################################################GET-UsabilityStatusServlet-END################################################");
		} catch (Exception e) {
			System.out.println("Exception Caught in executing GET method :" + e.getMessage());

			// creation of Error
			Error error = new Error();
			error.setErrorCode(Errors.GENERAL_ERROR.getErrorCode());
			error.setErrorName(e.getMessage());

			// creation of Error List and embedding error into it
			List<Error> errors = new ArrayList<>();
			errors.add(error);

			System.out.println("Attatching the errors list with the request object...");

			// setting the Status of Get method execution - Failure
			req.setAttribute("status", Status.FAILURE);
			req.setAttribute("errors", errors);

			System.out.println("Execution Status Parameters attaching with the request :");
			System.out.println("errors :" + errors.toString());
			System.out.println("status : " + Status.FAILURE);

			RequestDispatcher requestDispatcher = req.getRequestDispatcher("/showUsabilityStatus.jsp");
			requestDispatcher.forward(req, resp);

			System.out.println("Redirecting The control to the showUsabilityStatus.jsp Page unsuccessfully");

			System.out.println(
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
				System.out.println(
						"#############################################POST-UsabilityStatusServlet-START#######################################################");

				// Wrapping the Usertype parameter to USerType object...
				UsabilityStatus usabilityStatus = new UsabilityStatus();
				usabilityStatus.setUsabilityStatusName(req.getParameter("usabilityStatus"));

				System.out.println("Wrapping the UsabilityStatus parameter to UsabilityStatus object...");

				// VAlidating the USerTYpe parameter
				List<Error> errors = ValidationService.validateUsabilityStatusForPostMethod(usabilityStatus);

				System.out.println("Validating the UsabilityStatus wrapped object...");

				if (errors == null) {
					System.out.println("Validation of the Wrapped Object is successfull...");

					System.out.println("Converting the Wrapped object into Json String...");
					// converting the Object Of UserType to Json Format
					Gson gson = new Gson();
					String usabilityStatusJsonString = gson.toJson(usabilityStatus);

					System.out.println("Establishing URL Connection with the passed request parameters....");

					HttpURLConnection httpUrlConnection = HttpUrlService
							.getHttpURLConnection(Constants.USABILITYSTATUS_URL, req.getMethod(),
									CONTENT_TYPE_JSON, "Basic " + new ApiAuthenticationService()
											.generateAuthorizationKey(username, password),
									Constants.DO_OUTPUT_FLAG_TRUE, usabilityStatusJsonString);
					String urlResponse = HttpUrlService.readHttpUrlResponse(httpUrlConnection.getInputStream());

					System.out.println("Successfully got the response read from URL connection.....");
					System.out.println("Response : " + urlResponse);

					// setting the Status of Get method execution - Success
					req.setAttribute("response", urlResponse.toString());
					req.setAttribute("status", Status.SUCCESS);

					System.out.println("Execution Status Parameters attaching with the request :");
					System.out.println("response :" + urlResponse.toString());
					System.out.println("status : " + Status.SUCCESS);

					// Redirecting The control to the JSP Page successfully
					RequestDispatcher requestDispatcher = req.getRequestDispatcher("/addUsabilityStatus.jsp");
					requestDispatcher.forward(req, resp);

					System.out.println("Redirecting The control to the addUsabilityStatus.jsp Page successfully");

					System.out.println(
							"#############################################POST-UsabilityStatusServlet-END#############################################");
				} else {
					System.out.println("Validation of the Wrapped Object is failed...");

					// setting the Status of POST method execution - FAILED
					req.setAttribute("errors", errors);
					req.setAttribute("status", Status.FAILURE);

					System.out.println("Execution Status Parameters attaching with the request :");
					System.out.println("errors :" + errors.toString());
					System.out.println("status : " + Status.SUCCESS);

					// Redirecting The control to the JSP Page successfully
					RequestDispatcher requestDispatcher = req.getRequestDispatcher("/addUsabilityStatus.jsp");
					requestDispatcher.forward(req, resp);

					System.out.println("Redirecting The control to the addUsabilityStatus.jsp Page successfully");
					System.out.println(
							"#############################################POST-UsabilityStatusServlet-END#############################################");
				}
			} else {
				System.out.println("Username and Password not in session.....Session Expired....");

				// creation of Error
				Error error = new Error();
				error.setErrorCode(Errors.SESSION_EXPIRED_ERROR.getErrorCode());
				error.setErrorName(Errors.SESSION_EXPIRED_ERROR.getErrorName());

				// creation of Error List and embedding error into it
				List<Error> errors = new ArrayList<>();
				errors.add(error);

				System.out.println("Attatching the errors list with the request object...");

				// setting the Status of POST method execution - Failure
				req.setAttribute("status", Status.FAILURE);
				req.setAttribute("errors", errors);

				System.out.println("Execution Status Parameters attaching with the request :");
				System.out.println("errors :" + errors.toString());
				System.out.println("status : " + Status.FAILURE);

				RequestDispatcher requestDispatcher = req.getRequestDispatcher("/notInSession.jsp");
				requestDispatcher.forward(req, resp);

				System.out.println("Redirecting The control to the notInSession.jsp Page unsuccessfully");

				System.out.println(
						"#############################################POST-UsabilityStatusServlet-END#############################################");
			}
		} catch (IOException ioe) {

			System.out.println("IO Exception Caught in executing POST method :" + ioe.getMessage());
			// creation of Error
			Error error = new Error();
			error.setErrorCode(Errors.GENERAL_ERROR.getErrorCode());
			error.setErrorName(ioe.getMessage());

			// creation of Error List and embedding error into it
			List<Error> errors = new ArrayList<>();
			errors.add(error);

			System.out.println("Attatching the errors list with the request object...");

			// setting the Status of POST method execution - Failure
			req.setAttribute("status", Status.FAILURE);
			req.setAttribute("errors", errors);

			System.out.println("Execution Status Parameters attaching with the request :");
			System.out.println("errors :" + errors.toString());
			System.out.println("status : " + Status.FAILURE);

			RequestDispatcher requestDispatcher = req.getRequestDispatcher("/addUsabilityStatus.jsp");
			requestDispatcher.forward(req, resp);

			System.out.println("Redirecting The control to the addUsabilityStatus.jsp Page unsuccessfully");

			System.out.println(
					"#############################################POST-UsabilityStatusServlet-END#############################################");
		} catch (MissingMandatoryParametersException mmpe) {

			System.out.println(
					"Missing Manadatory Parameters Exception Caught in executing POST method :" + mmpe.getMessage());

			// creation of Error
			Error error = new Error();
			error.setErrorCode(Errors.MISSING_MANDATORY_PARAMETERS.getErrorCode());
			error.setErrorName(mmpe.getMessage());

			// creation of Error List and embedding error into it
			List<Error> errors = new ArrayList<>();
			errors.add(error);

			System.out.println("Attatching the errors list with the request object...");

			// setting the Status of POST method execution - Failure
			req.setAttribute("status", Status.FAILURE);
			req.setAttribute("errors", errors);

			System.out.println("Execution Status Parameters attaching with the request :");
			System.out.println("errors :" + errors.toString());
			System.out.println("status : " + Status.FAILURE);

			RequestDispatcher requestDispatcher = req.getRequestDispatcher("/addUsabilityStatus.jsp");
			requestDispatcher.forward(req, resp);

			System.out.println("Redirecting The control to the addUsabilityStatus.jsp Page unsuccessfully");

			System.out.println(
					"#############################################POST-UsabilityStatusServlet-END#############################################");
		} catch (Exception e) {
			System.out.println("Exception Caught in executing POST method :" + e.getMessage());

			// creation of Error
			Error error = new Error();
			error.setErrorCode(Errors.GENERAL_ERROR.getErrorCode());
			error.setErrorName(e.getMessage());

			// creation of Error List and embedding error into it
			List<Error> errors = new ArrayList<>();
			errors.add(error);

			System.out.println("Attatching the errors list with the request object...");

			// setting the Status of POST method execution - Failure
			req.setAttribute("status", Status.FAILURE);
			req.setAttribute("errors", errors);

			System.out.println("Execution Status Parameters attaching with the request :");
			System.out.println("errors :" + errors.toString());
			System.out.println("status : " + Status.FAILURE);

			RequestDispatcher requestDispatcher = req.getRequestDispatcher("/addUsabilityStatus.jsp");
			requestDispatcher.forward(req, resp);

			System.out.println("Redirecting The control to the addUsabilityStatus.jsp Page unsuccessfully");

			System.out.println(
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
				System.out.println(
						"#############################################PUT-UsabilityStatusServlet-START################################################");
				// Wrapping the usabiltiyStatus parameter to usabiltiyStatus
				// object...
				UsabilityStatus usabilityStatus = new UsabilityStatus();
				usabilityStatus.setUsabilityStatusName(updatedUsabilityStatusSelected);

				if (ValidationService.validateUsabilityStatusForPutMethod(usabilityStatus,
						usabilityStatusSelectedId) == null) {

					System.out.println("Wrapping the usabilityStatus parameter to usabilityStatus object...");

					System.out.println("Validation of the Wrapped Object is successfull...");

					System.out.println("Converting the Wrapped object into Json String...");
					// converting the Object Of UserType to Json Format
					Gson gson = new Gson();
					String usabilityStatusJsonString = gson.toJson(usabilityStatus);

					System.out.println("Establishing URL Connection with the passed request parameters....");

					HttpURLConnection httpUrlConnection = HttpUrlService.getHttpURLConnection(
							Constants.USABILITYSTATUS_URL + "/" + usabilityStatusSelectedId, "PUT", CONTENT_TYPE_JSON,
							"Basic " + new ApiAuthenticationService().generateAuthorizationKey(username, password),
							Constants.DO_OUTPUT_FLAG_TRUE, usabilityStatusJsonString);
					String urlResponse = HttpUrlService.readHttpUrlResponse(httpUrlConnection.getInputStream());

					System.out.println("Successfully got the response read from URL connection.....");
					System.out.println("Response : " + urlResponse);

					// setting the Status of PUT method execution - Success
					req.setAttribute("response", urlResponse.toString());
					req.setAttribute("status", Status.SUCCESS);

					System.out.println("Execution Status Parameters attaching with the request :");
					System.out.println("response :" + urlResponse.toString());
					System.out.println("status : " + Status.SUCCESS);

					// Redirecting The control to the JSP Page successfully
					RequestDispatcher requestDispatcher = req
							.getRequestDispatcher("/updateUsabilityStatusSuccessFailure.jsp");
					requestDispatcher.forward(req, resp);

					System.out.println(
							"Redirecting The control to the updateUsabilityStatusSuccessFailure.jsp Page successfully");

					System.out.println(
							"#############################################PUT-UsabilityStatusServlet-END#############################################");
				} else {

					// creation of Error
					Error error = new Error();
					error.setErrorCode(Errors.GENERAL_ERROR.getErrorCode());
					error.setErrorName("Missing the Updated Value of User Type Or USer Type Id..");

					// creation of Error List and embedding error into it
					List<Error> errors = new ArrayList<>();
					errors.add(error);

					System.out.println("Attatching the errors list with the request object...");

					// setting the Status of PUT method execution - Failure
					req.setAttribute("status", Status.FAILURE);
					req.setAttribute("errors", errors);

					System.out.println("Execution Status Parameters attaching with the request :");
					System.out.println("errors :" + errors.toString());
					System.out.println("status : " + Status.FAILURE);

					RequestDispatcher requestDispatcher = req
							.getRequestDispatcher("updateUsabilityStatusSuccessFailure.jsp");
					requestDispatcher.forward(req, resp);

					System.out.println(
							"Redirecting The control to the updateUsabilityStatusSuccessFailure.jsp Page unsuccessfully");

					System.out.println(
							"#############################################PUT-UsabilityStatusServlet-END#############################################");
				}
			} else {
				System.out.println("Username and Password not in session.....Session Expired....");

				// creation of Error
				Error error = new Error();
				error.setErrorCode(Errors.SESSION_EXPIRED_ERROR.getErrorCode());
				error.setErrorName(Errors.SESSION_EXPIRED_ERROR.getErrorName());

				// creation of Error List and embedding error into it
				List<Error> errors = new ArrayList<>();
				errors.add(error);

				System.out.println("Attatching the errors list with the request object...");

				// setting the Status of PUT method execution - Failure
				req.setAttribute("status", Status.FAILURE);
				req.setAttribute("errors", errors);

				System.out.println("Execution Status Parameters attaching with the request :");
				System.out.println("errors :" + errors.toString());
				System.out.println("status : " + Status.FAILURE);

				RequestDispatcher requestDispatcher = req.getRequestDispatcher("/notInSession.jsp");
				requestDispatcher.forward(req, resp);

				System.out.println("Redirecting The control to the notInSession.jsp Page unsuccessfully");

				System.out.println(
						"#############################################PUT-UsabilityStatusServlet-END#############################################");
			}

		} catch (IOException ioe) {

			System.out.println("IO Exception Caught in executing PUT method :" + ioe.getMessage());
			// creation of Error
			Error error = new Error();
			error.setErrorCode(Errors.GENERAL_ERROR.getErrorCode());
			error.setErrorName(ioe.getMessage());

			// creation of Error List and embedding error into it
			List<Error> errors = new ArrayList<>();
			errors.add(error);

			System.out.println("Attatching the errors list with the request object...");

			// setting the Status of PUT method execution - Failure
			req.setAttribute("status", Status.FAILURE);
			req.setAttribute("errors", errors);

			System.out.println("Execution Status Parameters attaching with the request :");
			System.out.println("errors :" + errors.toString());
			System.out.println("status : " + Status.FAILURE);

			RequestDispatcher requestDispatcher = req.getRequestDispatcher("/updateUsabilityStatusSuccessFailure.jsp");
			requestDispatcher.forward(req, resp);

			System.out.println(
					"Redirecting The control to the updateUsabilityStatusSuccessFailure.jsp Page unsuccessfully");

			System.out.println(
					"#############################################PUT-UsabilityStatusServlet-END#############################################");

		} catch (MissingMandatoryParametersException mmpe) {

			System.out.println(
					"Missing Manadatory Parameters Exception Caught in executing PUT method :" + mmpe.getMessage());

			// creation of Error
			Error error = new Error();
			error.setErrorCode(Errors.MISSING_MANDATORY_PARAMETERS.getErrorCode());
			error.setErrorName(mmpe.getMessage());

			// creation of Error List and embedding error into it
			List<Error> errors = new ArrayList<>();
			errors.add(error);

			System.out.println("Attatching the errors list with the request object...");

			// setting the Status of PUT method execution - Failure
			req.setAttribute("status", Status.FAILURE);
			req.setAttribute("errors", errors);

			System.out.println("Execution Status Parameters attaching with the request :");
			System.out.println("errors :" + errors.toString());
			System.out.println("status : " + Status.FAILURE);

			RequestDispatcher requestDispatcher = req.getRequestDispatcher("/updateUsabilityStatusSuccessFailure.jsp");
			requestDispatcher.forward(req, resp);

			System.out.println(
					"Redirecting The control to the updateUsabilityStatusSuccessFailure.jsp Page unsuccessfully");

			System.out.println(
					"#############################################PUT-UsabilityStatusServlet-END#############################################");
		} catch (Exception e) {
			System.out.println("Exception Caught in executing PUT method :" + e.getMessage());

			// creation of Error
			Error error = new Error();
			error.setErrorCode(Errors.GENERAL_ERROR.getErrorCode());
			error.setErrorName(e.getMessage());

			// creation of Error List and embedding error into it
			List<Error> errors = new ArrayList<>();
			errors.add(error);

			System.out.println("Attatching the errors list with the request object...");

			// setting the Status of PUT method execution - Failure
			req.setAttribute("status", Status.FAILURE);
			req.setAttribute("errors", errors);

			System.out.println("Execution Status Parameters attaching with the request :");
			System.out.println("errors :" + errors.toString());
			System.out.println("status : " + Status.FAILURE);

			RequestDispatcher requestDispatcher = req.getRequestDispatcher("/updateUsabilityStatusSuccessFailure.jsp");
			requestDispatcher.forward(req, resp);

			System.out.println(
					"Redirecting The control to the updateUsabilityStatusSuccessFailure.jsp Page unsuccessfully");

			System.out.println(
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
				System.out.println(
						"#############################################DELETE-UsabilityStatusServlet-START################################################");

				if (ValidationService.validateUsabilityStatusForDeleteMethod(usabilityStatusSelectedId) == null) {

					System.out.println("Wrapping the UsabilityStatus parameter to UsabilityStatus object...");

					System.out.println("Validation of the Wrapped Object is successfull...");

					System.out.println("Establishing URL Connection with the passed request parameters....");

					String usabilityStatusJsonString = "";

					HttpURLConnection httpUrlConnection = HttpUrlService.getHttpURLConnection(
							Constants.USABILITYSTATUS_URL + "/" + usabilityStatusSelectedId, "DELETE", contentType,
							"Basic " + new ApiAuthenticationService().generateAuthorizationKey(username, password),
							Constants.DO_OUTPUT_FLAG_TRUE, usabilityStatusJsonString);
					String urlResponse = HttpUrlService.readHttpUrlResponse(httpUrlConnection.getInputStream());

					System.out.println("Successfully got the response read from URL connection.....");
					System.out.println("Response : " + urlResponse);

					// setting the Status of DELETE method execution - Success
					req.setAttribute("response", urlResponse.toString());
					req.setAttribute("status", Status.SUCCESS);

					System.out.println("Execution Status Parameters attaching with the request :");
					System.out.println("response :" + urlResponse.toString());
					System.out.println("status : " + Status.SUCCESS);

					// Redirecting The control to the JSP Page successfully
					RequestDispatcher requestDispatcher = req
							.getRequestDispatcher("/deleteUsabilityStatusSuccessFailure.jsp");
					requestDispatcher.forward(req, resp);

					System.out.println(
							"Redirecting The control to the deleteUsabilityStatusSuccessFailure.jsp Page successfully");

					System.out.println(
							"#############################################DELETE-UsabilityStatusServlet-END#############################################");
				} else {

					// creation of Error
					Error error = new Error();
					error.setErrorCode(Errors.GENERAL_ERROR.getErrorCode());
					error.setErrorName("Missing the Updated Value of User Type Or USer Type Id..");

					// creation of Error List and embedding error into it
					List<Error> errors = new ArrayList<>();
					errors.add(error);

					System.out.println("Attatching the errors list with the request object...");

					// setting the Status of DELETE method execution - Failure
					req.setAttribute("status", Status.FAILURE);
					req.setAttribute("errors", errors);

					System.out.println("Execution Status Parameters attaching with the request :");
					System.out.println("errors :" + errors.toString());
					System.out.println("status : " + Status.FAILURE);

					RequestDispatcher requestDispatcher = req
							.getRequestDispatcher("/deleteUsabilityStatusSuccessFailure.jsp");
					requestDispatcher.forward(req, resp);

					System.out.println(
							"Redirecting The control to the deleteUsabilityStatusSuccessFailure.jsp Page unsuccessfully");

					System.out.println(
							"#############################################DELETE-UsabilityStatusServlet-END#############################################");
				}
			} else {
				System.out.println("Username and Password not in session.....Session Expired....");

				// creation of Error
				Error error = new Error();
				error.setErrorCode(Errors.SESSION_EXPIRED_ERROR.getErrorCode());
				error.setErrorName(Errors.SESSION_EXPIRED_ERROR.getErrorName());

				// creation of Error List and embedding error into it
				List<Error> errors = new ArrayList<>();
				errors.add(error);

				System.out.println("Attatching the errors list with the request object...");

				// setting the Status of DELETE method execution - Failure
				req.setAttribute("status", Status.FAILURE);
				req.setAttribute("errors", errors);

				System.out.println("Execution Status Parameters attaching with the request :");
				System.out.println("errors :" + errors.toString());
				System.out.println("status : " + Status.FAILURE);

				RequestDispatcher requestDispatcher = req.getRequestDispatcher("/notInSession.jsp");
				requestDispatcher.forward(req, resp);

				System.out.println("Redirecting The control to the notInSession.jsp Page unsuccessfully");

				System.out.println(
						"#############################################DELETE-UsabilityStatusServlet-END#############################################");
			}
		} catch (IOException ioe) {

			System.out.println("IO Exception Caught in executing DELETE method :" + ioe.getMessage());
			// creation of Error
			Error error = new Error();
			error.setErrorCode(Errors.GENERAL_ERROR.getErrorCode());
			error.setErrorName(ioe.getMessage());

			// creation of Error List and embedding error into it
			List<Error> errors = new ArrayList<>();
			errors.add(error);

			System.out.println("Attatching the errors list with the request object...");

			// setting the Status of DELETE method execution - Failure
			req.setAttribute("status", Status.FAILURE);
			req.setAttribute("errors", errors);

			System.out.println("Execution Status Parameters attaching with the request :");
			System.out.println("errors :" + errors.toString());
			System.out.println("status : " + Status.FAILURE);

			RequestDispatcher requestDispatcher = req.getRequestDispatcher("/deleteUsabilityStatusSuccessFailure.jsp");
			requestDispatcher.forward(req, resp);

			System.out.println(
					"Redirecting The control to the deleteUsabilityStatusSuccessFailure.jsp Page unsuccessfully");

			System.out.println(
					"#############################################DELETE-UsabilityStatusServlet-END#############################################");

		} catch (MissingMandatoryParametersException mmpe) {

			System.out.println(
					"Missing Manadatory Parameters Exception Caught in executing DELETE method :" + mmpe.getMessage());

			// creation of Error
			Error error = new Error();
			error.setErrorCode(Errors.MISSING_MANDATORY_PARAMETERS.getErrorCode());
			error.setErrorName(mmpe.getMessage());

			// creation of Error List and embedding error into it
			List<Error> errors = new ArrayList<>();
			errors.add(error);

			System.out.println("Attatching the errors list with the request object...");

			// setting the Status of DELETE method execution - Failure
			req.setAttribute("status", Status.FAILURE);
			req.setAttribute("errors", errors);

			System.out.println("Execution Status Parameters attaching with the request :");
			System.out.println("errors :" + errors.toString());
			System.out.println("status : " + Status.FAILURE);

			RequestDispatcher requestDispatcher = req.getRequestDispatcher("/deleteUsabilityStatusSuccessFailure.jsp");
			requestDispatcher.forward(req, resp);

			System.out.println(
					"Redirecting The control to the deleteUsabilityStatusSuccessFailure.jsp Page unsuccessfully");

			System.out.println(
					"#############################################DELETE-UsabilityStatusServlet-END#############################################");
		} catch (Exception e) {
			System.out.println("Exception Caught in executing DELETE method :" + e.getMessage());

			// creation of Error
			Error error = new Error();
			error.setErrorCode(Errors.GENERAL_ERROR.getErrorCode());
			error.setErrorName(e.getMessage());

			// creation of Error List and embedding error into it
			List<Error> errors = new ArrayList<>();
			errors.add(error);

			System.out.println("Attatching the errors list with the request object...");

			// setting the Status of DELETE method execution - Failure
			req.setAttribute("status", Status.FAILURE);
			req.setAttribute("errors", errors);

			System.out.println("Execution Status Parameters attaching with the request :");
			System.out.println("errors :" + errors.toString());
			System.out.println("status : " + Status.FAILURE);

			RequestDispatcher requestDispatcher = req.getRequestDispatcher("/deleteUsabilityStatusSuccessFailure.jsp");
			requestDispatcher.forward(req, resp);

			System.out.println(
					"Redirecting The control to the deleteUsabilityStatusSuccessFailure.jsp Page unsuccessfully");

			System.out.println(
					"#############################################DELETE-UsabilityStatusServlet-END#############################################");
		}

	}
}
