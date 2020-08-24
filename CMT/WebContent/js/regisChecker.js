function checkRegisForm()
{
	var username = document.forms["form"]["username"].value;
	var password = document.forms["form"]["password"].value;
	var confPassword = document.forms["form"]["confPassword"].value;
	var saldo = document.forms["form"]["saldo"].value;
	
	var potNumber = parseFloat(saldo);
		
	var numIsOk = true;
		
	if(potNumber.toString() !== saldo || potNumber < 0)
		numIsOk = false;
	
	return numIsOK && 6 <= username.value.length && username.value.length <= 20 && 6 <= password.value.length && password.value.length <= 20 &&
		   password.value == confPassword.value; 
}