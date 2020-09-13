$(document).ready(function()
{
	$(document).on("submit", "#filtreForm", function(event)
	{
		const $form = $(this);
		
		alert("111");
		
		$.post($form.attr("action"), $form.serialize(), function(responseText)
		{											
			alert("aaa");
		});
		
		event.preventDefault();
	});
});