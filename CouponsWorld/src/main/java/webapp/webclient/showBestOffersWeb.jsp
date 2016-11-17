<%@page import="com.couponsworld.dto.Offer"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@page import="com.couponsworld.apiresults.ResultantBestOffer"%>
<%@page import="com.google.gson.Gson"%>
<%@page import="com.couponsworld.enums.Status"%>
<%@page import="com.couponsworld.utilities.Constants"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Coupons World</title>
</head>
<body>

	<%
		if (request.getAttribute("status") != null) {
			if (request.getAttribute("status").equals(Status.SUCCESS)) {
				out.println("<b>Response successfully got from input provided by user : </b>");
				Gson allBestOffersGson = new Gson();
				ResultantBestOffer allBestOffers = allBestOffersGson
						.fromJson(request.getAttribute("allBestOffers").toString(), ResultantBestOffer.class);
				Map<Long, List<Offer>> mapOfBestOffers = allBestOffers.getMapOfBestOffer();
				if (mapOfBestOffers != null) {
					for (Map.Entry<Long, List<Offer>> allBestOfferEntries : mapOfBestOffers.entrySet()) {
						out.println("<br><b>Cashback :</b> " + allBestOfferEntries.getKey());
						out.println("<br>");
						out.println("<b>Offers :</b>");
						if (allBestOfferEntries.getValue().size() > 0
								&& allBestOfferEntries.getValue().size() == 1) {
							out.println("<br>" + allBestOfferEntries.getValue().get(0).getDescription());
						} else {
							for (Offer offer : allBestOfferEntries.getValue()) {
								out.println("<br>" + offer.getDescription());
							}
						}
					}
				} else {
					out.println("No Cashback offers found for input supplied...");
				}
				out.println("<br><br><br><br>");

				List<Offer> allFeasibleBestOffers = allBestOffers.getBestOffers();
				out.println("You can prefer the given below offers : ");
				if (allFeasibleBestOffers.size() > 0) {
					for (Offer offer : allFeasibleBestOffers) {
						out.println(offer.getDescription());
						out.println("<br>");
					}
				}
				//out.println(request.getAttribute("allBestOffers"));
			} else {
				out.println("Status is Failure...");
			}
		} else {
			out.println("Status is null...");
		}
	%>
</body>
</html>