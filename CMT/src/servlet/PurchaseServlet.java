package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.TicketDAO;
import DAO.ClientDAO;
import beans.Ticket;
import exceptions.CannotPurchaseException;

@WebServlet("/purchase")
public class PurchaseServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

    public PurchaseServlet()
    {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		int idClient = (Integer)request.getSession().getAttribute("id");	
		float amount = Float.parseFloat(request.getParameter("amount"));
		
		try
		{
			ClientDAO.spend(idClient, amount);
		}
		catch(CannotPurchaseException e)
		{
			writeMessage(response, "Saldo non sufficiente per effettuare l'acquisto");
		}
		catch(SQLException e)
		{
			System.out.println("Purchase Servlet SQL Exception");
		}		
		
		String seatsString = request.getParameter("seats");
		int idProjection = Integer.parseInt(request.getParameter("idProjection"));
		
		String[] aSeats = seatsString.split("/");
		
		try
		{
			int lastId = TicketDAO.getLastId();
			int counter = 0;
			
			for(String sSeat : aSeats)
			{
				counter++;
				String[] aSeat = sSeat.split("-");
				
				short seat = (short)((Integer.parseInt(aSeat[0]) + 1) * 100 + Integer.parseInt(aSeat[1]) + 1);
				
				TicketDAO.addTicket(new Ticket(lastId + counter, seat, idClient, idProjection));
			}
			
			 HttpSession oldSession = request.getSession(false);
 			
			// se esiste già una sessione per questo utente, viene invalidata
			if(oldSession != null)
				oldSession.invalidate();
			
			// crea una nuova sessione
			HttpSession currentSession = request.getSession();
			
			// attributo "id"
			currentSession.setAttribute("id", idClient);
			
			// attributo "tipo"
			currentSession.setAttribute("tipo", "client");
			
			// vengono impostati massimo 5 minuti di inattività prima che la sessione venga eliminata
			currentSession.setMaxInactiveInterval(5 * 60);
			
			// reindirizzamento a homepage
			response.sendRedirect("homepage.jsp");
		}
		catch(CannotPurchaseException e)
		{
			writeMessage(response, "Almeno uno dei biglietti è stato acquistato");
		}
		catch(SQLException e)
		{
			System.out.println(e);
		}
	}
	
	private void writeMessage(HttpServletResponse response, String msg) throws IOException
	{
		response.setContentType("text/html");	
		response.getOutputStream().println("<script>alert(\"" + msg + "\");</script>" + 
										   "<meta http-equiv=\"refresh\" content=\"0;URL=homepage.jsp\">");
		response.getOutputStream().flush();
	}
}