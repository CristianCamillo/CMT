// ritorna la data in forma DD-MM-YYYY
function parseDate(date)
{
	date = date + "";
	return date.substring(6, 8) + "-" + date.substring(4, 6) + "-" + date.substring(0, 4);
}

// ritorna l'orario in formato HH:MM
function parseTime(time)
{
	time = time + "";
	
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

// ritorna il posto in a-z-1-n
function parseSeat(seat)
{
	if(seat < 100)
		return "A-" + (seat + 1);
	
	var seatS = seat + "";
	var n;
	
	if(seatS.length == 3)
		n = parseInt(seatS.substring(0, 1));
	else
		n = parseInt(seatS.substring(0, 2));
	
	return String.fromCharCode(65 + n) + "-" + (seat % 100 + 1);
}