package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.ProjectionDAO;
import model.Projection;
import utils.FieldValidator;

@WebServlet("/addProjection")
public class AddProjectionServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

    public AddProjectionServlet()
    {
        super();
    }
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String date = request.getParameter("date");
		String time = request.getParameter("time");
		String price = request.getParameter("price");
		String idroom = request.getParameter("idroom");
		String idfilm = request.getParameter("idfilm");
		
		if(!FieldValidator.validateProjectionForm(date, time, price, idroom, idfilm))
			return;
		
		try
		{
			Projection projection = new Projection(ProjectionDAO.getAvailableId(), Integer.parseInt(date), Short.parseShort(time), Float.parseFloat(price), Integer.parseInt(idroom), Integer.parseInt(idfilm));
			ProjectionDAO.addProjection(projection);
		}
		catch(SQLException e)
		{
			System.out.println(e);
			return;
		}
		response.setContentType("text/plain");
	 	response.setCharacterEncoding("UTF-8");
	 	
		response.getWriter().write("0");
	}
}