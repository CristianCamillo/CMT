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
import utils.FieldValidator;

@WebServlet("/filtre")
public class FiltreServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;
       
    public FiltreServlet()
    {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{		
		String title = request.getParameter("title");
		String genre = request.getParameter("genre");
		String director = request.getParameter("director");
		String actor = request.getParameter("actor");

		if(!FieldValidator.validateFiltreForm(title, genre, director, actor))
			return;
		
	    ArrayList<Film> films = null;
	    
		try
		{
			films = FilmDAO.findFilm(title, genre, director, actor);
		}
		catch(SQLException e)
		{
			System.out.println(e);
			return;
		}
		
		String responseText = "[";
		
		for(int i = 0, l = films.size(); i < l; i++)
		{
			Film film = films.get(i);
			
			JSONObject object = new JSONObject();
			
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