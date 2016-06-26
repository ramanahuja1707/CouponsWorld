<%@page import="com.couponsworld.apiresults.Error"%>
<%@page import="java.util.ArrayList"%>
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
	<%
		if (session.getAttribute("username") == null && session.getAttribute("password") == null) {
			RequestDispatcher rd = request.getRequestDispatcher("/notInSession.jsp");
			rd.forward(request, response);
		}
	%>
	<div id="wrapper">
		<div class="container">
			<div class="header">
				<h1>Coupons World Admin Panel</h1>
			</div>

			<nav>
			<ul>
				<li><a href="adminPanel.jsp">Home </a>
					<div class="dropdown">
						<ul>
							<li><a href="logout">Logout</a></li>
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
							<li><a href="addUsabilityStatus.jsp">Add</a></li>
							<li><li><form method="get" action="usabilityStatus">
									<input type="text" value="GET" name="_method"
										style="display: none;" /> <input type="submit"
										value="display" />
								</form></li></li>

			</ul>
		
		</div>
		</li>
		<li><a href="#">User Platform</a>
			<div class="dropdown">
				<ul>
					<li><a href="addUserPlatform.jsp">Add</a></li>
					<li><form method="get" action="userPlatform">
							<input type="text" value="GET" name="_method"
								style="display: none;" /> <input type="submit" value="display" />
						</form></li>
				</ul>
			</div></li>
		<li><a href="#">User Type</a>
			<div class="dropdown">
				<ul>
					<li><a href="addUserType.jsp">Add</a></li>
					<li><form method="get" action="userType">
							<input type="text" value="GET" name="_method"
								style="display: none;" /> <input type="submit" value="display" />
						</form></li>
				</ul>
			</div></li>
		</ul>
		</nav>
	</div>
	<br />
								<center>
		<%
			//For Updation of the User Type
			if (request.getAttribute("status") != null) {
				if (request.getAttribute("status").equals(Status.SUCCESS)) {
					out.println("Usability Status deleted successfully...:-)");
				} else if (request.getAttribute("status").equals(Status.SUCCESS)) {
					out.println("Úsability Status Updation Failed :-(");
					out.println("Errors Occured : ");
					List<Error> errors = (ArrayList<Error>) request.getAttribute("errors");
					for (Error e : errors) {
						out.println(e.getErrorName() + "<br/>");
					}

				}
				request.removeAttribute("errors");
				request.removeAttribute("status");
			}
		%>


	</center>




							</body>
</html>