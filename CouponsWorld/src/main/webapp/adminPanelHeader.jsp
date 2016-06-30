<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
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
							<li><form method="get" action="addOffer">
									<input type="text" value="GET" name="_method"
										style="display: none;" /> <input type="submit" value="Add" />
								</form>
								</a></li>
							<li><form method="get" action="offers">
									<input type="text" value="GET" name="_method"
										style="display: none;" /> <input type="submit"
										value="display" />
								</form></li>
						</ul>
					</div></li>
				<li><a href="#">Categories</a>
					<div class="dropdown">
						<ul>
							<li><a href="addCategory">Add</a></li>
							<li><form method="get" action="categories">
									<input type="text" value="GET" name="_method"
										style="display: none;" /> <input type="submit"
										value="display" />
								</form></li>
						</ul>
					</div></li>
				<li><a href="#">Companies</a>
					<div class="dropdown">
						<ul>
							<li><a href="addCompany">Add</a></li>
							<li><form method="get" action="companies">
									<input type="text" value="GET" name="_method"
										style="display: none;" /> <input type="submit"
										value="display" />
								</form></li>
						</ul>
					</div></li>
				<li><a href="#">Sub Categories</a>
					<div class="dropdown">
						<ul>
							<li><a href="addSubCategory">Add</a></li>
							<li><form method="get" action="subCategories">
									<input type="text" value="GET" name="_method"
										style="display: none;" /> <input type="submit"
										value="display" />
								</form></li>

						</ul>
					</div></li>
				<li><a href="#">Usabilty Status</a>
					<div class="dropdown">
						<ul>
							<li><a href="addUsabilityStatus">Add</a></li>
							<li><form method="get" action="usabilityStatus">
									<input type="text" value="GET" name="_method"
										style="display: none;" /> <input type="submit"
										value="display" />
								</form></li>

						</ul>
					</div></li>
				<li><a href="#">User Platform</a>
					<div class="dropdown">
						<ul>
							<li><a href="addUserPlatform">Add</a></li>
							<li><form method="get" action="userPlatform">
									<input type="text" value="GET" name="_method"
										style="display: none;" /> <input type="submit"
										value="display" />
								</form></li>
						</ul>
					</div></li>
				<li><a href="#">User Type</a>
					<div class="dropdown">
						<ul>
							<li><a href="addUserType">Add</a></li>
							<li><form method="get" action="userType">
									<input type="text" value="GET" name="_method"
										style="display: none;" /> <input type="submit"
										value="display" />
								</form></li>
						</ul>
					</div></li>
			</ul>
			</nav>
		</div>
	</div>
</body>
</html>