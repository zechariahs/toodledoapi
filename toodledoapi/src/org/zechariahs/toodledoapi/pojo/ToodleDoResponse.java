package org.zechariahs.toodledoapi.pojo;

public class ToodleDoResponse 
{

	int id;
	String message = "";
	
	

	public ToodleDoResponse() 
	{
		// TODO Auto-generated constructor stub
	}
	
	public ToodleDoResponse(int a_iId, String a_sMessage)
	{
		setId(a_iId);
		setMessage(a_sMessage);
	}
	
	public int getId() 
	{
		return id;
	}

	public void setId(int id) 
	{
		this.id = id;
	}

	public String getMessage() 
	{
		return message;
	}

	public void setMessage(String message) 
	{
		this.message = message;
	}

}
