function checkLoginForm()
{		
	const username = document.forms["form"]["username"].value;
	const password = document.forms["form"]["password"].value;
	
	return 6 <= username.length && username.length <= 20 && 6 <= password.length && password.length <= 20;
}