<%@page import="com.couponsworld.dto.Offer"%>
<%@page import="com.couponsworld.enums.CashBackMode"%>
<%@page import="com.couponsworld.dto.UsabilityStatus"%>
<%@page import="com.couponsworld.apiresults.ResultantUsabilityStatus"%>
<%@page import="com.couponsworld.dto.UserPlatform"%>
<%@page import="com.couponsworld.apiresults.ResultantUserPlatform"%>
<%@page import="com.couponsworld.dto.UserType"%>
<%@page import="com.couponsworld.dto.SubCategory"%>
<%@page import="com.couponsworld.apiresults.ResultantSubCategory"%>
<%@page import="com.couponsworld.dto.Category"%>
<%@page import="com.couponsworld.apiresults.ResultantCategory"%>
<%@page import="com.couponsworld.dto.Company"%>
<%@page import="com.couponsworld.apiresults.ResultantCompany"%>
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
	<br />

	<center>
		<br /> <br />
		<%
			try {
				if (request.getAttribute("status") != null) {
					if (request.getAttribute("status").equals(Status.SUCCESS)) {

						//JsonObject jsonObject = new JsonObject(request.getAttribute("response"));
						//gson.fromJson(request.getAttribute("response"), ResultantUserType.class);
						//out.println(request.getAttribute("allCompanies"));

						String offerReceivedString = request.getParameter("offerSelected").toString();
						String[] offerReceivedAttributes = offerReceivedString.split("-");
						Offer offer = new Offer();
						offer.setOfferId(Long.parseLong(offerReceivedAttributes[0]));
						offer.setCashBack(Long.parseLong(offerReceivedAttributes[1]));
						offer.setCashBackMode(offerReceivedAttributes[2]);
						offer.setCatchyHeading(offerReceivedAttributes[3]);
						offer.setCategory(offerReceivedAttributes[4]);
						offer.setCompany(offerReceivedAttributes[5]);
						offer.setDescription(offerReceivedAttributes[6]);
						offer.setExpiryDate(offerReceivedAttributes[7]);
						offer.setExtraOfferReference(offerReceivedAttributes[8]);
						offer.setMaximumCashBack(Long.parseLong(offerReceivedAttributes[9]));
						offer.setPromoCode(offerReceivedAttributes[10]);
						offer.setRestrictions(offerReceivedAttributes[11]);
						offer.setStartDate(offerReceivedAttributes[12]);
						offer.setSubCategory(offerReceivedAttributes[13]);
						offer.setTermsAndConditions(offerReceivedAttributes[14]);
						offer.setTotalExtraOffers(Long.parseLong(offerReceivedAttributes[15]));
						offer.setUsabilityStatus(offerReceivedAttributes[16]);
						offer.setUserPlatform(offerReceivedAttributes[17]);
						offer.setUserType(offerReceivedAttributes[18]);
		%>
		<br /> <br />

		<form action="offers" method="post">
			Update Company :<input type="text" name="updatedCompany"
				value="<%=offer.getCompany()%>"><br /> <br /> Update
			Category : <input type="text" name="updatedCategory"
				value="<%=offer.getCategory()%>"> <br /> <br /> Update
			SubCategory :<input type="text" name="updatedSubCategory"
				value="<%=offer.getSubCategory()%>"> <br /> <br /> Update
			UserType :<input type="text" name="updatedUserType"
				value="<%=offer.getUserType()%>"> <br /> <br />Update User
			Platform : <input type="text" name="updatedUserPlatform"
				value="<%=offer.getUserPlatform()%>"> <br /> <br /> Update
			Usability Status :<input type="text" name="updatedUsabilityStatus"
				value="<%=offer.getUsabilityStatus()%>"> <br /> <br />
			Update cashback : <input type="text" name="updatedCashBack"
				value="<%=offer.getCashBack()%>"> <br /> <br /> Update
			cash back Mode : <input type="text" name="updatedCashBackMode"
				value="<%=offer.getCashBackMode()%>"> <br /> <br />Enter
			the description :
			<textarea cols="80" rows="10" name="updatedDescription"></textarea>
			<br /> <br /> Enter the termsAndConditions :
			<textarea cols="80" rows="10" name="termsAndConditions"></textarea>
			<br /> <br /> Enter the promoCode :<input type="text"
				name="promoCode"> <br /> <br /> Enter the startDate :<input
				type="date" name="startDate"> <br /> <br /> Enter the
			expiryDate :<input type="date" name="expiryDate"> <br /> <br />
			Enter the extraOfferReference :<input type="text"
				name="extraOfferReference"> <br /> <br /> Enter the
			totalExtraOffers :<input type="text" name="totalExtraOffers">
			<br /> <br /> Enter the restrictions :
			<textarea cols="80" rows="10" name="restrictions"></textarea>
			<br /> <br /> Enter the Catchy Heading :
			<textarea cols="80" rows="10" name="catchyHeading"></textarea>
			<br /> <br /> Enter the maximumCashBack :<input type="text"
				name="maximumCashBack"> <br /> <br /> <input type="submit"
				value="Add Offer">
		</form>
		<%
			} else if (request.getAttribute("errors") != null) {
						List<Error> errors = (ArrayList<Error>) request.getAttribute("errors");
						for (Error e : errors) {
							out.println(e.getErrorName());
						}
					}
				}
				request.removeAttribute("status");
				request.removeAttribute("errors");
			} catch (Exception e) {
				out.println(e.getMessage());
				e.printStackTrace();
			}
		%>


	</center>

</body>
</html>