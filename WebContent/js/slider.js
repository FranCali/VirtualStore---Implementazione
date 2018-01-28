
function loopNext(id){
    $("#"+id).stop().animate({scrollLeft:'+=40'}, 'fast', 'linear', function(){ loopNext(id)});
}

function loopPrev(id){
    $("#"+id).stop().animate({scrollLeft:'-=40'}, 'fast', 'linear', function(){ loopPrev(id)});
  
}

function stop(id){
    $("#"+id).stop();
}

	
		
