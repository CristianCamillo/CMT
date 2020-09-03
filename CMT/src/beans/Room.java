package beans;

public class Room
{
	private int id;
	private byte rows;
	private byte seatsPerRow;
	
	public Room(int id, byte rows, byte seatsPerRow)
	{
		this.id = id;
		this.rows = rows;
		this.seatsPerRow = seatsPerRow;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public byte getRows()
	{
		return rows;
	}

	public void setRows(byte rows)
	{
		this.rows = rows;
	}

	public byte getSeatsPerRow()
	{
		return seatsPerRow;
	}

	public void setSeatsPerRow(byte seatsPerRow)
	{
		this.seatsPerRow = seatsPerRow;
	}	
}