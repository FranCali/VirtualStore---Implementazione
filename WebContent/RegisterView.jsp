<!-- DEVI FINIRE I BANNER -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name = "description" content = "registration page for user"/>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel = "stylesheet" href = "css/main-style.css" type = "text/css"/>
	<link rel = "stylesheet" href = "css/form-style.css" type = "text/css"/>
	<link rel = "stylesheet" href = "css/log-reg-style.css" type = "text/css"/>
	<link rel="icon" href="images/icons/logoIcon.png">
	<title>V-Store Register</title>
</head>
<body onload = "document.getElementsByName('nome')[0].focus()">
	<%
		String register_error = (String)request.getAttribute("register_error");
		if(register_error == null)
			register_error = "";	
	%>
	
	<a href = "index.jsp"><img alt="logo" src="images/icons/logoIcon.png"/></a>
	<form  onsubmit = "return validateForm(this)" action = "LogRegControl?action=Register" method = "post" >
			
		<h1 class = "move">Create Account</h1> <br/>
	
		<div class="inputContainer">
			Name
			<span class="popuptext" id="namePopup"></span>
			<input class = "insert-input" type = "text" name = "nome" oninput="controlParameter(this, 'namePopup')"required/> <br/>
		</div>
			
		<div class="inputContainer">
			Surname
			<span class="popuptext" id="surnamePopup"></span>
			<input class = "insert-input" type = "text" name = "cognome" oninput="controlParameter(this, 'surnamePopup')" required/> <br/>
		</div>
			
		<div class="inputContainer">
			E-mail
			<span class="popuptext" id="emailPopup"></span>
			<input class = "insert-input" type = "email" name = "email"  onblur="controlParameter(this, 'emailPopup')"required/> <br/>
		</div>
				
		<div class="inputContainer">
			Password
			<span class="popuptext" id="passwordPopup">jaretry</span>
			<input id="password" class = "insert-input" type = "password" name = "password" oninput="controlParameter(this, 'passwordPopup')" required/> <br/>
		</div>
			
		<div class="inputContainer">
			<span class="popuptext" id="confirm-passwordPopup"></span>
			Repeat password
			<input class = "insert-input" type = "password" name = "againpassword" onblur="controlParameter(this, 'confirm-passwordPopup', password)" required/>
		</div>
		<br>
		<h5 class = "error"><%=register_error%></h5><br>
		<input class = "form-btn" type = "submit" value = "Create Account"> <br/><br/><br/><br>
			
			
		<p id = "policy">Accedendo dichiari integralmente le nostre 
			Condizioni generali di uso e vendita, la nostra Informativa sulla privacy e 
			le nostre policy su Cookies e pubblicità su Internet.
		</p><br/>
		<hr/><br/>
		Already have an account? <a class ="norm-anchor" href = "LoginView.jsp">Login</a>
	</form>

	<script src = "js/formcheck.js"></script>
		
</body>
</html>