var filmData;
var projectionData;
var roomNumbers;

function loadFilms()
{
	$.ajax
	({
		url: "filmsLoader",
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
			roomNumbers = responseText;
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
	
	document.getElementById("title").value = "";
	document.getElementById("runningTime").value = "";
	document.getElementById("genre").value = "";
	document.getElementById("director").value = "";
	document.getElementById("actor1").value = "";
	document.getElementById("actor2").value = "";
	document.getElementById("description").value = "";
	
	document.getElementById("filmModalTitle").innerHTML = "Aggiunta film";
	document.getElementById("filmModalButton").innerHTML = "Aggiungi";
	document.getElementById("filmModal").style.display = "flex";
}

function openUpdateFilmModal()
{
	var id = parseInt(document.getElementsByName("idFilm")[0].value);

	document.getElementById("title").value = filmData[id].title;
	document.getElementById("runningTime").value = filmData[id].runningTime;
	document.getElementById("genre").value = filmData[id].genre;
	document.getElementById("director").value = filmData[id].director;
	document.getElementById("actor1").value = filmData[id].actor1;
	document.getElementById("actor2").value = filmData[id].actor2;
	document.getElementById("description").value = filmData[id].description;
	document.getElementById("poster").value = "";
	
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
	document.getElementById("projectionModalTitle").innerHTML = "Aggiunta proiezione";
	document.getElementById("projectionModalButton").innerHTML = "Aggiungi";
	document.getElementById("projectionModal").style.display = "flex";
}

function openUpdateProjectionModal()
{
	document.getElementById("projectionModalTitle").innerHTML = "Modifica proiezione";
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
	const title = validateTitle(document.getElementById("title"));
	const runningTime = validatePositiveInteger(document.getElementById("runningTime"));
	const genre = validateNominative(document.getElementById("genre"));
	const director = validateNominative(document.getElementById("director"));
	const actor1 = validateNominative(document.getElementById("actor1"));
	const actor2 = validateNominative(document.getElementById("actor2"));
	const description = validateDescription(document.getElementById("description"));
	const poster = validatePoster(document.getElementById("poster"));
	
	return title && runningTime && genre && director && actor1 && actor2 && description && (poster || isUpdate);
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
		
		const isUpdate = document.getElementsByName("idFilm")[0].value == -1;
		
		if(!validateFilmForm(isUpdate))
			return;
				
        var form = $("#filmForm")[0];

        var formData = new FormData(form);

        $("#filmModalButton").prop("disabled", true);
		
		$.ajax
		({
			type: "post",
            enctype: "multipart/form-data",
            url: isUpdate ? "/CMT/addFilm" : "/CMT/updateFilm",
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
	/*
	$("#filmModalButton").click(function (event)
	{
        event.preventDefault();

        var form = $('#filmForm')[0];

        // Create an FormData object 
        var data = new FormData(form);

        // If you want to add an extra field for the FormData
        data.append("CustomField", "This is some extra data, testing");

        // disabled the submit button
        $("#filmModalButton").prop("disabled", true);

        $.ajax({
            type: "POST",
            enctype: 'multipart/form-data',
            url: "/CMT/addFilm",
            data: data,
            processData: false,
            contentType: false,
            cache: false,
            timeout: 600000,
            success: function (data) {
			loadFilms();
				
				if(document.getElementsByName("idFilm")[0].value == -1)
					$("#successMsg").html("Il film e' stato memorizzato");
				else
					$("#successMsg").html("Il film e' stato modificato");
				$("#successModal").css("display", "flex");
				$("#filmModal").css("display", "none");
				
				$("#filmModalButton").prop("disabled", false);
            },
            error: function (e) {

                $("#result").text(e.responseText);
                console.log("ERROR : ", e);
               $("#filmModalButton").prop("disabled", false);

            }
        });

    });*/
	
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