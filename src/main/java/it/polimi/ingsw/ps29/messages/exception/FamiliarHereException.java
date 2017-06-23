package it.polimi.ingsw.ps29.messages.exception;

public class FamiliarHereException extends RejectException {
	
	public FamiliarHereException () {
		message = "Action rejected: one memberof your family is already here!";
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 5896843365463548424L;

}
