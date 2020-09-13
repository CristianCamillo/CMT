package model;

public class Ticket
{
	private int id;
	private short seat;
	private final float price;
	private int idClient;
	private int idProjection;
	
	public Ticket(int id, short seat, float price, int idClient, int idProjection)
	{
		this.id = id;
		this.seat = seat;
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
	
	public short getSeat()
	{
		return seat;
	}
	
	public void setSeat(short seat)
	{
		this.seat = seat;
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