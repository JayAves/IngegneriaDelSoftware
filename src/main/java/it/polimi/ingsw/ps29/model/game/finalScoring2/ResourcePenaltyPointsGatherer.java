package it.polimi.ingsw.ps29.model.game.finalScoring2;

import it.polimi.ingsw.ps29.model.game.PersonalBoard;
import it.polimi.ingsw.ps29.model.game.resources.Resource;

public class ResourcePenaltyPointsGatherer implements VictoryPointsGatherer {
	
	int penalty = 0;
	int interval = 1;
	
	public void setPenalty(int penalty , int interval){
		this.penalty = penalty;
		this.interval = interval;
	}

	@Override
	public void getVictoryPoints(PersonalBoard board) {

		int totalAmount = board.getSpecificResource("wood").getAmount() +
			      		  board.getSpecificResource("coin").getAmount() +
			              board.getSpecificResource("stone").getAmount() +
			              board.getSpecificResource("servant").getAmount();
	
	Resource victoryPenalty = new Resource("victory", totalAmount/interval);
	victoryPenalty.negativeAmount();
	board.getResources().updateResource(victoryPenalty);
	}

}
