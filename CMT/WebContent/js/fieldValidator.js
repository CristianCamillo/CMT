function validateUsername(element)
{						   
	const str = /^(?=.{6,20}$)(?![_.0-9])(?!.*[_.]{2})[a-zA-Z0-9._]+(?<![_.])$/;
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

function validateNNegativeFloat(element)
{	
	const f = parseFloat(element.value);	
	const valid = f.toString() === element.value && f >= 0 && (f * 100) == parseInt(f * 100);
	
	if(valid)
		setGreen(element);
	else
		setRed(element);
	
	return valid;	
}

function validatePositiveFloat(element)
{	
	const f = parseFloat(element.value);	
	const valid = f.toString() === element.value && f > 0 && (f * 100) == parseInt(f * 100);
	
	if(valid)
		setGreen(element);
	else
		setRed(element);
	
	return valid;	
}

function validateNNegativeInteger(element)
{
	const i = parseInt(element.value);	
	const valid = i.toString() === element.value && i >= 0;
	
	if(valid)
		setGreen(element);
	else
		setRed(element);
	
	return valid;
}

function validatePositiveInteger(element)
{
	const i = parseInt(element.value);	
	const valid = i.toString() === element.value && i > 0;
	
	if(valid)
		setGreen(element);
	else
		setRed(element);
	
	return valid;
}

function validateTitle(element)
{
	const length = element.value.length;
	const valid = 0 < length && length <= 30;
	
	if(valid)
		setGreen(element);
	else
		setRed(element);
	
	return valid;
}

function validateNominative(element)
{
	const str = /^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$/;
	const valid = str.test(element.value);
	
	if(valid)
		setGreen(element);
	else
		setRed(element);
	
	return valid;
}

function validateDescription(element)
{
	const length = element.value.length;
	const valid = length <= 300;
	
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