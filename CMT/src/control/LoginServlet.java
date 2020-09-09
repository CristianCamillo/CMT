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
import model.Client;
import model.Manager;
import utils.DataChecker;

@WebServlet("/login")
public class LoginServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

    public LoginServlet()
    {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		if(!DataChecker.checkForLogin(username, password))
			return;
		
		boolean isManager = request.getParameter("isManager") != null;
		
		try
		{
			int id;
			float balance = -1;
			
			if(!isManager)
			{
				Client client = ClientDAO.getClient(username, password);
				
				if(client == null)
					return; // mettere messaggio errore
				
				id = client.getId();
				balance = client.getBalance();
			}
			else
			{
				Manager manager = ManagerDAO.getManager(username, password);
				
				if(manager == null)
					return; // mettere messaggio errore
				
				id = manager.getId();
			}
		    
		    HttpSession oldSession = request.getSession(false);		    			
			if(oldSession != null)
				oldSession.invalidate();
			
			HttpSession currentSession = request.getSession();
			
			currentSession.setAttribute("id", id);
			currentSession.setAttribute("username", username);
			
			if(!isManager)
				currentSession.setAttribute("balance", balance);
			
			currentSession.setAttribute("userType", !isManager ? "client" : "manager");			
	
			currentSession.setMaxInactiveInterval(60 * 60);

			response.sendRedirect("homepage.jsp");				
		}
		catch(SQLException e)
		{
			System.out.println(e);
		}		
	}
}