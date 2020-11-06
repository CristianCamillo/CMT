function validateRegistrationForm()
{
	const usr = validateUsername(document.getElementById("username"));
	const psw = validatePassword(document.getElementById("password"));
	const blc = validateNNegativeFloat(document.getElementById("balance"));
	
	return usr && psw && blc;
}

$(document).ready(function()
{
	$(document).on("submit", "#registrationForm", function(event)
	{
		const $form = $(this);
		
		$.post($form.attr("action"), $form.serialize(), function(responseText)
		{				
			if(responseText === "0")
				$("#successModal").css("display", "flex");
			else if(responseText === "1")
				$("#errorModal").css("display", "flex");
		});
		
		event.preventDefault();
	});
});