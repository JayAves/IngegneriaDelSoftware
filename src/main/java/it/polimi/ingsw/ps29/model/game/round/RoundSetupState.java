package it.polimi.ingsw.ps29.model.game.round;

public class RoundSetupState implements RoundState {

	@Override
	public RoundState doAction(int roundNumber) {
		//metti le carte sulle torri
		//lancia i dadi
		return new ActionsState ();
		
	}

}
