package it.polimi.ingsw.ps29.model.game.roundstates;

/**
 * Associates round status with a label and a number.
 * @author Pietro Melzi
 * @author Pietro Grotti
 * @author Giovanni Mele
 *
 */
public enum StateOfRoundIdentifier {
	
	ROUND_SETUP (1), ACTIONS (2), VATICAN_REPORT(3), END_OF_THE_ROUND(4);
	
	private final int stateNumber;
	
	StateOfRoundIdentifier (int n) {
		stateNumber = n;
	}
	
	public int getStateNumber () {
		return stateNumber;
	}
	
	
}
