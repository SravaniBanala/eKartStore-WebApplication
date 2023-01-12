package edu.northeastern.csye6220.eKartFinalProject.exception;

import org.springframework.stereotype.Component;

@Component
public class CategoryException extends Exception {

	public CategoryException() 
	{
	}
	public CategoryException(String message)
	{
		super("CategoryException-"+message);
	}
	
	public CategoryException(String message, Throwable cause)
	{
		super("CategoryException-"+message,cause);
	}
	
}
