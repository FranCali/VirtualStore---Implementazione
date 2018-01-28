<%@page
	import="it.unisa.model.*, java.util.*, it.unisa.util.*, it.unisa.beans.*"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String error = (String) request.getAttribute("error");
	ClientBean user = (ClientBean) session.getAttribute("user");
	ContentBean content = (ContentBean) request.getAttribute("content");
	ReviewBean user_review = (ReviewBean) request.getAttribute("user_review");
	WishlistBean wishlist = (WishlistBean) session.getAttribute("wishlist");
	LinkedList<?> reviews = (LinkedList<?>) request.getAttribute("reviews");
	LinkedList<?> downloads = (LinkedList<?>) session.getAttribute("downloads");
%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport"
	content="width = device-width, initial-scale = 1.0">

<link rel="icon" href="images/icons/logoIcon.png">
<link rel="stylesheet" href="css/main-style.css" type="text/css">
<link rel="stylesheet" href="css/form-style.css" type="text/css">

<%
	if (content != null) {
%>
<title><%=content.getName()%></title>
<%
	}
%>

</head>
<body>

	<%
		if (content != null) {
	%>

	<jsp:include page="template/TopBarView.jsp"></jsp:include>

	<section class="content-view">

		<img src="<%=content.getIcon()%>" alt="icon">


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
				Price: €<%=content.getPrice()%></p>
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

			<%
				if (user != null) {
						boolean downloaded = false;

						if (!downloads.isEmpty()) {
							for (int i = 0; i < downloads.size(); i++) {
								InfoDownload info = (InfoDownload) downloads.get(i);
								if (info.getContent().getId() == content.getId()) {
									downloaded = true;
									break;
								}
							}
						}

						if (content.getPrice() > 0) {
							if (downloaded == false) {
			%>
			<a
				href="DownloadControl?action=buyContent&email=<%=user.getAccount().getEmail()%>&id=<%=content.getId()%>"
				id="download" class="button">Buy</a>

			<%
				if (wishlist.getElementById(content.getId()) == null) {
			%>
			<a href="WishlistControl?action=insert&id=<%=content.getId()%>"
				class="button" id="add"><span
				style="font-size: large; margin-right: 5px;">&#9825;</span>Add to
				wishlist</a>
			<%
				} else {
			%>
			<a href="WishlistControl?action=remove&id=<%=content.getId()%>&page=ContentView"
				class="button" id="remove"><span
				style="font-size: large; margin-right: 5px;">&#10005;</span>Remove
				from wishlist</a>
			<%
				}

							} else {
			%>
			<a id="download" class="obtainedBtn">Obtained</a>
			<%
				if (user_review == null) {
			%>
			<a class="button" id="review"
				href="javascript: showWindow('reviewWindow')">Write a review
				&#9998;</a>
			<%
				} else {
			%>
			<a class="button" id="modify_review"
				href="javascript: showWindow('reviewWindow')">Modify your review
				&#9998;</a>
			<%
				}
							}
						} else {
							if (downloaded == false) {
			%>
			<a
				href="DownloadControl?action=downloadContent&email=<%=user.getAccount().getEmail()%>&id=<%=content.getId()%>"
				id="download" class="button">Download</a>

			<%
				if (wishlist.getElementById(content.getId()) == null) {
			%>
			<a href="WishlistControl?action=insert&id=<%=content.getId()%>"
				class="button" id="add"><span
				style="font-size: large; margin-right: 5px;">&#9825;</span>Add to
				wishlist</a>
			<%
				} else {
			%>
			<a href="WishlistControl?action=remove&id=<%=content.getId()%>&page=ContentView"
				class="button" id="remove"><span
				style="font-size: large; margin-right: 5px;">&#10005;</span>Remove
				from wishlist</a>
			<%
				}

							} else {
			%>
			<a id="download" class="obtainedBtn">Downloaded</a>

			<%
				if (user_review == null) {
			%>
			<a class="button" id="review"
				href="javascript: showWindow('reviewWindow')">Write a review
				&#9998;</a>
			<%
				} else {
			%>
			<a class="button" id="modify_review"
				href="javascript: showWindow('reviewWindow')">Modify your review
				&#9998;</a>
			<%
				}

							}
						}
			%>

			<!-- FINESTRA INSERIMENTO RECENSIONE -->


			<div id="reviewWindow">
				<%
					if (user_review == null) {
				%>
				<div>
					<h2>Write your review</h2>
					<a id="closeTag" href="javascript: closeWindow('reviewWindow')">Close
						&#10006;</a>
				</div>
				<form method="post" action="ReviewControl?&action=addReview"
					onsubmit="return controlInput(review_title) && controlInput(description)">
					Title: <input type="text" name="review_title" class="insert-input"
						oninput="controlInput(this);"> <span id="ratingTitle">Rating:</span>
					<a href="javascript: void(0)"
						onmouseenter="calculateRating(this.id)" id="star1">&#9733;</a> <a
						href="javascript: void(0)" onmouseenter="calculateRating(this.id)"
						id="star2">&#9733;</a> <a href="javascript: void(0)"
						onmouseenter="calculateRating(this.id)" id="star3">&#9733;</a> <a
						href="javascript: void(0)" onmouseenter="calculateRating(this.id)"
						id="star4">&#9733;</a> <a href="javascript: void(0)"
						onmouseenter="calculateRating(this.id)" id="star5">&#9733;</a> <br>
					<input id="rating" type="hidden" name="review_rating" value="1">
					<input type="hidden" name="content_id" value="<%=content.getId()%>">
					<input type="hidden" name="user_email"
						value="<%=user.getAccount().getEmail()%>"> <span
						id="descriptionTitle">Description:</span>
					<textarea id="description" name="review_description"
						placeholder="Enter description here..."
						oninput="controlInput(this);"></textarea>
					<input id="confirmReview" class="form-btn" type="submit"
						value="Confirm">
				</form>


				<%
					} else {
				%>
				<div>
					<h2>Modify your review</h2>
					<a id="closeTag" href="javascript: closeWindow('reviewWindow')">Close
						&#10006;</a>
				</div>
				<form method="post" action="ReviewControl?action=addReview"
					onsubmit="return controlInput(review_title) && controlInput(description)">
					Title: <input type="text" name="review_title" class="insert-input"
						oninput="controlInput(this);" value="<%=user_review.getTitle()%>"><span
						id="ratingTitle">Rating:</span> <a href="javascript: void(0)"
						onmouseenter="calculateRating(this.id)" id="star1">&#9733;</a> <a
						href="javascript: void(0)" onmouseenter="calculateRating(this.id)"
						id="star2">&#9733;</a> <a href="javascript: void(0)"
						onmouseenter="calculateRating(this.id)" id="star3">&#9733;</a> <a
						href="javascript: void(0)" onmouseenter="calculateRating(this.id)"
						id="star4">&#9733;</a> <a href="javascript: void(0)"
						onmouseenter="calculateRating(this.id)" id="star5">&#9733;</a>
					<script>
											for(i=1; i<= <%=user_review.getRating()%>; i++){
												$("#star"+i).css({
													'color': 'orange'
												});
											}
										</script>

					<br> <input id="rating" type="hidden" name="review_rating"
						value="<%=user_review.getRating()%>"> <input type="hidden"
						name="content_id" value="<%=content.getId()%>"> <input
						type="hidden" name="user_email"
						value="<%=user.getAccount().getEmail()%>"> <span
						id="descriptionTitle">Description:</span>
					<textarea id="description" name="review_description"
						placeholder="Enter description here..."
						oninput="controlInput(this);"><%=user_review.getDescription()%></textarea>
					<input id="confirmReview" class="form-btn" type="submit"
						value="Confirm">
				</form>


				<%
					}
						}
				%>
			</div>
		</div>
	</section>

	<!-- DESCRIZIONE -->
	<section id="description">
		<fieldset>
			<legend>Description</legend>
			<div class="scrollable">
				<p><%=content.getDescription()%></p>
			</div>
		</fieldset>

	</section>

	<!-- RECENSIONI -->
	<section id="reviews">
		<fieldset>
			<legend>User Reviews</legend>
			<div class="scrollable">
				<%
					if (reviews != null) {
							if (reviews.size() == 0) {
				%>
				<p style="font-weight: bold; color: red;">No Reviews</p>
				<%
					} else {

								String ratingStars = null;

								for (int i = 0; i < reviews.size(); i++) {
									ReviewBean review = (ReviewBean) reviews.get(i);

									switch (review.getRating()) {
										case 1 :
											ratingStars = "&#9733;";
											break;
										case 2 :
											ratingStars = "&#9733;" + "&#9733;";
											break;
										case 3 :
											ratingStars = "&#9733;" + "&#9733;" + "&#9733;";
											break;
										case 4 :
											ratingStars = "&#9733;" + "&#9733;" + "&#9733;" + "&#9733;";
											break;
										case 5 :
											ratingStars = "&#9733;" + "&#9733;" + "&#9733;" + "&#9733;" + "&#9733;";
											break;
									}
				%>
				<div class="review">
					<h3>
						Title:
						<%=review.getTitle()%></h3>
					<h3>
						Written By:
						<%=review.getUser_email()%></h3>
					<h3>
						Date:
						<%=review.getReview_date()%></h3>
					<h3>
						Rating: <span style="color: orange"><%=ratingStars%></span>
					</h3>
					<h3>Description:</h3>
					<p><%=review.getDescription()%></p>
				</div>
				<br>
				<%
					}
							}
						}
				%>
			</div>
		</fieldset>
	</section>

	<jsp:include page="template/Footer.html"></jsp:include>

	<%
		} else {
	%>
	<h2 style="text-align: center;">No Content Selected</h2>
	<%
		}
	%>



	<%
		if (user != null) {
	%>
	<script src="js/reviewWindow.js"></script>
	<%
		}
	%>



	<script src="js/windowAnimation.js"></script>
	<script>
		
		function controlInput(input) {
			//RIGUARDO AL TITOLO
			if(input.name == "review_title") {
				var controlTitle = /[\w]{2,20}/;
				if(!controlTitle.test(input.value)) {
					document.getElementsByName(input.name)[0].style.border = "2px solid red";
					document.getElementsByName(input.name)[0].style.outline = "none";

					return false;
				}else {
					document.getElementsByName(input.name)[0].style.border = "1px solid lightgray";
					
					return true;	
				}
			}else {
				//RIGUARDO ALLA TEXTAREA
				var controlDescription = /[.\w\s\d\D]{20,200}/;
				if(input.name == "review_description" && !controlDescription.test(input.value)) {
					document.getElementById(input.id).style.border = "2px solid red";
					document.getElementById(input.id).style.border = "2px solid red";
					
					return false;
				}else {
					document.getElementById(input.id).style.border = "2px solid white";
					return true;	
				}
			}
		}
	
	</script>
	<script>
		if(screen.width < 767){
			for(i=1; i<5; i++){
				$("#star"+i).on('click', calculateRating('star'+i));
			}
		}
	</script>
	<script>
						
		function VerifyCode() {

			var xhttp  = new XMLHttpRequest();	
			var code = document.getElementsByName('promoCode')[0].value;
		
			xhttp.onreadystatechange  = function (){
				
				if(this.readyState == 4 && this.status == 200) {
					var error = this.responseText;
					if(error != "Code not valid") {
						location.reload();	
					}
					document.getElementsByClassName('error')[0].innerHTML = error;
				}
				
			}
			
			var url = "CodePromotionalControl?content_id=<%=content.getId()%>
		&promoCode="
					+ code;
			xhttp.open("GET", url, true);
			xhttp.send();
		}
	</script>
</body>
</html>