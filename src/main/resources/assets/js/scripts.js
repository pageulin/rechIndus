Object.defineProperty(HTMLMediaElement.prototype, 'playing', {
    get: function(){
        return !!(this.currentTime > 0 && !this.paused && !this.ended && this.readyState > 2);
    }
})

$(document).ready(function(event) {
	$("#contactForm").submit(function(event) {
		event.preventDefault();
		console.log($(this).serializeArray());
		var parametersArray = $(this).serializeArray();
		var contactObj = {};
		for(var i=0; i < parametersArray.length; ++i) {
			contactObj[parametersArray[i].name] = parametersArray[i].value;
		};
		console.log(contactObj);
		$.ajax({
			  url:"/signup",
			  type:"POST",
			  data:JSON.stringify(contactObj),
			  contentType:"application/json; charset=utf-8",
			  dataType:"json",
			  success: function(data){
				  $("#contactForm").fadeOut();
			  },
			  fail: function(data){alert(data);}
			});

	});
	
	$("#authentForm").submit(function(event) {
		event.preventDefault();
		console.log($(this).serializeArray());
		var parametersArray = $(this).serializeArray();
		var contactObj = {};
		for(var i=0; i < parametersArray.length; ++i) {
			contactObj[parametersArray[i].name] = parametersArray[i].value;
		};
		console.log(contactObj);
		$.ajax({
			  url:"/login",
			  type:"POST",
			  data:JSON.stringify(contactObj),
			  contentType:"application/json; charset=utf-8",
			  dataType:"json",
			  success: function(data){
				  $("#contactForm").fadeOut();
			  },
			  fail: function(data){alert(data);}
			});

	});
	
	$(".navbar-nav .nav-link").click(function(event){
		event.preventDefault();
		$(".navbar-toggler").click();
		var page = $(this).attr('href'); // Page cible
		var speed = 750; // Durée de l'animation (en ms)
		console.log($(page).offset());
		$('html, body').animate( { scrollTop: $(page).offset().top }, speed ); // Go
		return false;
	});
	
	$(".carousel-item").click(function(event) {
		console.log("click carousel")
		var video = $(this).find("video")[0];
		if(video) {
			if(!video.playing) {
				video.play();
			}
			else {
				video.pause();
			}
		}
	});
	
	$(".carousel").carousel("pause");
});