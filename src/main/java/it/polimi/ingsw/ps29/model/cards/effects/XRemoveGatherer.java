package it.polimi.ingsw.ps29.model.cards.effects;

import it.polimi.ingsw.ps29.model.game.Player;

public class XRemoveGatherer extends Effect{
	
	String gathererType;

	@Override
	public void performEffect(Player player) {
		// TODO Auto-generated method stub
		player.removeGatherer(gathererType);
	}

}
