package it.polimi.ingsw.ps29.model.game.finalscoring;

import java.util.ArrayList;

import it.polimi.ingsw.ps29.model.cards.Card;
import it.polimi.ingsw.ps29.model.game.PersonalBoard;
import it.polimi.ingsw.ps29.model.game.resources.Resource;

/**removes victory points according to the sum of stones and woods that appear in
 * player's building card costs 
 * 
 * @author Giovanni Mele
 *
 */

public class BuildingCostsPenaltyPointsGatherer extends PenaltyGatherer{

	int interval = 1;
	
	@Override
	public void setPenalty(int interval){
		this.interval = interval;	
	}

	@Override
	public void getVictoryPoints(PersonalBoard board) {
		
		int totalAmount = 0;
		ArrayList<Resource> cost = new ArrayList<Resource>();
		ArrayList<Card> buildingCards = board.getCards("building");
		for (Card card : buildingCards){
			for (Resource res : card.getCost()){
				if (res.getType().equals("stone") || res.getType().equals("wood"))
					cost.add(res);
			}	
		}
		
		for (Resource res : cost){
			totalAmount += res.getAmount();
		}
	
		
		Resource victoryAmount = new Resource("victory",totalAmount/interval);
		victoryAmount.negativeAmount();
		board.getResources().updateResource(victoryAmount);
	

}
	}
