package utils;

import java.util.regex.*; 

public class FieldValidator
{		
	public static boolean validateLoginForm(String username, String password)
	{
		return validateUsername(username) && validatePassword(password);
	}
	
	public static boolean validateRegistrationForm(String username, String password, String balance)
	{
		return validateUsername(username) && validatePassword(password) && validateBalance(balance);
	}
	
	public static boolean validateUsername(String username)
	{
		if(username == null) return false;
		
		String regex = "^(?=.{6,20}$)(?![_.0-9])(?!.*[_.]{2})[a-zA-Z0-9._]+(?<![_.])$";
		
        Matcher m = Pattern.compile(regex).matcher(username); 
        
        return m.matches(); 
	}
	
	public static boolean validatePassword(String password)
	{
		if(password == null) return false;
		
		String regex = "^(?=.{6,20}$)(?![_.])(?!.*[_.]{2})[a-zA-Z0-9._]+(?<![_.])$";
		
        Matcher m = Pattern.compile(regex).matcher(password); 
        
        return m.matches(); 
	}
	
	public static boolean validateBalance(String number)
	{
		if(number == null) return false;
		
		try
		{
			return Float.parseFloat(number) >= 0;
		}
		catch(NumberFormatException e)
		{
			return false;
		}
	}
	
	public static boolean validateAmount(String number)
	{
		if(number == null) return false;
		
		try
		{
			return Float.parseFloat(number) > 0;
		}
		catch(NumberFormatException e)
		{
			return false;
		}
	}
}