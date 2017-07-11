package it.polimi.ingsw.ps29.model.game.finalscoring;

import java.util.HashMap;

import it.polimi.ingsw.ps29.model.game.PersonalBoard;
import it.polimi.ingsw.ps29.model.game.resources.Resource;

public class TerritoryCardVictoryPointsGatherer implements VictoryPointsGatherer{
	
	private HashMap < Integer, Resource> points;

	@Override
	public void getVictoryPoints(PersonalBoard board) {
		
		if (board.getCards("territory").size() > 0){
			points = new HashMap<Integer, Resource>();
			points.put(3, new Resource("victory", 1));
			points.put(4, new Resource("victory", 4));
			points.put(5, new Resource("victory", 10));
			points.put(6, new Resource("victory", 20));
			
			if(points.get(board.getCards("territory").size()) != null) 
		
				board.getResources().updateResource(points.get(board.getCards("territory").size()));
		}
		
	}

}
