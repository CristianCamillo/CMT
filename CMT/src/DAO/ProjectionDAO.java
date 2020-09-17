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
	
	public static float getPrice(int idProjection) throws SQLException
	{
		Connection con = DriverManagerConnectionPool.getConnection();
		
		String query = "SELECT price FROM projection WHERE id = " + idProjection;
		
		ResultSet rs = con.createStatement().executeQuery(query);
	    
	    DriverManagerConnectionPool.releaseConnection(con);
	    
	    rs.next();
	    
	    return rs.getFloat("price");		
	}
}