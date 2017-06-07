package it.polimi.ingsw.ps29.model.game.roundstates;

import it.polimi.ingsw.ps29.model.game.Match;

public class VaticanReportState implements RoundState {

	@Override
	public RoundState doAction(int roundNumber, Match match) {
		//gestione del rapporto in vaticano
		return new EndOfTheRoundState();
	}

}
