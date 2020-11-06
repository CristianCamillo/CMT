package control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import DAO.FilmDAO;
import model.Film;

@WebServlet("/filmsLoader")
public class FilmsLoaderServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

    public FilmsLoaderServlet()
    {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		ArrayList<Film> films;
		
		try
		{
			films = FilmDAO.getAllFilms();
		}
		catch(SQLException e)
		{
			System.out.println(e);
			return;
		}
		
		String responseText = "[";
		
		for(int i = 0, l = films.size(); i < l; i++)
		{
			JSONObject object = new JSONObject();
			
			Film film = films.get(i);
			
			object.put("id", film.getId());
			object.put("title", film.getTitle());
			object.put("runningTime", film.getRunningTime());
			object.put("genre", film.getGenre());
			object.put("director", film.getDirector());
			object.put("actor1", film.getActor1());
			object.put("actor2", film.getActor2());
			object.put("description", film.getDescription());
			object.put("poster", film.getPoster());
			
			responseText += object + (i + 1 != l ? "," : "");				
		}
		
		responseText += "]";
		
		response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");
	    
		response.getWriter().write(responseText);
	}
}