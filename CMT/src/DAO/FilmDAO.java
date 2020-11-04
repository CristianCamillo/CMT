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
	public static ArrayList<Film> findFilms(String title, String genre, String director, String actor) throws SQLException
	{
		Connection con = DriverManagerConnectionPool.getConnection();

		String query = "SELECT distinct(film.id) as id, film.title, film.runningTime, film.genre, film.director, film.actor1, film.actor2, film.description, film.poster FROM film INNER JOIN projection ON film.id = projection.idfilm WHERE strcmp(projection.date, DATE_FORMAT(SYSDATE(), '%Y%m%d')) >= 0";
		
		String toAdd = "";
		
		if(!title.equals(""))
			toAdd += " AND LOWER(title) LIKE '%" + title.toLowerCase() + "%'";
		
		if(!genre.equals(""))
			toAdd += " AND LOWER(genre) LIKE '%" + genre.toLowerCase() + "%'";
		
		if(!director.equals(""))
			toAdd += " AND LOWER(director) LIKE '%" + director.toLowerCase() + "%'";
		
		if(!actor.equals(""))
		{
			String al = actor.toLowerCase();
			
			toAdd += " AND (LOWER(actor1) LIKE '%" + al + "%' or LOWER(actor2) LIKE '%" + al + "%')";
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
			else
				titles.add("<i>eliminato</i>");
		}
		
		DriverManagerConnectionPool.releaseConnection(con);
		
		return titles;
	}
	
	public static ArrayList<Film> getAllFilms() throws SQLException
	{
		Connection con = DriverManagerConnectionPool.getConnection();
		
		String query = "SELECT * FROM film";
		
		ResultSet rs = con.createStatement().executeQuery(query);
		
		DriverManagerConnectionPool.releaseConnection(con);
		
		ArrayList<Film> films = new ArrayList<Film>();
		
		while(rs.next())
			films.add(new Film(rs.getInt("id"), rs.getString("title"), rs.getShort("runningtime"), rs.getString("genre"), rs.getString("director"), rs.getString("actor1"), rs.getString("actor2"), rs.getString("description"), rs.getString("Poster")));
		
		return films;
	}
	
	public static int getLastId() throws SQLException
	{
		Connection con = DriverManagerConnectionPool.getConnection();
		
		String query = "SELECT id FROM film ORDER BY id DESC LIMIT 1";
		
	    ResultSet rs = con.createStatement().executeQuery(query);
	    
	    DriverManagerConnectionPool.releaseConnection(con);

	    return rs.next() ? rs.getInt("id") : -1;
	}
	
	public static void addFilm(Film film) throws SQLException
	{		
		Connection con = DriverManagerConnectionPool.getConnection();
		
		String insert = "INSERT INTO film VALUES (" + film.getId() + ", '" + film.getTitle() + "', " + film.getRunningTime() + ", '" + film.getGenre() + "', '" +
													  film.getDirector() + "', '" + film.getActor1() + "', '" + film.getActor2() + "', '" + film.getDescription() + "', '" +
													  film.getPoster() + "')";
		
	    con.createStatement().executeUpdate(insert);
	    
	    DriverManagerConnectionPool.releaseConnection(con);
	}
	
	public static void updateFilm(Film film) throws SQLException
	{		
		Connection con = DriverManagerConnectionPool.getConnection();
		
		String update = "UPDATE film SET title = '" + film.getTitle() + "', runningtime = " + film.getRunningTime() + ", genre = '" + film.getGenre() + "', director = '" +
													  film.getDirector() + "', actor1 = '" + film.getActor1() + "', actor2 = '" + film.getActor2() + "', description = '" +
													  film.getDescription() + "', poster = '" + film.getPoster() + "' " + 
						"WHERE id = " + film.getId();
		
	    con.createStatement().executeUpdate(update);
	    
	    DriverManagerConnectionPool.releaseConnection(con);
	}
	
	public static void removeFilm(int id) throws SQLException
	{
		Connection con = DriverManagerConnectionPool.getConnection();
		
		String delete = "DELETE FROM film WHERE id = " + id;
		
		con.createStatement().executeUpdate(delete);		
		
		DriverManagerConnectionPool.releaseConnection(con);
	}
	
	public static String getPoster(int id) throws SQLException
	{
		Connection con = DriverManagerConnectionPool.getConnection();
		
		String query = "SELECT poster FROM film WHERE id = " + id;
		
		 ResultSet rs = con.createStatement().executeQuery(query);
		    
		 DriverManagerConnectionPool.releaseConnection(con);
		    

		 return rs.next() ? rs.getString("poster") : null;
	}	
}