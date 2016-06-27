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
			document.updateUserTypeForm.action = "/userType";
		} else if (clickedButton.name == "deleteButton") {
			document.forms["updateUserTypeForm"]["_method"].value = "DELETE";
			document.updateUserTypeForm.action = "/userType";
		}
		document.updateUserTypeForm.submit();
	}

	function validateUpdateUserTypeForm(clickedButton) {

		var userTypesSelected = document.getElementsByName("userTypeSelected");

		var userTypeSelected = null;
		for (var i = 0; i < userTypesSelected.length; i++) {
			if (userTypesSelected[i].checked == true) {
				userTypeSelected = userTypesSelected[i].value;
			}
		}
		//var userTypeSelected = document.forms["updateUserTypeForm"]["userTypeSelected"].checked;
		if (userTypeSelected == null || userTypeSelected == "") {
			document.getElementById("userTypeSelectedError").innerHTML = "Please Select a User Type ...";
		} else if (document.getElementById("updatedUserTypeSelected").value == "") {
			document.getElementById("userTypeSelectedError").innerHTML = "Please specify updated value...";
		} else {
			document.getElementById("userTypeSelectedError").innerHTML = "";
			submitForm(clickedButton);
		}
	}
	function validateDeleteUserTypeForm(clickedButton) {

		var userTypesSelected = document.getElementsByName("userTypeSelected");

		var userTypeSelected = null;
		for (var i = 0; i < userTypesSelected.length; i++) {
			if (userTypesSelected[i].checked == true) {
				userTypeSelected = userTypesSelected[i].value;
			}
		}
		//var userTypeSelected = document.forms["updateUserTypeForm"]["userTypeSelected"].checked;
		if (userTypeSelected == null || userTypeSelected == "") {
			document.getElementById("userTypeSelectedError").innerHTML = "Please Select a User Type ...";
		} else {
			document.getElementById("userTypeSelectedError").innerHTML = "";
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

						ResultantUserType resultantUserType = gson.fromJson(request.getAttribute("response").toString(),
								ResultantUserType.class);
						if (resultantUserType.getStatus().equals(Status.SUCCESS)) {
							List<UserType> userTypes = (List<UserType>) resultantUserType.getUserType();
							if (userTypes != null) {
		%><form action="#" method="get" name="updateUserTypeForm">
			<input type="text" style="display: none;" value="PUT" name="_method">
			<%
				for (UserType userType : userTypes) {
			%>
			<input type="radio"
				value="<%=userType.getUserTypeName() + "-" + userType.getUserTypeId()%>"
				name="userTypeSelected" id="userTypeSelected" /> <input type="text"
				style="visibility: hidden; display: none;"
				value="<%userType.getUserTypeId();%>" name="userTypeSelectedId" />
			<%
				out.println(userType.getUserTypeName() + "<br/>");
									}
			%>
			<br /> Please enter updated value of User Type :<input type="text"
				name="updatedUserTypeSelected" id="updatedUserTypeSelected" /><br />
			<br /> <input type="button" value="Update User Type"
				name="updateButton" onclick="validateUpdateUserTypeForm(this)" /> <br />
			<br /> <input type="button" value="Delete User Type"
				name="deleteButton" onclick="validateDeleteUserTypeForm(this)" />
		</form>
		<%
			} else {
								out.println("No User Types Found...");
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
		<p id="userTypeSelectedError" style="color: red;"></p>
	</center>

</body>
</html>