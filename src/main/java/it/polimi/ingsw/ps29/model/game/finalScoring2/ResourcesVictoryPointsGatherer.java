package it.polimi.ingsw.ps29.model.game.finalScoring2;

import it.polimi.ingsw.ps29.model.game.PersonalBoard;
import it.polimi.ingsw.ps29.model.game.resources.Resource;

public class ResourcesVictoryPointsGatherer implements VictoryPointsGatherer{
	
	private final int INTERVAL = 5;

	@Override
	public void getVictoryPoints(PersonalBoard board) {
		// TODO Auto-generated method stub
		int finalResources = board.getSpecificResource("wood").getAmount() +
                             board.getSpecificResource("coin").getAmount() +
                             board.getSpecificResource("stone").getAmount() +
                             board.getSpecificResource("servant").getAmount();
		System.out.println(finalResources);
		System.out.println(finalResources/INTERVAL);
		Resource finalVictoryPoints = new Resource("victory", finalResources/INTERVAL);
		board.getResources().updateResource(finalVictoryPoints);
	}
	
	

}
