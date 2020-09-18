package control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import DAO.FilmDAO;
import DAO.ProjectionDAO;
import model.Basket;
import model.Projection;
import model.Ticket;

@WebServlet("/basketRecap")
public class BasketRecapServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;
 
	public BasketRecapServlet()
    {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		System.out.println("helll");
		
		Basket basket = (Basket)request.getSession().getAttribute("basket");
		
		if(basket == null)
			return;
		
		ArrayList<Ticket> tickets = basket.getTickets();
		ArrayList<Projection> projections;
		ArrayList<String> titles;
	    
		try
		{
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
		
		response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");
	    
		response.getWriter().write(responseText);
	}
}