$(document).ready(function()
{
	$(document).on("submit", "#filtreForm", function(event)
	{
		const $form = $(this);
		
		$.post($form.attr("action"), $form.serialize(), function(responseText)
		{											
			alert("aaa");
		});
		
		event.preventDefault();
	});
});