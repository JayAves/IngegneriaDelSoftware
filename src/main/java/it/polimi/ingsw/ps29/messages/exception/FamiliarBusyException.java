package it.polimi.ingsw.ps29.messages.exception;

public class FamiliarBusyException extends RejectException {
	
	public FamiliarBusyException () {
		message = "Action rejected: the familiar you want to use is already busy!";
	}

	//auto-generated serialVersionUID
	private static final long serialVersionUID = 7844445028770171834L;

}
