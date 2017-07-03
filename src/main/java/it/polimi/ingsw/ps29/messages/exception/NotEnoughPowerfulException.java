package it.polimi.ingsw.ps29.messages.exception;

public class NotEnoughPowerfulException extends RejectException {
	
	public NotEnoughPowerfulException () {
		message = "Action rejected: value of the action isn't enough powerful!";
	}

	//auto-generated serialVersionUID
	private static final long serialVersionUID = 7564590807042881141L;

}
