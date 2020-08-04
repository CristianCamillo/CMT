package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class DriverManagerConnectionPool
{
	private static List<Connection> freeDbConnections;
	
	static
	{
		freeDbConnections = new LinkedList<Connection>();
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch(ClassNotFoundException e)
		{
			System.out.println("DB driver not found!");
		}
	}
	
	private static Connection createDBConnection() throws SQLException
	{
		Connection newConnection = null;
		String db = "world";
		String username = "root";
		String password = "f4s4n4r4";
		newConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + db + "?characterEncoding=latin1", username, password);
		
		return newConnection;
	}

	public static synchronized Connection getConnection() throws SQLException
	{
		Connection connection;
		if(!freeDbConnections.isEmpty())
		{
			connection = (Connection) freeDbConnections.get(0);
			DriverManagerConnectionPool.freeDbConnections.remove(0);
			System.out.println("qua");
			try
			{
				if(connection.isClosed())
					connection = DriverManagerConnectionPool.getConnection();
			}
			catch(SQLException e)
			{
				connection = DriverManagerConnectionPool.getConnection();
			}
		}
		else
			connection = DriverManagerConnectionPool.createDBConnection();
		
		return connection;
	}

	public static synchronized void releaseConnection(Connection connection)
	{
		freeDbConnections.add(connection);
	}
}