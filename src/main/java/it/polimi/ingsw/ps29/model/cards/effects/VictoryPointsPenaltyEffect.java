package it.polimi.ingsw.ps29.model.cards.effects;

import it.polimi.ingsw.ps29.model.game.Player;

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
