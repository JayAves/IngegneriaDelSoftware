package it.polimi.ingsw.ps29.model.game.finalScoring2;

import it.polimi.ingsw.ps29.model.game.PersonalBoard;
import it.polimi.ingsw.ps29.model.game.resources.Resource;

public class MilitaryPenaltyPointsGatherer extends PenaltyGatherer{
	
	int interval = 1;
	
	public void setPenalty(int interval){
		this.interval = interval;
	}

	@Override
	public void getVictoryPoints(PersonalBoard board) {
		
		Resource militaryAmount = new Resource("military", board.getResources().getResource("military").getAmount()/interval);
		militaryAmount.negativeAmount();
		board.getResources().updateResource(militaryAmount);
			
	}


}
