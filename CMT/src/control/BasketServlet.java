package control;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

@WebServlet("/basket")
public class BasketServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;
       
    public BasketServlet()
    {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
		
	 	String json = br.readLine();
	 	
	 	System.out.println(json);

	 	JSONObject obj = new JSONObject(json);
	 	String pageName = obj.getJSONObject("pageInfo").getString("pageName");
	 	
	 	response.setContentType("text/plain");
	 	response.setCharacterEncoding("UTF-8");
		
		response.getWriter().write("[]");
	}
}