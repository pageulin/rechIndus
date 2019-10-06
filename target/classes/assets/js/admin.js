Object.defineProperty(HTMLMediaElement.prototype, 'playing', {
    get: function(){
        return !!(this.currentTime > 0 && !this.paused && !this.ended && this.readyState > 2);
    }
})

$(document).ready(function(event) {
	
	$("#project").click(function(){
		$("#projectContent").attr("value", "toto");
		$("#projectModal").modal("show");
	})
	
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
		var video = $(this).find("video")[0];
		if(!video.playing) {
			video.play();
		}
		else {
			video.pause();
		}
	});
	
	$(".carousel").carousel("pause");
});