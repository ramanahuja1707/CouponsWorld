<%@page import="com.couponsworld.dto.Company"%>
<%@page import="com.couponsworld.dto.Category"%>
<%@page import="com.couponsworld.apiresults.ResultantCompany"%>
<%@page import="com.couponsworld.dto.SubCategory"%>
<%@page import="com.couponsworld.apiresults.ResultantSubCategory"%>
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
<style>
table {
	width: 50%;
}

table, th, td {
	border: 1px solid black;
	border-collapse: collapse;
}

th, td {
	padding: 5px;
	text-align: left;
}

table#t01 tr:nth-child(even) {
	background-color: #eee;
}

table#t01 tr:nth-child(odd) {
	background-color: #fff;
}

table#t01 th {
	background-color: black;
	color: white;
}
</style>
<script type="text/javascript">
	function submitForm(clickedButton) {
		if (clickedButton.name == "updateButton") {
			document.updateCompanyForm.action = "/companies";
		} else if (clickedButton.name == "deleteButton") {
			document.forms["updateCompanyForm"]["_method"].value = "DELETE";
			document.updateCompanyForm.action = "/companies";
		}
		document.updateCompanyForm.submit();
	}

	function validateUpdateCompanyForm(clickedButton) {

		var userCompaniesSelected = document
				.getElementsByName("companySelected");

		var companySelected = null;
		for (var i = 0; i < userCompaniesSelected.length; i++) {
			if (userCompaniesSelected[i].checked == true) {
				companySelected = userCompaniesSelected[i].value;
			}
		}
		//var userTypeSelected = document.forms["updateUserTypeForm"]["userTypeSelected"].checked;
		if (companySelected == null || companySelected == "") {
			document.getElementById("companySelectedError").innerHTML = "Please Select a Company ...";
		} else if (document.getElementById("updatedCompanySelected").value == "") {
			document.getElementById("companySelectedError").innerHTML = "Please specify updated value of company name ...";
		} else if (document.getElementById("updatedCompanyDescriptionSelected").value == "") {
			document.getElementById("companySelectedError").innerHTML = "Please specify updated value of company description...";
		} else if (document.getElementById("updatedCompanyLogoNameSelected").value == "") {
			document.getElementById("companySelectedError").innerHTML = "Please specify updated value of company Logo name...";
		} else {
			document.getElementById("companySelectedError").innerHTML = "";
			submitForm(clickedButton);
		}
	}
	function validateDeleteCompanyForm(clickedButton) {

		var companiesSelected = document.getElementsByName("companySelected");

		var companySelected = null;
		for (var i = 0; i < companiesSelected.length; i++) {
			if (companiesSelected[i].checked == true) {
				companySelected = companiesSelected[i].value;
			}
		}
		//var userTypeSelected = document.forms["updateUserTypeForm"]["userTypeSelected"].checked;
		if (companySelected == null || companySelected == "") {
			document.getElementById("companySelectedError").innerHTML = "Please Select a Company ...";
		} else {
			document.getElementById("companySelectedError").innerHTML = "";
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
	<center>
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
							List<Company> companies = (List<Company>) resultantCompany.getCompanies();
							if (companies != null) {
		%>
		<table>
			<tr>
				<th>Select</th>
				<th>Company Name</th>
				<th>Company Description Name</th>
				<th>Company Logo Name</th>
			</tr>

			<form action="#" method="get" name="updateCompanyForm">

				<input type="text" style="display: none;" value="PUT" name="_method">
				<%
					for (Company company : companies) {
				%>
				<tr>
					<td><input type="radio"
						value="<%=company.getCompanyName() + "-" + company.getCompanyId()%>"
						name="companySelected" id="companySelected" /></td>

					<input type="text" style="visibility: hidden; display: none;"
						value="<%company.getCompanyId();%>" name="companySelectedId" />
					<td>
						<%
							out.println(company.getCompanyName() + "<br/></td><td>"
															+ company.getCompanyDescription() + "</td><td>"
															+ company.getCompanyLogoName() + "</td></tr>");
												}
						%><br> <br /> Enter updated company name :<input type="text"
						name="updatedCompanySelected" id="updatedCompanySelected" /><br />
						<br /> Enter updated company description :<textarea rows="10"
							cols="80" name="updatedCompanyDescriptionSelected"
							id="updatedCompanyDescriptionSelected"></textarea><br /> <br />
						Enter updated company logo name : <input type="text"
						name="updatedCompanyLogoNameSelected"
						id="updatedCompanyLogoNameSelected" /><br /> <br /> <input
						type="button" value="Update Company" name="updateButton"
						onclick="validateUpdateCompanyForm(this)" /> <br /> <br /> <input
						type="button" value="Delete  Company" name="deleteButton"
						onclick="validateDeleteCompanyForm(this)" /> <br /> <br />
			</form>
		</table>
		<%
			} else {
								out.println("No Companies Found...");
							}
						} else if (resultantCompany.getStatus().equals(Status.FAILURE)) {
							List<Error> errorList = (List<Error>) resultantCompany.getErrors();
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
		<p id="companySelectedError" style="color: red;"></p>
	</center>

</body>
</html>