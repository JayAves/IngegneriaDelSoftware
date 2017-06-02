package it.polimi.ingsw.ps29.model.game.round;

public class ActionsState implements RoundState {

	private static final int NUMBER_OF_FAMILIARS = 4;
	
	@Override
	public RoundState doAction(int roundNumber) {
		int actionPerformed = 0;
		for (int i=0; i<NUMBER_OF_FAMILIARS; i++) {
			actionPerformed = 0;
			while (actionPerformed < 4) {
				//controller.callCorrectView();
				//if stateofaction instanceof PERFORMED
					//actionPerformed++;
			}
		}
		return (roundNumber%2==0) ? new VaticanReportState() : new EndOfTheRoundState();
			

	}

}
