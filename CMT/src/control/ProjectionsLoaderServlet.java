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
import DAO.ProjectionDAO;
import model.Projection;

@WebServlet("/projectionsLoader")
public class ProjectionsLoaderServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;
       
    public ProjectionsLoaderServlet()
    {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		ArrayList<Projection> projections;
		ArrayList<String> titles;
		
		try
		{
			projections = ProjectionDAO.getAllProjections();
			titles = FilmDAO.getTitles(projections);
		}
		catch(SQLException e)
		{
			System.out.println(e);
			return;
		}
		
		String responseText = "[";
		
		for(int i = 0, l = projections.size(); i < l; i++)
		{
			JSONObject object = new JSONObject();
			
			Projection projection = projections.get(i);
			
			object.put("title", titles.get(i));
			object.put("id", projection.getId());
			object.put("date", projection.getDate());
			object.put("time", projection.getTime());
			object.put("price", projection.getPrice());
			object.put("idroom", projection.getIdRoom());
			object.put("idfilm", projection.getIdFilm());
			
			responseText += object + (i + 1 != l ? "," : "");				
		}
		
		responseText += "]";
		
		response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");
	    
		response.getWriter().write(responseText);
	}
}