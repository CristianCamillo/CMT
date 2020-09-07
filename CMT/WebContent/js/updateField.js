function setupModal(idUser, idField)
{
	const title = document.getElementById("modalTitle");
	const input = document.getElementById("modalInput");
	const confPassword = document.getElementById('modalConfPassword');
	
	const script = document.createElement("script");
	script.src = "./fieldChecker.js";
	
	smartReset();
	
	switch(idField)
	{
		case 0: // username
			title.innerHTML = "Modifica username";
			
			input.type = "text";
			input.minlength = 6;
			input.maxlength = 20;
			
			break;
			
		case 1: //password
			title.innerHTML = "Modifica password";
			
			input.type = "password";
			input.minlength = 6;
			input.maxlength = 20;
			
			confPassword.type = "password";
			confPassword.required = true;
			
			break;
			
		case 2: // aggiunta fondi
			title.innerHTML = "Aggiunta fondi";
			
			input.type = "number";
			input.min = 0;
			input.oninput = checkIsNotNeg(input);
	}
	
	document.getElementById('modalContainer').style.display = "flex";
	
}

function smartReset()
{
	const input = document.getElementById('modalInput');
	const confPassword = document.getElementById('modalConfPassword');
	
	input.minlength = "";
	input.maxlength = "";
	input.min = "";
	confPassword.type = "hidden";
	confPassword.required = false;
	
}