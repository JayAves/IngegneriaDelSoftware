package it.polimi.ingsw.ps29.model.cards.effects;

import it.polimi.ingsw.ps29.model.game.Player;

/**
 * EXCOMMUNICATION: At the end of the game owning BuildingCard s will not give Victory Points

 * @author Giovanni Mele

 *
 */
public class BuildingCostsPenaltyEffect extends Effect{

	@Override
	public void performEffect(Player player) {
		player.getFinalScoring().setBuildingCostsPenalty();
		
	}

	@Override
	public String toString() {
		return super.toString()+"BuildingCostsPenaltyEffect";
	}
	
}
