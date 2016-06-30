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

						Gson gson = new Gson();

						//JsonObject jsonObject = new JsonObject(request.getAttribute("response"));
						//gson.fromJson(request.getAttribute("response"), ResultantUserType.class);
						//out.println(request.getAttribute("allCompanies"));

						ResultantCompany resultantCompany = gson
								.fromJson(request.getAttribute("allCompanies").toString(), ResultantCompany.class);
						List<Company> allCompanies = (ArrayList<Company>) resultantCompany.getCompanies();

						//out.println(resultantCompany.getCompanies().get(0).getCompanyName());
						ResultantCategory resultantCategory = gson
								.fromJson(request.getAttribute("allCategories").toString(), ResultantCategory.class);
						List<Category> allCategories = (ArrayList<Category>) resultantCategory.getCategories();

						ResultantSubCategory resultantSubCategory = gson.fromJson(
								request.getAttribute("allSubCategories").toString(), ResultantSubCategory.class);
						List<SubCategory> allSubCategories = resultantSubCategory.getSubCategories();

						ResultantUserType resultantUserType = gson
								.fromJson(request.getAttribute("allUserTypes").toString(), ResultantUserType.class);
						List<UserType> allUserTypes = resultantUserType.getUserType();

						ResultantUserPlatform resultantUserPlatform = gson.fromJson(
								request.getAttribute("allUserPlatforms").toString(), ResultantUserPlatform.class);
						List<UserPlatform> allUserPlatforms = resultantUserPlatform.getUserPlatform();

						ResultantUsabilityStatus resultantUsabilityStatus = gson.fromJson(
								request.getAttribute("allUsabilityStatuses").toString(),
								ResultantUsabilityStatus.class);
						List<UsabilityStatus> allUsabilityStatuses = resultantUsabilityStatus.getUsabilityStatus();
		%>
		<br /> <br />

		<form action="#">
			Select the company : <select name="company">
				<%
					if (resultantCompany.getCompanies() != null) {
									for (Company company : resultantCompany.getCompanies()) {
										//out.println(company.getCompanyName());
				%>
				<option value="<%=company.getCompanyName()%>">
					<%=company.getCompanyName()%>
				</option>
				<%
					}
								}
				%>
			</select><br /> <br />Select the category : <select name="category">
				<%
					if (resultantCategory.getCategories() != null) {
									for (Category category : resultantCategory.getCategories()) {
										//out.println(category.getCategoryName());
				%>
				<option value="<%=category.getCategoryName()%>">
					<%=category.getCategoryName()%>
				</option>
				<%
					}
								}
				%>
			</select> <br /> <br />Select the Sub Category : <select name="subCategory">
				<%
					if (resultantSubCategory.getSubCategories() != null) {
									for (SubCategory subCategory : resultantSubCategory.getSubCategories()) {
										//out.println(category.getCategoryName());
				%>
				<option value="<%=subCategory.getSubCategoryName()%>">
					<%=subCategory.getSubCategoryName()%>
				</option>
				<%
					}
								}
				%>
			</select> <br /> <br />Select the User Type : <select name="userType">
				<%
					if (resultantUserType.getUserType() != null) {
									for (UserType userType : resultantUserType.getUserType()) {
										//out.println(category.getCategoryName());
				%>
				<option value="<%=userType.getUserTypeName()%>">
					<%=userType.getUserTypeName()%>
				</option>
				<%
					}
								}
				%>
			</select> <br /> <br />Select the User Platform : <select
				name="userPlatform">
				<%
					if (resultantUserPlatform.getUserPlatform() != null) {
									for (UserPlatform userPlatform : resultantUserPlatform.getUserPlatform()) {
										//out.println(category.getCategoryName());
				%>
				<option value="<%=userPlatform.getUserPlatformName()%>">
					<%=userPlatform.getUserPlatformName()%>
				</option>
				<%
					}
								}
				%>
			</select> <br /> <br />Select the Usability Status : <select
				name="usabilityStatus">
				<%
					if (resultantUsabilityStatus.getUsabilityStatus() != null) {
									for (UsabilityStatus usabilityStatus : resultantUsabilityStatus.getUsabilityStatus()) {
										//out.println(category.getCategoryName());
				%>
				<option value="<%=usabilityStatus.getUsabilityStatusName()%>">
					<%=usabilityStatus.getUsabilityStatusName()%>
				</option>
				<%
					}
								}
				%>
			</select> <br /> <br /> Enter the cashback : <input type="text"
				name="cashBack"> <br /> <br /> Enter the cashBackMode : <input
				type="text" name="cashBackMode"> <br /> <br />Enter the
			description :
			<textarea cols="80" rows="10" name="description"></textarea>
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