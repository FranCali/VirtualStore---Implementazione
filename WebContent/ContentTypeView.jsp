<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="java.util.*, it.unisa.model.*, it.unisa.beans.*"%>

<%
	LinkedList<?> contents = (LinkedList<?>) request.getAttribute("contents");
	String type = (String) request.getAttribute("type");
	LinkedList<?> numDownload = (LinkedList<?>) request.getAttribute("numDownload");
	LinkedList<?> average = (LinkedList<?>) request.getAttribute("average");
	String rankTitle = (String) request.getAttribute("rankTitle");
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport"
	content="width = device-width, initial-scale = 1.0">

<link rel="icon" href="images/icons/logoIcon.png">
<link rel="stylesheet" href="css/main-style.css" type="text/css">
<title>Contents</title>
</head>
<body onload="selectOrder('<%=request.getAttribute("sort")%>')">

	<jsp:include page="template/TopBarView.jsp"></jsp:include>
	<%
		if (rankTitle != null) {
	%>
	<h1 class="margin-title"><%=rankTitle%></h1>
	<%
		}
	%>
	<section class="contents-table">
		<%
			if (type != null) {
		%>
		<form id="orderForm" action="ContentShowControl">
			<div id="orderSelection">
				Order By: <select name="sort"
					onchange="document.getElementById('orderForm').submit()">
					<!--  <option value = "valutazione">Rating</option> -->
					<option id="nome" value="nome">Name</option>
					<option id="prezzo" value="prezzo">Price</option>
				</select>
			</div>
			<input type="hidden" name="type" value="<%=type%>">
		</form>

		<%
			}
			if (contents != null) {
				for (int i = 0; i < contents.size(); i++) {
					ContentBean content = (ContentBean) contents.get(i);
		%>
		<div class="content-wrapper">
			<div class="content-square">
				<a
					href="./ContentShowControl?action=showContent&id=<%=content.getId()%>"><img
					src="<%=content.getIcon()%>" alt="icon"></a>
				<p class="content-name"><%=content.getName()%></p>
				<%
					if (content.getPrice() == 0) {
				%>
				<p>Gratis</p>
				<%
					} else {
				%>
				<p class="content-price">
					€<%=String.format("%.2f", content.getPrice())%></p>
				<%
					}
							if (numDownload != null) {
				%>
				<p>
					Download:
					<%=numDownload.get(i)%></p>
				<%
					}
							if (average != null) {
				%>
				<p><%=String.format("%.2f", average.get(i))%>
					&#9733;
				</p>

				<%
					}
				%>

			</div>
		</div>
		<%
			}
			} else {
		%>
		<h2 style="text-align: center; margin-top: 20%"><%=request.getAttribute("error")%></h2>
		<%
			}
		%>

	</section>


	<jsp:include page="template/Footer.html"></jsp:include>
	<script type="text/javascript">
		var type = "<%=request.getAttribute("type-active")%>
		";

		if (type != null)
			document.getElementById(type).className += " active";

		function selectOrder(sort) {
			document.getElementById(sort).selected = "selected";
		}
	</script>


</body>
</html>