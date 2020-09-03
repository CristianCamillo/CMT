package beans;

public class Projection
{
	private int id;
	private int date;
	private short time;
	private float price;
	private int idRoom;
	private int idFilm;
	
	public Projection(int id, int date, short time, float price, int idRoom, int idFilm)
	{
		this.id = id;
		this.date = date;
		this.time = time;
		this.price = price;
		this.idRoom = idRoom;
		this.idFilm = idFilm;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public int getDate()
	{
		return date;
	}

	public void setDate(int date)
	{
		this.date = date;
	}
	
	public short getTime()
	{
		return time;
	}

	public void setTime(short time)
	{
		this.time = time;
	}

	public float getPrice()
	{
		return price;
	}

	public void setPrice(float price)
	{
		this.price = price;
	}

	public int getIdRoom()
	{
		return idRoom;
	}

	public void setIdRoom(int idRoom)
	{
		this.idRoom = idRoom;
	}

	public int getIdFilm()
	{
		return idFilm;
	}

	public void setIdFilm(int idFilm)
	{
		this.idFilm = idFilm;
	}
	
	// for javascript
	public String toString()
	{
		return "[\"" + id + "\", \"" + date + "\", \"" + time + "\", \"" + price + "\", \"" + idRoom + "\", \"" + idFilm + "\"]";
	}
}