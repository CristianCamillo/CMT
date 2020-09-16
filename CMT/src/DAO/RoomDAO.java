package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Room;
import utils.DriverManagerConnectionPool;

public class RoomDAO
{
	public static Room getRoom(int idProjection) throws SQLException
	{
		Connection con = DriverManagerConnectionPool.getConnection();
		
		String query = "SELECT room.* FROM room INNER JOIN projection ON room.id = projection.idroom WHERE projection.id = " + idProjection;
		
		ResultSet rs = con.createStatement().executeQuery(query);
	    
	    DriverManagerConnectionPool.releaseConnection(con);
	    
	    rs.next();
	    
	    return new Room(rs.getInt("id"), rs.getByte("rows"), rs.getByte("columns"));
	}
}