package it.polimi.ingsw.ps29.model.cards.effects;

import it.polimi.ingsw.ps29.model.game.Player;

public class VentureCardsPenaltyEffect extends Effect{

	@Override
	public void performEffect(Player player) {
		// TODO Auto-generated method stub
		player.setVentureCardPenalty();
	}
	

}
