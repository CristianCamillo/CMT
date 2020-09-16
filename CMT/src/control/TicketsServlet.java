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
import DAO.TicketDAO;
import model.Projection;
import model.Ticket;

@WebServlet("/tickets")
public class TicketsServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	
    public TicketsServlet()
    {
        super();
    }
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		int idClient = (int)request.getSession().getAttribute("id");
		
		response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");
		
		try
		{
			ArrayList<Ticket> tickets = TicketDAO.getTickets(idClient);
			ArrayList<Projection> projections = ProjectionDAO.getProjections(tickets); 
			ArrayList<String> titles = FilmDAO.getTitles(projections);
			
			String responseText = "[";
			
			for(int i = 0, l = tickets.size(); i < l; i++)
			{
				JSONObject object = new JSONObject();
				
				object.put("id", tickets.get(i).getId());
				object.put("title", titles.get(i));
				object.put("date", projections.get(i).getDate());
				object.put("time", projections.get(i).getTime());
				object.put("room", projections.get(i).getIdRoom());
				object.put("x", tickets.get(i).getX());
				object.put("y", tickets.get(i).getY());
				object.put("price", tickets.get(i).getPrice());
				
				responseText += object + (i + 1 != l ? "," : "");				
			}
			
			responseText += "]";
			
			response.getWriter().write(responseText);
		}
		catch(SQLException e)
		{
			System.out.println(e);
		}
	}
}