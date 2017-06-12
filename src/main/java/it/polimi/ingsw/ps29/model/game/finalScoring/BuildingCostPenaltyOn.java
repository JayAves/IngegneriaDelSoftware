package it.polimi.ingsw.ps29.model.game.finalScoring;

import java.util.ArrayList;

import it.polimi.ingsw.ps29.model.cards.Card;
import it.polimi.ingsw.ps29.model.game.PersonalBoard;
import it.polimi.ingsw.ps29.model.game.resources.Resource;

public class BuildingCostPenaltyOn implements EndGameVictoryPointsGatherer{

	@Override
	public void getVictoryPoints(PersonalBoard board) {
		int penalty;
		ArrayList<Card> buildingCards = board.getCards("building");
		for (Card card : buildingCards){
			ArrayList<Resource> cost = card.getCost();
			//da finire problema con arrayList
		}
		
		
	}

}
