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
		<form action="usabilityStatus" method="post">
			Usability Status :<input type="text" name="usabilityStatus">
			<br /> <br /> <input type="submit" name="Add" value="Add">

		</form>
		<br /> <br />
		<%
			try {
				if (request.getAttribute("status") != null) {
					if (request.getAttribute("status").equals(Status.SUCCESS)) {
						Gson gson = new Gson();

						//JsonObject jsonObject = new JsonObject(request.getAttribute("response"));
						//gson.fromJson(request.getAttribute("response"), ResultantUserType.class);

						ResultantUsabilityStatus resultantUsabilityStatus = gson
								.fromJson(request.getAttribute("response").toString(), ResultantUsabilityStatus.class);

						if (resultantUsabilityStatus.getStatus().equals(Status.SUCCESS)) {
							out.println("Usability Status : " + "<b>"
									+ resultantUsabilityStatus.getUsabilityStatus().get(0).getUsabilityStatusName()
									+ "</b>" + " added Successfully");
							out.println(resultantUsabilityStatus.getUsabilityStatus().get(0).getUsabilityStatusName());
						} else if (resultantUsabilityStatus.getStatus().equals(Status.FAILURE)) {
							List<Error> errorList = (List<Error>) resultantUsabilityStatus.getErrors();
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