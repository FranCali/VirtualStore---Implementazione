<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="it.unisa.model.* , java.util.*, it.unisa.beans.*"
	pageEncoding="ISO-8859-1"%>

<%
	WishlistBean wishlist = (WishlistBean) session.getAttribute("wishlist");
	LinkedList<ContentBean> contents = null;

	if (wishlist != null) {
		contents = (LinkedList<ContentBean>) wishlist.getContents();
	}

	String error = (String) request.getAttribute("error");
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport"
	content="width = device-width, initial-scale = 1.0">

<link rel="icon" href="images/icons/logoIcon.png">
<link rel="stylesheet" href="css/main-style.css" type="text/css">
<%
	ClientBean user = (ClientBean) session.getAttribute("user");
%>
<%
	if (user != null) {
%>
<title><%=user.getName()%>'s wishlist</title>
<%
	}
%>

</head>
<body>


	<%
		if (wishlist != null) {
	%>
	<jsp:include page="template/TopBarView.jsp"></jsp:include>
	<h1 id="title" align="center">WishList</h1>

	<%
		if (wishlist.isEmpty()) {
	%>
	<h2>Your list is empty</h2>
	<%
		} else {
	%>
	<section id="wishlist">
		<ul>
			<%
				for (ContentBean content : contents) {
			%>
			<li>


				<div id="remove-wrapper">
					<a
						href="WishlistControl?action=remove&id=<%=content.getId()%>&page=WishlistView"><img
						id="removeFromList" src="images/icons/remove-icon.png"></a>

					<div id="content-wrapper">

						<div class="content-info">

							<a
								href="./ContentShowControl?action=showContent&id=<%=content.getId()%>"><img
								id="content-icon" src="<%=content.getIcon()%>"
								alt="content-icon"></a>

							<div class="content-details">
								<h2><%=content.getName()%></h2>
								<%
									if (content.getPrice() == 0) {
								%>
								<p>Gratis</p>
								<%
									} else {
								%>
								<p>
									Price: &euro;<%=content.getPrice()%></p>
								<%
									}
								%>
								<p>
									Size:
									<%=content.getSize()%>
									MB
								</p>
								<%
									if (content.getType().equals("Applicazione")) {
								%>
								<p>
									Version:
									<%=content.getVersion()%></p>
								<%
									} else {
								%>
								<p>
									Format:
									<%=content.getVersion()%></p>
								<%
									}
								%>
							</div>
						</div>
					</div>
				</div>
			</li>

			<%
				}
			%>
		</ul>

	</section>
	<a class="button" href="WishlistControl?action=removeAll">Remove
		All</a>
	<%
		}
		} else {
	%>
	<h1><%=error%></h1>
	<%
		}
	%>
</body>
</html>