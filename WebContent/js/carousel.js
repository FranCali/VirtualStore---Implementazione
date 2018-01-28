
	var myIndex1 = 0;
	var myIndex2 = 0;
		
		
	setInterval(carouselHorizontal, 20000);
	setInterval(carouselVertical, 20000);

	function carouselHorizontal() {
		var i;
		var slides = document.getElementById("rightPanel").getElementsByClassName("mySlides");
		

		for(i = 0; i < slides.length; i++) {
			slides[i].style.display = "none";
		}
		
		myIndex1++;
		
		if(myIndex1 > slides.length) {
			myIndex1 = 1;
		}
			slides[myIndex1 - 1].style.display = "block";
				
	}
		
	function carouselVertical() {
		
		var i;
		var slides = document.getElementById("topleftPanel").getElementsByClassName("mySlides");
		
		for(i = 0; i < slides.length; i++) {
			slides[i].style.display = "none";
		}
		
		myIndex2++;
		
		if(myIndex2 > slides.length) {
			myIndex2 = 1;
		}
			slides[myIndex2 - 1].style.display = "block";			
	}
		
	