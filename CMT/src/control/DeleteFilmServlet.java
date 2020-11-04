package control;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.FilmDAO;

@WebServlet("/deleteFilm")
public class DeleteFilmServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

    public DeleteFilmServlet()
    {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		int id = Integer.parseInt(request.getParameter("idFilm"));
		
		try
		{			
			/*
			
			TODO: fixare (curret path è il desktop) 
			
			File poster = new File("posters/" + FilmDAO.getPoster(id));
			
			if(!poster.delete())
				System.out.println("Impossibile eliminare il poster " + poster);*/
			
			FilmDAO.removeFilm(id);
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