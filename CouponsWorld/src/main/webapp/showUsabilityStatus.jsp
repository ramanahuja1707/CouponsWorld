<%@page import="com.couponsworld.dto.UsabilityStatus"%>
<%@page import="com.couponsworld.apiresults.ResultantUsabilityStatus"%>
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
			document.updateUsabilityStatusForm.action = "/usabilityStatus";
		} else if (clickedButton.name == "deleteButton") {
			document.forms["updateUsabilityStatusForm"]["_method"].value = "DELETE";
			document.updateUsabilityStatusForm.action = "/usabilityStatus";
		}
		document.updateUsabilityStatusForm.submit();
	}

	function validateUpdateUsabilityStatusForm(clickedButton) {

		var usabilityStatusesSelected = document
				.getElementsByName("usabilityStatusSelected");

		var usabilityStatusSelected = null;
		for (var i = 0; i < usabilityStatusesSelected.length; i++) {
			if (usabilityStatusesSelected[i].checked == true) {
				usabilityStatusSelected = usabilityStatusesSelected[i].value;
			}
		}
		//var userTypeSelected = document.forms["updateUserTypeForm"]["userTypeSelected"].checked;

		if (usabilityStatusSelected == null || usabilityStatusSelected == "") {
			document.getElementById("usabilityStatusSelectedError").innerHTML = "Please Select a Usability Status ...";
		} else if (document.getElementById("updatedUsabilityStatusSelected").value == "") {
			document.getElementById("usabilityStatusSelectedError").innerHTML = "Please specify updated value...";
		} else {
			document.getElementById("usabilityStatusSelectedError").innerHTML = "";
			submitForm(clickedButton);
		}
	}
	function validateDeleteUsabilityStatusForm(clickedButton) {

		var usabilityStatusesSelected = document
				.getElementsByName("usabilityStatusSelected");

		var usabilityStatusSelected = null;
		for (var i = 0; i < usabilityStatusesSelected.length; i++) {
			if (usabilityStatusesSelected[i].checked == true) {
				usabilityStatusSelected = usabilityStatusesSelected[i].value;
			}
		}
		//var userTypeSelected = document.forms["updateUserTypeForm"]["userTypeSelected"].checked;
		if (usabilityStatusSelected == null || usabilityStatusSelected == "") {
			document.getElementById("usabilityStatusSelectedError").innerHTML = "Please Select a Usability Status ...";
		} else {
			document.getElementById("usabilityStatusSelectedError").innerHTML = "";
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

						ResultantUsabilityStatus resultantUsabilityStatus = gson
								.fromJson(request.getAttribute("response").toString(), ResultantUsabilityStatus.class);
						if (resultantUsabilityStatus.getStatus().equals(Status.SUCCESS)) {
							List<UsabilityStatus> usabilityStatuses = (List<UsabilityStatus>) resultantUsabilityStatus
									.getUsabilityStatus();
							if (usabilityStatuses != null) {
		%><form action="#" method="get" name="updateUsabilityStatusForm">
			<input type="text" style="display: none;" value="PUT" name="_method">
			<%
				for (UsabilityStatus usabilityStatus : usabilityStatuses) {
			%>
			<input type="radio"
				value="<%=usabilityStatus.getUsabilityStatusName() + "-"
											+ usabilityStatus.getUsabilityStatusId()%>"
				name="usabilityStatusSelected" id="usabilityStatusSelected" /> <input
				type="text" style="visibility: hidden; display: none;"
				value="<%usabilityStatus.getUsabilityStatusId();%>"
				name="usabilityStatusSelectedId" />
			<%
				out.println(usabilityStatus.getUsabilityStatusName() + "<br/>");
									}
			%>
			<br /> Please enter updated value of Usability Status :<input
				type="text" name="updatedUsabilityStatusSelected"
				id="updatedUsabilityStatusSelected" /><br /> <br /> <input
				type="button" value="Update Usability Status" name="updateButton"
				onclick="validateUpdateUsabilityStatusForm(this)" /> <br /> <br />
			<input type="button" value="Delete Usability Status"
				name="deleteButton"
				onclick="validateDeleteUsabilityStatusForm(this)" />
		</form>
		<%
			} else {
								out.println("No Usability Status Found...");
							}
						} else if (resultantUsabilityStatus.getStatus().equals(Status.FAILURE)) {
							List<Error> errorList = (List<Error>) resultantUsabilityStatus.getErrors();
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
		<p id="usabilityStatusSelectedError" style="color: red;"></p>
	</center>
</body>
</html>