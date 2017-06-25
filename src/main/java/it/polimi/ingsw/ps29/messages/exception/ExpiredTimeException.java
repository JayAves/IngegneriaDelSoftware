package it.polimi.ingsw.ps29.messages.exception;

public class ExpiredTimeException extends RejectException {

	public ExpiredTimeException () {
		message = "Time expired!";
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1431338168254942152L;

}
