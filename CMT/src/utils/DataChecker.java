package utils;

public class DataChecker
{
	public static boolean checkForFiltre(String title, String genre, String director, String actor)
	{
		return checkMaxLength(title, 30) && checkMaxLength(genre, 30) && checkMaxLength(director, 30) && checkMaxLength(actor, 30);
	}
	
	public static boolean checkForLogin(String username, String password)
	{
		return checkLength(username, 6, 20) && checkLength(password, 6, 20);
	}
	
	public static boolean checkForRegistration(String username, String password, String balance)
	{
		return checkLength(username, 6, 20) && checkLength(password, 6, 20) && checkIsNotNeg(balance);
	}
	
	public static boolean checkLength(String string, int min, int max)
	{
		return min <= string.length() && string.length() <= max;
	}
	
	public static boolean checkMaxLength(String string, int max)
	{
		return string.length() <= max;
	}
	
	public static boolean checkIsNotNeg(String number)
	{
		try
		{
			return Float.parseFloat(number) >= 0;
		}
		catch(NumberFormatException e)
		{
			return false;
		}
	}
	
	public static boolean checkIsPos(String number)
	{
		try
		{
			return Float.parseFloat(number) > 0;
		}
		catch(NumberFormatException e)
		{
			return false;
		}
	}
	
	public static boolean checkRange(String number, int min, int max)
	{
		try
		{
			int n = Integer.parseInt(number);
			return min <= n && n <= max;
		}
		catch(NumberFormatException e)
		{
			return false;
		}
	}
}