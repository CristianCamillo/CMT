package exceptions;

public class UsernamePasswordNotFoundException extends RuntimeException
{	
	private static final long serialVersionUID = 13143769754901513L;
	
	public UsernamePasswordNotFoundException()
	{
		super("Coppia username-password non associata ad alcun utente.");
	}
}
