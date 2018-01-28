<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="it.unisa.model.*, it.unisa.beans.*"%>

<%
	ClientBean user = (ClientBean) session.getAttribute("user");
	if (user == null) {
		user = new ClientBean();
	}
	String role = (String) session.getAttribute("privilege");
%>


<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="css/topbar-style.css" type="text/css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<%
	response.sendRedirect("login.jsp");
%>

<nav class="topBar">
	<a id="hamburgerBtn" onclick="menuHandler()">&#9776;</a>
	<ul id="mainMenu">
		<li><a id="Home" href="index.jsp">Home</a></li>
		<li><a id="App" href="./ContentShowControl?action=showApps"
			onclick="menuHandler()">App</a></li>
		<li><a id="Music" href="./ContentShowControl?action=showMusics"
			onclick="menuHandler()">Music</a></li>
		<li><a id="Book" href="./ContentShowControl?action=showBooks"
			onclick="menuHandler()">Book</a></li>
		<li><a id="Film" href="./ContentShowControl?action=showFilms"
			onclick="menuHandler()">Film</a></li>
	</ul>

	<%
		if (role == null) {
	%>
	<h4 style="float: left">Role: Guest</h4>
	<%
		} else {
	%>
	<h4 style="float: left">
		Role:
		<%=role%></h4>
	<%} %>	

	<div class="userPanel">
		<a id="responsiveSearchBtn"><img src="images/icons/searchIcon.png"
			onclick="showBar()"></a>
		<div id="searchPanel">
			<form action="ContentShowControl" method="get" id="formSearch"
				onsubmit="return validateSearch()">
				<input type="hidden" name="action" value="search"> <input
					class="search" id="searchBtn" type="image"
					src="images/icons/searchIcon.png" alt="searchIcon"> <input
					class="search" name="string" oninput="refreshHint()"
					id="searchInput" autocomplete="off" type="text"
					placeholder="search" size="35">
				<div id="hints-div"></div>
			</form>
		</div>
		<%
			if (user.getAccount() == null) {
		%>
		<a id="login" href="LoginView.jsp">Login</a>
		<%
			} else {
				if (role != null && (role.equals("Admin") || role.equals("Manager"))) {
		%>
		<a id="manage" href="admin/ControlPanelView.jsp">Control Panel</a>
		<%
			}
		%>
		<div id="container">
			<button id="userProfile">
				<img src="images/icons/userIcon.png" alt="userLogo">
			</button>
			<div id="menuList">
				<div id="triangle"></div>
				<a href="./UserProfileView.jsp">Welcome <%=user.getName()%></a>
				<hr>
				<a href="./DownloadControl?action=showDownloads">My Downloads</a> <a
					href="./MethodPaymentControl?action=controlPayment">Payment</a> <a
					href="./ContentShowControl?action=showWishlist">Wishlist</a> <a
					id="logout" href="./LogoutServlet">Logout</a>
			</div>
		</div>
		<%
			}
		%>

	</div>

</nav>

<script>
	var searchBar = document.getElementById('searchInput');
	var hintDiv = document.getElementById('hints-div');

	if (screen.width < 767) {
		hintDiv.style.display = "none";
	}

	function validateSearch() {
		if (searchBar.value.length < 3)
			return false;

		return true;
	}

	function refreshHint() {

		var string = searchBar.value;
		var xhttp = new XMLHttpRequest();

		xhttp.onreadystatechange = function() {
			if (this.readyState == 4 && this.status == 200) {
				hintDiv.innerHTML = "";
				var hints = JSON.parse(this.responseText);

				if (hints.length == 0)
					hintDiv.style.border = "0";
				else {
					hintDiv.style.border = "1px solid lightgray";
					hintDiv.style.display = "block";
				}

				for (i = 0; i < hints.length; i++) {
					var string = "<a class = \"hint\" href=\"./ContentShowControl?action=showContent&id="
							+ hints[i].id
							+ "&piva="
							+ hints[i].piva
							+ "\">"
							+ hints[i].nome + "</a> ";
					console.log(string);
					hintDiv.innerHTML += string;
				}
			}
		}

		if (string.length > 2) {
			xhttp.open("GET", "createHintsControl?string=" + string, true);
			xhttp.send();
		} else {
			hintDiv.innerHTML = "";
			hintDiv.style.border = "0";
		}
	}
</script>

<script type="text/javascript">
	function showBar() {
		$("#searchPanel").show();
		$("#responsiveSearchBtn").hide();
		$("#manage").hide();
		$("#login").hide();
		$("#userProfile").hide();
	}

	$(".userPanel").on("mouseleave", function() {
		if (screen.width < 767) {
			$("#searchPanel").hide();
			$("#responsiveSearchBtn").show();
			$("#manage").show();
			$("#login").show();
			$("#userProfile").show();
		}
	});

	function menuHandler() {
		var el = $("#mainMenu").width();
		if (el == 0) {
			$("#mainMenu").css({
				'width' : '150px'
			});
			$("#hamburgerBtn").css({
				'color' : 'orange'
			});
		} else {
			$("#mainMenu").css({
				'width' : '0'
			});
			$("#hamburgerBtn").css({
				'color' : 'gray'
			});
		}
	}
</script>

