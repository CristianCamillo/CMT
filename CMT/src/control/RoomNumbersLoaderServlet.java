package control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.RoomDAO;

@WebServlet("/roomNumbersLoader")
public class RoomNumbersLoaderServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

    public RoomNumbersLoaderServlet() 
    {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		ArrayList<Integer> roomNumbers = null;
		
		try
		{
			roomNumbers = RoomDAO.getAllNumbers();
		}
		catch(SQLException e)
		{
			System.out.println(e);
			return;
		}
		
		String responseText = "[";
		
		for(int i = 0, l = roomNumbers.size(); i < l; i++)			
			responseText += roomNumbers.get(i) + (i + 1 != l ? "," : "");				
		
		responseText += "]";
		
		response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");
	    
		response.getWriter().write(responseText);
	}
}