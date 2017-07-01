package it.polimi.ingsw.ps29.messages.exception;


public class ExpiredTimeException extends RejectException {

	public ExpiredTimeException () {
		message = "Time expired! Restart main to get back in the game! \n";
	}
	
	private static final long serialVersionUID = 1431338168254942152L;

}
