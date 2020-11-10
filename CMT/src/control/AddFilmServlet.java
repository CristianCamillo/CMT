package control;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import DAO.FilmDAO;
import model.Film;
import utils.FieldValidator;

@WebServlet("/addFilm")
@MultipartConfig(
		fileSizeThreshold = 1024 * 1024 * 2,
		maxFileSize = 1024 * 1024 * 10,
		maxRequestSize = 1024 * 1024 * 50)
public class AddFilmServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	private static final String POSTERS_PATH = "C:\\Users\\Cristian\\git\\CMT\\CMT\\WebContent\\posters\\";
	
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
		
		Part posterPart = request.getPart("poster");
		String posterName = posterPart.getSubmittedFileName();

		if(!FieldValidator.validateFilmForm(title, runningTime, genre, director, actor1, actor2, description) || posterName.equals(""))
			return;
		
		int counter = 0;
		String extension = posterName.substring(posterName.lastIndexOf('.') + 1);
		
		while(new File(POSTERS_PATH + counter + "." + extension).exists())
			counter++;
		
		posterName = counter + "." + extension;
		
		try
		{
			Film film = new Film(FilmDAO.getAvailableId(), title, Short.parseShort(runningTime), genre, director, actor1, actor2, description, posterName);
			FilmDAO.addFilm(film);
		}
		catch(SQLException e)
		{
			System.out.println(e);
			return;
		}
		
		for(Part part : request.getParts())
			part.write(POSTERS_PATH + posterName);
		
		response.setContentType("text/plain");
	 	response.setCharacterEncoding("UTF-8");
	 	
		response.getWriter().write("0");
	}
}