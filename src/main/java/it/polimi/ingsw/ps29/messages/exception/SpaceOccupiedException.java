package it.polimi.ingsw.ps29.messages.exception;

public class SpaceOccupiedException extends RejectException {

	public SpaceOccupiedException() {
		message = "Action rejected: this space is already occupied!";
	}
	
	//auto-generated serialVersionUID
	private static final long serialVersionUID = -3111913233764049846L;

}
