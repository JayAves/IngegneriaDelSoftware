package it.polimi.ingsw.ps29.model.game.finalScoring;

import it.polimi.ingsw.ps29.model.game.PersonalBoard;

public class CharacterFinalPointsOff implements EndGameVictoryPointsGatherer{

	@Override
	public void getVictoryPoints(PersonalBoard board) {
		System.out.println("You don't get victoryPoints for Characters you own");
	}

}
