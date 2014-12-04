package org.erp.util.exception;

public class AppException extends RuntimeException {
	public AppException()
	{
		
	}
	public AppException(String msg)
	{
		super(msg);
	}
	public AppException(Throwable t)
	{
		super(t);
	}
	public AppException(String msg , Throwable t)
	{
		super(msg , t);
	}
}

