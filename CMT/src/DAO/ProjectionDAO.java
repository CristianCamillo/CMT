package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Projection;
import model.Ticket;
import utils.DriverManagerConnectionPool;

public class ProjectionDAO
{
	public static ArrayList<Projection> getProjections(int idFilm, boolean includePast) throws SQLException
	{
		Connection con = DriverManagerConnectionPool.getConnection();
		
		String query = "SELECT * FROM projection WHERE projection.idfilm = " + idFilm +
					   (includePast ? " " : " AND STRCMP(projection.date, DATE_FORMAT(SYSDATE(), '%Y%m%d')) >= 0 ") +
					   "ORDER BY projection.date, projection.time";
	    
	    ResultSet rs = con.createStatement().executeQuery(query);
	    
	    DriverManagerConnectionPool.releaseConnection(con);
	    
	    ArrayList<Projection> list = new ArrayList<Projection>();
		
		while(rs.next())	
			list.add(new Projection(rs.getInt("id"), rs.getInt("date"), rs.getShort("time"), rs.getFloat("price"), rs.getInt("idroom"), rs.getInt("idfilm")));

		return list;
	}
	
	public static ArrayList<Projection> getProjections(ArrayList<Ticket> tickets) throws SQLException
	{
		Connection con = DriverManagerConnectionPool.getConnection();
		
		String query = "SELECT * FROM projection WHERE id = ?";
		
		PreparedStatement ps = con.prepareStatement(query);
		
		ArrayList<Projection> projections = new ArrayList<Projection>();
		
		for(Ticket ticket : tickets)
		{
			ps.setInt(1, ticket.getIdProjection());
			ResultSet rs = ps.executeQuery();
			
			if(rs.next())
				projections.add(new Projection(rs.getInt("id"), rs.getInt("date"), rs.getShort("time"), rs.getFloat("price"), rs.getInt("idroom"), rs.getInt("idfilm")));
		}
		
		DriverManagerConnectionPool.releaseConnection(con);
		
		return projections;
	}
	
	public static ArrayList<Projection> getAllProjections() throws SQLException
	{
		Connection con = DriverManagerConnectionPool.getConnection();
		
		String query = "SELECT * FROM projection";
		
		ResultSet rs = con.createStatement().executeQuery(query);
		
		DriverManagerConnectionPool.releaseConnection(con);
		
		ArrayList<Projection> projections = new ArrayList<Projection>();
		
		while(rs.next())
			projections.add(new Projection(rs.getInt("id"), rs.getInt("date"), rs.getShort("time"), rs.getFloat("price"), rs.getInt("idroom"), rs.getInt("idfilm")));
		
		return projections;
	}
	
	public static float getPrice(int idProjection) throws SQLException
	{
		Connection con = DriverManagerConnectionPool.getConnection();
		
		String query = "SELECT price FROM projection WHERE id = " + idProjection;
		
		ResultSet rs = con.createStatement().executeQuery(query);
	    
	    DriverManagerConnectionPool.releaseConnection(con);
	    
	    rs.next();
	    
	    return rs.getFloat("price");		
	}
	
	public static int getLastId() throws SQLException
	{
		Connection con = DriverManagerConnectionPool.getConnection();
		
		String query = "SELECT id FROM projection ORDER BY id DESC LIMIT 1";
		
	    ResultSet rs = con.createStatement().executeQuery(query);
	    
	    DriverManagerConnectionPool.releaseConnection(con);

	    return rs.next() ? rs.getInt("id") : -1;
	}
	
	public static void addProjection(Projection projection) throws SQLException
	{		
		Connection con = DriverManagerConnectionPool.getConnection();
		
		String insert = "INSERT INTO projection VALUES (" + projection.getId() + ", " + projection.getDate() + ", " + projection.getTime() + ", " +
													  		projection.getPrice() + ", " + projection.getIdRoom() + ", " + projection.getIdFilm() + "')";
		
	    con.createStatement().executeUpdate(insert);
	    
	    DriverManagerConnectionPool.releaseConnection(con);
	}
	
	public static void updateProjection(Projection projection) throws SQLException
	{		
		Connection con = DriverManagerConnectionPool.getConnection();
		
		String update = "UPDATE projection SET date = " + projection.getDate() + ", time = " + projection.getTime() + ", price = " +
														  projection.getPrice() + ", idroom = " + projection.getIdRoom() + ", idfilm = " + projection.getIdFilm() + " " + 
						"WHERE id = " + projection.getId();
		
	    con.createStatement().executeUpdate(update);
	    
	    DriverManagerConnectionPool.releaseConnection(con);
	}
	
	public static void removeProjection(int id) throws SQLException
	{
		Connection con = DriverManagerConnectionPool.getConnection();
		
		String delete = "DELETE FROM projection WHERE id = " + id;
		
		con.createStatement().executeUpdate(delete);		
		
		DriverManagerConnectionPool.releaseConnection(con);
	}
}