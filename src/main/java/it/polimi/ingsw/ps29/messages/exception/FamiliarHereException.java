package it.polimi.ingsw.ps29.messages.exception;

public class FamiliarHereException extends RejectException {
	
	public FamiliarHereException () {
		message = "Action rejected: one member of your family is already here!";
	}

	//auto-generated serialVersionUID
	private static final long serialVersionUID = 5896843365463548424L;

}
