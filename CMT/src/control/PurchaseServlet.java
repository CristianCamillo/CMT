package control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		
		float balance = (float)session.getAttribute("balance");
		Basket basket = (Basket)session.getAttribute("basket");

		response.setContentType("text/plain");
	 	response.setCharacterEncoding("UTF-8");
	 	
	 	if(balance < basket.getTotalPrice())
	 	{
	 		response.getWriter().write("1");
	 		return;
	 	}
	}
}