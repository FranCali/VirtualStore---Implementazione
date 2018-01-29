/**
 * 
 */

var window;
		
		
		function showWindow(window){
			window = document.getElementById(window);
			//document.body.style.opacity = "0.5";
			window.style.display = 'block';
			window.style.animation = 'fullScale 0.5s';
		}
		
		function closeWindow(window) {
			window = document.getElementById(window);
			window.style.animation = "disappear 0.5s";
			setTimeout(function () {window.style.display = "none";}, 400);
		}
		