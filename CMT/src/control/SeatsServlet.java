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
		String idProjection = request.getParameter("idProjection");
		String idRoom = request.getParameter("idRoom");
				
		response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");
		
		try
		{
			Room room = RoomDAO.getRoom(Integer.parseInt(idRoom));
			ArrayList<Short> seats = TicketDAO.getSeats(Integer.parseInt(idProjection));
			
			String responseText = "[";
			
			JSONObject object = new JSONObject();
			
			object.put("rows", room.getRows());
			object.put("columns", room.getColumns());
			
			responseText += object;
			
			for(int i = 0, l = seats.size(); i < l; i++)
			{
				responseText += ",";
				
				short seat = seats.get(i);
				
				byte rows = (byte)(seat / 100);
				byte columns = (byte)(seat % 100);
				
				object = new JSONObject();
				
				object.put("rows", rows);
				object.put("columns", columns);
				
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