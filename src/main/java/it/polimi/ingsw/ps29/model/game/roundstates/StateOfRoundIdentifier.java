package it.polimi.ingsw.ps29.model.game.roundstates;

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
