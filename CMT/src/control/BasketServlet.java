package control;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

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

	 	ArrayList<byte[]> list = manualParseSeats(json);
	 	
	 	for(byte[] el : list)
	 		System.out.println(el[0] + "-" + el[1]);
	 	
	 	//JSONObject obj = new JSONObject(json);
	 	//String pageName = obj.getJSONObject("pageInfo").getString("pageName");
	 	/*
	 	 JSONArray arr = obj.getJSONArray("posts");
        for (int i = 0; i < arr.length(); i++) {
            String post_id = arr.getJSONObject(i).getString("post_id");
            System.out.println(post_id);
        }*/
	 	
	 	response.setContentType("text/plain");
	 	response.setCharacterEncoding("UTF-8");
		
		response.getWriter().write("0");
	}
	
	private ArrayList<byte[]> manualParseSeats(String json)
	{
		ArrayList<byte[]> list = new ArrayList<byte[]>();
		
		boolean onX = true;
		
		int n = 0;
		
		int currentX = 0;
		
		for(int i = 6, l = json.length(); i < l; i++)
		{
			char c = json.charAt(i);

			if(onX)
			{
				if(c != ',')
					n = n * 10 + (c - 48);
				else
				{
					onX = false;
					currentX = n;
					n = 0;
					i += 4;
				}
			}
			else
			{
				if(c != '}')
					n = n * 10 + (c - 48);
				else
				{
					onX = true;
					list.add(new byte[] {(byte)currentX, (byte)n});
					n = 0;
					i += 6;
				}
			}
		}
		
		return list;
	}
}