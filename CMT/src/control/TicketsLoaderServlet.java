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

@WebServlet("/ticketsLoader")
public class TicketsLoaderServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	
    public TicketsLoaderServlet()
    {
        super();
    }
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		int idClient = (int)request.getSession().getAttribute("id");
		
	    ArrayList<Ticket> tickets;
		ArrayList<Projection> projections;
		ArrayList<String> titles;
	    
		try
		{
			tickets = TicketDAO.getTickets(idClient);
			projections = ProjectionDAO.getProjections(tickets); 
			titles = FilmDAO.getTitles(projections);			
		}
		catch(SQLException e)
		{
			System.out.println(e);
			return;
		}
		
		String responseText = "[";
		
		for(int i = 0, l = tickets.size(); i < l; i++)
		{
			JSONObject object = new JSONObject();
			
			Projection projection = projections.get(i);
			
			object.put("id", tickets.get(i).getId());
			object.put("title", titles.get(i));
			
			if(projection.getId() != -1)
			{
				object.put("date", projection.getDate());
				object.put("time", projection.getTime());
				object.put("room", projection.getIdRoom());
			}
			else
			{
				object.put("date", "<i>eliminato</i>");
				object.put("time", "<i>eliminato</i>");
				object.put("room", "<i>eliminato</i>");
			}
			
			object.put("x", tickets.get(i).getX());
			object.put("y", tickets.get(i).getY());
			object.put("price", tickets.get(i).getPrice());
			
			responseText += object + (i + 1 != l ? "," : "");				
		}
		
		responseText += "]";
		
		response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");
	    
		response.getWriter().write(responseText);
	}
}