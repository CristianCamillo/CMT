function checkFieldLength(id, min, max)
{
	const field = document.getElementById(id);
	
	if(field.value.length < min || field.value.length > max)
    {	
		field.style.border = "2px solid #d02020";
		field.style.color = "#9a2f2f";
		field.style.background = "#fff9f9";
	}
    else
	{
		field.style.border = "2px solid #117d11";
		field.style.color = "#1c631c";
		field.style.background = "#f9fff9";
	}
}