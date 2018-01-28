		
	document.getElementById('star1').style.color = "orange";

	function calculateRating(star_id) {
			
			var rating = star_id.charAt(4);
			document.getElementById('rating').value = rating;
	}
	
	for(i = 0; i < 5; i++) {
		document.getElementById('star'+ (i+1)).onmouseover = function getRating(event){
			var index = event.target.id.charAt(4);
			for(j = 0; j < 5; j++) {
			if(j < index)
					document.getElementById('star'+ (j+1)).style.color = "orange";
				else
					document.getElementById('star'+ (j+1)).style.color = "lightgray";
			}
		}			
	}