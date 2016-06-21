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
			UserType userType = new UserType();

			userType.setUserTypeName(req.getParameter("userType"));
			List<Error> errors = ValidationService.validateUserType(userType);
			if (errors == null) {
				// System.out.println(errors);

				// converting the Object Of UserType to Json Format
				Gson gson = new Gson();
				String userTypeJsonString = gson.toJson(userType);
				System.out.println(userTypeJsonString);

				HttpURLConnection httpUrlConnection = HttpUrlService.getHttpURLConnection(Constants.USERTYPE_URL,
						req.getMethod(),
						CONTENT_TYPE_JSON, "Basic " + new ApiAuthenticationService()
								.generateAuthorizationKey("innovate.garr", "garr.innovate"),
						Constants.DO_OUTPUT_FLAG_TRUE, userTypeJsonString);
				String urlResponse = HttpUrlService.readHttpUrlResponse(httpUrlConnection.getInputStream());

				System.out.println(urlResponse);

				req.setAttribute("response", urlResponse.toString());
				req.setAttribute("status", Status.SUCCESS);
				RequestDispatcher requestDispatcher = req.getRequestDispatcher("/addUserType.jsp");
				requestDispatcher.forward(req, resp);
			} else {
				req.setAttribute("status", Status.FAILURE);
				req.setAttribute("errors", errors);
				RequestDispatcher requestDispatcher = req.getRequestDispatcher("/addUserType.jsp");
				requestDispatcher.forward(req, resp);
			}
		} catch (IOException ioe) {
			// creation of Error
			Error error = new Error();
			error.setErrorCode(Errors.GENERAL_ERROR.getErrorCode());
			error.setErrorName(ioe.getMessage());

			// creation of Error List and embedding error into it
			List<Error> errors = new ArrayList<>();
			errors.add(error);

			req.setAttribute("status", Status.FAILURE);
			req.setAttribute("errors", errors);
			RequestDispatcher requestDispatcher = req.getRequestDispatcher("/addUserType.jsp");
			requestDispatcher.forward(req, resp);
		} catch (MissingMandatoryParametersException mmpe) {

			// creation of Error
			Error error = new Error();
			error.setErrorCode(Errors.GENERAL_ERROR.getErrorCode());
			error.setErrorName(mmpe.getMessage());

			// creation of Error List and embedding error into it
			List<Error> errors = new ArrayList<>();
			errors.add(error);

			req.setAttribute("status", Status.FAILURE);
			req.setAttribute("errors", errors);
			RequestDispatcher requestDispatcher = req.getRequestDispatcher("/addUserType.jsp");
			requestDispatcher.forward(req, resp);
		} catch (Exception e) {
			// creation of Error
			Error error = new Error();
			error.setErrorCode(Errors.GENERAL_ERROR.getErrorCode());
			error.setErrorName(e.getMessage());

			// creation of Error List and embedding error into it
			List<Error> errors = new ArrayList<>();
			errors.add(error);

			req.setAttribute("status", Status.FAILURE);
			req.setAttribute("errors", errors);
			RequestDispatcher requestDispatcher = req.getRequestDispatcher("/addUserType.jsp");
			requestDispatcher.forward(req, resp);
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {

			HttpURLConnection httpUrlConnection = HttpUrlService
					.getHttpURLConnection(Constants.USERTYPE_URL, req.getMethod(),
							"text/html", "Basic " + new ApiAuthenticationService()
									.generateAuthorizationKey("innovate.garr", "garr.innovate"),
							Constants.DO_OUTPUT_FLAG_TRUE, null);
			String urlResponse = HttpUrlService.readHttpUrlResponse(httpUrlConnection.getInputStream());

			System.out.println(urlResponse);

			req.setAttribute("response", urlResponse.toString());
			req.setAttribute("status", Status.SUCCESS);
			RequestDispatcher requestDispatcher = req.getRequestDispatcher("/showUserType.jsp");
			requestDispatcher.forward(req, resp);

		} catch (IOException ioe) {
			// creation of Error
			Error error = new Error();
			error.setErrorCode(Errors.GENERAL_ERROR.getErrorCode());
			error.setErrorName(ioe.getMessage());

			// creation of Error List and embedding error into it
			List<Error> errors = new ArrayList<>();
			errors.add(error);

			req.setAttribute("status", Status.FAILURE);
			req.setAttribute("errors", errors);
			RequestDispatcher requestDispatcher = req.getRequestDispatcher("/showUserType.jsp");
			requestDispatcher.forward(req, resp);
		} catch (MissingMandatoryParametersException mmpe) {

			// creation of Error
			Error error = new Error();
			error.setErrorCode(Errors.GENERAL_ERROR.getErrorCode());
			error.setErrorName(mmpe.getMessage());

			// creation of Error List and embedding error into it
			List<Error> errors = new ArrayList<>();
			errors.add(error);

			req.setAttribute("status", Status.FAILURE);
			req.setAttribute("errors", errors);
			RequestDispatcher requestDispatcher = req.getRequestDispatcher("/showUserType.jsp");
			requestDispatcher.forward(req, resp);
		} catch (Exception e) {
			// creation of Error
			Error error = new Error();
			error.setErrorCode(Errors.GENERAL_ERROR.getErrorCode());
			error.setErrorName(e.getMessage());

			// creation of Error List and embedding error into it
			List<Error> errors = new ArrayList<>();
			errors.add(error);

			req.setAttribute("status", Status.FAILURE);
			req.setAttribute("errors", errors);
			RequestDispatcher requestDispatcher = req.getRequestDispatcher("/showUserType.jsp");
			requestDispatcher.forward(req, resp);
		}

	}

}
