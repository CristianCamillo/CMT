$(document).ready(function()
{
	$(document).on("submit", "#form", function(event)
	{		
		event.preventDefault();
		
	    var form = $("#form")[0];
	
	    var formData = new FormData(form);
		
		$.ajax
		({
			type: "post",
            enctype: "multipart/form-data",
            url: "/CMT/addFilm",
            data: formData,
            processData: false,
            contentType: false,
            cache: false,
            timeout: 600000,
            success: function (responseText)
			{								
				alert("successo");
	        },
	        error: function (responseText)
			{				
	        	alert("errore");
	        }
	     });
	});
});