package com.couponsworld.servlets.webclient;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.couponsworld.apiresults.Error;
import com.couponsworld.enums.Errors;
import com.couponsworld.enums.Status;
import com.couponsworld.utilities.ApiAuthenticationService;
import com.couponsworld.utilities.Constants;
import com.couponsworld.utilities.HttpUrlService;

public class SearchBestOffers extends HttpServlet {
	// declaration of logger
	private static final Logger log = Logger.getLogger(SearchBestOffers.class.getName());

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			log.info("-------------------------SearchBestOffers - START-----------------------------");
			// TODO Auto-generated method stub
			PrintWriter out = resp.getWriter();
			String category = req.getParameter("category");
			String subCategory = req.getParameter("subCategory");
			String userType = req.getParameter("userType");
			String userPlatform = req.getParameter("userPlatform");
			String amountToSpend = req.getParameter("amountToSpend");
			log.info(req.getParameter("category"));
			log.info(req.getParameter("subCategory"));
			log.info(req.getParameter("userType"));
			log.info(req.getParameter("userPlatform"));
			log.info(req.getParameter("amountToSpend"));
			log.info("Fetching all Data required to load showBestOffers.jsp Page ...");

			String allBestOffersURL = (Constants.BEST_OFFERS_URL + "?category=" + category + "&company=&subCategory="
					+ subCategory + "&userType=" + userType + "&userPlatform=" + userPlatform
					+ "&usabilityStatus=&amountToSpend=" + amountToSpend).replaceAll(" ", "%20");
			log.info("All Best Offers URL Created as per the parameters passed :" + allBestOffersURL);

			HttpURLConnection allBestOffersUrlConnection = HttpUrlService.getHttpURLConnection(allBestOffersURL, "GET",
					"",
					new ApiAuthenticationService().generateAuthorizationKey(Constants.AUTH_USERNAME,
							Constants.AUTH_PASSWORD),
					Constants.DO_OUTPUT_FLAG_TRUE, "", Constants.AUTH_USERNAME, Constants.AUTH_PASSWORD,
					Constants.ADMIN_ACCESS_ID, Constants.WEB_ACCESS_PLATFORM);
			String allBestOffers = HttpUrlService.readHttpUrlResponse(allBestOffersUrlConnection.getInputStream());
			log.info("All Best Offers response from Rest api of coupons world :" + allBestOffers);
			if (allBestOffers == null || allBestOffers.equals("")) {
				log.info("Required Data All Best Offers Fetching Error Occured...FAILURE");
				// setting the Status of Get method execution - Failure
				log.info("All Best Offers in Response Object : " + allBestOffers);
				req.setAttribute("status", Status.FAILURE);
				req.setAttribute("errors",
						getErrorsList("Error in Fetching All Best Offers...", Errors.GENERAL_ERROR.getErrorCode()));
				RequestDispatcher requestDispatcher = req.getRequestDispatcher("/index.jsp");
				requestDispatcher.forward(req, resp);
			} else {
				log.info("Required Data Fetched Successfully...");
				log.info("Adding all the data to the response object...");
				req.setAttribute("status", Status.SUCCESS);
				log.info("All Best Offers in Response Object : " + allBestOffers);
				req.setAttribute("allBestOffers", allBestOffers);
				log.info("Redirecting the control to load the showBestOffers.jsp Page...");
				RequestDispatcher requestDispatcher = req.getRequestDispatcher("/showBestOffers.jsp");
				requestDispatcher.forward(req, resp);
			}
		} catch (Exception e) {

			// TODO Auto-generated catch block
			log.info("Error Occured :" + e.getMessage());
			e.printStackTrace();
			req.setAttribute("status", Status.FAILURE);
			req.setAttribute("errors", getErrorsList(e.getMessage(), Errors.GENERAL_ERROR.getErrorCode()));
			RequestDispatcher requestdDispatcher = req.getRequestDispatcher("/index.jsp");
			requestdDispatcher.forward(req, resp);
		}

	}

	private List<Error> getErrorsList(String errorName, long errorCode) {

		// creating error object
		Error error = new Error();
		error.setErrorCode(errorCode);
		error.setErrorName(errorName);

		// wrapping the error into errors list
		List<Error> errors = new ArrayList<>();
		errors.add(error);

		// returning errors list
		return errors;

	}

}
