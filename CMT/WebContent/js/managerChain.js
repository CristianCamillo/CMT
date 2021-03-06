var films;
var projections;

function loadFilms()
{
	$.ajax
	({
		url: "filmsLoader",
		type: "post",
		
        success: function(responseText)
		{		
			films = responseText;
				
			$("#filmsTable tbody").empty();
			$("#titles").empty();
			
			if(films.length > 0)
				$("#addProjectionButton").prop("disabled", false);
			else
				$("#addProjectionButton").prop("disabled", true);
				
			for(var i = 0, l = films.length; i < l; i++)
			{
				var film = films[i];
				
				var row = "<tr onclick=\"selectFilm(this, " + film.id + "); enableFilmButtons();\">" + 
						  "<td>" + film.title + "</td>" +
						  "<td>" + film.runningTime + "</td>" +
						  "<td>" + film.genre + "</td>" +
						  "<td>" + film.director + "</td>" +
						  "</tr>";
				
				var option = "<option id=\"" + film.id + "\" value=\"" + film.id + "\">" + film.title + "</select>";
				
				$("#filmsTable tbody").append(row);
				$("#titles").append(option);
			}	
        },

        error: function (xhr, ajaxOptions, thrownError)
		{
			alert("Errore filmsLoader servlet");
        }
	});
}

function loadProjections()
{
	$.ajax
	({
		url: "projectionsLoader",
		type: "post",
		
        success: function(responseText)
		{			
			projections = [];
			
			$("#projectionsTable tbody").empty();
			
			for(var i = 0, l = responseText.length; i < l; i++)
			{							
				var projection = responseText[i];
				
				projections.push({"id": projection.id,
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
			alert("Errore projectionsLoader servlet");
        }
	});
}

function loadRoomNumbers()
{
	$.ajax
	({
		url: "roomNumbersLoader",
		type: "post",
		
		success: function(responseText)
		{
			var roomNumbers = responseText;
			
			for(var i = 0, l = roomNumbers.length; i < l; i++)
			{
				var option = "<option value=\"" + (i + 1) + "\">" + (i + 1) + "</select>";
				
				$("#rooms").append(option);
			}
        },
		
        error: function (xhr, ajaxOptions, thrownError)
		{
			alert("Errore roomNumbers servlet");
        }
	});
}

function selectFilm(row, idFilm)
{
	var table = document.getElementById("filmsTable");
				
	for(var i = 1, l = table.rows.length; i < l; i++)	
		table.rows[i].classList.remove("selected");
		
	row.classList.add("selected");
	
	document.getElementsByName("idFilm")[0].value = idFilm;
	document.getElementsByName("idFilm")[1].value = idFilm;
	document.getElementsByName("idFilm")[2].value = idFilm;
}

function selectProjection(row, idProjection)
{
	var table = document.getElementById("projectionsTable");
				
	for(var i = 1, l = table.rows.length; i < l; i++)	
		table.rows[i].classList.remove("selected");
		
	row.classList.add("selected");
	
	document.getElementsByName("idProjection")[0].value = idProjection;
	document.getElementsByName("idProjection")[1].value = idProjection;
}

function openAddFilmModal()
{
	document.getElementsByName("idFilm")[0].value = -1;
	
	document.getElementsByName("title")[0].value = "";
	document.getElementsByName("runningTime")[0].value = "";
	document.getElementsByName("genre")[0].value = "";
	document.getElementsByName("director")[0].value = "";
	document.getElementsByName("actor1")[0].value = "";
	document.getElementsByName("actor2")[0].value = "";
	document.getElementsByName("description")[0].value = "";
	document.getElementsByName("poster")[0].value = "";
	
	document.getElementById("filmModalTitle").innerHTML = "Aggiunta film";
	document.getElementById("filmModalButton").innerHTML = "Aggiungi";
	document.getElementById("filmModal").style.display = "flex";
}

function openUpdateFilmModal()
{
	var id = parseInt(document.getElementsByName("idFilm")[1].value);

	var i = 0;
	while(films[i].id != id)
		i++;
		
	var film = films[i];

	document.getElementsByName("title")[0].value = film.title;
	document.getElementsByName("runningTime")[0].value = film.runningTime;
	document.getElementsByName("genre")[0].value = film.genre;
	document.getElementsByName("director")[0].value = film.director;
	document.getElementsByName("actor1")[0].value = film.actor1;
	document.getElementsByName("actor2")[0].value = film.actor2;
	document.getElementsByName("description")[0].value = film.description;
	document.getElementsByName("poster")[0].value = "";
	
	document.getElementById("filmModalTitle").innerHTML = "Modifica film";
	document.getElementById("filmModalButton").innerHTML = "Modifica";
	document.getElementById("filmModal").style.display = "flex";
}

function openDeleteFilmModal()
{
	document.getElementById("deleteFilmModal").style.display = "flex";
}

function openAddProjectionModal()
{
	document.getElementsByName("idProjection")[0].value = -1;
	
	document.getElementById("titles").selectedIndex = 0;
	document.getElementsByName("date")[0].value = "";
	document.getElementsByName("time")[0].value = "";
	document.getElementsByName("price")[0].value = "";
	document.getElementById("rooms").selectedIndex = 0;
	
	document.getElementById("titles").disabled = false;
	
	document.getElementById("projectionModalTitle").innerHTML = "Aggiunta proiezione";
	document.getElementById("projectionForm").action = "/CMT/addProjection";
	document.getElementById("projectionModalButton").innerHTML = "Aggiungi";
	document.getElementById("projectionModal").style.display = "flex";
}

function openUpdateProjectionModal()
{
	var id = parseInt(document.getElementsByName("idProjection")[1].value);

	var i = 0;
	while(projections[i].id != id)
		i++;
		
	var projection = projections[i];
	
	var j = 0;
	while(projection.idfilm != films[j].id)
		j++;
	
	document.getElementById("titles").selectedIndex = j;
	document.getElementsByName("date")[0].value = parseDate2(projection.date);
	document.getElementsByName("time")[0].value = parseTime(projection.time);
	document.getElementsByName("price")[0].value = projection.price;
	document.getElementById("rooms").selectedIndex = projection.idroom - 1;
	
	document.getElementById("titles").disabled = true;
	
	document.getElementById("projectionModalTitle").innerHTML = "Modifica proiezione";
	document.getElementById("projectionForm").action = "/CMT/updateProjection";
	document.getElementById("projectionModalButton").innerHTML = "Modifica";
	document.getElementById("projectionModal").style.display = "flex";
}

function openDeleteProjectionModal()
{
	document.getElementById("deleteProjectionModal").style.display = "flex";
}

function enableFilmButtons()
{
	document.getElementById("updateFilmButton").disabled = false;
	document.getElementById("deleteFilmButton").disabled = false;
}

function enableProjectionButtons()
{
	document.getElementById("updateProjectionButton").disabled = false;
	document.getElementById("deleteProjectionButton").disabled = false;
}

function disableFilmButtons()
{
	document.getElementById("updateFilmButton").disabled = true;
	document.getElementById("deleteFilmButton").disabled = true;
}

function disableProjectionButtons()
{
	document.getElementById("updateProjectionButton").disabled = true;
	document.getElementById("deleteProjectionButton").disabled = true;
}

function validateFilmForm(isUpdate)
{	
	const title = validateTitle(document.getElementsByName("title")[0]);
	const runningTime = validatePositiveInteger(document.getElementsByName("runningTime")[0]);
	const genre = validateNominative(document.getElementsByName("genre")[0]);
	const director = validateNominative(document.getElementsByName("director")[0]);
	const actor1 = validateNominative(document.getElementsByName("actor1")[0]);
	const actor2 = validateNominative(document.getElementsByName("actor2")[0]);
	const description = validateDescription(document.getElementsByName("description")[0]);
	var poster = true;
	
	if(!isUpdate)
		poster = validatePoster(document.getElementsByName("poster")[0]);
	
	return title && runningTime && genre && director && actor1 && actor2 && description && poster;
}

function validateProjectionForm()
{
	const date = validateDate(document.getElementsByName("date")[0]);
	const time = validateTime(document.getElementsByName("time")[0]);
	const price = validateNNegativeFloat(document.getElementsByName("price")[0]);
	
	return date && time && price;
}

$(document).ready(function()
{
	window.onload = function()
	{
		loadFilms();
		loadProjections();
		loadRoomNumbers();
	}
	
	$(document).on("submit", "#filmForm", function(event)
	{				
		event.preventDefault();
		
		const isUpdate = document.getElementsByName("idFilm")[0].value != -1;
		
		if(!validateFilmForm(isUpdate))
			return;
				
        var form = $("#filmForm")[0];

        var formData = new FormData(form);

        $("#filmModalButton").prop("disabled", true);
		
		$.ajax
		({
			type: "post",
            enctype: "multipart/form-data",
            url: !isUpdate ? "/CMT/addFilm" : "/CMT/updateFilm",
            data: formData,
            processData: false,
            contentType: false,
            cache: false,
            timeout: 600000,
            success: function (responseText)
			{												
				loadFilms();
				
				if(document.getElementsByName("idFilm")[0].value == -1)
					$("#successMsg").html("Il film \u00E8 stato memorizzato");
				else
					$("#successMsg").html("Il film \u00E8 stato modificato");
					
				$("#successModal").css("display", "flex");
				$("#filmModal").css("display", "none");
				
				$("#filmModalButton").prop("disabled", false);
            },
            error: function (responseText)
			{								
				alert("Errore addFilm servlet");
            	$("#filmModalButton").prop("disabled", false);
            }
        });
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
					$("#successMsg").html("La proiezione \u00E8 stata memorizzata");
				else
					$("#successMsg").html("La proiezione \u00E8 stata modificata");
				$("#successModal").css("display", "flex");
				$("#projectionModal").css("display", "none");
			}
		});
		
		event.preventDefault();
	});
	
	$(document).on("submit", "#deleteFilmForm", function(event)
	{
		const $form = $(this);
		
		$.post($form.attr("action"), $form.serialize(), function(responseText)
		{
			if(responseText != "")
			{
				loadFilms();
				loadProjections();
				disableFilmButtons();
				
				$("#successMsg").html("Il film selezionato \u00E8 stato eliminato");
				$("#successModal").css("display", "flex");
				$("#deleteFilmModal").css("display", "none");
			}
		});
		
		event.preventDefault();
	});
	
	$(document).on("submit", "#deleteProjectionForm", function(event)
	{
		const $form = $(this);
		
		$.post($form.attr("action"), $form.serialize(), function(responseText)
		{
			if(responseText != "")
			{
				loadProjections();
				disableProjectionButtons();
				
				$("#successMsg").html("La proiezione selezionata \u00E8 stata eliminata");
				$("#successModal").css("display", "flex");
				$("#deleteProjectionModal").css("display", "none");
			}
		});
		
		event.preventDefault();
	});
});