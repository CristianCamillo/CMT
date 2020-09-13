package control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import DAO.FilmDAO;
import model.Film;
import utils.FieldValidator;

@WebServlet("/filtre")
public class FiltreServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;
       
    public FiltreServlet()
    {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		System.out.println("INIZIO FILTRE SERVLET");
		
		String title = request.getParameter("title");
		String genre = request.getParameter("genre");
		String director = request.getParameter("director");
		String actor = request.getParameter("actor");

		if(!FieldValidator.validateFiltreForm(title, genre, director, actor))
			return;
		
		response.setContentType("text/plain");
	    response.setCharacterEncoding("UTF-8");
			    
		try
		{
			ArrayList<Film> films = FilmDAO.findFilm(title, genre, director, actor);
			
			String responseText = "";
			
			for(Film film : films)
			{
				JSONObject filmDetails = new JSONObject();
				filmDetails.put("id", film.getId());
				filmDetails.put("title", film.getTitle());
				filmDetails.put("runningTime", film.getRunningTime());
				filmDetails.put("genre", film.getGenre());
				filmDetails.put("director", film.getDirector());
				filmDetails.put("actor1", film.getActor1());
				filmDetails.put("actor2", film.getActor2());
				filmDetails.put("description", film.getDescription());
				filmDetails.put("poster", film.getPoster());
			         
		        JSONObject filmObject = new JSONObject(); 
		        filmObject.put("film", filmDetails);
		        
		        responseText += filmObject;
		        
		        System.out.println(filmObject);
			}
			
			response.getWriter().write(responseText);
			
			/*ArrayList<Film> films = FilmDAO.findFilm(title, genre, director, actor);
			
			request.getSession().setAttribute("films", films);
			
			ArrayList<ArrayList<Projection>> projections = new ArrayList<ArrayList<Projection>>();			
			ArrayList<ArrayList<ArrayList<String>>> roomStates = new ArrayList<ArrayList<ArrayList<String>>>(); // IL MALE
			
			for(int i = 0; i < films.size(); i++)
			{
				ArrayList<Projection> ps = ProjectionDAO.getProjections(films.get(i).getId(), false);
				ArrayList<ArrayList<String>> ss = new ArrayList<ArrayList<String>>();
				
				for(Projection p : ps)
				{
					Room room = RoomDAO.getRoom(p.getIdRoom());
					
					ArrayList<Short> seats = TicketDAO.getSeats(p.getId());
					
					ArrayList<String> stato = new ArrayList<String>();
					
					stato.add("\"" + room.getSeatsPerRow() + "-" + room.getRows() + "\"");
					
					for(Short seat : seats)
					{						
						int x = seat / 100;
						int y = seat % 100;
						
						stato.add("\"" + x + "-" + y + "\"");
					}
							
					ss.add(stato);
				}
				
				projections.add(ps);
				roomStates.add(ss);
			}
			
			request.getSession().setAttribute("projections", projections);
			request.getSession().setAttribute("roomStates", roomStates);
			
			request.getRequestDispatcher("homepage.jsp").forward(request, response);*/
		}
		catch(SQLException e)
		{
			System.out.println(e);
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
}