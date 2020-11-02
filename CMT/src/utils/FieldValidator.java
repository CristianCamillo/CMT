package utils;

import java.util.regex.*; 

public class FieldValidator
{		
	private static final String USERNAME_REGEX   = "^(?=.{6,20}$)(?![_.0-9])(?!.*[_.]{2})[a-zA-Z0-9._]+(?<![_.])$";
	private static final String PASSWORD_REGEX   = "^(?=.{6,20}$)(?![_.])(?!.*[_.]{2})[a-zA-Z0-9._]+(?<![_.])$";
	private static final String NOMINATIVE_REGEX = "^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$";
	
	private static boolean validateRegex(String string, String regex)
	{
		if(string == null) return false;
		
        return Pattern.compile(regex).matcher(string).matches();
	}
	
	public static boolean validateFiltreForm(String title, String genre, String director, String actor)
	{
		if(title == null || genre == null || director == null || actor == null) return false;
		
		return title.length() <= 30 &&
			   genre.length() <= 30 &&
			   director.length() <= 30 &&
			   actor.length() <= 30;
	}
	
	public static boolean validateLoginForm(String username, String password)
	{
		return validateUsername(username) && validatePassword(password);
	}
	
	public static boolean validateRegistrationForm(String username, String password, String balance)
	{
		return validateUsername(username) && validatePassword(password) && validateNonNegativeFloat(balance);
	}	
	
	public static boolean validateFilmForm(String title, String runningTime, String genre, String director, String actor1, String actor2, String description)
	{
		if(title == null || description == null) return false;
		
		return 0 < title.length() && title.length() <= 30 &&
			   validatePositiveInteger(runningTime) &&
			   validateNominative(genre) &&
			   validateNominative(director) &&
			   validateNominative(actor1) &&
			   validateNominative(actor2) &&
			   description.length() <= 300;
	}
	
	public static boolean validateProjectionForm(String date, String time, String price, String idroom, String idfilm)
	{		
		return validatePositiveInteger(date) &&
			   validateNonNegativeInteger(time) &&
			   validateNonNegativeFloat(price) &&
			   validatePositiveInteger(idroom) &&
			   validateNonNegativeInteger(idfilm);
	}
	
	public static boolean validateUsername(String username)
	{
		return validateRegex(username, USERNAME_REGEX);
	}
	
	public static boolean validatePassword(String password)
	{
		return validateRegex(password, PASSWORD_REGEX);
	}
	
	public static boolean validateNominative(String nominative)
	{
		return validateRegex(nominative, NOMINATIVE_REGEX);
	}
	
	public static boolean validateNonNegativeFloat(String number)
	{
		if(number == null) return false;
		
		try
		{
			float f = Float.parseFloat(number);
			
			return f >= 0 && (f * 100) == (int)(f * 100);
		}
		catch(NumberFormatException e)
		{
			return false;
		}
	}
	
	public static boolean validatePositiveFloat(String number)
	{
		if(number == null) return false;
		
		try
		{
			float f = Float.parseFloat(number);
			
			return f > 0 && (f * 100) == (int)(f * 100);
		}
		catch(NumberFormatException e)
		{
			return false;
		}
	}
	
	public static boolean validateNonNegativeInteger(String number)
	{
		if(number == null) return false;
		
		try
		{
			int i = Integer.parseInt(number);
			
			return i >= 0;
		}
		catch(NumberFormatException e)
		{
			return false;
		}
	}
	
	public static boolean validatePositiveInteger(String number)
	{
		if(number == null) return false;
		
		try
		{
			int i = Integer.parseInt(number);
			
			return i > 0;
		}
		catch(NumberFormatException e)
		{
			return false;
		}
	}
}