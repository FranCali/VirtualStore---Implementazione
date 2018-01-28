<%@page import="it.unisa.util.Encryptor"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="it.unisa.model.*, it.unisa.beans.*" pageEncoding="UTF-8"%>

<%
	ClientBean user = (ClientBean) session.getAttribute("user");
	String error = (String) request.getAttribute("error");
	String role = (String) session.getAttribute("privilege");
	Encryptor encryptor;
	if (user != null) {
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport"
	content="width = device-width, initial-scale = 1.0">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<link rel="stylesheet" href="css/main-style.css" type="text/css">
<link rel="stylesheet" href="css/form-style.css" type="text/css">
<link rel="stylesheet" href="css/userProfile-style.css" type="text/css">
<link rel="icon" href="images/icons/userIcon.png">

<title><%=user.getName() + " " + user.getSurname()%>'s profile</title>

</head>
<body>
	<jsp:include page="template/TopBarView.jsp"></jsp:include>

	<div id="userData">
		<img alt="userImage" src="images/icons/userProfileIcon.png">
		<fieldset>
			<legend>Data</legend>
			<div>
				Name<br /> <input class="read-input" type="text" name="name"
					value="<%=user.getName()%>" readonly><br /> Surname<br />
				<input class="read-input" type="text" name="surname"
					value="<%=user.getSurname()%>" readonly><br /> Email<br />
				<input class="read-input" type="text" name="email"
					value="<%=user.getAccount().getEmail()%>" readonly><br />
			</div>

			<a id="changepass-btn"
				onclick="showContent('changePasswordContent',this.id)"
				class="form-btn">Change password</a>

			<form id="changePasswordContent"
				onsubmit="return controlMatchingPassword('againPassword', 'newPassword')"
				action="UserProfileControl" method="post">
				<br> Current password<br /> <input class="insert-input"
					type="password" name="currentPassword" required><br /> New
				password<br /> <input id="newPassword" class="insert-input"
					type="password" name="password"
					onblur="controlPasswordLength(this.id)" required><br />
				Confirm password<br /> <input id="againPassword"
					class="insert-input" type="password" name="againpassword"
					onblur="controlMatchingPassword(this.id)" required><br />

				<%
					if (error != null) {
				%>
				<p class="error"><%=error%></p>
				<%
					}
				%>
				<p class="error"></p>
				<br> <input id="hide-btn" type="button" value="&#10094;"
					onclick="hideContent('changePasswordContent','changepass-btn')">
				<input id="send-btn" class="form-btn" type="submit" value="Send">
			</form>
		</fieldset>

	</div>

	<%
		if (role.equals("Client")) {
	%>
	<input type="button" value="Remove Account" class="error"
		onclick="askConfirmation()">

	<%
		}
	%>

	<script src="js/formcheck.js"></script>
	<script>
		function askConfirmation() {
			console.log("cliccato")
			var val = window
					.confirm('Are you sure you want to delete your account?');
			if (val == true) {

			}
		}
	</script>

	<script src="js/formcheck.js"></script>

</body>
</html>


<%
	}
%>