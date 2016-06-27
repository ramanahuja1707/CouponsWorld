package com.couponsworld.servlets;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.couponsworld.enums.Status;

@SuppressWarnings("serial")
public class AdminLogoutAccessServlet extends HttpServlet {

	// declaration of logger
	private static final Logger log = Logger.getLogger(AdminLogoutAccessServlet.class.getName());

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.info("Getting existing session....");
		HttpSession session = req.getSession(false);
		if (session != null) {
			log.info("Got existing session successfully...");

			session.removeAttribute("username");
			session.removeAttribute("password");

			log.info("Removing the credentials from session object...and making it null");
			session = null;

			log.info("Diverting the Control to homePage : index.jsp");
			req.setAttribute("logoutStatus", Status.SUCCESS);
			req.setAttribute("errors", null);
			RequestDispatcher rd = req.getRequestDispatcher("/index.jsp");
			rd.forward(req, resp);
		} else {
			log.info("Session not configured ...");
			log.info("Diverting the control to session expired page : notInSession.jsp");
			RequestDispatcher rd = req.getRequestDispatcher("/notInSession.jsp");
			rd.forward(req, resp);
		}
	}
}
