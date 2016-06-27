<%@page import="com.couponsworld.dto.UserPlatform"%>
<%@page import="com.couponsworld.apiresults.ResultantUserPlatform"%>
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
<script type="text/javascript">
	function submitForm(clickedButton) {
		if (clickedButton.name == "updateButton") {
			document.updateUserPlatformForm.action = "/userPlatform";
		} else if (clickedButton.name == "deleteButton") {
			document.forms["updateUserPlatformForm"]["_method"].value = "DELETE";
			document.updateUserPlatformForm.action = "/userPlatform";
		}
		document.updateUserPlatformForm.submit();
	}

	function validateUpdateUserPlatformForm(clickedButton) {

		var userPlatformsSelected = document
				.getElementsByName("userPlatformSelected");

		var userPlatformSelected = null;
		for (var i = 0; i < userPlatformsSelected.length; i++) {
			if (userPlatformsSelected[i].checked == true) {
				userPlatformSelected = userPlatformsSelected[i].value;
			}
		}
		//var userTypeSelected = document.forms["updateUserTypeForm"]["userTypeSelected"].checked;
		if (userPlatformSelected == null || userPlatformSelected == "") {
			document.getElementById("userPlatformSelectedError").innerHTML = "Please Select a User Platform ...";
		} else if (document.getElementById("updatedUserPlatformSelected").value == "") {
			document.getElementById("userPlatformSelectedError").innerHTML = "Please specify updated value...";
		} else {
			document.getElementById("userPlatformSelectedError").innerHTML = "";
			submitForm(clickedButton);
		}
	}
	function validateDeleteUserPlatformForm(clickedButton) {

		var userPlatformsSelected = document
				.getElementsByName("userPlatformSelected");

		var userPlatformSelected = null;
		for (var i = 0; i < userPlatformsSelected.length; i++) {
			if (userPlatformsSelected[i].checked == true) {
				userPlatformSelected = userPlatformsSelected[i].value;
			}
		}
		//var userTypeSelected = document.forms["updateUserTypeForm"]["userTypeSelected"].checked;
		if (userPlatformSelected == null || userPlatformSelected == "") {
			document.getElementById("userPlatformSelectedError").innerHTML = "Please Select a User Platform ...";
		} else {
			document.getElementById("userPlatformSelectedError").innerHTML = "";
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

						ResultantUserPlatform resultantUserPlatform = gson
								.fromJson(request.getAttribute("response").toString(), ResultantUserPlatform.class);
						if (resultantUserPlatform.getStatus().equals(Status.SUCCESS)) {
							List<UserPlatform> userPlatforms = (List<UserPlatform>) resultantUserPlatform
									.getUserPlatform();
							if (userPlatforms != null) {
		%><form action="#" method="get" name="updateUserPlatformForm">
			<input type="text" style="display: none;" value="PUT" name="_method">
			<%
				for (UserPlatform userPlatform : userPlatforms) {
			%>
			<input type="radio"
				value="<%=userPlatform.getUserPlatformName() + "-"
											+ userPlatform.getUserPlatformId()%>"
				name="userPlatformSelected" id="userPlatformSelected" /> <input
				type="text" style="visibility: hidden; display: none;"
				value="<%userPlatform.getUserPlatformId();%>"
				name="userPlatformSelectedId" />
			<%
				out.println(userPlatform.getUserPlatformName() + "<br/>");
									}
			%>
			<br /> Please enter updated value of User Platform :<input
				type="text" name="updatedUserPlatformSelected"
				id="updatedUserPlatformSelected" /><br /> <br /> <input
				type="button" value="Update User Platform" name="updateButton"
				onclick="validateUpdateUserPlatformForm(this)" /> <br /> <br /> <input
				type="button" value="Delete User Platform" name="deleteButton"
				onclick="validateDeleteUserPlatformForm(this)" />
		</form>
		<%
			} else {
								out.println("No User Platforms Found...");
							}
						} else if (resultantUserPlatform.getStatus().equals(Status.FAILURE)) {
							List<Error> errorList = (List<Error>) resultantUserPlatform.getErrors();
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
		<p id="userPlatformSelectedError" style="color: red;"></p>
	</center>
</body>
</html>