package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.ClientDAO;
import DAO.ManagerDAO;
import exceptions.UsernameTakenException;
import utils.FieldValidator;

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
		
		String data = "";
		
		switch(toChange)
		{
			case "0":
				data = request.getParameter("newUsername");
				if(!FieldValidator.validateUsername(data))
					return;
				break;
			case "1":
				data = request.getParameter("newPassword");
				if(!FieldValidator.validatePassword(data))
					return;
				break;
			case "2":
				data = request.getParameter("amount");
				if(!FieldValidator.validateAmount(data))
					return;
				break;
			default:
				return;
		}		
		
		HttpSession currentSession = request.getSession();
		
		int id = (Integer)currentSession.getAttribute("id");
		String userType = (String)currentSession.getAttribute("userType");

		response.setContentType("text/plain");
	    response.setCharacterEncoding("UTF-8");
		
		try
		{
			switch(toChange)
			{
				case "0":					
					if(userType.equals("client"))
						ClientDAO.updateUsername(id, data);
					else
						ManagerDAO.updateUsername(id, data);
					
					currentSession.setAttribute("username", data);
					
					response.getWriter().write(data);
				
					break;
				case "1":					
					if(userType.equals("client"))
						ClientDAO.updatePassword(id, data);
					else
						ManagerDAO.updatePassword(id, data);
					
					response.getWriter().write("_success_");
					
					break;
				case "2":
					float amount = Float.parseFloat(data);
					
					ClientDAO.addAmount(id, amount);
					
					float newBalance = (Float)currentSession.getAttribute("balance") + amount;
					
					currentSession.setAttribute("balance", newBalance + "");
					
					response.getWriter().write(newBalance + "");
			}
		}
		catch(UsernameTakenException e)
		{
			response.getWriter().write("1");
		}
		catch(SQLException e)
		{
			System.out.println(e);
		}
	}
}