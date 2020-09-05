package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import exceptions.CannotPurchaseException;
import exceptions.UsernamePasswordNotFoundException;
import exceptions.UsernameTakenException;
import model.Client;
import utils.DriverManagerConnectionPool;

public class ClientDAO
{	
	public static int getId(String username, String password) throws SQLException, UsernamePasswordNotFoundException
	{		
		Connection con = DriverManagerConnectionPool.getConnection();
		
		String query = "SELECT id FROM client WHERE LOWER(username) = '" + username.toLowerCase() + "' AND password = '" + password + "'";
	    
	    ResultSet rs = con.createStatement().executeQuery(query);
	    
	    DriverManagerConnectionPool.releaseConnection(con);
	    
	    if(rs.next())
	    	return rs.getInt("id");
	    else
	    	throw new UsernamePasswordNotFoundException();  
	}
	
	public static boolean isRegistered(String username) throws SQLException
	{		
		Connection con = DriverManagerConnectionPool.getConnection();
		
		String query = "SELECT id FROM client WHERE LOWER(username) = '" + username.toLowerCase() + "'";
	    
	    ResultSet rs = con.createStatement().executeQuery(query);
	    
	    DriverManagerConnectionPool.releaseConnection(con);
	    
	    if(rs.next())
	    	return true;
	    else
	    	return false;
	}
	
	public static int getLastId() throws SQLException
	{
		Connection con = DriverManagerConnectionPool.getConnection();
		
		String query = "SELECT id FROM client ORDER BY id DESC LIMIT 1";
		
	    ResultSet rs = con.createStatement().executeQuery(query);
	    
	    DriverManagerConnectionPool.releaseConnection(con);

	    if(rs.next())    
	    	return rs.getInt("id");
	    else
	    	return -1;
	}
	
	public static void addClient(Client client) throws SQLException, UsernameTakenException
	{
		Connection con = DriverManagerConnectionPool.getConnection();
		
		String query = "SELECT id FROM client WHERE LOWER(username) = '" + client.getUsername().toLowerCase() + "'";
		
		ResultSet rs = con.createStatement().executeQuery(query);
		
		if(rs.next())
			throw new UsernameTakenException(client.getUsername());
		
		String insert = "INSERT INTO client VALUES (" + client.getId() + ", '" + client.getUsername() + "', '" + client.getPassword() + "', '" + client.getBalance() + "')";
		
	    con.createStatement().executeUpdate(insert);
	    
	    DriverManagerConnectionPool.releaseConnection(con);
	}
	
	public static void spend(int id, float amount) throws SQLException, CannotPurchaseException
	{
		Connection con = DriverManagerConnectionPool.getConnection();
		
		String query = "SELECT balance FROM client WHERE id = " + id;
	    
	    ResultSet rs = con.createStatement().executeQuery(query);
	    
	    DriverManagerConnectionPool.releaseConnection(con);
		
		if(rs.next())
		{
			float balance = rs.getFloat("balance");
			
			if(balance < amount)
			{
				DriverManagerConnectionPool.releaseConnection(con);
				throw new CannotPurchaseException();
			}
			
			String update = "UPDATE client SET balance = " + (balance - amount) + " WHERE id = " + id;
			
		    con.createStatement().executeUpdate(update);
		}
		
		DriverManagerConnectionPool.releaseConnection(con);
	}
}