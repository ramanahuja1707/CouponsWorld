<%@page import="com.couponsworld.apiresults.Error"%>
<%@page import="java.util.ArrayList"%>
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
		<%
			out.println("Error in loading page...");
			//For Updation of the User Type
			if (request.getAttribute("status") != null) {
				if (request.getAttribute("status").equals(Status.FAILURE)) {
					List<Error> errors = (ArrayList<Error>) request.getAttribute("errors");
					for (Error e : errors) {
						out.println(e.getErrorName() + "<br/>");
					}
				}
				request.removeAttribute("errors");
				request.removeAttribute("status");
			}
		%>
	</center>
</body>
</html>