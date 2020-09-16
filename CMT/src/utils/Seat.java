package utils;

public class Seat
{
	private byte x;
	private byte y;
	
	public Seat(byte x, byte y)
	{
		this.x = x;
		this.y = y;
	}
	
	public byte getX() { return x; }
	public byte getY() { return y; }
	
	public void setX(byte x) { this.x = x; }
	public void setY(byte y) { this.y = y; }
}