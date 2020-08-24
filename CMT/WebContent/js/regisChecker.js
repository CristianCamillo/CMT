function checkRegisForm()
{
	const username = document.forms["form"]["username"].value;
	const password = document.forms["form"]["password"].value;
	const confPassword = document.forms["form"]["confPassword"].value;
	const saldo = document.forms["form"]["saldo"].value;
	
	const potNumber = parseFloat(saldo);
		
	var numIsOk = true;
		
	if(potNumber.toString() !== saldo || potNumber < 0)
		numIsOk = false;
	
	return numIsOK && 6 <= username.length && username.length <= 20 && 6 <= password.length && password.length <= 20 && password == confPassword; 
}