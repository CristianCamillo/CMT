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
					$("#username").text(responseText);
					$("#successLabel").html("L'username e' stato modificato con successo");
					$("#successModal").css("display", "flex");
					$("#usernameModal").css("display", "none");
				}
				else
				{
					$("#errorLabel").html("L'username indicato e' gia' memorizzato");
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
				$("#successLabel").html("La password e' stata modificata con successo");
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
			alert("qua");
			if(responseText != "")
			{
				$("#balance").text(responseText);
				$("#successLabel").html("I fondi sono stati aggiunti con successo");
				$("#successModal").css("display", "flex");				
			}	
		});
		
		event.preventDefault();
	});
});