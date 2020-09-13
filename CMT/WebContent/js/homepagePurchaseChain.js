var filmData;

function openDetailsModal(filmNumber)
{
	var film = filmData[filmNumber];
	
	document.getElementById("poster").src = "posters/" + film.poster;
	document.getElementById("title").innerHTML = film.title;
	document.getElementById("runningTime").innerHTML = film.runningTime + " min";
	document.getElementById("genre").innerHTML = film.genre;
	document.getElementById("director").innerHTML = film.director;
	document.getElementById("actor1").innerHTML = film.actor1;
	document.getElementById("actor2").innerHTML = film.actor2;
	document.getElementById("description").innerHTML = film.description;
	
	document.getElementsByName("idFilm")[0].value = film.id;
	
	document.getElementById("detailsModal").style.display = "flex";	
}

$(document).ready(function()
{
	$(document).on("submit", "#projectionsForm", function(event)
	{
		const $form = $(this);
		
		$.post($form.attr("action"), $form.serialize(), function(responseText)
		{	
			$("#projectionsTable tbody").empty();
									
			for(var i = 0, l = responseText.length; i < l; i++)
			{
				var projection = responseText[i];
				
				var row = "<tr>" + 
						  "<td>" + parseDate(projection.date) + "</td>" +
						  "<td>" + parseTime(projection.time) + "</td>" +
						  "<td>" + projection.price + "</td>" +
						  "</tr>";
				
				$("#projectionsTable tbody").append(row);
			}
			
			$("#projectionsModal").css("display", "flex");
		});
		
		event.preventDefault();
	});
});