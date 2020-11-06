$(document).ready(function()
{
	$(document).on("submit", "#usernameForm", function(event)
	{
		const $form = $(this);
		
		$.post($form.attr("action"), $form.serialize(), function(responseText)
		{
			if(responseText != "")
				if(responseText != "1")
				{
					$("#username").html(responseText);
					$("#successMsg").html("L'username \u00E8 stato modificato");
					$("#successModal").css("display", "flex");
					$("#usernameModal").css("display", "none");
				}
				else
				{
					$("#errorMsg").html("L'username indicato \u00E8 gi\u00E0 utilizzato");
					$("#errorModal").css("display", "flex");
				}
		});
		
		event.preventDefault();
	});
	
	$(document).on("submit", "#passwordForm", function(event)
	{
		const $form = $(this);
		
		$.post($form.attr("action"), $form.serialize(), function(responseText)
		{
			if(responseText != "")
			{
				$("#successMsg").html("La password \u00E8 stata modificata");
				$("#successModal").css("display", "flex");
				$("#passwordModal").css("display", "none");
			}
		});
		
		event.preventDefault();
	});
	
	$(document).on("submit", "#amountForm", function(event)
	{
		const $form = $(this);
		
		$.post($form.attr("action"), $form.serialize(), function(responseText)
		{
			if(responseText != "")
			{
				$("#balance").html(responseText);
				$("#successMsg").html("I fondi sono stati aggiunti");
				$("#successModal").css("display", "flex");
				$("#amountModal").css("display", "none");			
			}	
		});
		
		event.preventDefault();
	});
});