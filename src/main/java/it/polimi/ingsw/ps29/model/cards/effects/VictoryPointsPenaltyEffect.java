package it.polimi.ingsw.ps29.model.cards.effects;

import it.polimi.ingsw.ps29.model.game.Player;

/**
 * EXCOMMUNICATION: At the end of the game  Victory Points gain will be reduced.

 * @author Giovanni Mele

 *
 */
public class VictoryPointsPenaltyEffect extends Effect{

	@Override
	public void performEffect(Player player) {
		player.getFinalScoring().setVictoryPointsPenalty();
		
	}

	@Override
	public String toString() {
		return super.toString()+"VictoryPointsPenaltyExcommunication";
	}
	
}
