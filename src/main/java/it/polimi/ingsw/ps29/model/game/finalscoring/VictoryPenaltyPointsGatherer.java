package it.polimi.ingsw.ps29.model.game.finalscoring;

import it.polimi.ingsw.ps29.model.game.PersonalBoard;
import it.polimi.ingsw.ps29.model.game.resources.Resource;

/**removes victory points according to the amount of victory points divided by a coefficient
 * 
 * @author Giovanni Mele
 *
 */

public class VictoryPenaltyPointsGatherer extends PenaltyGatherer{
	
	int interval = 1;
	
	@Override
	public void setPenalty(int interval){
		this.interval = interval;
	}

	@Override
	public void getVictoryPoints(PersonalBoard board) {
		
		Resource victoryAmount = new Resource("victory", board.getResources().getResource("victory").getAmount()/interval);
		victoryAmount.negativeAmount();
		board.getResources().updateResource(victoryAmount);
			
	}

}
