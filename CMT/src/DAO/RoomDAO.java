package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import utils.DriverManagerConnectionPool;

public class RoomDAO
{
	public static byte[] getRoomSize(int idProjection) throws SQLException
	{
		Connection con = DriverManagerConnectionPool.getConnection();
		
		String query = "SELECT room.rows, room.columns FROM room INNER JOIN projection ON room.id = projection.idroom WHERE projection.id = " + idProjection;
		
		ResultSet rs = con.createStatement().executeQuery(query);
	    
	    DriverManagerConnectionPool.releaseConnection(con);
	    
	    rs.next();
	    
	    return new byte[] {rs.getByte("rows"), rs.getByte("columns")};
	}
}