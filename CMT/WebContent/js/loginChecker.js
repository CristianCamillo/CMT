function checkLoginForm()
{		
	const username = document.forms["form"]["username"];
	const password = document.forms["form"]["password"];
	
	if(username.value.length < 6 || username.value.length > 20)
    {		
		username.style.border = "2px solid red";
		username.placeholder = "porcod io";
		return false;
	}
//	return "L'username deve avere tra 6 e 20 caratteri";
    	
	
	if(password.length < 6 || password.length > 20)
    	return "La password deve avere tra 6 e 20 caratteri";
	
	return true;
}