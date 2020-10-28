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

@WebServlet("/personalData")
public class PersonalDataServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	
    public PersonalDataServlet()
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
				if(!FieldValidator.validatePositiveFloat(data))
					return;
				break;
			default:
				return;
		}
		
		HttpSession session = request.getSession();
		
		int id = (Integer)session.getAttribute("id");
		String userType = (String)session.getAttribute("userType");

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
					
					session.setAttribute("username", data);
					
					response.getWriter().write(data);
				
					break;
				case "1":					
					if(userType.equals("client"))
						ClientDAO.updatePassword(id, data);
					else
						ManagerDAO.updatePassword(id, data);
					
					response.getWriter().write("placeholder");
					
					break;
				case "2":
					float amount = Float.parseFloat(data);
					
					ClientDAO.addAmount(id, amount);

					float newBalance = Float.parseFloat(session.getAttribute("balance") + "") + amount;
					
					session.setAttribute("balance", newBalance + "");
					
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