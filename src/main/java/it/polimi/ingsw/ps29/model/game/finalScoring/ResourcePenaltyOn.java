package it.polimi.ingsw.ps29.model.game.finalScoring;

import it.polimi.ingsw.ps29.model.game.PersonalBoard;
import it.polimi.ingsw.ps29.model.game.resources.Resource;

public class ResourcePenaltyOn implements EndGameVictoryPointsGatherer{

	@Override
	public void getVictoryPoints(PersonalBoard board) {
		int penalty = board.getSpecificResource("wood").getAmount() +
				      board.getSpecificResource("coin").getAmount() +
				      board.getSpecificResource("stone").getAmount() +
				      board.getSpecificResource("servant").getAmount();
		
		Resource victoryPenalty = new Resource("victory", penalty);
		victoryPenalty.negativeAmount();
		board.getResources().updateResource(victoryPenalty);
	}
	
	

}
