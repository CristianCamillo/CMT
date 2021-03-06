// ritorna la data in forma DD-MM-YYYY
function parseDate(date)
{	
	date = date + "";
	
	if(date.includes("<i>"))
		return date;
	
	return date.substring(6, 8) + "-" + date.substring(4, 6) + "-" + date.substring(0, 4);
}

// ritorna la data in forma YYYY-MM-DD
function parseDate2(date)
{	
	date = date + "";
	
	if(date.includes("<i>"))
		return date;
	
	return date.substring(0, 4) + "-" + date.substring(4, 6) + "-" + date.substring(6, 8);
}

// ritorna l'orario in formato HH:MM
function parseTime(time)
{	
	time = time + "";
	
	if(time.includes("<i>"))
		return time;
		
	var h;
	var m;
	
	if(time.length <= 2)
	{
		h = "00";
		m = time.length == 2 ? time : ("0" + time);
	}
	else if(time.length == 3)
	{
		h = "0" + time.substring(0, 1);
		m = time.substring(1, 3);
	}
	else
	{
		h = time.substring(0, 2);
		m = time.substring(2, 4);
	}
	
	return h + ":" + m;
}

function parseSeat(x, y)
{	
	return String.fromCharCode(65 + y) + "-" + (x + 1);
}