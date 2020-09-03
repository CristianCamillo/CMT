package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import beans.Ticket;
import exceptions.CannotPurchaseException;
import utils.DriverManagerConnectionPool;

public class TicketDAO
{
	public static ArrayList<Short> getSeats(int idProjection) throws SQLException
	{
		Connection con = DriverManagerConnectionPool.getConnection();
		
		String query = "SELECT seat FROM ticket WHERE idProjection = " + idProjection;
	    
	    ResultSet rs = con.createStatement().executeQuery(query);
	    
	    DriverManagerConnectionPool.releaseConnection(con);
	    
	    ArrayList<Short> list = new ArrayList<Short>();
	    
	    while(rs.next())
	    	list.add(rs.getShort("seat"));
	    
	    return list;
	}
	
	public static int getLastId() throws SQLException
	{
		Connection con = DriverManagerConnectionPool.getConnection();
		
		String query = "SELECT id FROM ticket ORDER BY id DESC LIMIT 1";
		
	    ResultSet rs = con.createStatement().executeQuery(query);
	    
	    DriverManagerConnectionPool.releaseConnection(con);

	    if(rs.next())    
	    	return rs.getInt("id");
	    else
	    	return -1;
	}
	
	public static void addTicket(Ticket ticket) throws SQLException, CannotPurchaseException
	{
		Connection con = DriverManagerConnectionPool.getConnection();
		
		String query = "SELECT id FROM ticket WHERE seat = " + ticket.getSeat() + " AND idprojection = " + ticket.getIdProjection();
	    
	    ResultSet rs = con.createStatement().executeQuery(query);
	    
	    if(rs.next())
	    {
	    	DriverManagerConnectionPool.releaseConnection(con);
	    	throw new CannotPurchaseException();
	    }
	    
	    String insert = "INSERT INTO ticket VALUES (" + ticket.getId() + ", " + ticket.getSeat() + ", " + ticket.getIdClient() + ", " + ticket.getIdProjection() + ")";
	   
	    con.createStatement().executeUpdate(insert);
	    
	    DriverManagerConnectionPool.releaseConnection(con);
	}
}
