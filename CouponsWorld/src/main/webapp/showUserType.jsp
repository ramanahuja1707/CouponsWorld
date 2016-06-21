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
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="style.css" />
<title>Coupons World</title>
</head>
<body>
	<div id="wrapper">
		<div class="container">
			<div class="header">
				<h1>Coupons World Admin Panel</h1>
			</div>

			<nav>
			<ul>
				<li><a href="#">Home </a>
					<div class="dropdown">
						<ul>
							<li><a href="#">Logout</a></li>
						</ul>
					</div></li>

				<li><a href="#">Offers</a>

					<div class="dropdown">
						<ul>
							<li><a href="#">Add</a></li>
							<li><a href="#">update</a></li>
							<li><a href="#">delete</a></li>
							<li><a href="#">display</a></li>
						</ul>
					</div></li>
				<li><a href="#">Categories</a>
					<div class="dropdown">
						<ul>
							<li><a href="#">Add</a></li>
							<li><a href="#">update</a></li>
							<li><a href="#">delete</a></li>
							<li><a href="#">display</a></li>
						</ul>
					</div></li>
				<li><a href="#">Companies</a>
					<div class="dropdown">
						<ul>
							<li><a href="#">Add</a></li>
							<li><a href="#">update</a></li>
							<li><a href="#">delete</a></li>
							<li><a href="#">display</a></li>
						</ul>
					</div></li>
				<li><a href="#">Sub Categories</a>
					<div class="dropdown">
						<ul>
							<li><a href="#">Add</a></li>
							<li><a href="#">update</a></li>
							<li><a href="#">delete</a></li>
							<li><a href="#">display</a></li>
						</ul>
					</div></li>
				<li><a href="#">Usabilty Status</a>
					<div class="dropdown">
						<ul>
							<li><a href="#">Add</a></li>
							<li><a href="#">update</a></li>
							<li><a href="#">delete</a></li>
							<li><a href="#">display</a></li>
						</ul>
					</div></li>
				<li><a href="#">User Platform</a>
					<div class="dropdown">
						<ul>
							<li><a href="#">Add</a></li>
							<li><a href="#">update</a></li>
							<li><a href="#">delete</a></li>
							<li><a href="#">display</a></li>
						</ul>
					</div></li>
				<li><a href="#">User Type</a>
					<div class="dropdown">
						<ul>
							<li><a href="addUserType.jsp">Add</a></li>
							<li><a href="#">update</a></li>
							<li><a href="#">delete</a></li>
							<li><a href="userType">display</a></li>
						</ul>
					</div></li>
			</ul>
			</nav>
		</div>
	</div>
	<center>
		<br /> <br />
		<%
			try {
				if (request.getAttribute("status") != null) {
					if (request.getAttribute("status").equals(Status.SUCCESS)) {
						Gson gson = new Gson();

						//JsonObject jsonObject = new JsonObject(request.getAttribute("response"));
						//gson.fromJson(request.getAttribute("response"), ResultantUserType.class);

						ResultantUserType resultantUserType = gson.fromJson(request.getAttribute("response").toString(),
								ResultantUserType.class);
						if (resultantUserType.getStatus().equals(Status.SUCCESS)) {
							List<UserType> userTypes = (List<UserType>) resultantUserType.getUserType();
							for (UserType userType : userTypes) {
								out.println(userType.getUserTypeName() + "<br/>");
							}
						} else if (resultantUserType.getStatus().equals(Status.FAILURE)) {
							List<Error> errorList = (List<Error>) resultantUserType.getErrors();
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
				} else {
					System.out.println("Nothing To Display");
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