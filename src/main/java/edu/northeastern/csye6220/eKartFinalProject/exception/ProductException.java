package edu.northeastern.csye6220.eKartFinalProject.exception;

import org.springframework.stereotype.Component;

@Component
public class ProductException extends Exception
{
	public ProductException() 
	{
	}
	public ProductException(String message)
	{
		super("ProductException-"+ message);
	}
	
	public ProductException(String message, Throwable cause)
	{
		super("ProductException-"+ message,cause);
	}
}