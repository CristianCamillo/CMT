function validateLoginForm()
{
	const usr = validateUsername(document.getElementById("username"));
	const psw = validatePassword(document.getElementById("password"));
	
	return usr && psw;
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