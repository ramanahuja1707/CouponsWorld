<%@page import="com.couponsworld.apiresults.Error"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
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
		if (request.getAttribute("errors") != null) {
			out.println("Errors Occured :");
			List<Error> errors = (ArrayList<Error>) request.getAttribute("errors");
			for (Error e : errors) {
				out.println(e.getErrorName() + "<br/>");
			}
		}
	%>
</body>
</html>