package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import beans.Projection;
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
}