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
import utils.DataChecker;

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
		
		if(!DataChecker.checkForRegistration(response, username, password, balance))
			return;
		
		try
		{					
			if(ClientDAO.isRegistered(username) || ManagerDAO.isRegistered(username))
			{
				writeErrorMessage(response, "Questo utente è già registrato");
				return;
			}

			Client newClient = new Client(ClientDAO.getLastId() + 1, username, password, Float.parseFloat(balance));

			ClientDAO.addClient(newClient);

			HttpSession oldSession = request.getSession(false);		    			
			if(oldSession != null)
				oldSession.invalidate();
			
			HttpSession currentSession = request.getSession();
			
			currentSession.setAttribute("id", newClient.getId());
			currentSession.setAttribute("userType", "client");			
	
			currentSession.setMaxInactiveInterval(60 * 60);
			
			// reindirizzamento a homepage
			response.sendRedirect("homepage.jsp");
		}
		catch(SQLException e)
		{
			System.out.println(e);
		}		
	}
	
	private void writeErrorMessage(HttpServletResponse response, String msg) throws IOException
	{
		response.setContentType("text/html");	
		response.getOutputStream().println("<script>alert(\"" + msg + "\");</script>" + 
										   "<meta http-equiv=\"refresh\" content=\"0;URL=registrazione.jsp\">");
		response.getOutputStream().flush();
	}
}