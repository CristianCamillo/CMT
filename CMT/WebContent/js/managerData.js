var filmData;
var projectionData;

$(document).ready(function()
{
	$.ajax
	({
		url: "filmManager",
		type: "post",
		
        success: function(responseText)
		{		
			filmData = responseText;
				
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
			alert("Errore filmManager servlet");
        }
	});
	
	$.ajax
	({
		url: "projectionManager",
		type: "post",
		
        success: function(responseText)
		{			
			projectionData = [];
			
			for(var i = 0, l = responseText.length; i < l; i++)
			{							
				var projection = responseText[i];
				
				projectionData.push({"id": projection.id,
									 "date": projection.date,
									 "time": projection.time,
									 "price": projection.price,
									 "idroom": projection.idroom,
									 "idfilm": projection.idfilm});
				
				var row = "<tr>" + 
						  "<td>" + projection.title + "</td>" +
						  "<td>" + parseDate(projection.date) + "</td>" +
						  "<td>" + parseTime(projection.time) + "</td>" +
						  "<td>" + projection.price + "</td>" +
						  "<td>" + projection.idroom + "</td>" +
						  "</tr>";
				
				$("#projectionTable tbody").append(row);
			}
        },

        error: function (xhr, ajaxOptions, thrownError)
		{
			alert("Errore projectionManager servlet");
        }
	});
});