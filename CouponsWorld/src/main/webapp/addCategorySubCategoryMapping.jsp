<%@page import="com.couponsworld.enums.OfferTypeEnum"%>
<%@page import="com.couponsworld.enums.CashBackMode"%>
<%@page import="com.couponsworld.dto.UsabilityStatus"%>
<%@page import="com.couponsworld.apiresults.ResultantUsabilityStatus"%>
<%@page import="com.couponsworld.dto.UserPlatform"%>
<%@page import="com.couponsworld.apiresults.ResultantUserPlatform"%>
<%@page import="com.couponsworld.dto.UserType"%>
<%@page import="com.couponsworld.dto.SubCategory"%>
<%@page import="com.couponsworld.apiresults.ResultantSubCategory"%>
<%@page import="com.couponsworld.dto.Category"%>
<%@page import="com.couponsworld.apiresults.ResultantCategory"%>
<%@page import="com.couponsworld.dto.Company"%>
<%@page import="com.couponsworld.apiresults.ResultantCompany"%>
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

<script type="text/javascript">
	function submitForm(clickedButton) {

		document.getElementById("addCategorySubCategoryMappingForm").submit();

	}
	//get selected checkboxes
	function getSelectedCheckboxes(clickedButton) {

		var e = document.getElementById("category");
		var strCategory = e.options[e.selectedIndex].value;
		if (strCategory == 0) {
			document.getElementById('error').innerHTML = "Please select a category from the dropdown list...";
		} else {

			var selchbox = [];// array that will store the value of selected checkboxes
			var selchboxString = "";
			// gets all the input tags in frm, and their number
			var inpfields = document.getElementsByName("checkBoxGroup");
			var nr_inpfields = inpfields.length;
			// traverse the inpfields elements, and adds the value of selected (checked) checkbox in selchbox
			for (var i = 0; i < nr_inpfields; i++) {
				if (inpfields[i].type == 'checkbox'
						&& inpfields[i].checked == true)

					selchbox.push(inpfields[i].value);
			}

			if (selchbox.length == 0) {
				document.getElementById('error').innerHTML = "Please select atleast sub category to map ...";
			} else {
				document.getElementById('subCategories').value = selchbox;
				document.getElementById('error').innerHTML = document
						.getElementById('subCategories').value;
				//document.forms["addCategorySubCategoryMappingForm"].action = "/categorySuCategoryMapping";
				alert("Sub Categories selected are :"
						+ document.getElementById('subCategories').value);
				submitForm(clickedButton);
			}
		}
		//	return selchbox;
	}
</script>
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
						//out.println(request.getAttribute("allCompanies"));

						//out.println(resultantCompany.getCompanies().get(0).getCompanyName());
						ResultantCategory resultantCategory = gson
								.fromJson(request.getAttribute("allCategories").toString(), ResultantCategory.class);
						List<Category> allCategories = (ArrayList<Category>) resultantCategory.getCategories();

						ResultantSubCategory resultantSubCategory = gson.fromJson(
								request.getAttribute("allSubCategories").toString(), ResultantSubCategory.class);
						List<SubCategory> allSubCategories = resultantSubCategory.getSubCategories();
		%>
		<br /> <br />

		<form action="categorySubCategoryMapping" method="post"
			name="addCategorySubCategoryMappingForm"
			id="addCategorySubCategoryMappingForm">
			Select the category : <select name="category" id="category">
				<%
					if (resultantCategory.getCategories() != null) {
									for (Category category : resultantCategory.getCategories()) {
										//out.println(category.getCategoryName());
				%>
				<option value="<%=category.getCategoryName()%>">
					<%=category.getCategoryName()%>
				</option>
				<%
					}
								}
				%>
				<option value="" selected="selected"></option>
			</select> <br /> <br />Select the Sub Categories :
			<fieldset>
				<%
					if (resultantSubCategory.getSubCategories() != null) {
									for (SubCategory subCategory : resultantSubCategory.getSubCategories()) {
										//out.println(category.getCategoryName());
				%>
				<%=subCategory.getSubCategoryName()%>
				<input type="checkbox" name="checkBoxGroup"
					value="<%=subCategory.getSubCategoryName()%>" /><br /> <br />
				<%
					}
								}
				%>

			</fieldset>
			<input type="text" name="subCategories" id="subCategories"
				style="display: none;"> <input type="button"
				value="Map Category to Selected Sub Categories"
				onclick="getSelectedCheckboxes(this)">
		</form>
		<%
			} else if (request.getAttribute("errors") != null) {
						List<Error> errors = (ArrayList<Error>) request.getAttribute("errors");
						for (Error e : errors) {
							out.println(e.getErrorName());
						}
					}
				}
				request.removeAttribute("status");
				request.removeAttribute("errors");
			} catch (Exception e) {
				out.println(e.getMessage());
				e.printStackTrace();
			}
		%>
		<p id="error"></p>
	</center>

</body>
</html>