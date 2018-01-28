<%@ 
	page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="it.unisa.model.*, java.util.*"%>

<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport"
	content="width = device-width, initial-scale = 1.0">

<link rel="stylesheet" href="../css/main-style.css" type="text/css">
<link rel="stylesheet" href="../css/form-style.css" type="text/css">
<link rel="stylesheet" href="../css/administration.css" type="text/css">

<link rel="icon" href="images/icons/logoIcon.png">
<title>Control Panel</title>

<style type="text/css">
</style>

</head>
<body>

	<header>
		<a id="home-btn" href="/VirtualStoreIS/home"><img
			src="../images/icons/home-icon.png" alt="back to home"></a>
		<h1 class="title">Administration Panel</h1>
	</header>

<!--  
	<div id="controlMenu" class="dock">
		<ul>
			<li><a
				href="javascript: retrieveTable('manage-user'); createWindow('manage-user');"><img
					src="../images/icons/removeUserIcon.png"></a></li>
			<li><a
				href="javascript: retrieveTable('manage-content'); createWindow('manage-content');"><img
					src="../images/icons/addRemoveContentIcon.png"></a></li>
			<li><a href="javascript: createWindow('manage-review');"><img
					src="../images/icons/removeReview.png"></a></li>
		</ul>
	</div>
-->
	<div class="window-container"></div>

	<section id = "grid-menu">
		<table id = "administration-panel">
			<tr>
				<td><a href = "javascript: retrieveTable('manage-content'); createWindow('manage-content');">Add/Remove<br> Content</a></td>
				<td><a href = "javascript: retrieveTable('manage-content'); createWindow('manage-content');">Add/Remove<br> Content</a></td>
			</tr>
			<tr>
				<td><a href = "javascript: retrieveTable('manage-user'); createWindow('manage-user');">Remove<br> User</a></td>
				<td><a href = "javascript: createWindow('manage-review');">Remove Review</a></td>
			</tr>
		</table>
	</section>


	<script>
		function retrieveTable(window_id) {
			var xhttp = new XMLHttpRequest();
			var elements = 'undefined';

			xhttp.onreadystatechange = function() {

				if (this.readyState == 4 && this.status == 200) {
					if (this.responseText != "")
						elements = JSON.parse(this.responseText);

					var table = document.createElement('TABLE');

					if (elements != 'undefined') {

						if (elements[0].hasOwnProperty('user_name')) {

							table.id = "users-table";

							var row, headerName, headerSurname, headerEmail, headerAction, col1, col2, col3, col4;

							row = document.createElement('TR');
							headerName = document.createElement('TH');
							headerSurname = document.createElement('TH');
							headerEmail = document.createElement('TH');
							headerAction = document.createElement('TH');

							headerName.innerHTML = "Name";
							headerSurname.innerHTML = "Surname";
							headerEmail.innerHTML = "Email";
							headerAction.innerHTML = "Action";

							row.appendChild(headerName);
							row.appendChild(headerSurname);
							row.appendChild(headerEmail);
							row.appendChild(headerAction);

							table.appendChild(row);

							for (i = 0; i < elements.length; i++) {

								row = document.createElement('TR');

								col1 = document.createElement('TD');
								col2 = document.createElement('TD');
								col3 = document.createElement('TD');
								col4 = document.createElement('TD');

								col1.innerHTML = elements[i].user_name;
								col2.innerHTML = elements[i].user_surname;
								col3.innerHTML = elements[i].user_email;
								col4.innerHTML = '<a href = "javascript: removeRow(\''
										+ elements[i].user_email
										+ '\', \'user\');"'
										+ ' class = \"remove-btn\">Delete</a>';

								row.appendChild(col1);
								row.appendChild(col2);
								row.appendChild(col3);
								row.appendChild(col4);

								table.appendChild(row);
							}

							document.getElementsByClassName('main-table')[0]
									.appendChild(table);
							

						} else if (elements[0].hasOwnProperty('content_name')) {

							table.id = "contents-table";

							var row, col1, col2;

							for (i = 0; i < elements.length; i++) {

								row = document.createElement('TR');

								col1 = document.createElement('TD');
								col2 = document.createElement('TD');

								col1.innerHTML = elements[i].content_name;
								col2.innerHTML = '<a href = "javascript: removeRow('
										+ elements[i].content_id
										+ ', \'content\');" class = \"remove-btn\">Delete</a>';

								row.appendChild(col1);
								row.appendChild(col2);

								table.appendChild(row);

							}
							document.getElementsByClassName('main-table')[0]
									.appendChild(table);


						} else if (elements[0].hasOwnProperty('title')) {

							table.id = "reviews-table";

							var row, col1, col2, col3, col4, col5, headerTitle, headerAuthor, headerDescription, headerAction;

							row = document.createElement('TR');
							headerTitle = document.createElement('TH');
							headerAuthor = document.createElement('TH');
							headerDescription = document.createElement('TH');
							headerRating = document.createElement('TH');
							headerAction = document.createElement('TH');

							headerTitle.innerHTML = "Title";
							headerAuthor.innerHTML = "Author";
							headerDescription.innerHTML = "Description";
							headerRating.innerHTML = "Rating";
							headerAction.innerHTML = "Action";

							row.appendChild(headerTitle);
							row.appendChild(headerAuthor);
							row.appendChild(headerDescription);
							row.appendChild(headerRating);
							row.appendChild(headerAction);

							table.appendChild(row);

							document.getElementsByClassName('main-table')[0]
									.appendChild(table);

							for (i = 0; i < elements.length; i++) {

								row = document.createElement('TR');

								table.appendChild(row);

								col1 = document.createElement('TD');
								col2 = document.createElement('TD');
								col3 = document.createElement('TD');
								col4 = document.createElement('TD');
								col5 = document.createElement('TD');

								row.appendChild(col1);
								row.appendChild(col2);
								row.appendChild(col3);
								row.appendChild(col4);
								row.appendChild(col5);

								col1.innerHTML = elements[i].title;
								col2.innerHTML = elements[i].author;
								col3.innerHTML = elements[i].description;
								col4.innerHTML = elements[i].rating;

								col5.innerHTML = '<a href = "javascript: removeReview('
										+ elements[i].review_id
										+ ',\''
										+ elements[i].author
										+ '\' )" class = "remove-btn">Delete</a>';

							}

						} 
						
					}
				}

			}

			switch (window_id) {
			case 'manage-content':
				xhttp.open("GET",
						"../ControlPanelTables?action=createContentsTable",
						true);
				break;
			case 'manage-user':
				xhttp.open("GET",
						"../ControlPanelTables?action=createUsersTable", true);
				break;
			case 'manage-review':
				var search = document.getElementById('contentNameInput').value;
				xhttp.open("GET",
						"../ControlPanelTables?action=createReviewsTable&search="
								+ search, true);
				break;
			}
			xhttp.send();
		}

		function flushTable(table_id) {
			var table = document.getElementById(table_id);
			if (table != null)
				table.parentNode.removeChild(table);
		}

		//richiesta asincrona per eliminare la riga

		function removeRow(elementId, table) {
			var xhttp = new XMLHttpRequest();

			if (table == 'user') {

				xhttp.onreadystatechange = function() {

					if (this.readyState == 4 && this.status == 200) {
						var email = this.responseText;
						var table = document.getElementById('users-table');
						var rows = table.childNodes;

						for (k = 0; k < rows.length; k++) {
							if (rows[k].childNodes[2].innerHTML == email) {
								table.removeChild(table.childNodes[k]);
							}
						}
					}
				}
				xhttp.open("GET",
						"../AdministrationControl?action=deleteUser&email="
								+ elementId, true);
				xhttp.send();

			} else if (table == 'content') {

				xhttp.onreadystatechange = function() {

					if (this.readyState == 4 && this.status == 200) {
						var content = JSON.parse(this.responseText);
						var table = document.getElementById('contents-table');
						var rows = table.childNodes;

						for (k = 0; k < rows.length; k++) {
							if (rows[k].childNodes[0].innerHTML == content.content_name) {
								table.removeChild(table.childNodes[k]);
							}
						}
					}
				}
				xhttp.open("GET",
						"../AdministrationControl?action=deleteContent&id="
								+ elementId, true);
				xhttp.send();
			}
		}

		//richiesta asincrona per aggiungere la riga

		function addContentRow(name, type, description, price, size, piva, icon) {

			var name = document.getElementsByName(name)[0].value;
			var type = document.getElementsByName(type)[0].value;
			var description = document.getElementsByName(description)[0].value;
			var price = document.getElementsByName(price)[0].value;
			var size = document.getElementsByName(size)[0].value;
			var piva = document.getElementsByName(piva)[0].value;
			var icon = document.getElementsByName(icon)[0].value;

			var xhttp = new XMLHttpRequest();

			xhttp.onreadystatechange = function() {

				if (this.readyState == 4 && this.status == 200) {

					flushTable('delete-table');
					retrieveTable('manage-content');
				}
			}

			var url = "../AdministrationControl?action=addContent&name=" + name
					+ "&type=" + type + "&description=" + description
					+ "&price=" + price + "&size=" + size + "&piva=" + piva
					+ "&icon=" + icon;

			xhttp.open("GET", url, true);
			xhttp.send();

		}

		function addCode(input, content_id) {

			var code_id = input.value;

			var xhttp = new XMLHttpRequest();

			xhttp.onreadystatechange = function() {
				if (this.readyState == 4 && this.status == 200) {
					input.value = this.responseText;
				}
			}

			var url = "../AdministrationControl?action=addCode&code_id="
					+ code_id + "&content_id=" + content_id;
			xhttp.open("GET", url, true);
			xhttp.send();
		}

		function removeCode(input) {

			var code_id = input.value;

			var xhttp = new XMLHttpRequest();

			xhttp.onreadystatechange = function() {
				if (this.readyState == 4 && this.status == 200) {
					input.value = this.responseText;
				}
			}

			var url = "../AdministrationControl?action=deleteCode&code_id="
					+ code_id;
			xhttp.open("GET", url, true);
			xhttp.send();
		}

		function removeReview(contentId, userEmail) {

			var xhttp = new XMLHttpRequest();

			xhttp.onreadystatechange = function() {
				if (this.readyState == 4 && this.status == 200) {

					var email = this.responseText;
					console.log(email);
					var table = document.getElementById('reviews-table');
					var rows = table.childNodes;

					for (k = 0; k < rows.length; k++) {
						if (rows[k].childNodes[1].innerHTML == email) {
							table.removeChild(table.childNodes[k]);
						}
					}
				}
			}

			var url = "../AdministrationControl?action=deleteReview&content_id="
					+ contentId + "&email=" + userEmail;
			xhttp.open("GET", url, true);
			xhttp.send();

		}
	</script>

	<script>
		var container = document.getElementsByClassName('window-container')[0];

		function createWindow(window_id) {

			container.id = window_id;

			if (window_id == "manage-content") {

				container.innerHTML = '<header>'
						+ '<h2>Add/Remove Content</h2>'
						+ '<a id = "closeTag" href = "javascript: closeWindow(\''
						+ window_id
						+ '\')">&#10006;</a>'
						+ '</header>'
						+ '<div id = "input-fields">'
						+ '<input type = "hidden" name = "action" value="addContent">'
						+ 'Name: <input type = "text" name = "nome" size = "20">'
						+ 'Price: <input type = "text" name = "prezzo" size = "2">'
						+ 'Size: <input type = "text" name = "dimensione" size = "3">'
						+ 'PIVA: <input type = "text" name = "piva" size = "11">'
						+ 'Type: <select onchange = "refreshPath(this.value)" name = "tipo">'
						+ '<option value = "Applicazione">Application</option>'
						+ '<option value = "Musica">Music</option>'
						+ '<option value = "Film">Film</option>'
						+ '<option value = "Libro">Book</option>'
						+ '</select><br><br>'
						+ 'Icon: <input name = "icona" type = "text" size = "40">'
						+ 'Description: <input type = "text" name = "descrizione">'
						+ '<a href= "javascript: addContentRow(\'nome\', \'tipo\', \'descrizione\', \'prezzo\', \'dimensione\', \'piva\', \'icona\')" class = "form-btn">Add</a>'
						+ '</div>' + '<div class = "main-table">' + '</div>';

				document.getElementsByName('icona')[0].value = "images/contents_images/apps/";

			} else if (window_id == "manage-user") {

				container.innerHTML = '<header>'
						+ '<h2>Remove User</h2>'
						+ '<a id = "closeTag" href = "javascript: closeWindow(\''
						+ window_id + '\')">&#10006;</a>' + '</header>'
						+ '<div class = "main-table">' + '</div>';

				/*PER IMPLEMENTAZIONI FUTURE
				+'<form method = "post" action = "AdministrationControl">'
				+'<input type = "hidden" name = "action" value = "removeUser">'
				+'User Email: <input type = "text" class = "insert-input" name = "user_email">'
				+'<input type = "submit" value = "confirm">'
				+'</form>';*/

			} else if (window_id == "manage-review") {

				container.innerHTML = '<header>'
						+ '<h2>Remove Review</h2>'
						+ '<a id = "closeTag" href = "javascript: closeWindow(\''
						+ window_id
						+ '\')">&#10006;</a>'
						+ '</header>'
						+ '<div id = "input-fields">'
						+ 'Name: <input id = "contentNameInput" type = "text" name = "nome" size = "20" autocomplete = "off" placeholder = "enter content name here">'
						+ '<a href= "javascript: flushTable(\'reviews-table\');retrieveTable(\'manage-review\');" class = "form-btn">Search</a>'
						+ '</div>' + '<div class = "main-table">' + '</div>';

			} else if (window_id == "manage-code") {

				container.innerHTML = '<header>'
						+ '<h2>Add/Remove Promotional Code</h2>'
						+ '<a id = "closeTag" href = "javascript: closeWindow(\''
						+ window_id + '\')">&#10006;</a>' + '</header>'
						+ '<div class = "main-table">' + '_$tag_';
			} else if (window_id == "manage-content-choice") {

				container.innerHTML = '_$tag___'
						+ '_$taSelect Team Choice_$tag'
						+ '<a id = "closeTag" href = "javascript: closeWindow(\''
						+ window_id
						+ '\')">&#10006;_$ta'
						+ '_$tag____'
						+ '_$tag_____________________'
						+ '_$tag_'
						+ '_$tag___________________'
						+ 'Number of Contents: _$tag__________________________________________________________________________'
						+ '<a href = "javascript: addTeamChoice()" class = "form-btn">Confirm Selection_$ta'
						+ '_$tag_';

			}

			showWindow(window_id);

		}

		function addTeamChoice() {
			var xhttp = new XMLHttpRequest();
			var myJSON = JSON.stringify(contents);

			xhttp.open("GET",
					"../AdministrationControl?action=createTeamChoice&idList="
							+ myJSON, true);
			xhttp.send();

			console.log(contents);
		}

		function refreshPath(value) {
			var type;
			switch (value) {
			case 'Applicazione':
				type = 'apps';
				break;
			case 'Musica':
				type = 'music';
				break;
			case 'Film':
				type = 'films';
				break;
			case 'Libro':
				type = 'books';
				break;

			}

			document.getElementsByName('icona')[0].value = "images/contents_images/"
					+ type + "/";
		}
	</script>

	<script src="../js/formcheck.js"></script>
	<script src="../js/windowAnimation.js"></script>


</body>
</html>