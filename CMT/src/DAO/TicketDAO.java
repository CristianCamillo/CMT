package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import exceptions.SeatBookedException;
import model.Basket;
import model.Ticket;
import utils.DriverManagerConnectionPool;

public class TicketDAO
{
	public static ArrayList<byte[]> getOccupiedSeats(int idProjection) throws SQLException
	{
		Connection con = DriverManagerConnectionPool.getConnection();
		
		String query = "SELECT x, y FROM ticket WHERE idProjection = " + idProjection;
	    
	    ResultSet rs = con.createStatement().executeQuery(query);
	    
	    DriverManagerConnectionPool.releaseConnection(con);
	    
	    ArrayList<byte[]> list = new ArrayList<byte[]>();
	    
	    while(rs.next())
	    	list.add(new byte[] {rs.getByte("x"), rs.getByte("y")});
	    
	    return list;
	}
	
	public static int getAvailableId() throws SQLException
	{
		Connection con = DriverManagerConnectionPool.getConnection();
		
		String query = "SELECT id FROM ticket";
		
		ResultSet rs = con.createStatement().executeQuery(query);
		    
		DriverManagerConnectionPool.releaseConnection(con);
		
		int id = 0;
		
		while(rs.next())
		{
			if(id != rs.getInt("id"))
				break;
			id++;
		}	
		
		return id;
	}
			
	public static void addTickets(Basket basket) throws SQLException, SeatBookedException
	{			
		ArrayList<Ticket> tickets = basket.getTickets();
		
		int id = getAvailableId();
		
		Connection con = DriverManagerConnectionPool.getConnection();
		
		String insert = "INSERT INTO ticket VALUES (?, ?, ?, ?, ?, ?)";
		
		PreparedStatement ps = con.prepareStatement(insert);
		
		for(Ticket ticket : tickets)
		{					
			ps.setInt(1, id);
			ps.setByte(2, ticket.getX());
			ps.setByte(3, ticket.getY());
			ps.setFloat(4, ticket.getPrice());
			ps.setInt(5, ticket.getIdClient());
			ps.setInt(6, ticket.getIdProjection());
			
			ps.executeUpdate();
			
			id++;
		}
	    
	    DriverManagerConnectionPool.releaseConnection(con);
	}
	
	public static ArrayList<Ticket> getTickets(int idClient) throws SQLException
	{
		Connection con = DriverManagerConnectionPool.getConnection();
		
		String query = "SELECT * FROM ticket WHERE idclient = " + idClient;
	    
	    ResultSet rs = con.createStatement().executeQuery(query);
	    
	    DriverManagerConnectionPool.releaseConnection(con);
	    
	    ArrayList<Ticket> tickets = new ArrayList<Ticket>();
	    
	    while(rs.next())
	    	tickets.add(new Ticket(rs.getInt("id"), rs.getByte("x"), rs.getByte("y"), rs.getFloat("price"), rs.getInt("idclient"), rs.getInt("idprojection")));
	    
	    return tickets;
	}
}
