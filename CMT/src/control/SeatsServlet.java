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
		
		int id = Integer.parseInt(idProjection);
		
		request.getSession().setAttribute("idProjection", id);
		
		response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");
		
		try
		{
			byte[] roomSize = RoomDAO.getRoomSize(id);
			ArrayList<Short> seats = TicketDAO.getSeats(id);
			
			String responseText = "[";
			
			JSONObject object = new JSONObject();
			
			object.put("rows", roomSize[0]);
			object.put("columns", roomSize[1]);
			
			responseText += object;
			
			for(int i = 0, l = seats.size(); i < l; i++)
			{
				responseText += ",";
				
				short seat = seats.get(i);
				
				object = new JSONObject();
				
				object.put("rows", (byte)(seat / 100));
				object.put("columns", (byte)(seat % 100));
				
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