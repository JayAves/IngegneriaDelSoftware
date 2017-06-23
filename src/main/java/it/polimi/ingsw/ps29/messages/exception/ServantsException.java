package it.polimi.ingsw.ps29.messages.exception;

public class ServantsException extends RejectException{

	public ServantsException() {
		message = "Action rejected: you don't have enough servants!";
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = -2729074865585947957L;

}
