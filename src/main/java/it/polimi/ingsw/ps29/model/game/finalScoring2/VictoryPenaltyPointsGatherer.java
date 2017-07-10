package it.polimi.ingsw.ps29.model.game.finalScoring2;

import it.polimi.ingsw.ps29.model.game.PersonalBoard;
import it.polimi.ingsw.ps29.model.game.resources.Resource;

public class VictoryPenaltyPointsGatherer implements VictoryPointsGatherer{
	
	int penalty = 0;
	int interval = 1;
	
	public void setPenalty(int penalty , int interval){
		this.penalty = penalty;
	}

	@Override
	public void getVictoryPoints(PersonalBoard board) {
		
		Resource victoryAmount = new Resource("victory", board.getResources().getResource("victory").getAmount()/interval);
		victoryAmount.negativeAmount();
		board.getResources().updateResource(victoryAmount);
			
	}

}
