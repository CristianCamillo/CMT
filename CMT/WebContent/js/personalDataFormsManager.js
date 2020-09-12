$(document).ready(function()
{
	$(document).on("submit", "#usernameForm", function(event)
	{
		const $form = $(this);
		
		$.post($form.attr("action"), $form.serialize(), function(responseText)
		{
			if(responseText != "")
				$("#username").text(responseText);
		});
		
		event.preventDefault();
	});
	
	$(document).on("submit", "#passwordForm", function(event)
	{
		const $form = $(this);
		
		$.post($form.attr("action"), $form.serialize(), function(responseText)
		{
		});
		
		event.preventDefault();
	});
	
	$(document).on("submit", "#amountForm", function(event)
	{
		const $form = $(this);
		
		$.post($form.attr("action"), $form.serialize(), function(responseText)
		{
			if(responseText != "")
				$("#amount").text(responseText);
		});
		
		event.preventDefault();
	});
});