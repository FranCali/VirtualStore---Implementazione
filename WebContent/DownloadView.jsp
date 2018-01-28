<%@page import="javax.sound.midi.MidiDevice.Info"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="java.util.*, it.unisa.model.*, it.unisa.util.*"%>

<%
	LinkedList<?> downloads = (LinkedList<?>) session.getAttribute("downloads");
%>

<jsp:include page="template/TopBarView.jsp"></jsp:include>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport"
	content="width = device-width, initial-scale = 1.0">

<link rel="stylesheet" href="css/main-style.css" type="text/css">
<title>Download Section</title>
</head>
<body>

	<section class="contents-table">
		<%
			if (!downloads.isEmpty()) {
				for (int i = 0; i < downloads.size(); i++) {
					InfoDownload info = (InfoDownload) downloads.get(i);
		%>
		<div class="content-square">
			<a
				href="./ContentShowControl?action=showContent&id=<%=info.getContent().getId()%>"><img
				src="<%=info.getContent().getIcon()%>" alt="icon"></a>
			<p class="content-name"><%=info.getContent().getName()%></p>
			<p>
				Downloaded on:
				<%=info.getDate()%></p>
		</div>
		<%
			}
			} else {
		%>
		<h2 style="text-align: center; margin-top: 20%">No content
			downloaded</h2>
		<%
			}
		%>
	</section>


</body>
</html>