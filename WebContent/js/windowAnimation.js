/**
 * 
 */

var window;
		
		
		function showWindow(window){
			window = document.getElementById(window);
			//document.body.style.opacity = "0.5";
			window.style.display = 'block';
			window.style.animation = 'fullScale 1s';
		}
		
		function closeWindow(window) {
			window = document.getElementById(window);
			window.style.animation = "disappear 1s";
			setTimeout(function () {window.style.display = "none";}, 800);
		}
		