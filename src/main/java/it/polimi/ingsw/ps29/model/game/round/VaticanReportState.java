package it.polimi.ingsw.ps29.model.game.round;

public class VaticanReportState implements RoundState {

	@Override
	public RoundState doAction(int roundNumber) {
		//gestione del rapporto in vaticano
		return new EndOfTheRoundState();
	}

}
