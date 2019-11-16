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
				  $("#confirmContact").modal('show');
			  },
			  error: function(data){$("#errorContact").modal("show")}
			});
	});
	
	$("#confirmContact").on('hidden.bs.modal', function(event) {
		$("#contactForm")[0].reset();
	});
	
});