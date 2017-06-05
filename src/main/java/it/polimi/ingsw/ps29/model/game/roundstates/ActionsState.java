package it.polimi.ingsw.ps29.model.game.roundstates;

import it.polimi.ingsw.ps29.model.game.GameBoard;

public class ActionsState implements RoundState {

	
	@Override
	public RoundState doAction(int roundNumber, GameBoard board) {
		
		return (roundNumber%2==0) ? new VaticanReportState() : new EndOfTheRoundState();
			

	}

}
