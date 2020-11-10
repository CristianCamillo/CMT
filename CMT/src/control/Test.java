package control;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 * Servlet implementation class Test
 */
@WebServlet("/test")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB after which the file will be
// temporarily stored on disk
maxFileSize = 1024 * 1024 * 10, // 10MB maximum size allowed for uploaded files
maxRequestSize = 1024 * 1024 * 50) // 50MB overall size of all uploaded files
public class Test extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String POSTERS_PATH = "C:\\Users\\Cristian\\git\\CMT\\CMT\\WebContent\\posters\\";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Test() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String test = request.getParameter("test");
		
		System.out.println("Test: " + test);
		
		Part posterPart = request.getPart("image");
		String posterName = posterPart.getSubmittedFileName();
		
		System.out.println("PosterName: " + posterName);
		
		for(Part part : request.getParts())
			part.write(POSTERS_PATH + posterName);
	}

}
