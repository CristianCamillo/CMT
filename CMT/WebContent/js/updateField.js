function setupModal(idUser, idField)
{
	const title = document.getElementById("modalTitle");
	const input = document.getElementById("modalInput");
	
	smartReset();
	
	switch(idField)
	{
		case 0: // username
			title.innerHTML = "Modifica username";
			
			input.type = "text";
			input.minlength = 6;
			input.maxlength = 20;
			
			break;
			
		case 1:
			title.innerHTML = "Modifica password";
			
			input.type = "password";
			input.minlength = 6;
			input.maxlength = 20;
			
			confPassword.type = "password";
			
			break;
			
		case 2:
			title.innerHTML = "Aggiunta fondi al saldo";
			
			input.type = "number";
			input.min = 0;
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