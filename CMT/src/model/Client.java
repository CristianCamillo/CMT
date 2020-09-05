package model;

public class Client
{	
	private int id;
	private String username;
	private String password;
	private float balance;
	
	public Client(int id, String username, String password, float balance)
	{
		this.id = id;
		this.username = username;
		this.password = password;
		this.balance = balance;
	}
	
	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}
	
	public float getBalance()
	{
		return balance;
	}
	
	public void setBalance(float balance)
	{
		this.balance = balance;
	}
}