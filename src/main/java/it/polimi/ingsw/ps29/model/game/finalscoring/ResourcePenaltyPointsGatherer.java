package it.polimi.ingsw.ps29.model.game.finalscoring;

import it.polimi.ingsw.ps29.model.game.PersonalBoard;
import it.polimi.ingsw.ps29.model.game.resources.Resource;

public class ResourcePenaltyPointsGatherer extends PenaltyGatherer {
	
	int interval = 1;
	
	@Override
	public void setPenalty( int interval){
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
