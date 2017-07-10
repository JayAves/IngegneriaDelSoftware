package it.polimi.ingsw.ps29.model.cards.effects;

import it.polimi.ingsw.ps29.model.game.Player;

public class XAddGatherer extends Effect{
	
	String gathererType;
	int interval;
	
	//public AddGatherer( String gathererType, int interval){
		//this.gathererType = g
	//}

	@Override
	public void performEffect(Player player) {
		// TODO Auto-generated method stub
		player.addGatherer(gathererType, interval);
	}

}
