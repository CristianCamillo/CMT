package model;

public class Room
{
	private int id;
	private byte rows;
	private byte columns;
	
	public Room(int id, byte rows, byte columns)
	{
		this.id = id;
		this.rows = rows;
		this.columns = columns;
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

	public byte getColumns()
	{
		return columns;
	}

	public void setColumns(byte columns)
	{
		this.columns = columns;
	}	
}