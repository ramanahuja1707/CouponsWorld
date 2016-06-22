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

import com.couponsworld.apiresults.Error;
import com.couponsworld.dto.UserType;
import com.couponsworld.enums.Errors;
import com.couponsworld.enums.Status;
import com.couponsworld.exceptions.MissingMandatoryParametersException;
import com.couponsworld.utilities.ApiAuthenticationService;
import com.couponsworld.utilities.Constants;
import com.couponsworld.utilities.HttpUrlService;
import com.couponsworld.utilities.ValidationService;
import com.google.gson.Gson;

@SuppressWarnings("serial")
public class UserTypeServlet extends HttpServlet {

	private final String CONTENT_TYPE_JSON = "application/json";

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			System.out.println(
					"#############################################POST-UserTypeServlet-START#######################################################");

			// Wrapping the Usertype parameter to USerType object...
			UserType userType = new UserType();
			userType.setUserTypeName(req.getParameter("userType"));

			System.out.println("Wrapping the Usertype parameter to USerType object...");

			// VAlidating the USerTYpe parameter
			List<Error> errors = ValidationService.validateUserTypeForPostMethod(userType);

			System.out.println("Validating the UserType wrapped object...");

			if (errors == null) {
				System.out.println("Validation of the Wrapped Object is successfull...");

				System.out.println("Converting the Wrapped object into Json String...");
				// converting the Object Of UserType to Json Format
				Gson gson = new Gson();
				String userTypeJsonString = gson.toJson(userType);

				System.out.println("Establishing URL Connection with the passed request parameters....");

				HttpURLConnection httpUrlConnection = HttpUrlService.getHttpURLConnection(Constants.USERTYPE_URL,
						req.getMethod(),
						CONTENT_TYPE_JSON, "Basic " + new ApiAuthenticationService()
								.generateAuthorizationKey("innovate.garr", "garr.innovate"),
						Constants.DO_OUTPUT_FLAG_TRUE, userTypeJsonString);
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
				RequestDispatcher requestDispatcher = req.getRequestDispatcher("/addUserType.jsp");
				requestDispatcher.forward(req, resp);

				System.out.println("Redirecting The control to the addUserType.jsp Page successfully");

				System.out.println(
						"#############################################POST-UserTypeServlet-END#############################################");
			} else {
				System.out.println("Validation of the Wrapped Object is failed...");

				// setting the Status of POST method execution - FAILED
				req.setAttribute("errors", errors);
				req.setAttribute("status", Status.FAILURE);

				System.out.println("Execution Status Parameters attaching with the request :");
				System.out.println("errors :" + errors.toString());
				System.out.println("status : " + Status.SUCCESS);

				// Redirecting The control to the JSP Page successfully
				RequestDispatcher requestDispatcher = req.getRequestDispatcher("/addUserType.jsp");
				requestDispatcher.forward(req, resp);

				System.out.println("Redirecting The control to the addUserType.jsp Page successfully");
				System.out.println(
						"#############################################POST-UserTypeServlet-END#############################################");
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

			RequestDispatcher requestDispatcher = req.getRequestDispatcher("/addUserType.jsp");
			requestDispatcher.forward(req, resp);

			System.out.println("Redirecting The control to the addUserType.jsp Page unsuccessfully");

			System.out.println(
					"#############################################POST-UserTypeServlet-END#############################################");
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

			RequestDispatcher requestDispatcher = req.getRequestDispatcher("/addUserType.jsp");
			requestDispatcher.forward(req, resp);

			System.out.println("Redirecting The control to the addUserType.jsp Page unsuccessfully");

			System.out.println(
					"#############################################POST-UserTypeServlet-END#############################################");
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

			RequestDispatcher requestDispatcher = req.getRequestDispatcher("/addUserType.jsp");
			requestDispatcher.forward(req, resp);

			System.out.println("Redirecting The control to the addUserType.jsp Page unsuccessfully");

			System.out.println(
					"#############################################POST-UserTypeServlet-END#############################################");
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			// configuration for PUT Request
			if (req.getParameter("_method").equals("PUT")) {
				// System.out.println("##########################----PUT----######################");
				doPut(req, resp);
			} else if (req.getParameter("_method").equals("GET")) {
				String contentType = "", objectJson = "";

				System.out.println(
						"################################################GET-UserTypeServlet-START################################################");
				// System.out.println(req.getContentType());
				System.out.println("Establishing URL Connection with the passed request parameters....");

				// Creating Connection from the URL passed
				HttpURLConnection httpUrlConnection = HttpUrlService.getHttpURLConnection(Constants.USERTYPE_URL,
						req.getMethod(), contentType, "Basic " + new ApiAuthenticationService()
								.generateAuthorizationKey("innovate.garr", "garr.innovate"),
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
				RequestDispatcher requestDispatcher = req.getRequestDispatcher("/showUserType.jsp");
				requestDispatcher.forward(req, resp);

				System.out.println("Redirecting The control to the showUserType.jsp Page successfully");

				System.out.println(
						"################################################GET-UserTypeServlet-END################################################");
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

			RequestDispatcher requestDispatcher = req.getRequestDispatcher("/showUserType.jsp");
			requestDispatcher.forward(req, resp);

			System.out.println("Redirecting The control to the showUserType.jsp Page unsuccessfully");

			System.out.println(
					"################################################GET-UserTypeServlet-END################################################");
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

			RequestDispatcher requestDispatcher = req.getRequestDispatcher("/showUserType.jsp");
			requestDispatcher.forward(req, resp);

			System.out.println("Redirecting The control to the showUserType.jsp Page unsuccessfully");

			System.out.println(
					"################################################GET-UserTypeServlet-END################################################");
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

			RequestDispatcher requestDispatcher = req.getRequestDispatcher("/showUserType.jsp");
			requestDispatcher.forward(req, resp);

			System.out.println("Redirecting The control to the showUserType.jsp Page unsuccessfully");

			System.out.println(
					"################################################GET-UserTypeServlet-END################################################");
		}

	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {

			String[] userTypeArray = req.getParameter("userTypeSelected").split("-");
			String userTypeSelectedId = userTypeArray[1];
			String updatedUserTypeSelected = req.getParameter("updatedUserTypeSelected");
			System.out.println(
					"#############################################PUT-UserTypeServlet-START################################################");
			// Wrapping the Usertype parameter to USerType object...
			UserType userType = new UserType();
			userType.setUserTypeName(updatedUserTypeSelected);

			if (ValidationService.validateUserTypeForPutMethod(userType, userTypeSelectedId) == null) {

				System.out.println("Wrapping the Usertype parameter to USerType object...");

				System.out.println("Validation of the Wrapped Object is successfull...");

				System.out.println("Converting the Wrapped object into Json String...");
				// converting the Object Of UserType to Json Format
				Gson gson = new Gson();
				String userTypeJsonString = gson.toJson(userType);

				System.out.println("Establishing URL Connection with the passed request parameters....");

				HttpURLConnection httpUrlConnection = HttpUrlService.getHttpURLConnection(
						Constants.USERTYPE_URL + "/" + userTypeSelectedId, "PUT",
						CONTENT_TYPE_JSON, "Basic " + new ApiAuthenticationService()
								.generateAuthorizationKey("innovate.garr", "garr.innovate"),
						Constants.DO_OUTPUT_FLAG_TRUE, userTypeJsonString);
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
				RequestDispatcher requestDispatcher = req.getRequestDispatcher("/successFailure.jsp");
				requestDispatcher.forward(req, resp);

				System.out.println("Redirecting The control to the successFailure.jsp Page successfully");

				System.out.println(
						"#############################################PUT-UserTypeServlet-END#############################################");
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

				RequestDispatcher requestDispatcher = req.getRequestDispatcher("successFailure.jsp");
				requestDispatcher.forward(req, resp);

				System.out.println("Redirecting The control to the successFailure.jsp Page unsuccessfully");

				System.out.println(
						"#############################################PUT-UserTypeServlet-END#############################################");
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

			RequestDispatcher requestDispatcher = req.getRequestDispatcher("/successFailure.jsp");
			requestDispatcher.forward(req, resp);

			System.out.println("Redirecting The control to the successFailure.jsp Page unsuccessfully");

			System.out.println(
					"#############################################PUT-UserTypeServlet-END#############################################");

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

			RequestDispatcher requestDispatcher = req.getRequestDispatcher("/successFailure.jsp");
			requestDispatcher.forward(req, resp);

			System.out.println("Redirecting The control to the successFailure.jsp Page unsuccessfully");

			System.out.println(
					"#############################################PUT-UserTypeServlet-END#############################################");
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

			RequestDispatcher requestDispatcher = req.getRequestDispatcher("/successFailure.jsp");
			requestDispatcher.forward(req, resp);

			System.out.println("Redirecting The control to the successFailure.jsp Page unsuccessfully");

			System.out.println(
					"#############################################PUT-UserTypeServlet-END#############################################");
		}
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("_method :" + req.getParameter("_method"));
		// resp.getWriter().println("_method :" + req.getParameter("_method"));
	}

}
