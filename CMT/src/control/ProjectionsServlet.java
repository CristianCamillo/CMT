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

import DAO.ProjectionDAO;
import model.Projection;

@WebServlet("/projections")
public class ProjectionsServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;
       
    public ProjectionsServlet()
    {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String idFilm = request.getParameter("idFilm");
		
	    ArrayList<Projection> projections = null;
	    
		try
		{
			projections = ProjectionDAO.getProjections(Integer.parseInt(idFilm), false);
		}
		catch(SQLException e)
		{
			System.out.println(e);
			return;
		}
		
		String responseText = "[";
		
		for(int i = 0, l = projections.size(); i < l; i++)
		{			
			Projection projection = projections.get(i);
			
			JSONObject object = new JSONObject();
			
			object.put("id", projection.getId());
			object.put("date", projection.getDate());
			object.put("time", projection.getTime());
			object.put("price", projection.getPrice());
			
			responseText += object + (i + 1 != l ? "," : "");				
		}
		
		responseText += "]";
		
		response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");
		
		response.getWriter().write(responseText);
	}
}