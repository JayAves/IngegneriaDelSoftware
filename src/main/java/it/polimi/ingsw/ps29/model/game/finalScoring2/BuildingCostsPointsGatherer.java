package it.polimi.ingsw.ps29.model.game.finalScoring2;

import java.util.ArrayList;

import it.polimi.ingsw.ps29.model.cards.Card;
import it.polimi.ingsw.ps29.model.game.PersonalBoard;
import it.polimi.ingsw.ps29.model.game.resources.Resource;

public class BuildingCostsPointsGatherer implements VictoryPointsGatherer{
	
	int penalty = 0;
	int interval = 1;
	
	public void setPenalty(int penalty , int interval){
		this.penalty = penalty;
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
