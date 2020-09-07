package exceptions;

public class CannotPurchaseException extends RuntimeException
{	
	private static final long serialVersionUID = -1356025249165677846L;
	
	public CannotPurchaseException()
	{
		super("Acquisto non ultimabile.");
	}
}
