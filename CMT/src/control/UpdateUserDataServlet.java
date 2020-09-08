package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.ClientDAO;
import DAO.ManagerDAO;

@WebServlet("/updateUserData")
public class UpdateUserDataServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	
    public UpdateUserDataServlet()
    {
        super();
    }
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{		
		String toChange = request.getParameter("toChange");
		
		String userType = (String)request.getSession().getAttribute("userType");
		
		try
		{
			switch(toChange)
			{
				case "0":
					String newUsername = request.getParameter("newUsername");
					
					if(userType.equals("client"))
						ClientDAO.updateUsername((Integer)request.getSession().getAttribute("id"), newUsername);
					else
						ManagerDAO.updateUsername((Integer)request.getSession().getAttribute("id"), newUsername);
						
					break;
					
			}
		}
		catch (SQLException e)
		{
			System.out.println(e);
		}
		

		response.setContentType("text/plain");  // Set content type of the response so that jQuery knows what it can expect.
	    response.setCharacterEncoding("UTF-8"); // You want world domination, huh?
	    response.getWriter().write(request.getParameter("newUsername"));
		//request.getRequestDispatcher(userType + "PersonalArea.jsp").forward(request, response);
	}
}