package model;

public class Ticket
{
	private int id;
	private short seat;
	private int idClient;
	private int idProjection;
	
	public Ticket(int id, short seat, int idClient, int idProjection)
	{
		this.id = id;
		this.seat = seat;
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