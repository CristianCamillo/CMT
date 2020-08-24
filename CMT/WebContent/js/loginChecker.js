function checkLoginForm()
{		
	const username = document.forms["form"]["username"];
	const password = document.forms["form"]["password"];
	
	return username.style.color == "rgb(0, 128, 0)" && password.style.color == "rgb(0, 128, 0)";
}