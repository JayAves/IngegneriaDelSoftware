package it.polimi.ingsw.ps29.model.game.roundstates;

import it.polimi.ingsw.ps29.model.game.GameBoard;

public class VaticanReportState implements RoundState {

	@Override
	public RoundState doAction(int roundNumber, GameBoard board) {
		//gestione del rapporto in vaticano
		return new EndOfTheRoundState();
	}

}
