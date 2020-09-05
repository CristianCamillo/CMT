package model;

public class Film
{
	private int id;
	private String title;
	private short runningTime;
	private String genre;
	private String director;
	private String actor1;
	private String actor2;
	private String description;
	private String poster;
	
	public Film(int id, String title, short runningTime, String genre, String director, String actor1, String actor2, String description, String poster)
	{
		this.id = id;
		this.title = title;
		this.runningTime = runningTime;
		this.genre = genre;
		this.director = director;
		this.actor1 = actor1;
		this.actor2 = actor2;
		this.description = description;
		this.poster = poster;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public short getRunningTime()
	{
		return runningTime;
	}

	public void setRunningTime(short runningTime)
	{
		this.runningTime = runningTime;
	}

	public String getGenre()
	{
		return genre;
	}

	public void setGenre(String genre)
	{
		this.genre = genre;
	}

	public String getDirector()
	{
		return director;
	}

	public void setDirector(String director)
	{
		this.director = director;
	}

	public String getActor1()
	{
		return actor1;
	}

	public void setActor1(String actor1)
	{
		this.actor1 = actor1;
	}

	public String getActor2()
	{
		return actor2;
	}

	public void setActor2(String actor2)
	{
		this.actor2 = actor2;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public String getPoster()
	{
		return poster;
	}

	public void setPoster(String poster)
	{
		this.poster = poster;
	}
	
	// for javascript
	public String toString()
	{
		return "[\"" + id + "\", \"" + title + "\", \"" + runningTime + "\", \"" + genre + "\", \"" + director + "\", \"" + actor1 + "\", \"" + actor2 + "\", \"" + description + "\", \"" + poster + "\"]";
	}
}