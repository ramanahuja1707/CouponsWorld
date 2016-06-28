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
import com.couponsworld.dto.SubCategory;
import com.couponsworld.enums.Errors;
import com.couponsworld.enums.Status;
import com.couponsworld.exceptions.MissingMandatoryParametersException;
import com.couponsworld.utilities.ApiAuthenticationService;
import com.couponsworld.utilities.Constants;
import com.couponsworld.utilities.HttpUrlService;
import com.couponsworld.utilities.ValidationService;
import com.google.gson.Gson;

public class SubCategoryServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 602657771357316004L;

	// declaration of logger
	private static final Logger log = Logger.getLogger(UserTypeServlet.class.getName());

	private final String CONTENT_TYPE_JSON = "application/json";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			HttpSession session = req.getSession(false);
			String username = (String) session.getAttribute("username");
			String password = (String) session.getAttribute("password");
			if (Constants.AUTH_PASSWORD.equals(password) && Constants.AUTH_USERNAME.equals(username)) {
				// configuration for PUT Request
				if (req.getParameter("_method").equals("PUT")) {
					doPut(req, resp);
				} else if (req.getParameter("_method").equals("DELETE")) {
					doDelete(req, resp);
				} else if (req.getParameter("_method").equals("GET")) {
					String contentType = "", objectJson = "";

					log.info(
							"################################################GET-SubCategoryServlet-START################################################");
					// log.info(req.getContentType());
					log.info("Establishing URL Connection with the passed request parameters....");

					// Creating Connection from the URL passed
					HttpURLConnection httpUrlConnection = HttpUrlService
							.getHttpURLConnection(Constants.SUBCATEGORY_URL, req.getMethod(), contentType,
									"Basic " + new ApiAuthenticationService().generateAuthorizationKey(username,
											password),
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
					RequestDispatcher requestDispatcher = req.getRequestDispatcher("/showSubCategory.jsp");
					requestDispatcher.forward(req, resp);

					log.info("Redirecting The control to the showSubCategory.jsp Page successfully");

					log.info(
							"################################################GET-SubCategoryServlet-END################################################");
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
						"################################################GET-SubCategoryServlet-END################################################");

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

			RequestDispatcher requestDispatcher = req.getRequestDispatcher("/showSubCategory.jsp");
			requestDispatcher.forward(req, resp);

			log.info("Redirecting The control to the showSubCategory.jsp Page unsuccessfully");

			log.info(
					"################################################GET-SubCategoryServlet-END################################################");
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

			RequestDispatcher requestDispatcher = req.getRequestDispatcher("/showSubCategory.jsp");
			requestDispatcher.forward(req, resp);

			log.info("Redirecting The control to the showSubCategory.jsp Page unsuccessfully");

			log.info(
					"################################################GET-SubCategoryServlet-END################################################");
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

			RequestDispatcher requestDispatcher = req.getRequestDispatcher("/showSubCategory.jsp");
			requestDispatcher.forward(req, resp);

			log.info("Redirecting The control to the showSubCategory.jsp Page unsuccessfully");

			log.info(
					"################################################GET-SubCategoryServlet-END################################################");
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {

			HttpSession session = req.getSession(false);
			String username = (String) session.getAttribute("username");
			String password = (String) session.getAttribute("password");
			if (Constants.AUTH_PASSWORD.equals(password) && Constants.AUTH_USERNAME.equals(username)) {

				log.info(
						"#############################################POST-SubCategoryServlet-START#######################################################");

				// Wrapping the Usertype parameter to USerType object...
				SubCategory subCategory = new SubCategory();
				subCategory.setSubCategoryName(req.getParameter("subCategory"));

				log.info("Wrapping the SubCategory parameter to SubCategory object...");

				// VAlidating the USerTYpe parameter
				List<Error> errors = ValidationService.validateSubCategoryForPostMethod(subCategory);

				log.info("Validating the UserType wrapped object...");

				if (errors == null) {
					log.info("Validation of the Wrapped Object is successfull...");

					log.info("Converting the Wrapped object into Json String...");
					// converting the Object Of UserType to Json Format
					Gson gson = new Gson();
					String subCategoryJsonString = gson.toJson(subCategory);

					log.info("Establishing URL Connection with the passed request parameters....");

					HttpURLConnection httpUrlConnection = HttpUrlService
							.getHttpURLConnection(Constants.SUBCATEGORY_URL, req.getMethod(), CONTENT_TYPE_JSON,
									"Basic " + new ApiAuthenticationService().generateAuthorizationKey(username,
											password),
									Constants.DO_OUTPUT_FLAG_TRUE, subCategoryJsonString, username, password,
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
					RequestDispatcher requestDispatcher = req.getRequestDispatcher("/addSubCategory.jsp");
					requestDispatcher.forward(req, resp);

					log.info("Redirecting The control to the addSubCategory.jsp Page successfully");

					log.info(
							"#############################################POST-SubCategoryServlet-END#############################################");
				} else {
					log.info("Validation of the Wrapped Object is failed...");

					// setting the Status of POST method execution - FAILED
					req.setAttribute("errors", errors);
					req.setAttribute("status", Status.FAILURE);

					log.info("Execution Status Parameters attaching with the request :");
					log.info("errors :" + errors.toString());
					log.info("status : " + Status.SUCCESS);

					// Redirecting The control to the JSP Page successfully
					RequestDispatcher requestDispatcher = req.getRequestDispatcher("/addSubCategory.jsp");
					requestDispatcher.forward(req, resp);

					log.info("Redirecting The control to the addSubCategory.jsp Page successfully");
					log.info(
							"#############################################POST-SubCategoryServlet-END#############################################");
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
						"#############################################POST-SubCategoryServlet-END#############################################");
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

			RequestDispatcher requestDispatcher = req.getRequestDispatcher("/addSubCategory.jsp");
			requestDispatcher.forward(req, resp);

			log.info("Redirecting The control to the addSubCategory.jsp Page unsuccessfully");

			log.info(
					"#############################################POST-SubCategoryServlet-END#############################################");
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

			RequestDispatcher requestDispatcher = req.getRequestDispatcher("/addSubCategory.jsp");
			requestDispatcher.forward(req, resp);

			log.info("Redirecting The control to the addSubCategory.jsp Page unsuccessfully");

			log.info(
					"#############################################POST-SubCategoryServlet-END#############################################");
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

			RequestDispatcher requestDispatcher = req.getRequestDispatcher("/addSubCategory.jsp");
			requestDispatcher.forward(req, resp);

			log.info("Redirecting The control to the addSubCategory.jsp Page unsuccessfully");

			log.info(
					"#############################################POST-SubCategoryServlet-END#############################################");
		}
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			HttpSession session = req.getSession(false);
			String username = (String) session.getAttribute("username");
			String password = (String) session.getAttribute("password");
			if (Constants.AUTH_PASSWORD.equals(password) && Constants.AUTH_USERNAME.equals(username)) {
				String[] subCategoryArray = req.getParameter("subCategorySelected").split("-");
				String subCategorySelectedId = subCategoryArray[1];
				String updatedSubCategorySelected = req.getParameter("updatedSubCategorySelected");
				log.info(
						"#############################################PUT-SubCategoryServlet-START################################################");
				// Wrapping the SubCategory parameter to SubCategory object...
				SubCategory subCategory = new SubCategory();
				subCategory.setSubCategoryName(updatedSubCategorySelected);

				if (ValidationService.validateSubCategoryForPutMethod(subCategory, subCategorySelectedId) == null) {

					log.info("Wrapping the SubCategory parameter to SubCategory object...");

					log.info("Validation of the Wrapped Object is successfull...");

					log.info("Converting the Wrapped object into Json String...");
					// converting the Object Of UserType to Json Format
					Gson gson = new Gson();
					String subCategoryJsonString = gson.toJson(subCategory);

					log.info("Establishing URL Connection with the passed request parameters....");

					HttpURLConnection httpUrlConnection = HttpUrlService
							.getHttpURLConnection(Constants.SUBCATEGORY_URL + "/" + subCategorySelectedId, "PUT",
									CONTENT_TYPE_JSON,
									"Basic " + new ApiAuthenticationService().generateAuthorizationKey(username,
											password),
									Constants.DO_OUTPUT_FLAG_TRUE, subCategoryJsonString, username, password,
									session.getAttribute("accessId").toString(),
									session.getAttribute("accessPlatform").toString());
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
							.getRequestDispatcher("/updateSubCategorySuccessFailure.jsp");
					requestDispatcher.forward(req, resp);

					log.info("Redirecting The control to the updateSubCategorySuccessFailure.jsp Page successfully");

					log.info(
							"#############################################PUT-SubCategoryServlet-END#############################################");
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
							.getRequestDispatcher("updateSubCategorySuccessFailure.jsp");
					requestDispatcher.forward(req, resp);

					log.info("Redirecting The control to the updateSubCategorySuccessFailure.jsp Page unsuccessfully");

					log.info(
							"#############################################PUT-SubCategoryServlet-END#############################################");
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
						"#############################################PUT-SubCategoryServlet-END#############################################");
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

			RequestDispatcher requestDispatcher = req.getRequestDispatcher("/updateSubCategorySuccessFailure.jsp");
			requestDispatcher.forward(req, resp);

			log.info("Redirecting The control to the updateSubCategorySuccessFailure.jsp Page unsuccessfully");

			log.info(
					"#############################################PUT-SubCategoryServlet-END#############################################");

		} catch (MissingMandatoryParametersException mmpe) {

			log.info("Missing Manadatory Parameters Exception Caught in executing PUT method :" + mmpe.getMessage());

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

			RequestDispatcher requestDispatcher = req.getRequestDispatcher("/updateSubCategorySuccessFailure.jsp");
			requestDispatcher.forward(req, resp);

			log.info("Redirecting The control to the updateSubCategorySuccessFailure.jsp Page unsuccessfully");

			log.info(
					"#############################################PUT-SubCategoryServlet-END#############################################");
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

			RequestDispatcher requestDispatcher = req.getRequestDispatcher("/updateSubCategorySuccessFailure.jsp");
			requestDispatcher.forward(req, resp);

			log.info("Redirecting The control to the updateSubCategorySuccessFailure.jsp Page unsuccessfully");

			log.info(
					"#############################################PUT-SubCategoryServlet-END#############################################");
		}
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			HttpSession session = req.getSession(false);
			String username = (String) session.getAttribute("username");
			String password = (String) session.getAttribute("password");
			if (Constants.AUTH_PASSWORD.equals(password) && Constants.AUTH_USERNAME.equals(username)) {

				String[] subCategoryArray = req.getParameter("subCategorySelected").split("-");
				String subCategorySelectedId = subCategoryArray[1];
				String contentType = "";
				log.info(
						"#############################################DELETE-SubCategoryServlet-START################################################");

				if (ValidationService.validateSubCategoryForDeleteMethod(subCategorySelectedId) == null) {

					log.info("Wrapping the subCategory parameter to subCategory object...");

					log.info("Validation of the Wrapped Object is successfull...");

					log.info("Establishing URL Connection with the passed request parameters....");

					String subCategoryJsonString = "";

					HttpURLConnection httpUrlConnection = HttpUrlService
							.getHttpURLConnection(Constants.SUBCATEGORY_URL + "/" + subCategorySelectedId, "DELETE",
									contentType,
									"Basic " + new ApiAuthenticationService().generateAuthorizationKey(username,
											password),
									Constants.DO_OUTPUT_FLAG_TRUE, subCategoryJsonString, username, password,
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
					RequestDispatcher requestDispatcher = req
							.getRequestDispatcher("/deleteSubCategorySuccessFailure.jsp");
					requestDispatcher.forward(req, resp);

					log.info("Redirecting The control to the deleteSubCategorySuccessFailure.jsp Page successfully");

					log.info(
							"#############################################DELETE-SubCategoryServlet-END#############################################");
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
							.getRequestDispatcher("/deleteSubCategorySuccessFailure.jsp");
					requestDispatcher.forward(req, resp);

					log.info("Redirecting The control to the deleteSubCategorySuccessFailure.jsp Page unsuccessfully");

					log.info(
							"#############################################DELETE-SubCategoryServlet-END#############################################");
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
						"#############################################DELETE-SubCategoryServlet-END#############################################");
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

			RequestDispatcher requestDispatcher = req.getRequestDispatcher("/deleteSubCategorySuccessFailure.jsp");
			requestDispatcher.forward(req, resp);

			log.info("Redirecting The control to the deleteSubCategorySuccessFailure.jsp Page unsuccessfully");

			log.info(
					"#############################################DELETE-SubCategoryServlet-END#############################################");

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

			RequestDispatcher requestDispatcher = req.getRequestDispatcher("/deleteSubCategorySuccessFailure.jsp");
			requestDispatcher.forward(req, resp);

			log.info("Redirecting The control to the deleteSubCategorySuccessFailure.jsp Page unsuccessfully");

			log.info(
					"#############################################DELETE-SubCategoryServlet-END#############################################");
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

			RequestDispatcher requestDispatcher = req.getRequestDispatcher("/deleteSubCategorySuccessFailure.jsp");
			requestDispatcher.forward(req, resp);

			log.info("Redirecting The control to the deleteSubCategorySuccessFailure.jsp Page unsuccessfully");

			log.info(
					"#############################################DELETE-SubCategoryServlet-END#############################################");
		}

	}

}
