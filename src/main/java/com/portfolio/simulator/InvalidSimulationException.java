package com.portfolio.simulator;


/*
 * A custom exception to represent an invalid simulation.
 */
public class InvalidSimulationException extends Exception {
	
	private static final long serialVersionUID = 103960239313821997L;

	public InvalidSimulationException()
	{
		
	}
	
	public InvalidSimulationException(String message)
	{		
		super(message);
	}
	
	public InvalidSimulationException(Throwable cause)
	{		
		super(cause);
	}
	
	public InvalidSimulationException(String message, Throwable cause) {
        super(message, cause);
    }
}