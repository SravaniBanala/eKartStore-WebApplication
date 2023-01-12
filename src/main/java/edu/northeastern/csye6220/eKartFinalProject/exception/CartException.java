package edu.northeastern.csye6220.eKartFinalProject.exception;

import org.springframework.stereotype.Component;

@Component
public class CartException extends Exception {

	public CartException() 
	{
	}
	public CartException(String message)
	{
		super("CategoryException-"+message);
	}
	
	public CartException(String message, Throwable cause)
	{
		super("CategoryException-"+message,cause);
	}
	
}
