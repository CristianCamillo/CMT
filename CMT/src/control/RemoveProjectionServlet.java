package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.ProjectionDAO;

@WebServlet("/removeProjection")
public class RemoveProjectionServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

    public RemoveProjectionServlet()
    {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		int id = Integer.parseInt(request.getParameter("id"));
		
		try
		{
			ProjectionDAO.removeProjection(id);
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