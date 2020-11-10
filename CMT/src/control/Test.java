package control;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet("/test")
@MultipartConfig(
		fileSizeThreshold = 1024 * 1024 * 2,
		maxFileSize = 1024 * 1024 * 10,
		maxRequestSize = 1024 * 1024 * 50)
public class Test extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	
	private static final String POSTERS_PATH = "C:\\Users\\Cristian\\git\\CMT\\CMT\\WebContent\\posters\\";

    public Test()
    {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		//String test = request.getParameter("test");
		
		//System.out.println("Test: " + test);
		
		Part posterPart = request.getPart("poster");
		String posterName = posterPart.getSubmittedFileName();
		
		System.out.println("PosterName: " + posterName);
		
		for(Part part : request.getParts())
			part.write(POSTERS_PATH + posterName);
	}
}