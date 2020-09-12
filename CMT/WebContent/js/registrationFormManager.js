function validateRegistrationForm()
{
	const usr = validateUsername(document.getElementsByName("username")[0]);
	const psw = validatePassword(document.getElementsByName("password")[0]);
	const blc = validateBalance(document.getElementsByName("balance")[0]);
	
	return usr && psw && blc;
}

$(document).ready(function()
{
	$(document).on("submit", "#registrationForm", function(event)
	{
		const $form = $(this);
		
		$.post($form.attr("action"), $form.serialize(), function(responseText)
		{	
			if(responseText != "")				
				if(responseText === "0")
					$("#successModal").css("display", "flex");
				else
					$("#errorModal").css("display", "flex");
		});
		
		event.preventDefault();
	});
});