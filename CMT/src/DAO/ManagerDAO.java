package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import exceptions.UsernamePasswordNotFoundException;
import utils.DriverManagerConnectionPool;

public class ManagerDAO
{
	public static int getId(String username, String password) throws SQLException, UsernamePasswordNotFoundException
	{		
		Connection con = DriverManagerConnectionPool.getConnection();
		
		String query = "SELECT id FROM manager WHERE LOWER(username) = '" + username.toLowerCase() + "' AND password = '" + password + "'";
	    
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
		
		String query = "SELECT id FROM manager WHERE LOWER(username) = '" + username.toLowerCase() + "'";
	    
	    ResultSet rs = con.createStatement().executeQuery(query);
	    
	    DriverManagerConnectionPool.releaseConnection(con);
	    
	    return rs.next();
	}
	
	public static void updateUsername(int id, String newUsername) throws SQLException
	{
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