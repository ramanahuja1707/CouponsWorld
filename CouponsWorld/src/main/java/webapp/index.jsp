<%@page import="com.couponsworld.dto.CategorySubCategoryMapping"%>
<%@page
	import="com.couponsworld.apiresults.ResultantCategorySubCategoryMapping"%>
<%@page import="com.couponsworld.enums.OfferTypeEnum"%>
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
	<br />

	<center>
		<br /> <br />
		<%
			try {

				if (request.getAttribute("status") != null) {

					out.println(request.getAttribute("status"));
					if (request.getAttribute("status").equals(Status.SUCCESS)) {

						Gson gson = new Gson();

						//JsonObject jsonObject = new JsonObject(request.getAttribute("response"));
						//gson.fromJson(request.getAttribute("response"), ResultantUserType.class);
						//out.println(request.getAttribute("allCompanies"));

						ResultantUserType resultantUserType = gson
								.fromJson(request.getAttribute("allUserTypes").toString(), ResultantUserType.class);
						List<UserType> allUserTypes = resultantUserType.getUserType();

						ResultantUserPlatform resultantUserPlatform = gson.fromJson(
								request.getAttribute("allUserPlatforms").toString(), ResultantUserPlatform.class);
						List<UserPlatform> allUserPlatforms = resultantUserPlatform.getUserPlatform();

						ResultantCategorySubCategoryMapping resultantCategorySubCategoryMapping = gson.fromJson(
								request.getAttribute("allCategorySubCategoryMappings").toString(),
								ResultantCategorySubCategoryMapping.class);
						List<CategorySubCategoryMapping> allCategorySubCategoryMappings = resultantCategorySubCategoryMapping
								.getCategorySubCategoryMappings();
		%>
		<br /> <br />

		<form action="searchBestOffers" method="get">
			<br /> <br /> Select A Sub Category<select name="category"
				style="width: 150px">
				<%
					if (resultantCategorySubCategoryMapping.getCategorySubCategoryMappings() != null) {
									for (CategorySubCategoryMapping categorySubCategoryMapping : allCategorySubCategoryMappings) {
										//out.println(category.getCategoryName());
				%>
				<option value="<%=categorySubCategoryMapping.getCategoryName()%>">
					<%=categorySubCategoryMapping.getCategoryName()%>
				</option>
				<%
					}
								}
				%>
				<option value="" selected="selected"></option>
			</select>Select A Sub Category <select name="subCategory" style="width: 150px">
				<%
					if (resultantCategorySubCategoryMapping.getCategorySubCategoryMappings() != null) {
									for (CategorySubCategoryMapping categorySubCategoryMapping : allCategorySubCategoryMappings) {
										//out.println(category.getCategoryName());
										String[] subCategoryNames = categorySubCategoryMapping.getSubCategoryNames().split(",");
										for (int j = 0; j < subCategoryNames.length; j++) {
				%>
				<option value="<%=subCategoryNames[j]%>">
					<%=subCategoryNames[j]%>
				</option>
				<%
					}
									}
								}
				%>
				<option value="" selected="selected"></option>
			</select><br /> <br />Select the User Type : <select name="userType">
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
				<option value="" selected="selected"></option>
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
				<option value="" selected="selected"></option>
			</select> <br /> <br /> <br /> <br />Enter the Amount to spend :<input
				type="text" name="amountToSpend"><br /> <br /> <input
				type="submit" value="Search Offer">
		</form>
	</center>

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

	<a href="/adminLoginPanel.jsp">Admin Login</a>
</body>
</html>