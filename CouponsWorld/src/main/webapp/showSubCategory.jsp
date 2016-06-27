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
<script type="text/javascript">
	function submitForm(clickedButton) {
		if (clickedButton.name == "updateButton") {
			document.updateSubCategoryForm.action = "/subCategories";
		} else if (clickedButton.name == "deleteButton") {
			document.forms["updateSubCategoryForm"]["_method"].value = "DELETE";
			document.updateSubCategoryForm.action = "/subCategories";
		}
		document.updateSubCategoryForm.submit();
	}

	function validateUpdateSubCategoryForm(clickedButton) {

		var userSubCategoriesSelected = document
				.getElementsByName("subCategorySelected");

		var subCategorySelected = null;
		for (var i = 0; i < userSubCategoriesSelected.length; i++) {
			if (userSubCategoriesSelected[i].checked == true) {
				subCategorySelected = userSubCategoriesSelected[i].value;
			}
		}
		//var userTypeSelected = document.forms["updateUserTypeForm"]["userTypeSelected"].checked;
		if (subCategorySelected == null || subCategorySelected == "") {
			document.getElementById("subCategorySelectedError").innerHTML = "Please Select a Sub Category ...";
		} else if (document.getElementById("updatedSubCategorySelected").value == "") {
			document.getElementById("subCategorySelectedError").innerHTML = "Please specify updated value...";
		} else {
			document.getElementById("subCategorySelectedError").innerHTML = "";
			submitForm(clickedButton);
		}
	}
	function validateDeleteSubCategoryForm(clickedButton) {

		var subCategoriesSelected = document
				.getElementsByName("subCategorySelected");

		var subCategorySelected = null;
		for (var i = 0; i < subCategoriesSelected.length; i++) {
			if (subCategoriesSelected[i].checked == true) {
				subCategorySelected = subCategoriesSelected[i].value;
			}
		}
		//var userTypeSelected = document.forms["updateUserTypeForm"]["userTypeSelected"].checked;
		if (subCategorySelected == null || subCategorySelected == "") {
			document.getElementById("subCategorySelectedError").innerHTML = "Please Select a Sub Category ...";
		} else {
			document.getElementById("subCategorySelectedError").innerHTML = "";
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

						ResultantSubCategory resultantSubCategory = gson
								.fromJson(request.getAttribute("response").toString(), ResultantSubCategory.class);
						if (resultantSubCategory.getStatus().equals(Status.SUCCESS)) {
							List<SubCategory> subCategories = (List<SubCategory>) resultantSubCategory
									.getSubCategories();
							if (subCategories != null) {
		%><form action="#" method="get" name="updateSubCategoryForm">
			<input type="text" style="display: none;" value="PUT" name="_method">
			<%
				for (SubCategory subCategory : subCategories) {
			%>
			<input type="radio"
				value="<%=subCategory.getSubCategoryName() + "-" + subCategory.getSubCategoryId()%>"
				name="subCategorySelected" id="subCategorySelected" /> <input
				type="text" style="visibility: hidden; display: none;"
				value="<%subCategory.getSubCategoryId();%>"
				name="subCategorySelectedId" />
			<%
				out.println(subCategory.getSubCategoryName() + "<br/>");
									}
			%>
			<br /> Please enter updated value of Sub Category :<input
				type="text" name="updatedSubCategorySelected"
				id="updatedSubCategorySelected" /><br /> <br /> <input
				type="button" value="Update Sub Category" name="updateButton"
				onclick="validateUpdateSubCategoryForm(this)" /> <br /> <br /> <input
				type="button" value="Delete Sub Category" name="deleteButton"
				onclick="validateDeleteSubCategoryForm(this)" />
		</form>
		<%
			} else {
								out.println("No Sub Categories Found...");
							}
						} else if (resultantSubCategory.getStatus().equals(Status.FAILURE)) {
							List<Error> errorList = (List<Error>) resultantSubCategory.getErrors();
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
		<p id="subCategorySelectedError" style="color: red;"></p>
	</center>

</body>
</html>