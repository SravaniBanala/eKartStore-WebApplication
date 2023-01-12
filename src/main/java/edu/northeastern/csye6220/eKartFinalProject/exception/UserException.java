package edu.northeastern.csye6220.eKartFinalProject.exception;

import org.springframework.stereotype.Component;

@Component
public class UserException extends Exception {

	public UserException() 
	{
	}
	
	public UserException(String message)
	{
		super("UserException-"+message);
	}
	
	public UserException(String message, Throwable cause)
	{
		super("UserException-"+message,cause);
	}
	
}
