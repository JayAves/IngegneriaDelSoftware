package it.polimi.ingsw.ps29.model.game.finalscoring;

import java.util.HashMap;

import it.polimi.ingsw.ps29.model.game.PersonalBoard;
import it.polimi.ingsw.ps29.model.game.resources.Resource;

/**adds victory points according to the number of character cards
 * 
 * @author Giovanni
 *
 */
public class CharacterCardVictoryPointsGatherer implements VictoryPointsGatherer{
	
	private HashMap < Integer, Resource> points;

@Override
public void getVictoryPoints(PersonalBoard board) {
	
	if (board.getCards("character").size() > 0){
		points = new HashMap<Integer, Resource>();
		points.put(1, new Resource("victory", 1));
		points.put(2, new Resource("victory", 3));
		points.put(3, new Resource("victory", 6));
		points.put(4, new Resource("victory", 10));
		points.put(5, new Resource("victory", 15));
		points.put(5, new Resource("victory", 21));
		
		if(points.get(board.getCards("character").size()) != null)
	
		board.getResources().updateResource(points.get(board.getCards("character").size()));
	}
	
}

}
