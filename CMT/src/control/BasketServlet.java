package control;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/basket")
public class BasketServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;
       
    public BasketServlet()
    {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String selected = request.getParameter("selected");
		
		System.out.println(selected);
	}
}