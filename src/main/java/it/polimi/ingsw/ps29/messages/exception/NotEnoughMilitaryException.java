package it.polimi.ingsw.ps29.messages.exception;

public class NotEnoughMilitaryException extends RejectException {

	public NotEnoughMilitaryException() {
		message = "Action rejected: you don't have enough military points to take this card!";
	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -924280406593117473L;

}
