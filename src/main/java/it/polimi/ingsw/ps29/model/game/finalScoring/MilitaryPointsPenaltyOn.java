package it.polimi.ingsw.ps29.model.game.finalScoring;

import it.polimi.ingsw.ps29.model.game.PersonalBoard;
import it.polimi.ingsw.ps29.model.game.resources.Resource;

public class MilitaryPointsPenaltyOn implements EndGameVictoryPointsGatherer{

	@Override
	public void getVictoryPoints(PersonalBoard board) {

		Resource tot = new Resource("military", board.getResources().getResource("military").getAmount()/5);
		tot.negativeAmount();
		board.getResources().updateResource(tot);
	}

}
