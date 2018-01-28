<%@page import="it.unisa.beans.PaymentBean"%>
<%@page import="it.unisa.model.PaymentModelDM"%>
<%@page import="java.io.PrintWriter"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%	
	PaymentBean payment = (PaymentBean)session.getAttribute("method");
%>    
    
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name = "viewport" content = "width = device-width, initial-scale = 1.0">
	
	<link rel="stylesheet" href="css/main-style.css" type="text/css">
	<link rel="stylesheet" href="css/userProfile-style.css" type="text/css">
	<link rel="stylesheet" href="css/form-style.css" type="text/css">

	<title>MethodPayment</title>
</head>
<body>
	<jsp:include page="template/TopBarView.jsp"></jsp:include>
	
	<%
		if(payment == null) { 
	%>
			<div id="insertMethod" style = "margin-top: 10%;">
				<fieldset>
					<legend>Insert Payment Method</legend>
					<form action="MethodPaymentControl" method="post">
						Card Serial<br>
						<input class = "insert-input" type="text" name="nCarta" required><br>
						Security Code(CVC)<br>
						<input class = "insert-input" type="text" name="cSicurezza" required><br>
						Expiration Date<br>
						<input class = "insert-input" type="date" name="dScadenza" required><br><br>
						<input type = "hidden" name = "action" value = "insertMethod">
						<input id="insert" type="submit" class = "form-btn" value="Insert">
					</form>
				</fieldset>
			</div>
	<%
		} else {
				
	%>
			<div id="viewMethod" style = "margin-top: 10%;">
				Email<br>
				<input class = "read-input" type="text" name="email" value="<%=payment.getEmail() %>" readonly><br>
				Numero Carta<br>
				<input class = "read-input" type="text" name="nCarta" value="<%=payment.getIdentifier()%>" readonly><br>
				Codice di Sicurezza<br>
				<input class = "read-input" type="text" name="cSicurezza" value="<%=payment.getSecurityCode()%>" readonly><br>
				DataScadenza<br>
				<input class = "read-input" type="text" name="dScadenza" value="<%=payment.getExpireDate()%>" readonly><br><br>
				<hr><br>
				<a href = "MethodPaymentControl?action=removeMethod" class = "remove-btn">Remove Method</a>
			</div>
			
	<%
		}
	%>	
			
</body>
</html>