package model;

import java.util.ArrayList;

public class Basket
{
	private ArrayList<Ticket> tickets;
	
	public Basket()
	{
		tickets = new ArrayList<Ticket>();
	}
	
	public ArrayList<Ticket> getTickets()
	{
		return tickets;
	}
	
	public void addTicket(Ticket ticket)
	{
		tickets.add(ticket);
	}
	
	public void removeTicket(int number)
	{
		tickets.remove(number);
	}
	
	public void removeProjectionTickets(int idProjection)
	{		
		for(int i = 0, l = tickets.size(); i < l; i++)
			if(tickets.get(i).getIdProjection() == idProjection)
			{				
				tickets.remove(i);
				i--;
				l--;
			}
	}
	
	public float getTotalPrice()
	{
		float price = 0;
		
		for(Ticket ticket : tickets)
			price += ticket.getPrice();
		
		return price;
	}
}