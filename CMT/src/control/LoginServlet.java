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
import exceptions.UsernamePasswordNotFoundException;
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
		
		if(!DataChecker.checkForLogin(response, username, password))
			return;
		
		try
		{
			int id;
			boolean isClient;
			
			try
			{
				id = ClientDAO.getId(username, password);
				isClient = true;
			}
			catch(UsernamePasswordNotFoundException e)
			{
				try
				{
					id = ManagerDAO.getId(username, password);
				   	isClient = false;
				}
				catch(UsernamePasswordNotFoundException e1)
				{
					writeErrorMessage(response, "Dati non associati ad alcun utente");
					return;
				}
			}
		    
		    HttpSession oldSession = request.getSession(false);
		    			
			// se esiste già una sessione per questo utente, viene invalidata
			if(oldSession != null)
				oldSession.invalidate();
			
			// crea una nuova sessione
			HttpSession currentSession = request.getSession();
			
			// attributo "id"
			currentSession.setAttribute("id", id);
			
			// attributo "tipo"
			currentSession.setAttribute("userType", isClient ? "client" : "manager");
			
			// vengono impostati massimo 5 minuti di inattività prima che la sessione venga eliminata
			currentSession.setMaxInactiveInterval(5 * 60);
			
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
										   "<meta http-equiv=\"refresh\" content=\"0;URL=login.jsp\">");
		response.getOutputStream().flush();
	}
}