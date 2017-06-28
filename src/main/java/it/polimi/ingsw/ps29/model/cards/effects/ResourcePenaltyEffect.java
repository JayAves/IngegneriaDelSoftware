package it.polimi.ingsw.ps29.model.cards.effects;

import it.polimi.ingsw.ps29.model.game.Player;

public class ResourcePenaltyEffect extends Effect{

	@Override
	public void performEffect(Player player) {
		player.getFinalScoring().setResourcePenalty();
				
	}

	@Override
	public String toString() {
		return "ResourcePenaltyExcommunication";	
	}
	
}
