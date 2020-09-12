function validateRegistrationForm()
{
	var usr = validateUsername(document.getElementsByName("username")[0]);
	var psw = validatePassword(document.getElementsByName("password")[0]);
	var blc = validateBalance(document.getElementsByName("balance")[0]);
	
	return usr && psw && blc;
}

$(document).ready(function()
{
	$(document).on("submit", "#registrationForm", function(event)
	{
		var $form = $(this);
		
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