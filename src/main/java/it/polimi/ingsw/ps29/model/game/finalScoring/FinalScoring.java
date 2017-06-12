package it.polimi.ingsw.ps29.model.game.finalScoring;

import it.polimi.ingsw.ps29.model.game.PersonalBoard;
import it.polimi.ingsw.ps29.model.game.resources.Resource;

public class FinalScoring {
	
	PersonalBoard board;

	private EndGameVictoryPointsGatherer characterCardsPenalty;
	private EndGameVictoryPointsGatherer territoryCardsPenalty;
	private EndGameVictoryPointsGatherer resourcePenalty;
	private EndGameVictoryPointsGatherer victoryPointsPenalty;
	private EndGameVictoryPointsGatherer militaryPointsPenalty;
	private EndGameVictoryPointsGatherer buildingCostsPenalty;
	
	public FinalScoring(){
		characterCardsPenalty = new CharacterFinalPoints();
		territoryCardsPenalty = new TerritoryFinalPoints();
	}
	
	public void setCharacterCardsPenalty(){
		characterCardsPenalty = new CharacterFinalPointsOff();
	}
	
	public void setTerritoryCardsPenalty(){
		territoryCardsPenalty = new CharacterFinalPointsOff();
	}
	
    public void calculateFinalScore (){
    	if (victoryPointsPenalty != null)
    		victoryPointsPenalty.getVictoryPoints(board);
    	if (territoryCardsPenalty != null)
    		territoryCardsPenalty.getVictoryPoints(board);
    	if (characterCardsPenalty != null)
    		characterCardsPenalty.getVictoryPoints(board);
    	if (resourcePenalty != null)
    		resourcePenalty.getVictoryPoints(board);
    	if (militaryPointsPenalty != null)
    		militaryPointsPenalty.getVictoryPoints(board);
    	if (victoryPointsPenalty != null)
    		buildingCostsPenalty.getVictoryPoints(board);
    	
    	int finalResources = board.getSpecificResource("wood").getAmount() +
			                 board.getSpecificResource("coin").getAmount() +
			                 board.getSpecificResource("stone").getAmount() +
			                 board.getSpecificResource("servant").getAmount();
    	Resource finalVictoryPoints = new Resource("victory", finalResources/5);
    	board.getResources().updateResource(finalVictoryPoints);
  
    }
		
    public void getPersonalBoard(PersonalBoard board){
    	this.board = board;
    }
}


