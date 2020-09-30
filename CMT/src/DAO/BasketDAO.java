package DAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import model.Basket;
import model.Ticket;

public class BasketDAO
{
	public synchronized static boolean removeTakenSeats(Basket basket) throws SQLException
	{
		ArrayList<Ticket> tickets = basket.getTickets();
		
		HashMap<Integer, ArrayList<byte[]>> projHM = new HashMap<Integer, ArrayList<byte[]>>();
		
		for(Ticket ticket : tickets)
		{
			int idProjection = ticket.getIdProjection();
			
			if(!projHM.containsKey(idProjection))
				projHM.put(idProjection, TicketDAO.getOccupiedSeats(idProjection));
		}
		
		boolean removed = false;
		
		for(int i = 0, l = tickets.size(); i < l; i++)
		{		
			Ticket ticket = tickets.get(i);
			
			ArrayList<byte[]> seats = projHM.get(ticket.getIdProjection());
			
			byte x = ticket.getX();
			byte y = ticket.getY();
			
			for(byte[] seat: seats)
				if(x == seat[0] && y == seat[1])
				{
					tickets.remove(i);
					i--;
					l--;
					removed = true;
				}
		}
		
		return removed;
	}
}