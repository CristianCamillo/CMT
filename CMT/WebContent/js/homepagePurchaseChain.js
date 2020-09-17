var filmData;

var selectedSeats;

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

function selectProjection(row, idProjection)
{
	var table = document.getElementById("projectionsTable");
				
	for(var i = 1; i < table.rows.length; i++)  // rows[0] is used for the header	
		table.rows[i].classList.remove("selected");
		
	row.classList.add("selected");
	
	document.getElementsByName("idProjection")[0].value = idProjection;
}

function selectSeat(seat)
{					
	if(seat.src.includes("occupied.jpg"))
		return;
		
	var price = parseInt($(".selected td:last-child").html());
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
	
	document.getElementById("basketButton").disabled = selectedSeats == 0;
}

function sendTickets()
{	
	var seats = [];
	
	var table = document.getElementById("seatsTable");
	var tbody = table.tBodies[0];

	for(var y = 0, width = tbody.rows[0].cells.length, height = tbody.rows.length; y < height; y++)
	{
		var tr = tbody.rows[y];
		
		for(var x = 0; x < width; x++)
		{
			var td = tr.cells[x];
			var seat = td.getElementsByTagName("img")[0];
			
			if(seat.src.includes("selected.jpg"))
				seats.push({x: x, y: y});
		}
	}
	
	$.ajax
	({
		url: "basket",
		type: "post",
		dataType: "json",
		data: JSON.stringify(seats),
		contentType: "application/json",
		mimeType: "application/json",
		
        success: function(responseText)
		{
			if(responseText == "0")
				$("#successModal").css("display", "flex");
        },

        error: function (xhr, ajaxOptions, thrownError)
		{
			alert(xhr + "/n" + ajaxOptions + "/n" + thrownError);
        }
	});
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
				
				var row = "<tr onclick=\"selectProjection(this, " + projection.id + "); document.getElementById('seatsButton').disabled = false;\">" + 
						  "<td>" + parseDate(projection.date) + "</td>" +
						  "<td>" + parseTime(projection.time) + "</td>" +
						  "<td>" + projection.price + "</td>" +
						  "</tr>";
				
				$("#projectionsTable tbody").append(row);
			}
			
			$("#seatsButton").prop("disabled", true);
			$("#projectionsModal").css("display", "flex");
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
					
					seat.onclick = function() { selectSeat(this); };
					
					td.appendChild(seat);
					tr.appendChild(td);
				}
				
				tbody.appendChild(tr);			
			}
				
			selectedSeats = 0;
				
			for(var i = 1, l = responseText.length; i < l; i++)
			{					
				var id = responseText[i].x + "-" + responseText[i].y;				
				
				if(!responseText[i].occupied)
					selectedSeats++;
				
				document.getElementById(id).src = "seats/" + (responseText[i].occupied ? "occupied.jpg" : "selected.jpg");
			}
			
			$("#film").html($("#title").html());
			$("#projection").html($(".selected td:first-child").html() + " - " + $(".selected td:nth-child(2)").html());
			$("#price").html(parseInt($(".selected td:last-child").html()) * selectedSeats);
	
			$("#basketButton").prop("disabled", true);
			$("#seatsModal").css("display", "flex");
		});
		
		event.preventDefault();
	});
});