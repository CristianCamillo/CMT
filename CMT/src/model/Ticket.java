package model;

public class Ticket
{
	private int id;
	private byte x;
	private byte y;
	private final float price;
	private int idClient;
	private int idProjection;
	
	public Ticket(int id, byte x, byte y, float price, int idClient, int idProjection)
	{
		this.id = id;
		this.x = x;
		this.y = y;
		this.price = price;
		this.idClient = idClient;
		this.idProjection = idProjection;
	}
	
	public int getId()
	{
		return id;
	}
	
	public void setId(int id)
	{
		this.id = id;
	}
	
	public byte getX()
	{
		return x;
	}
	
	public void setX(byte x)
	{
		this.x = x;
	}
	
	public byte getY()
	{
		return y;
	}
	
	public void setY(byte y)
	{
		this.y = y;
	}
	
	public float getPrice()
	{
		return price;
	}
	
	public int getIdClient()
	{
		return idClient;
	}
	
	public void setIdClient(int idClient)
	{
		this.idClient = idClient;
	}
	
	public int getIdProjection()
	{
		return idProjection;
	}
	
	public void setIdProjection(int idProjection)
	{
		this.idProjection = idProjection;
	}
}