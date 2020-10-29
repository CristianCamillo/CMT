var filmData;
var projectionData;

function selectFilm(row, idFilm)
{
	var table = document.getElementById("filmsTable");
				
	for(var i = 1; i < table.rows.length; i++)	
		table.rows[i].classList.remove("selected");
		
	row.classList.add("selected");
	
	document.getElementsByName("idFilm")[0].value = idFilm;
}

function openAddFilmModal()
{
	document.getElementById("filmModalTitle").innerHTML = "Aggiunta film";
	document.getElementById("filmModalButton").innerHTML = "Aggiungi";
	document.getElementById("filmModal").style.display = "flex";
}

function openUpdateFilmModal()
{
	document.getElementById("filmModalTitle").innerHTML = "Modifica film";
	document.getElementById("filmModalButton").innerHTML = "Modifica";
	document.getElementById("filmModal").style.display = "flex";
}

function enableFilmButtons()
{
	document.getElementById('updateFilmButton').disabled = false;
	document.getElementById('deleteFilmButton').disabled = false;
}

function loadFilms()
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
				
				var row = "<tr onclick=\"selectFilm(this, " + film.id + "); enableFilmButtons();\">" + 
						  "<td>" + film.title + "</td>" +
						  "<td>" + film.runningTime + "</td>" +
						  "<td>" + film.genre + "</td>" +
						  "<td>" + film.director + "</td>" +
						  "</tr>";
				
				$("#filmsTable tbody").append(row);
			}	
        },

        error: function (xhr, ajaxOptions, thrownError)
		{
			alert("Errore filmManager servlet");
        }
	});
}

function loadProjections()
{
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
				
				$("#projectionsTable tbody").append(row);
			}
        },

        error: function (xhr, ajaxOptions, thrownError)
		{
			alert("Errore projectionManager servlet");
        }
	});
}

$(document).ready(function()
{
	loadFilms();
	loadProjections();	
});