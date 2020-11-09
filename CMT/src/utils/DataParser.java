package utils;

public class DataParser
{
	public static String parseDate(String date)
	{
		return date.substring(0, 4) + date.substring(5, 7) + date.substring(8);
	}
	
	public static String parseTime(String time)
	{
		return time.substring(0, 2) + time.substring(3);
	}
}