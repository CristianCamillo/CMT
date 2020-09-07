package exceptions;

public class IdNotFoundException extends RuntimeException
{
	private static final long serialVersionUID = -2956766282409540201L;

	public IdNotFoundException(int id)
	{
		super("Id " + id + " non associato ad alcun utente.");
	}
}
