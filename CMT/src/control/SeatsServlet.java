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

import DAO.RoomDAO;
import DAO.TicketDAO;
import model.Basket;
import model.Room;
import model.Ticket;

@WebServlet("/seats")
public class SeatsServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

    public SeatsServlet()
    {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		int idProjection = Integer.parseInt(request.getParameter("idProjection"));
		
		request.getSession().setAttribute("idProjection", idProjection);
		
		Basket basket = (Basket)request.getSession().getAttribute("basket");
		
	    Room room = null;
	    ArrayList<byte[]> seats = null;
	    
		try
		{
			room = RoomDAO.getRoom(idProjection);
			seats = TicketDAO.getOccupiedSeats(idProjection);			
		}
		catch(SQLException e)
		{
			System.out.println(e);
			return;
		}
		
		String responseText = "[";
		
		JSONObject object = new JSONObject();
		
		object.put("rows", room.getRows());
		object.put("columns", room.getColumns());			
		
		responseText += object;
		
		if(basket != null)
		{
			ArrayList<Ticket> tickets = basket.getTickets();
			
			for(Ticket ticket : tickets)
				if(ticket.getIdProjection() == idProjection)
				{
					responseText += ",";
					
					object = new JSONObject();
					
					object.put("x", ticket.getX());
					object.put("y", ticket.getY());
					object.put("occupied", false);
					responseText += object;					
				}			
		}
		
		for(int i = 0, l = seats.size(); i < l; i++)
		{
			responseText += ",";
			
			byte[] seat = seats.get(i);
			
			object = new JSONObject();
			
			object.put("x", seat[0]);
			object.put("y", seat[1]);
			object.put("occupied", true);
			
			responseText += object;				
		}
		
		responseText += "]";
		
		response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");
		
		response.getWriter().write(responseText);
	}
}