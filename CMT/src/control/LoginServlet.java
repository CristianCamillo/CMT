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
import utils.FieldValidator;

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
		
		if(!FieldValidator.validateLoginForm(username, password))
			return;
		
		boolean isManager = request.getParameter("isManager") != null;
		
		response.setContentType("text/plain");
	    response.setCharacterEncoding("UTF-8");
		
	    int id = -1;
		float balance = -1;
	    
		try
		{			
			if(!isManager)
			{
				Client client = ClientDAO.getClient(username, password);
				
				if(client == null)
				{
					response.getWriter().write("1");
					return;
				}
				
				id = client.getId();
				balance = client.getBalance();
			}
			else
			{
				Manager manager = ManagerDAO.getManager(username, password);
				
				if(manager == null)
				{
					response.getWriter().write("2");
					return;
				}
				
				id = manager.getId();
			}
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
		
		currentSession.setAttribute("id", id);
		currentSession.setAttribute("username", username);			
		if(!isManager)
			currentSession.setAttribute("balance", balance);			
		currentSession.setAttribute("userType", !isManager ? "client" : "manager");			

		response.getWriter().write("0");
	}
}