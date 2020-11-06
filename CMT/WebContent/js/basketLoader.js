$(document).ready(function()
{
	$.ajax
	({
		url: "basketLoader",
		type: "post",
		
        success: function(responseText)
		{
			if(responseText.length > 0)
				$("#buyButton").prop("disabled", false);
			
			for(var i = 0, l = responseText.length; i < l; i++)
			{
				var ticket = responseText[i];
				
				var row = "<tr>" + 
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
			alert("Errore basketLoader servlet");
        }
	});
});