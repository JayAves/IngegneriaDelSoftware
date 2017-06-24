package it.polimi.ingsw.ps29.messages.exception;

public class TowerCoinMalusException extends RejectException {

	public TowerCoinMalusException() {
		message = "Action rejected: you can't afford to pay three coins to place your familiar on this tower!";
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1313686619506337222L;
	
}
