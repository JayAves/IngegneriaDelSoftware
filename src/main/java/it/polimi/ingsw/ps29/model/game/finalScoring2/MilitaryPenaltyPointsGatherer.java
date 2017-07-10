package it.polimi.ingsw.ps29.model.game.finalScoring2;

import it.polimi.ingsw.ps29.model.game.PersonalBoard;
import it.polimi.ingsw.ps29.model.game.resources.Resource;

public class MilitaryPenaltyPointsGatherer implements VictoryPointsGatherer{
	
	int penalty = 0;
	int interval = 1;
	
	public void setPenalty(int penalty , int interval){
		this.penalty = penalty;
	}

	@Override
	public void getVictoryPoints(PersonalBoard board) {
		
		Resource militaryAmount = new Resource("military", board.getResources().getResource("military").getAmount()/interval);
		militaryAmount.negativeAmount();
		board.getResources().updateResource(militaryAmount);
			
	}


}
