package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import beans.Room;
import utils.DriverManagerConnectionPool;

public class RoomDAO
{
	public static Room getRoom(int id) throws SQLException
	{
		Connection con = DriverManagerConnectionPool.getConnection();
		
		String query = "SELECT * FROM room WHERE id = " + id;
	    
	    ResultSet rs = con.createStatement().executeQuery(query);
	    
	    DriverManagerConnectionPool.releaseConnection(con);
		
		if(rs.next())
			return new Room(rs.getInt("id"), rs.getByte("rows"), rs.getByte("seatsperrow"));
		else
			return null;
	}
}
