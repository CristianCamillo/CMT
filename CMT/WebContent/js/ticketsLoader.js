$(document).ready(function()
{
	$.ajax
	({
		url: "tickets",
		type: "post",
		
        success: function(responseText)
		{			
			for(var i = 0, l = responseText.length; i < l; i++)
			{
				var ticket = responseText[i];
				
				var row = "<tr>" + 
						  "<td>" + ticket.id + "</td>" +
						  "<td>" + ticket.title + "</td>" +
						  "<td>" + parseDate(ticket.date) + "</td>" +
						  "<td>" + parseTime(ticket.time) + "</td>" +
						  "<td>" + ticket.room + "</td>" +
						  "<td>" + parseSeat(ticket.x, ticket.y) + "</td>" +
						  "<td>" + ticket.price + "</td>" +
						  "</tr>";
				
				$("#ticketTable tbody").append(row);
			}	
        },

        error: function (xhr, ajaxOptions, thrownError)
		{
			alert("Errore");
        }
	});
});