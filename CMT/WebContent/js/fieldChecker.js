function checkFieldLength(name, min, max)
{
	const field = document.getElementsByName(name)[0];
	
	if(field.value.length < min || field.value.length > max)
		setRed(field);
    else
		setGreen(field);
}

function checkIsNotNeg(name)
{
	const field = document.getElementsByName(name)[0];
	
	const potNumber = parseFloat(field.value);
		
	if(potNumber.toString() !== field.value || potNumber < 0)
		setRed(field);
	else
		setGreen(field);
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