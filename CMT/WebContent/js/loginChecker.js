function checkLoginForm()
{		
	const username = document.forms["form"]["username"];
	const password = document.forms["form"]["password"];
	
	return 6 <= username.value.length && username.value.length <= 20 && 6 <= password.value.length && password.value.length <= 20;
}