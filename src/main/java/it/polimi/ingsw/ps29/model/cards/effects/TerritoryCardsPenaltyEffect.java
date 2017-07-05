package it.polimi.ingsw.ps29.model.cards.effects;

import it.polimi.ingsw.ps29.model.game.Player;

/**
 * EXCOMMUNICATION: At the end of the game owning TerritryCard s will not give Victory Points

 * @author Giovanni Mele

 *
 */
public class TerritoryCardsPenaltyEffect extends Effect{

	@Override
	public void performEffect(Player player) {
		player.getFinalScoring().setTerritoryCardsPenalty();
		
	}

	@Override
	public String toString() {
		return super.toString()+"TerritoryCardsPenaltyEffect ";
	}
	
}
