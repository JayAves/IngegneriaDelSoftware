package it.polimi.ingsw.ps29.model.cards.effects;

import it.polimi.ingsw.ps29.model.game.Player;

/**
 * EXCOMMUNICATION EFFECT: adds a penaltyGatherer in player finalScoring HashMap
 * @author Giovanni Mele
 *
 */

public class AddGatherer extends Effect{
	
	String gathererType;
	int interval;
	
	public AddGatherer ( String gathererType, int interval){
		this.gathererType = gathererType;
		this.interval = interval;
	}

	@Override
	public void performEffect(Player player) {
		// TODO Auto-generated method stub
		player.addGatherer(gathererType, interval);
	}

}
