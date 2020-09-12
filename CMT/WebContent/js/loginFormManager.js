function validateLoginForm()
{
	const usr = validateUsername(document.getElementsByName("username")[0]);
	const psw = validatePassword(document.getElementsByName("password")[0]);
	
	return usr && psw;
}			

$(document).ready(function()
{
	$(document).on("submit", "#loginForm", function(event)
	{
		const $form = $(this);
		
		$.post($form.attr("action"), $form.serialize(), function(responseText)
		{											
			if(responseText != "")
				if(responseText === "0")
					window.location.href = 'homepage.jsp';
				else 
				{
					var msg = "Dati non associati ad alcun ";
					if(responseText === "1")
						msg += "cliente";
					else
						msg += "gestore";
					
					$("#errorLabel").html(msg);
					$("#errorModal").css("display", "flex");
				}
		});
		
		event.preventDefault();
	});
});