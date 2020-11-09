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
import utils.DataParser;
import utils.FieldValidator;

@WebServlet("/updateProjection")
public class UpdateProjectionServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

    public UpdateProjectionServlet()
    {
        super();
    }
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		int id = Integer.parseInt(request.getParameter("idProjection"));
		String date = DataParser.parseDate(request.getParameter("date"));
		String time = DataParser.parseTime(request.getParameter("time"));
		String price = request.getParameter("price");
		String idroom = request.getParameter("idRoom");
		
		if(!FieldValidator.validateProjectionForm(date, time, price, idroom, "0"))
			return;
		
		try
		{
			Projection projection = new Projection(id, Integer.parseInt(date), Short.parseShort(time), Float.parseFloat(price), Integer.parseInt(idroom), 0);
			ProjectionDAO.updateProjection(projection);
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

