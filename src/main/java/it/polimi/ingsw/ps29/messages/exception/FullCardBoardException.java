package it.polimi.ingsw.ps29.messages.exception;

public class FullCardBoardException extends RejectException {
	
	public FullCardBoardException() {
		message = "Action rejected: you have no space on your board for this card!";
	}

	//auto-generated serialVersionUID
	private static final long serialVersionUID = -9171963974503204315L;

}
