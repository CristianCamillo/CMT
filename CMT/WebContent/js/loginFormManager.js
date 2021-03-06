function validateLoginForm()
{
	const username = validateUsername(document.getElementsByName("username")[0]);
	const password = validatePassword(document.getElementsByName("password")[0]);
	
	return username && password;
}			

$(document).ready(function()
{
	$(document).on("submit", "#loginForm", function(event)
	{
		const $form = $(this);
		
		$.post($form.attr("action"), $form.serialize(), function(responseText)
		{	
			if(responseText === "0")
				location.href = "homepage.jsp";
			else if(responseText === "1" || responseText === "2")
			{
				var msg = "Dati non associati ad alcun ";
				if(responseText === "1")
					msg += "cliente";
				else
					msg += "gestore";
				
				$("#errorMsg").html(msg);
				$("#errorModal").css("display", "flex");
			}
		});
		
		event.preventDefault();
	});
});