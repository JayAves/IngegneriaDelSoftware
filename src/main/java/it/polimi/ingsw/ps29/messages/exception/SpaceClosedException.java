package it.polimi.ingsw.ps29.messages.exception;

/**
 * If the number of players is less than four and one of them tries to place a familiar in a closed market
 * @author Pietro Grotti
 *
 */
public class SpaceClosedException extends RejectException {

	public SpaceClosedException() {
		message = "Action rejected: this market is closed!";
	}
	
	//auto-generated serialVersionUID
	private static final long serialVersionUID = -3111913233764049846L;

}
