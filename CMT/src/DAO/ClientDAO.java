package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import exceptions.CannotPurchaseException;
import exceptions.UsernameTakenException;
import model.Client;
import utils.DriverManagerConnectionPool;

public class ClientDAO
{	
	public static Client getClient(String username, String password) throws SQLException
	{		
		Connection con = DriverManagerConnectionPool.getConnection();
		
		String query = "SELECT * FROM client WHERE LOWER(username) = '" + username.toLowerCase() + "' AND password = '" + password + "'";
	    
	    ResultSet rs = con.createStatement().executeQuery(query);
	    
	    DriverManagerConnectionPool.releaseConnection(con);
	    
	    if(rs.next())
	    	return new Client(rs.getInt("id"), rs.getString("username"), rs.getString("password"), rs.getFloat("balance"));
	    else
	    	return null;
	}
	
	public static boolean isRegistered(String username) throws SQLException
	{		
		Connection con = DriverManagerConnectionPool.getConnection();
		
		String query = "SELECT id FROM client WHERE LOWER(username) = '" + username.toLowerCase() + "'";
	    
	    ResultSet rs = con.createStatement().executeQuery(query);
	    
	    DriverManagerConnectionPool.releaseConnection(con);
	    
	    return rs.next();
	}
	
	public static int getLastId() throws SQLException
	{
		Connection con = DriverManagerConnectionPool.getConnection();
		
		String query = "SELECT id FROM client ORDER BY id DESC LIMIT 1";
		
	    ResultSet rs = con.createStatement().executeQuery(query);
	    
	    DriverManagerConnectionPool.releaseConnection(con);

	    return rs.next() ? rs.getInt("id") : -1;
	}
	
	public static void addClient(Client client) throws SQLException, UsernameTakenException
	{		
		if(isRegistered(client.getUsername()))
			throw new UsernameTakenException(client.getUsername());
		
		Connection con = DriverManagerConnectionPool.getConnection();
		
		String insert = "INSERT INTO client VALUES (" + client.getId() + ", '" + client.getUsername() + "', '" + client.getPassword() + "', " + client.getBalance() + ")";
		
	    con.createStatement().executeUpdate(insert);
	    
	    DriverManagerConnectionPool.releaseConnection(con);
	}
	
	public static void spend(int id, float amount) throws SQLException, CannotPurchaseException
	{
		if(amount <= 0)
			return;
		
		Connection con = DriverManagerConnectionPool.getConnection();
		
		String query = "SELECT balance FROM client WHERE id = " + id;
	    
	    ResultSet rs = con.createStatement().executeQuery(query);
		
		if(!rs.next())
			return;
	
		float balance = rs.getFloat("balance");
		
		if(balance < amount)
		{
			DriverManagerConnectionPool.releaseConnection(con);
			throw new CannotPurchaseException();
		}
		
		String update = "UPDATE client SET balance = " + (balance - amount) + " WHERE id = " + id;
		
	    con.createStatement().executeUpdate(update);
	    
	    DriverManagerConnectionPool.releaseConnection(con);
	}
	
	public static void updateUsername(int id, String newUsername) throws SQLException
	{
		Connection con = DriverManagerConnectionPool.getConnection();
		
		String update = "UPDATE client SET username = '" + newUsername + "' WHERE id = " + id;
		
		con.createStatement().executeUpdate(update);
		    
		DriverManagerConnectionPool.releaseConnection(con);
	}
	
	public static void updatePassword(int id, String newPassword) throws SQLException
	{
		Connection con = DriverManagerConnectionPool.getConnection();
		
		String update = "UPDATE client SET password = '" + newPassword + "' WHERE id = " + id;
		
		con.createStatement().executeUpdate(update);
		    
		DriverManagerConnectionPool.releaseConnection(con);
	}
	
	public static void addAmount(int id, float amount) throws SQLException
	{
		if(amount <= 0)
			return;
		
		Connection con = DriverManagerConnectionPool.getConnection();
		
		String update = "UPDATE client SET balance = balance + " + amount + " WHERE id = " + id;
		
	    con.createStatement().executeUpdate(update);
	    
	    DriverManagerConnectionPool.releaseConnection(con);
	}
}