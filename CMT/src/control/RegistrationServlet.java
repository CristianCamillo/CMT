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
import model.Client;
import utils.FieldValidator;

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;
       
	public RegistrationServlet()
    {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String balance = request.getParameter("balance");
		
		if(!FieldValidator.validateRegistrationForm(username, password, balance))
			return;
		
		response.setContentType("text/plain");
	    response.setCharacterEncoding("UTF-8");
		
	    Client newClient = null;
	    
		try
		{					
			if(ClientDAO.isRegistered(username))
			{
				response.getWriter().write("1");
				return;
			}

			newClient = new Client(ClientDAO.getAvailableId(), username, password, Float.parseFloat(balance));

			ClientDAO.addClient(newClient);
		}
		catch(SQLException e)
		{
			System.out.println(e);
			return;
		}		
		
		HttpSession oldSession = request.getSession(false);
		if(oldSession != null)
			oldSession.invalidate();			
		HttpSession currentSession = request.getSession();
		currentSession.setMaxInactiveInterval(60 * 60);
		
		currentSession.setAttribute("id", newClient.getId());
		currentSession.setAttribute("username", newClient.getUsername());
		currentSession.setAttribute("balance", newClient.getBalance());
		currentSession.setAttribute("userType", "client");			
		
		response.getWriter().write("0");
	}
}