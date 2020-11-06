package control;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.ProjectionDAO;
import model.Basket;
import model.Ticket;

@WebServlet("/addToBasket")
public class AddToBasketServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;
       
    public AddToBasketServlet()
    {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		HttpSession session = request.getSession();
		
		String userType = (String)session.getAttribute("userType");
		
		if(userType == null || !userType.equals("client"))
			return;
		
		int idClient = (int)session.getAttribute("id");
		int idProjection = (int)session.getAttribute("idProjection");
		
		float price = -1;
		
		try
		{
			price = ProjectionDAO.getPrice(idProjection);
		}
		catch(SQLException e)
		{
			System.out.println(e);
			return;
		}
		
		BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
		
	 	String json = br.readLine();

	 	ArrayList<byte[]> seats = manualParseSeats(json);
	 	
	 	Basket basket = (Basket)session.getAttribute("basket");
	 	
	 	if(basket == null)
	 	{
	 		basket = new Basket();
	 		session.setAttribute("basket", basket);
	 	}
	 	
	 	basket.removeProjectionTickets(idProjection);
	 	
	 	for(byte[] seat : seats)
	 		basket.addTicket(new Ticket(0, seat[0], seat[1], price, idClient, idProjection));
	 		 	
	 	response.setContentType("text/plain");
	 	response.setCharacterEncoding("UTF-8");
	 	
		response.getWriter().write("0");
	}
	
	private ArrayList<byte[]> manualParseSeats(String json)
	{
		ArrayList<byte[]> list = new ArrayList<byte[]>();
		
		boolean onX = true;
		
		int n = 0;
		
		int currentX = 0;
		
		for(int i = 6, l = json.length(); i < l; i++)
		{
			char c = json.charAt(i);

			if(onX)
			{
				if(c != ',')
					n = n * 10 + (c - 48);
				else
				{
					onX = false;
					currentX = n;
					n = 0;
					i += 4;
				}
			}
			else
			{
				if(c != '}')
					n = n * 10 + (c - 48);
				else
				{
					onX = true;
					list.add(new byte[] {(byte)currentX, (byte)n});
					n = 0;
					i += 6;
				}
			}
		}
		
		return list;
	}
}