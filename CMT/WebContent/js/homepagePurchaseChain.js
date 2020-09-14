var filmData;
var projectionData;

var filmN;
var projectionN;

var selectedSeats;

function openDetailsModal(filmNumber)
{
	filmN = filmNumber;
	
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

function selectProjection(projectionNumber)
{
	projectionN = projectionNumber;
	
	var table = document.getElementById("projectionsTable");
				
	for(var i = 1; i < table.rows.length; i++)			
		table.rows[i].classList.remove("selected");
		
	table.rows[projectionNumber + 1].classList.add("selected");
	
	var projection = projectionData[projectionNumber];
	
	document.getElementsByName("idProjection")[0].value = projection.id;
	document.getElementsByName("idRoom")[0].value = projection.idroom;
}

function selectSeat(seat)
{					
	if(seat.src.includes("occupied.jpg"))
		return;
		
	var price = projectionData[projectionN].price;
	var totalPrice = parseInt(document.getElementById("price").innerHTML);			
	
	if(seat.src.includes("vacant.jpg"))
	{		
		seat.src = "seats/selected.jpg";
		totalPrice += price;
		
		selectedSeats++;
	}
	else
	{		
		seat.src = "seats/vacant.jpg";
		totalPrice -= price;
		
		selectedSeats--;
	}
	
	document.getElementById("price").innerHTML = totalPrice;
	
	if(selectedSeats == 0)
		document.getElementById("endSelectionButton").disabled = true;
	else
		document.getElementById("endSelectionButton").disabled = false;
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
				
				var row = "<tr onclick=\"selectProjection(" + i + "); document.getElementById('seatsButton').disabled = false;\">" + 
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
			$("#seatsTable tbody").empty();
			
			var table = document.getElementById("seatsTable");
			var tbody = table.tBodies[0];
			
			for(var y = 0, width = responseText[0].columns, height = responseText[0].rows; y < height; y++)
			{
				var tr = document.createElement('tr');
				for(var x = 0; x < width; x++)
				{
					var td = document.createElement("td");
					
					var seat = document.createElement("img");
					
					seat.setAttribute("id", x + "-" + y);
					seat.setAttribute("src", "seats/vacant.jpg");
					
					seat.onclick = function() {	selectSeat(this); };
					
					td.appendChild(seat);
					tr.appendChild(td);
				}
				
				tbody.appendChild(tr);			
			}
					
			for(var i = 1, l = responseText.length; i < l; i++)
			{					
				var id = responseText[i].columns + "-" + responseText[i].rows;
				
				document.getElementById(id).src = "seats/occupied.jpg";
			}
			
			selectedSeats = 0;
			
			$("#film").html(filmData[filmN].title);
			$("#projection").html(parseDate(projectionData[projectionN].date) + " - " + parseTime(projectionData[projectionN].time));
			$("#price").html("0");
						
			$("#endSelectionButton").prop("disabled", true);
			$("#seatsModal").css("display", "flex");
		});
		
		event.preventDefault();
	});
});