package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.BasketDAO;
import DAO.ClientDAO;
import DAO.TicketDAO;
import model.Basket;

@WebServlet("/purchase")
public class PurchaseServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

    public PurchaseServlet()
    {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{	
		HttpSession session = request.getSession();
		
		int id = (int)session.getAttribute("id");
		float balance = (float)session.getAttribute("balance");
		Basket basket = (Basket)session.getAttribute("basket");

		response.setContentType("text/plain");
	 	response.setCharacterEncoding("UTF-8");
	 	
	 	synchronized(PurchaseServlet.class)
	 	{
		 	try
		 	{
			 	if(BasketDAO.removeTakenSeats(basket))
			 	{
			 		response.getWriter().write("1");
			 		return;
			 	}
			 	
			 	float totalPrice = basket.getTotalPrice();
			 	
			 	if(balance < totalPrice)
			 	{
			 		response.getWriter().write("2");
			 		return;
			 	}
			 	
			 	TicketDAO.addTickets(basket);
			 	ClientDAO.spend(id, totalPrice);
			 	
			 	session.setAttribute("balance", balance - totalPrice);
			 	
			 	response.getWriter().write("0");
		 	}
		 	catch(SQLException e)
			{
		 		System.out.println(e);
				return;
			}
	 	}
	}
}