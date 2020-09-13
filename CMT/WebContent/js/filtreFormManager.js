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
				var img ="<img src=\"posters/" + responseText[i].poster + "\" class=\"presPoster\" onclick=\"openDetailsFrame('" + i + "')\">";				
				$("#posterContainer").append(img);
			}
		});
		
		event.preventDefault();
	});
});