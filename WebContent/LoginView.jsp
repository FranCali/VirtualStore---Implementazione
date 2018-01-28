<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="description" content="log-in page for user validation" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>V-Store Login</title>
<link rel="icon" href="images/icons/logoIcon.png">
<link rel="stylesheet" href="css/main-style.css">
<link rel="stylesheet" href="css/form-style.css">
<link rel="stylesheet" href="css/log-reg-style.css">

</head>
<body onload="document.getElementsByName('email')[0].focus()">
	<%
		String login_error = (String) request.getAttribute("login_error");
		if (login_error == null)
			login_error = "";
	%>

	<a href="index.jsp"><img alt="logo" src="images/icons/logoIcon.png" /></a>
	<form method="post" action="LogRegControl?action=Login"
		onsubmit="return validateLoginForm(this);">
		<h1>Login</h1>
		<br> Email address <input class="insert-input" name="email"
			type="email" oninput="controlParLogin(this)" required><br />
		<br /> Password <input class="insert-input" name="password"
			type="password" oninput="controlParLogin(this)" required><br>
		<br>
	  <input
			class="form-btn" type="submit" value="Login"><br>
		<h5 class="error"><%=login_error%></h5>
		<br>
		<p style="margin: -5px 5% -10px 5%; color: gray; font-size: small;">New
			on V-Store?</p>
		<br> <a href="RegisterView.jsp"><input class="form-btn"
			name="signin" type="button" value="Register"></a><br>
		<br>
	</form>

	<script src="js/formcheck.js"></script>
</body>
</html>