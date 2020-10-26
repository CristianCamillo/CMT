$(document).ready(function()
{
	$.ajax
	({
		url: "filmProjection",
		type: "post",
		
        success: function(responseText)
		{			
			for(var i = 0, l = responseText.length; i < l; i++)
			{
				var film = responseText[i];
				
				var row = "<tr>" + 
						  "<td>" + film.title + "</td>" +
						  "<td>" + film.runningTime + "</td>" +
						  "<td>" + film.genre + "</td>" +
						  "<td>" + film.director + "</td>" +
						  "</tr>";
				
				$("#filmTable tbody").append(row);
			}	
        },

        error: function (xhr, ajaxOptions, thrownError)
		{
			alert("Errore filmProjection servlet");
        }
	});
});