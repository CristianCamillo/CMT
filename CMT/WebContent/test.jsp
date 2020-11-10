<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<script src="js/jquery.js"></script>
<script>

	$(document).on("submit", "#form", function(event)
	{		
		event.preventDefault();
		
	    var form = $("#form")[0];
	
	    var formData = new FormData(form);
		
		$.ajax
		({
			type: "post",
            enctype: "multipart/form-data",
            url: "/CMT/test",
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

</script>
</head>
<body>
<form id="form">
<input type="text" name="test">
<input name="image" type="file" accept="image/gif, image/jpeg, image/png">
<button type="submit">Invia</button>
</form>
</body>
</html>