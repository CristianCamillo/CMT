var filmData;
var projectionData;

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

function selectProjection(tr)
{
	var table = document.getElementById("projectionsTable");
				
	for(var i = 1; i < table.rows.length; i++)			
		table.rows[i].classList.remove("selected");
		
	tr.classList.add("selected");
	
	var ids= (tr.id + "").split(".");
	
	document.getElementsByName("idProjection")[0].value = ids[0];
	document.getElementsByName("idRoom")[0].value = ids[1];
}

$(document).ready(function()
{
	window.onload = function()
	{
		$("#filtreForm").submit();
	}
	
	$(document).on("submit", "#filtreForm", function(event)
	{
		const $form = $(this);
		
		$.post($form.attr("action"), $form.serialize(), function(responseText)
		{	
			$("#posterContainer").empty();
			
			for(var i = 0, l = responseText.length; i < l; i++)
			{
				var img ="<img src=\"posters/" + responseText[i].poster + "\" class=\"presPoster\" onclick=\"openDetailsModal(" + i + ")\">";				
				$("#posterContainer").append(img);
			}
			
			filmData = responseText;
		});
		
		event.preventDefault();
	});
	
	$(document).on("submit", "#projectionsForm", function(event)
	{
		const $form = $(this);
		
		$.post($form.attr("action"), $form.serialize(), function(responseText)
		{	
			$("#projectionsTable tbody").empty();
									
			for(var i = 0, l = responseText.length; i < l; i++)
			{
				var projection = responseText[i];
				
				var row = "<tr id=\"" + projection.id + "." + projection.idroom + "\" onclick=\"selectProjection(this); document.getElementById('seatsButton').disabled = false;\">" + 
						  "<td>" + parseDate(projection.date) + "</td>" +
						  "<td>" + parseTime(projection.time) + "</td>" +
						  "<td>" + projection.price + "</td>" +
						  "</tr>";
				
				$("#projectionsTable tbody").append(row);
			}
			
			$("#seatsButton").prop("disabled", true);
			$("#projectionsModal").css("display", "flex");
			
			projectionData = responseText;
		});
		
		event.preventDefault();
	});
	
	$(document).on("submit", "#seatsForm", function(event)
	{
		const $form = $(this);
		
		$.post($form.attr("action"), $form.serialize(), function(responseText)
		{	
			alert(responseText[0].rows);
		});
		
		event.preventDefault();
	});
});