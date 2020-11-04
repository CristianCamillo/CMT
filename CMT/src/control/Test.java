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
@MultipartConfig
public class Test extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Test() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		Part imagePart = request.getPart("image");
		String imageName = imagePart.getSubmittedFileName();
		
		for(Part part : request.getParts())
			part.write("C:\\Users\\Cristian\\git\\CMT\\CMT\\WebContent\\posters\\" + imageName);
		
		System.out.println(imageName);
		
		response.getWriter().print("Successo");
	}

}
