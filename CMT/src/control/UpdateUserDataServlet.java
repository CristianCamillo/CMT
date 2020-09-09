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
import utils.DataChecker;

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
		
		if(!DataChecker.checkRange(toChange, 0, 2))
			return;
		
		String data = "";
		
		switch(toChange)
		{
			case "0":
				data = request.getParameter("newUsername");
				if(!DataChecker.checkLength(data, 6, 20))
					return;
				break;
			case "1":
				data = request.getParameter("newPassword");
				if(!DataChecker.checkLength(data, 6, 20))
					return;
				break;
			case "2":
				data = request.getParameter("amount");
				if(!DataChecker.checkIsPos(data))
					return;
		}
		
		int id = (Integer)request.getSession().getAttribute("id");
		String userType = (String)request.getSession().getAttribute("userType");
		
		// setup per risposta ajax
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
						
					response.getWriter().write(data);
				
					break;
				case "1":					
					if(userType.equals("client"))
						ClientDAO.updatePassword(id, data);
					else
						ManagerDAO.updatePassword(id, data);
					
					break;
				case "2":
					float amount = Float.parseFloat(data);
					
					ClientDAO.addAmount(id, amount);
					
					response.getWriter().write(((Float)request.getSession().getAttribute("balance") + amount) + "");
			}
		}
		catch (SQLException e)
		{
			System.out.println(e);
		}
	}
}