$(document).ready(function()
{
	$(document).on("submit", "#purchaseForm", function(event)
	{
		const $form = $(this);
		
		$.post($form.attr("action"), $form.serialize(), function(responseText)
		{	
			if(responseText === "0")
			{
				$("#successModal").css("display", "flex");
			}
			else if(responseText === "1")
			{
				$("#errorMsg").html("Alcuni posti sono stati gia' acquistati.<br>Rimozione posti non disponibili dal carrello...");
				$("#errorModal").css("display", "flex");
				$("#errorButton").attr("onclick", "location.href = 'basket.jsp'");
			}
			else if(responseText === "2")
			{
				$("#errorMsg").html("Saldo insufficiente");
				$("#errorModal").css("display", "flex");
			}
		});
		
		event.preventDefault();
	});
});