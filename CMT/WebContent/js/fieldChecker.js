function checkFieldLength(caller, min, max)
{	
	if(caller.value.length < min || caller.value.length > max)
		setRed(caller);
    else
		setGreen(caller);
}

function checkPswMatching(password, confPassword)
{
	const psw = document.getElementsByName(password)[0];
	const cnfPsw = document.getElementsByName(confPassword)[0];
	
	if(psw.value == cnfPsw.value)
		setGreen(cnfPsw);
	else
		setRed(cnfPsw);	
}

function checkIsNotNeg(caller)
{
	const field = document.getElementsByName(name)[0];
	
	const potNumber = parseFloat(caller.value);
		
	if(potNumber.toString() !== caller.value || potNumber < 0)
		setRed(caller);
	else
		setGreen(caller);
}

function setRed(field)
{
	field.style.borderColor = "#b22222";
	field.style.color = "#b22222";
	field.style.background = "#fffbfb";
}

function setGreen(field)
{
	field.style.borderColor = "#008000";
	field.style.color = "#008000";
	field.style.background = "#fbfffb";
}

export { checkFieldLength, checkPswMatching, checkIsNotNeg };