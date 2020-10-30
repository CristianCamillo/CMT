package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.FilmDAO;
import model.Film;
import utils.FieldValidator;

@WebServlet("/addFilm")
public class AddFilmServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

    public AddFilmServlet()
    {
        super();
    }
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String title = request.getParameter("title");
		String runningTime = request.getParameter("runningTime");
		String genre = request.getParameter("genre");
		String director = request.getParameter("director");
		String actor1 = request.getParameter("actor1");
		String actor2 = request.getParameter("actor2");
		String description = request.getParameter("description");
		
		if(!FieldValidator.validateFilmForm(title, runningTime, genre, director, actor1, actor2, description))
			return;
		
		try
		{
			Film film = new Film(FilmDAO.getLastId() + 1, title, Short.parseShort(runningTime), genre, director, actor1, actor2, description, null);
			FilmDAO.addFilm(film);
		}
		catch(SQLException e)
		{
			System.out.println(e);
			return;
		}
		response.setContentType("text/plain");
	 	response.setCharacterEncoding("UTF-8");
	 	
		response.getWriter().write("0");
	}
}