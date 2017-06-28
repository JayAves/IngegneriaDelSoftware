package it.polimi.ingsw.ps29.model.cards.effects;

import it.polimi.ingsw.ps29.model.game.Player;

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
