package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Film;
import model.Projection;
import utils.DriverManagerConnectionPool;

public class FilmDAO
{
	public static ArrayList<Film> findFilm(String title, String genre, String director, String actor) throws SQLException
	{
		Connection con = DriverManagerConnectionPool.getConnection();

		String query = "SELECT distinct(film.id) as id, film.title, film.runningTime, film.genre, film.director, film.actor1, film.actor2, film.description, film.poster FROM film INNER JOIN projection ON film.id = projection.idfilm WHERE strcmp(projection.date, DATE_FORMAT(SYSDATE(), '%Y%m%d')) >= 0";
		
		String toAdd = "";
		
		if(!title.equals(""))
			toAdd += " AND LOWER(title) = '" + title.toLowerCase() + "'";
		
		if(!genre.equals(""))
			toAdd += " AND LOWER(genre) = '" + genre.toLowerCase() + "'";
		
		if(!director.equals(""))
			toAdd += " AND LOWER(director) = '" + director.toLowerCase() + "'";
		
		if(!actor.equals(""))
		{
			String al = actor.toLowerCase();
			
			toAdd += " AND (LOWER(actor1) = '" + al + "' or LOWER(actor2) = '" + al + "')";
		}
		
		ResultSet rs = con.createStatement().executeQuery(query + toAdd);

		DriverManagerConnectionPool.releaseConnection(con);

		ArrayList<Film> list = new ArrayList<Film>();
		
		while(rs.next())	
			list.add(new Film(rs.getInt("id"), rs.getString("title"), rs.getShort("runningTime"),
							  rs.getString("genre"), rs.getString("director"), rs.getString("actor1"),
							  rs.getString("actor2"), rs.getString("description"), rs.getString("poster")));

		return list;
	}
	
	public static ArrayList<String> getTitles(ArrayList<Projection> projections) throws SQLException
	{
		Connection con = DriverManagerConnectionPool.getConnection();
		
		String query = "SELECT title FROM film WHERE id = ?";
		
		PreparedStatement ps = con.prepareStatement(query);
		
		ArrayList<String> titles = new ArrayList<String>();
		
		for(Projection projection : projections)
		{
			ps.setInt(1, projection.getIdFilm());
			ResultSet rs = ps.executeQuery();
			
			if(rs.next())
				titles.add(rs.getString("title"));
		}
		
		DriverManagerConnectionPool.releaseConnection(con);
		
		return titles;
	}
}