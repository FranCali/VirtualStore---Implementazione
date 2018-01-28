<%@ 
	page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="it.unisa.model.*, java.util.*, it.unisa.beans.*"%>

<%
	LinkedList<?> cover_contents = (LinkedList<?>) request.getAttribute("cover_contents");
	LinkedList<?> contents = (LinkedList<?>) request.getAttribute("contents");
	String[] types = {"Applicazione", "Musica", "Libro", "Film"};
	if (contents == null) {
		response.sendRedirect("home");
	}

%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport"
	content="width = device-width, initial-scale = 1.0">
<title>V-Store</title>
<link rel="icon" href="images/icons/logoIcon.png">
<link rel="stylesheet" href="css/main-style.css" type="text/css">
<link rel="stylesheet" href="css/carousel.css" type="text/css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript" src="js/slider.js"></script>
</head>

<body onload="carouselHorizontal(); carouselVertical();">
	<!-- TOP BAR -->
	<jsp:include page="template/TopBarView.jsp"></jsp:include>
	<!-- PARTE SUPERIORE -->
	<section class="topContent">
		<div id="leftPanel">
			<div id="topleftPanel">
				<%
					if (cover_contents != null)
						for (int i = 0; i < cover_contents.size(); i++) {
							ContentBean content = (ContentBean) cover_contents.get(i);
							if (content.getCover().matches("(.*)_hor(.*)")) {
				%>
				<a
					href="./ContentShowControl?action=showContent&id=<%=content.getId()%>">
					<img src=<%=content.getCover()%> class="mySlides animate-vertical">
				</a>
				<%
					}
						}
				%>
			</div>
			<div id="bottomleftPanel">
				<table>
					<tr>
						<td><a href="./ContentShowControl?action=showMostSold">Most
								Sold Apps</a></td>
						<td><a href="./ContentShowControl?action=showPopularApps">Popular
								Apps</a></td>
					</tr>
				</table>
			</div>
		</div>
		<div id="rightPanel">
			<%
				if (cover_contents != null)
					for (int i = 0; i < cover_contents.size(); i++) {
						ContentBean content = (ContentBean) cover_contents.get(i);
						if (content.getCover().matches("(.*)_ver(.*)")) {
			%>
			<a
				href="./ContentShowControl?action=showContent&id=<%=content.getId()%>">
				<img src=<%=content.getCover()%> class="mySlides animate-horizontal">
			</a>
			<%
				}
					}
			%>
		</div>
	</section>

	<!-- PARTE CENTRALE DEI CONTENUTI -->
	<%
		for (int i = 0; i < types.length; i++) {
			int count = 0;
			String type_show = null;

			switch (types[i]) {
				case "Applicazione" :
					type_show = "Apps";
					break;
				case "Musica" :
					type_show = "Musics";
					break;
				case "Film" :
					type_show = "Films";
					break;
				case "Libro" :
					type_show = "Books";
					break;
			}
	%>
	<section class="contents-section">
		<nav>
			<h3><%=type_show%></h3>
			<a href="./ContentShowControl?action=show<%=type_show%>" id="more">&#9783;Show
				more</a>
		</nav>
		<div id="<%=type_show%>" class="sliderWrapper">
			<div class="prev" onmouseenter="loopPrev('<%=type_show%>')"
				onmouseleave="stop('<%=type_show%>')">
				<img src="images/icons/previousArrow.png" />
			</div>
			<div class="next" onmouseenter="loopNext('<%=type_show%>')"
				onmouseleave="stop('<%=type_show%>')">
				<img src="images/icons/nextArrow.png" />
			</div>
			<table>
				<tr>
					<%
						if (contents != null)
								for (int k = 0; k < contents.size() && count < 15; k++) {

									ContentBean content = (ContentBean) contents.get(k);

									if (content.getType().equals(types[i])) {
										count++;
					%>
					<td>
						<div class="content-square">
							<a
								href="./ContentShowControl?action=showContent&id=<%=content.getId()%>"><img
								src="<%=content.getIcon()%>" alt="icon"></a>
							<p class="content-name"><%=content.getName()%></p>
							<%
								if (content.getPrice() == 0) {
							%>
							<p class="content-price">Gratis</p>
							<%
								} else {
							%>
							<p class="content-price">
								Price: €<%=content.getPrice()%></p>
							<%
								}
							%>
						</div>
					</td>


					<%
						}
								}
					%>
				</tr>
			</table>
		</div>
	</section>


	<%
		}
	%>

	<!-- FOOTER -->
	<jsp:include page="template/Footer.html"></jsp:include>


	<script type="text/javascript">
		document.getElementById("Home").className += " active";

		if (screen.width < 767)
			$(".sliderWrapper").css({
				'overflow' : 'auto'
			});
	</script>
	<script src="js/carousel.js"></script>

</body>
</html>