package it.polimi.ingsw.ps29.messages.exception;

public class NotEnoughResourcesException extends RejectException {

	public NotEnoughResourcesException() {
		message = "Action rejected: you don't have enough resources to pay for this card!";
	}
	
	//auto-generated serialVersionUID
	private static final long serialVersionUID = -6075616447599332006L;

}
