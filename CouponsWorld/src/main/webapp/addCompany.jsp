<%@page import="com.couponsworld.apiresults.ResultantCompany"%>
<%@page import="com.couponsworld.apiresults.ResultantCategory"%>
<%@page import="com.couponsworld.apiresults.ResultantSubCategory"%>
<%@page import="com.couponsworld.apiresults.ResultantUsabilityStatus"%>
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
		<form action="companies" method="post">
			Enter Company :<input type="text" name="company"> <br /> <br />
			Enter Company Description :
			<textarea name="companyDescription" rows="10" cols="80"></textarea>
			<br /> <br /> Enter Company Logo Name : <input type="text"
				name="companyLogoName"> <br /> <br /> <input type="submit"
				name="Add" value="Add">

		</form>
		<br /> <br />
		<%
			try {
				if (request.getAttribute("status") != null) {
					if (request.getAttribute("status").equals(Status.SUCCESS)) {
						Gson gson = new Gson();

						//JsonObject jsonObject = new JsonObject(request.getAttribute("response"));
						//gson.fromJson(request.getAttribute("response"), ResultantUserType.class);

						ResultantCompany resultantCompany = gson.fromJson(request.getAttribute("response").toString(),
								ResultantCompany.class);

						if (resultantCompany.getStatus().equals(Status.SUCCESS)) {
							out.println("Company : " + "<b>" + resultantCompany.getCompanies().get(0).getCompanyName()
									+ "</b>" + " added Successfully");
							out.println(resultantCompany.getCompanies().get(0).getCompanyName());
						} else if (resultantCompany.getStatus().equals(Status.FAILURE)) {
							List<Error> errorList = (List<Error>) resultantCompany.getErrors();
							for (Error e : errorList) {
								out.println(e.getErrorName());
							}
						}
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