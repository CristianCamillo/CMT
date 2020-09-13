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

@WebServlet("/clientTickets")
public class ClientTicketsServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	
    public ClientTicketsServlet()
    {
        super();
    }
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		int id = (int)request.getSession().getAttribute("id");
		
		response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");
		
		try
		{
			ArrayList<Ticket> tickets = TicketDAO.getTickets(id);
			ArrayList<Projection> projections = ProjectionDAO.getProjections(tickets); 
			ArrayList<String> titles = FilmDAO.getTitles(projections);
			
			String responseText = "[";
			
			for(int i = 0, l = tickets.size(); i < l; i++)
			{
				int idticket = tickets.get(i).getId();
				String title = titles.get(i);
				int date = projections.get(i).getDate();
				short time = projections.get(i).getTime();
				int room = projections.get(i).getIdRoom();
				int seat = tickets.get(i).getSeat();
				float price = tickets.get(i).getPrice();
				
				JSONObject object = new JSONObject();
				
				object.put("id", idticket);
				object.put("title", title);
				object.put("date", date);
				object.put("time", time);
				object.put("room", room);
				object.put("seat", seat);
				object.put("price", price);
				
				responseText += object + (i + 1 != l ? "," : "");				
			}
			
			responseText += "]";
			
			response.getWriter().write(responseText);
		}
		catch (SQLException e)
		{
			System.out.println(e);
		}
	}
}