package it.polimi.ingsw.ps29.model.game.round;

public class EndOfTheRoundState implements RoundState {

	@Override
	public RoundState doAction(int roundNumber) {
		//modifica ordine di turno
		//imposta tutti i busy su familiare a false
		if(roundNumber == 6) {
			//var di fine partita in match = true
			return null;
		} else
			return new RoundSetupState();
	}

}
