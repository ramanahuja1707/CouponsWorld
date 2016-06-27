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

import com.couponsworld.enums.Errors;
import com.couponsworld.enums.Status;
import com.couponsworld.utilities.Constants;

@SuppressWarnings("serial")
public class AdminLoginAccessServlet extends HttpServlet {

	// declaration of loggern
	private static final Logger log = Logger.getLogger(AdminLoginAccessServlet.class.getName());

	private static List<com.couponsworld.apiresults.Error> errors = null;
	RequestDispatcher requestDispatcher;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username, password;
		username = request.getParameter("username");
		password = request.getParameter("password");

		log.info("Checking the username and password supplied");

		if (!(username.equals("") || password.equals(""))) {
			log.info("Username and password are not null...");
			// System.out.println("Not null");
			if (Constants.AUTH_PASSWORD.equals(password) && Constants.AUTH_USERNAME.equals(username)) {
				log.info("Username and password are successfully validated..");
				// System.out.println("Correct Credentials ...");

				log.info("Diverting the control to the Admin Panel : adminPanel.jsp");

				HttpSession session = request.getSession(true);
				session.setAttribute("username", username);
				session.setAttribute("password", password);
				response.sendRedirect("adminPanel.jsp");
			} else {
				log.info("Username and password validation failure occured..");
				com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
				error.setErrorCode(Errors.LOGIN_AUTHENTICATION_ERROR.getErrorCode());
				error.setErrorName(Errors.LOGIN_AUTHENTICATION_ERROR.getErrorName());

				errors = new ArrayList<com.couponsworld.apiresults.Error>();
				errors.add(error);

				log.info("Diverting the control to the Login page  : index.jsp");

				request.setAttribute("errors", errors);
				request.setAttribute("status", Status.FAILURE);
				requestDispatcher = request.getRequestDispatcher("/index.jsp");
				requestDispatcher.forward(request, response);
			}
		}

		else {
			log.info("Username and password validation failure occured..: values passsed are empty ");
			com.couponsworld.apiresults.Error error = new com.couponsworld.apiresults.Error();
			error.setErrorCode(Errors.NULL_POINTER_ERROR.getErrorCode());
			error.setErrorName("Username/Password not entered...");

			errors = new ArrayList<com.couponsworld.apiresults.Error>();
			errors.add(error);

			log.info("Diverting the control to the Login page  : index.jsp");

			request.setAttribute("errors", errors);
			request.setAttribute("status", Status.FAILURE);
			requestDispatcher = request.getRequestDispatcher("/index.jsp");
			requestDispatcher.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
