package it.polimi.ingsw.ps29.model.cards.effects;

import it.polimi.ingsw.ps29.model.game.Player;

/**
 * EXCOMMUNICATION: at the end of the game, Resources do not give any VictoryPoint
 * @author Giovanni Mele
 *
 */
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
