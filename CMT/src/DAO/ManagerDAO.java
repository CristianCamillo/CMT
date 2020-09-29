package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import exceptions.UsernameTakenException;
import model.Manager;
import utils.DriverManagerConnectionPool;

public class ManagerDAO
{
	public static Manager getManager(String username, String password) throws SQLException
	{		
		Connection con = DriverManagerConnectionPool.getConnection();
		
		String query = "SELECT * FROM manager WHERE LOWER(username) = '" + username.toLowerCase() + "' AND password = '" + password + "'";
	    
	    ResultSet rs = con.createStatement().executeQuery(query);
	    
	    DriverManagerConnectionPool.releaseConnection(con);
	    
	    if(rs.next())
	    	return new Manager(rs.getInt("id"), rs.getString("username"), rs.getString("password"));
	    else
	    	return null;  
	}
	
	public static boolean isRegistered(String username) throws SQLException
	{		
		Connection con = DriverManagerConnectionPool.getConnection();
		
		String query = "SELECT id FROM manager WHERE LOWER(username) = '" + username.toLowerCase() + "'";
	    
	    ResultSet rs = con.createStatement().executeQuery(query);
	    
	    DriverManagerConnectionPool.releaseConnection(con);
	    
	    return rs.next();
	}
	
	public static void updateUsername(int id, String newUsername) throws SQLException, UsernameTakenException
	{
		if(isRegistered(newUsername))
			throw new UsernameTakenException();
		
		Connection con = DriverManagerConnectionPool.getConnection();
		
		String update = "UPDATE manager SET username = '" + newUsername + "' WHERE id = " + id;
		
		con.createStatement().executeUpdate(update);
		    
		DriverManagerConnectionPool.releaseConnection(con);
	}
	
	public static void updatePassword(int id, String newPassword) throws SQLException
	{
		Connection con = DriverManagerConnectionPool.getConnection();
		
		String update = "UPDATE manager SET password = '" + newPassword + "' WHERE id = " + id;
		
		con.createStatement().executeUpdate(update);
		    
		DriverManagerConnectionPool.releaseConnection(con);
	}
}