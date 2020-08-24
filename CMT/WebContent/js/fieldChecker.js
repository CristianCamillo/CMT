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
	
	var potNumber = parseFloat(field.value);
		
	if(potNumber.toString() !== field.value || potNumber < 0)
		setRed(field);
	else
		setGreen(field);
}

function setRed(field)
{
	field.style.borderColor = "#d02020";
	field.style.color = "#9a2f2f";
	field.style.background = "#fffbfb";
}

function setGreen(field)
{
	field.style.borderColor = "#117d11";
	field.style.color = "#1c631c";
	field.style.background = "#fbfffb";
}