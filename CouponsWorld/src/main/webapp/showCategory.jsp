<%@page import="com.couponsworld.dto.Category"%>
<%@page import="com.couponsworld.apiresults.ResultantCategory"%>
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
			document.updateCategoryForm.action = "/categories";
		} else if (clickedButton.name == "deleteButton") {
			document.forms["updateCategoryForm"]["_method"].value = "DELETE";
			document.updateCategoryForm.action = "/categories";
		}
		document.updateCategoryForm.submit();
	}

	function validateUpdateCategoryForm(clickedButton) {

		var userCategoriesSelected = document
				.getElementsByName("categorySelected");

		var categorySelected = null;
		for (var i = 0; i < userCategoriesSelected.length; i++) {
			if (userCategoriesSelected[i].checked == true) {
				categorySelected = userCategoriesSelected[i].value;
			}
		}
		//var userTypeSelected = document.forms["updateUserTypeForm"]["userTypeSelected"].checked;
		if (categorySelected == null || categorySelected == "") {
			document.getElementById("categorySelectedError").innerHTML = "Please Select a Category ...";
		} else if (document.getElementById("updatedCategorySelected").value == "") {
			document.getElementById("categorySelectedError").innerHTML = "Please specify updated value...";
		} else {
			document.getElementById("categorySelectedError").innerHTML = "";
			submitForm(clickedButton);
		}
	}
	function validateDeleteCategoryForm(clickedButton) {

		var categoriesSelected = document.getElementsByName("categorySelected");

		var categorySelected = null;
		for (var i = 0; i < categoriesSelected.length; i++) {
			if (categoriesSelected[i].checked == true) {
				categorySelected = categoriesSelected[i].value;
			}
		}
		//var userTypeSelected = document.forms["updateUserTypeForm"]["userTypeSelected"].checked;
		if (categorySelected == null || categorySelected == "") {
			document.getElementById("categorySelectedError").innerHTML = "Please Select a Category ...";
		} else {
			document.getElementById("categorySelectedError").innerHTML = "";
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

						ResultantCategory resultantCategory = gson.fromJson(request.getAttribute("response").toString(),
								ResultantCategory.class);
						if (resultantCategory.getStatus().equals(Status.SUCCESS)) {
							List<Category> categories = (List<Category>) resultantCategory.getCategories();
							if (categories != null) {
		%><form action="#" method="get" name="updateCategoryForm">
			<input type="text" style="display: none;" value="PUT" name="_method">
			<%
				for (Category category : categories) {
			%>
			<input type="radio"
				value="<%=category.getCategoryName() + "-" + category.getCategoryId()%>"
				name="categorySelected" id="categorySelected" /> <input type="text"
				style="visibility: hidden; display: none;"
				value="<%category.getCategoryId();%>" name="categorySelectedId" />
			<%
				out.println(category.getCategoryName() + "<br/>");
									}
			%>
			<br /> Please enter updated value of Category :<input type="text"
				name="updatedCategorySelected" id="updatedCategorySelected" /><br />
			<br /> <input type="button" value="Update Category"
				name="updateButton" onclick="validateUpdateCategoryForm(this)" /> <br />
			<br /> <input type="button" value="Delete  Category"
				name="deleteButton" onclick="validateDeleteCategoryForm(this)" />
		</form>
		<%
			} else {
								out.println("No Categories Found...");
							}
						} else if (resultantCategory.getStatus().equals(Status.FAILURE)) {
							List<Error> errorList = (List<Error>) resultantCategory.getErrors();
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
		<p id="categorySelectedError" style="color: red;"></p>
	</center>

</body>
</html>