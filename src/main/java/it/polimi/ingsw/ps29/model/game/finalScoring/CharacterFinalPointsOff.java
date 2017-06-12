package it.polimi.ingsw.ps29.model.game.finalScoring;

import it.polimi.ingsw.ps29.model.game.PersonalBoard;

public class CharacterFinalPointsOff implements EndGameVictoryPointsGatherer{

	@Override
	public void getVictoryPoints(PersonalBoard board) {
		System.out.println("Non prendi punti per le carte personaggio");
	}

}
