<%@page import="com.couponsworld.apiresults.Error"%>
<%@page import="com.couponsworld.enums.Status"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Coupons World</title>
</head>
<body>

	<form action="accessAdmin" method="post">
		<table>
			<tr>
				<td>UserName</td>
				<td><input type="text" name="username"></td>
			</tr>
			<tr>
				<td>Password</td>
				<td><input type="password" name="password"></td>
			</tr>
			<tr>
				<td><input type="submit" value="Login"></td>
			</tr>
		</table>
	</form>

	<%
		if (request.getAttribute("status") != null) {
			if (request.getAttribute("status").equals(Status.FAILURE)) {
				out.println("Errors Occured : ");
				List<Error> errors = (ArrayList<Error>) request.getAttribute("errors");
				for (Error e : errors) {
					out.println(e.getErrorName() + "<br/>");
				}

			}
			request.removeAttribute("errors");
			request.removeAttribute("status");
		}
		if (request.getAttribute("logoutStatus") != null) {
			if (request.getAttribute("logoutStatus").equals(Status.SUCCESS)) {
				out.println("Logout Successfully..");
			}
		}
	%>
</body>
</html>