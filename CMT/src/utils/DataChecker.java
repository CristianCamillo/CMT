package utils;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

public class DataChecker
{
	private enum Field
	{
		USERNAME,
		PASSWORD,
		BALANCE,
		TITLE,
		GENRE,
		DIRECTOR,
		ACTOR
	}
	
	private enum Page
	{
		HOMEPAGE,
		REGISTRATION,
		LOGIN
	}
	
	private static final Map<Field, String> errorMsgMap;
    static
    {
        Map<Field, String> map = new HashMap<Field, String>();
        map.put(Field.USERNAME,	"L'username deve avere tra 6 e 20 caratteri");
        map.put(Field.PASSWORD, "La password deve avere tra 6 e 20 caratteri");
        map.put(Field.BALANCE, 	"Il saldo deve essere un numero maggiore o uguale a 0");
        map.put(Field.TITLE,	"Il titolo deve avere massimo 30 caratteri");
        map.put(Field.GENRE,	"Il genere deve avere massimo 30 caratteri");
        map.put(Field.DIRECTOR, "Il regista deve avere massimo 30 caratteri");
        map.put(Field.ACTOR,	"L'attore deve avere massimo 30 caratteri");
        errorMsgMap = Collections.unmodifiableMap(map);
    }
    
    private static final Map<Page, String> pageMap;
    static
    {
        Map<Page, String> map = new HashMap<Page, String>();
        map.put(Page.HOMEPAGE, 		"homepage.jsp");
        map.put(Page.REGISTRATION, "registrazione.jsp");
        map.put(Page.LOGIN, 		"login.jsp");
        pageMap = Collections.unmodifiableMap(map);
    }
	
	private static void writeErrorMessage(HttpServletResponse response, String msg, String page) throws IOException
	{
		response.setContentType("text/html");	
		response.getOutputStream().println("<script>alert(\"" + msg + "\");</script>" + 
										   "<meta http-equiv=\"refresh\" content=\"0;URL=" + page + "\">");
		response.getOutputStream().flush();
	}
	
	private static boolean checkBetween(String string, int min, int max)
	{
		return min <= string.length() && string.length() <= max;
	}
	
	private static boolean checkMax(String string, int max)
	{
		return string.length() <= max;
	}
	
	private static boolean checkIsNumber(String string)
	{
		try
		{
			Double.parseDouble(string);
			return true;
		}
		catch(NumberFormatException e)
		{
			return false;
		}
	}
	
	public static boolean checkForFiltre(HttpServletResponse response, String title, String genre, String director, String actor) throws IOException
	{
		if(!checkMax(title, 30))
		{
			writeErrorMessage(response, errorMsgMap.get(Field.TITLE), pageMap.get(Page.HOMEPAGE));
			return false;
		}
		
		if(!checkMax(genre, 30))
		{
			writeErrorMessage(response, errorMsgMap.get(Field.GENRE), pageMap.get(Page.HOMEPAGE));
			return false;
		}
		
		if(!checkMax(director, 30))
		{
			writeErrorMessage(response, errorMsgMap.get(Field.DIRECTOR), pageMap.get(Page.HOMEPAGE));
			return false;
		}
		
		if(!checkMax(actor, 30))
		{
			writeErrorMessage(response, errorMsgMap.get(Field.ACTOR), pageMap.get(Page.HOMEPAGE));
			return false;
		}
		
		return true;
	}
	
	public static boolean checkForRegistration(HttpServletResponse response, String username, String password, String balance) throws IOException
	{
		if(!checkBetween(username, 6, 20))
		{
			writeErrorMessage(response, errorMsgMap.get(Field.USERNAME), pageMap.get(Page.REGISTRATION));
			return false;
		}
		
		if(!checkBetween(password, 6, 20))
		{
			writeErrorMessage(response, errorMsgMap.get(Field.PASSWORD), pageMap.get(Page.REGISTRATION));
			return false;
		}
		
		if(!checkIsNumber(balance) || Float.parseFloat(balance) < 0)
		{
			writeErrorMessage(response, errorMsgMap.get(Field.BALANCE), pageMap.get(Page.REGISTRATION));
			return false;
		}
		
		return true;
	}
	
	public static boolean checkForLogin(HttpServletResponse response, String username, String password) throws IOException
	{
		if(!checkBetween(username, 6, 20))
		{
			writeErrorMessage(response, errorMsgMap.get(Field.USERNAME), pageMap.get(Page.REGISTRATION));
			return false;
		}
		
		if(!checkBetween(password, 6, 20))
		{
			writeErrorMessage(response, errorMsgMap.get(Field.PASSWORD), pageMap.get(Page.REGISTRATION));
			return false;
		}
		
		return true;
	}
}