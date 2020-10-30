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

function selectProjection(row, idProjection)
{
	var table = document.getElementById("projectionsTable");
				
	for(var i = 1; i < table.rows.length; i++)	
		table.rows[i].classList.remove("selected");
		
	row.classList.add("selected");
	
	document.getElementsByName("idProjection")[0].value = idProjection;
}

function openAddFilmModal()
{
	document.getElementsByName("idFilm")[0].value = -1;
	
	document.getElementById("filmModalTitle").innerHTML = "Aggiunta film";
	document.getElementById("filmModalButton").innerHTML = "Aggiungi";
	document.getElementById("filmForm").action = "/CMT/addFilm";
	document.getElementById("filmModal").style.display = "flex";
}

function openUpdateFilmModal()
{
	document.getElementById("filmModalTitle").innerHTML = "Modifica film";
	document.getElementById("filmModalButton").innerHTML = "Modifica";
	document.getElementById("filmForm").action = "/CMT/updateFilm";
	document.getElementById("filmModal").style.display = "flex";
}

function openDeleteFilmModal()
{
	
}

function openAddProjectionModal()
{
	document.getElementById("projectionModalTitle").innerHTML = "Aggiunta proiezione";
	document.getElementById("projectionModalButton").innerHTML = "Aggiungi";
	document.getElementById("projectionForm").action = "/CMT/addProjection";
	document.getElementById("projectionModal").style.display = "flex";
}

function openUpdateProjectionModal()
{
	document.getElementById("projectionModalTitle").innerHTML = "Modifica proiezione";
	document.getElementById("projectionModalButton").innerHTML = "Modifica";
	document.getElementById("projectionForm").action = "/CMT/updateProjection";
	document.getElementById("projectionModal").style.display = "flex";
}

function openDeleteProjectionModal()
{
	
}

function enableFilmButtons()
{
	document.getElementById('updateFilmButton').disabled = false;
	document.getElementById('deleteFilmButton').disabled = false;
}

function enableProjectionButtons()
{
	document.getElementById('updateProjectionButton').disabled = false;
	document.getElementById('deleteProjectionButton').disabled = false;
}

function validateFilmForm()
{
	const title = validateTitle(document.getElementsByName("title")[0]);
	const runningTime = validatePositiveInteger(document.getElementsByName("runningTime")[0]);
	const genre = validateNominative(document.getElementsByName("genre")[0]);
	const director = validateNominative(document.getElementsByName("director")[0]);
	const actor1 = validateNominative(document.getElementsByName("actor1")[0]);
	const actor2 = validateNominative(document.getElementsByName("actor2")[0]);
	
	return title && runningTime && genre && director && actor1 && actor2;
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
				
			$("#filmsTable tbody").empty();
				
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
			
			$("#projectionsTable tbody").empty();
			
			for(var i = 0, l = responseText.length; i < l; i++)
			{							
				var projection = responseText[i];
				
				projectionData.push({"id": projection.id,
									 "date": projection.date,
									 "time": projection.time,
									 "price": projection.price,
									 "idroom": projection.idroom,
									 "idfilm": projection.idfilm});
				
				var row = "<tr onclick=\"selectProjection(this, " + projection.id + "); enableProjectionButtons();\">" + 
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
	
	$(document).on("submit", "#filmForm", function(event)
	{
		const $form = $(this);
		
		$.post($form.attr("action"), $form.serialize(), function(responseText)
		{
			if(responseText != "")
			{
				loadFilms();
				
				if(document.getElementsByName("idFilm")[0].value == -1)
					$("#successMsg").html("Il film e' stato memorizzato");
				else
					$("#successMsg").html("Il film e' stato modificato");
				$("#successModal").css("display", "flex");
				$("#filmModal").css("display", "none");
			}
		});
		
		event.preventDefault();
	});
	
	$(document).on("submit", "#projectionForm", function(event)
	{
		const $form = $(this);
		
		$.post($form.attr("action"), $form.serialize(), function(responseText)
		{
			if(responseText != "")
			{
				loadProjections();
				
				if(document.getElementsByName("idProjection")[0].value == -1)
					$("#successMsg").html("La proiezione e' stata memorizzata");
				else
					$("#successMsg").html("La proiezione e' stata modificata");
				$("#successModal").css("display", "flex");
				$("#projectionModal").css("display", "none");
			}
		});
		
		event.preventDefault();
	});
});