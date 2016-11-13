<%@page import="com.couponsworld.dto.Offer"%>
<%@page import="com.couponsworld.apiresults.ResultantOffer"%>
<%@page import="com.couponsworld.dto.Company"%>
<%@page import="com.couponsworld.dto.Category"%>
<%@page import="com.couponsworld.apiresults.ResultantCompany"%>
<%@page import="com.couponsworld.dto.SubCategory"%>
<%@page import="com.couponsworld.apiresults.ResultantSubCategory"%>
<%@page import="com.couponsworld.enums.Errors"%>
<%@page import="com.couponsworld.dto.UserType"%>
<%@page import="com.couponsworld.apiresults.ResultantFilter"%>
<%@page import="com.google.gson.JsonObject"%>
<%@page import="com.couponsworld.apiresults.ResultantUserType"%>
<%@page import="com.google.gson.Gson"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.couponsworld.apiresults.Error"%>
<%@page import="java.util.List"%>
<%@page import="com.couponsworld.enums.Status"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style>
table {
	width: 50%;
}

table, th, td {
	border: 1px solid black;
	border-collapse: collapse;
}

th, td {
	padding: 5px;
	text-align: left;
}

table#t01 tr:nth-child(even) {
	background-color: #eee;
}

table#t01 tr:nth-child(odd) {
	background-color: #fff;
}

table#t01 th {
	background-color: black;
	color: white;
}
</style>
<script type="text/javascript">
	function submitForm(clickedButton) {
		if (clickedButton.name == "updateButton") {
			document.updateDeleteOfferForm.action = "/offers";
		} else if (clickedButton.name == "deleteButton") {
			document.forms["updateDeleteOfferForm"]["_method"].value = "DELETE";
			document.updateDeleteOfferForm.action = "/offers";
		}
		document.updateDeleteOfferForm.submit();
	}

	function validateUpdateDeleteOfferForm(clickedButton) {

		var offersSelected = document.getElementsByName("offerSelected");

		var offerSelected = null;
		for (var i = 0; i < offersSelected.length; i++) {
			if (offersSelected[i].checked == true) {
				offerSelected = offersSelected[i].value;
			}
		}

		if (offerSelected == null || offerSelected == "") {
			document.getElementById("offerSelectedError").innerHTML = "Please Select a offer ...";
		} else {
			document.getElementById("offerSelectedError").innerHTML = "";
			submitForm(clickedButton);
		}
	}
</script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="style.css" />
<title>Coupons World</title>
</head>
<body>
	<%
		if (session.getAttribute("username") == null && session.getAttribute("password") == null) {
			RequestDispatcher rd = request.getRequestDispatcher("/notInSession.jsp");
			rd.forward(request, response);
		}
	%>
	<jsp:include page="adminPanelHeader.jsp"></jsp:include>
	<center>
		<br />
		<br />
		<form method="get" action="OfferPage">
			<input type="text" value="GET" name="_method" style="display: none;" /><input
				type="text" value="searchOffer.jsp" name="_page"
				style="display: none;" /> <input type="submit"
				value="<< Refine Offers" />
		</form>
		<br /> <br />
		<%
			try {
				if (request.getAttribute("status") != null) {
					if (request.getAttribute("status").equals(Status.SUCCESS)) {
						Gson gson = new Gson();

						//JsonObject jsonObject = new JsonObject(request.getAttribute("response"));
						//gson.fromJson(request.getAttribute("response"), ResultantUserType.class);

						ResultantOffer resultantOffer = gson.fromJson(request.getAttribute("response").toString(),
								ResultantOffer.class);
						if (resultantOffer.getStatus().equals(Status.SUCCESS)) {
							List<Offer> offers = (List<Offer>) resultantOffer.getOffers();
							if (offers != null) {
		%>
		<table>
			<tr>
				<th>Select</th>
				<th>Offer Id</th>
				<th>Company Name</th>
				<th>Category Name</th>
			</tr>



			<form action="#" method="get" name="updateDeleteOfferForm">

				<input type="text" style="display: none;" value="PUT" name="_method">
				<%
					for (Offer offer : offers) {
				%>
				<tr>
					<td><input type="radio"
						value="<%=offer.getOfferId() + "-" + offer.getCashBack() + "-"
											+ offer.getCashBackMode() + "-" + offer.getCatchyHeading() + "-"
											+ offer.getCategory() + "-" + offer.getCompany() + "-"
											+ offer.getDescription() + "-" + offer.getExpiryDate() + "-"
											+ offer.getExtraOfferReference() + "-" + offer.getMaximumCashBack() + "-"
											+ offer.getPromoCode() + "-" + offer.getRestrictions() + "-"
											+ offer.getStartDate() + "-" + offer.getSubCategory() + "-"
											+ offer.getTermsAndConditions() + "-" + offer.getTotalExtraOffers() + "-"
											+ offer.getUsabilityStatus() + "-" + offer.getUserPlatform() + "-"
											+ offer.getUserType()%>"
						name="offerSelected" id="offerSelected" /></td>

					<input type="text" style="visibility: hidden; display: none;"
						value="<%offer.getOfferId();%>" name="offerSelectedId" />
					<td>
						<%
							out.println(offer.getOfferId() + "<br/></td><td>" + offer.getCompany() + "</td><td>"
															+ offer.getCategory() + "</td></tr>");
												}
						%><br> <br /> <input type="button" value="Update Offer"
						name="updateButton" style="display: none;"
						onclick="validateUpdateDeleteOfferForm(this)" /> <br /> <br />
						<input type="button" value="Delete Offer" name="deleteButton"
						onclick="validateUpdateDeleteOfferForm(this)" /> <br /> <br />
			</form>
		</table>
		<%
			} else {
								out.println("No Offers Found...");
							}
						} else if (resultantOffer.getStatus().equals(Status.FAILURE)) {
							List<Error> errorList = (List<Error>) resultantOffer.getErrors();
							for (Error e : errorList) {
								out.println(e.getErrorName() + "<br/>");
							}
						}
					} else if (request.getAttribute("errors") != null) {
						out.println("Getting Errors in Response");
						//out.println(request.getAttribute("response").toString());
						List<Error> errors = (ArrayList<Error>) request.getAttribute("errors");
						for (Error e : errors) {
							out.println(e.getErrorName() + "<br/>");
						}
					}
					request.removeAttribute("response");
					request.removeAttribute("status");
					request.removeAttribute("errors");
				} else {
					System.out.println("Sorry, Nothing To Display");
				}

			} catch (Exception e) {
				e.printStackTrace();
				out.println(e.getMessage());
			}
		%>
		<p id="offerSelectedError" style="color: red;"></p>
	</center>

</body>
</html>