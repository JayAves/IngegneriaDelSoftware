package it.polimi.ingsw.ps29.model.game.roundstates;

import it.polimi.ingsw.ps29.model.game.GameBoard;

public class RoundSetupState implements RoundState {

	@Override
	public RoundState doAction(int roundNumber, GameBoard board) {
		//metti le carte sulle torri
		//lancia i dadi
		return new ActionsState ();
		
	}

}
