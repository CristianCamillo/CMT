function checkRegisForm()
{
	const username = document.forms["form"]["username"];
	const password = document.forms["form"]["password"];
	const confPassword = document.forms["form"]["confPassword"];
	const saldo = document.forms["form"]["saldo"];
	
	return username.style.color == "rgb(0, 128, 0)" && password.style.color == "rgb(0, 128, 0)" && password.value == confPassword.value && saldo.style.color == "rgb(0, 128, 0)"; 
}