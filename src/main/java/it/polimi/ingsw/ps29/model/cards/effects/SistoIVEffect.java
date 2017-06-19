package it.polimi.ingsw.ps29.model.cards.effects;

import it.polimi.ingsw.ps29.model.game.Player;

public class SistoIVEffect extends Effect{

	@Override
	public void performEffect(Player player) {
		
		player.setSistoIV();
		
	}
	
	

}
