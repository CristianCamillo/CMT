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
import model.Room;

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
		
		response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");
		
		try
		{
			Room room = RoomDAO.getRoom(idProjection);
			ArrayList<byte[]> seats = TicketDAO.getOccupiedSeats(idProjection);			
			
			JSONObject object = new JSONObject();
			
			object.put("rows", room.getRows());
			object.put("columns", room.getColumns());			
			
			String responseText = "[" + object;
			
			for(int i = 0, l = seats.size(); i < l; i++)
			{
				responseText += ",";
				
				byte[] seat = seats.get(i);
				
				object = new JSONObject();
				
				object.put("x", seat[0]);
				object.put("y", seat[1]);				
				
				responseText += object;				
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