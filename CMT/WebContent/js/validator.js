function validateUsername(element)
{					   
	const str = /^(?=.{6,20}$)(?![_.])(?!.*[_.]{2})[a-zA-Z0-9._]+(?<![_.])$/;
	const valid = str.test(element.value);
	
	if(valid)
		setGreen(element);
	else
		setRed(element);
		
	return valid;	
}

function validatePassword(element)
{			   	
	const str = /^(?=.{6,20}$)(?![_.])(?!.*[_.]{2})[a-zA-Z0-9._]+(?<![_.])$/;
	const valid = str.test(element.value);
	
	if(valid)
		setGreen(element);
	else
		setRed(element);
		
	return valid;	
}

function validateBalance(element)
{	
	const potNumber = parseFloat(element.value);
	const valid = potNumber.toString() === element.value && potNumber >= 0;
	
	if(valid)
		setGreen(element);
	else
		setRed(element);
		
	return valid;	
}

function setGreen(element)
{
	element.style.borderColor = "#008000";
	element.style.color = "#008000";
	element.style.background = "#f0fff0";
}

function setRed(element)
{
	element.style.borderColor = "#b22222";
	element.style.color = "#b22222";
	element.style.background = "#fff0f0";
}