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
	
	@Override
	public String toString(){
		String msg = "you will be penalized by ";
		switch(gathererType){
		case("victory") :msg +=  + interval +" victory point every five victory points";
				break;
		case("military") :msg += + interval +" victory point for each  military point";
		    break;
		case("cost") :msg +=  + interval +" victory point wood or stone in yor building cards costs";
	    	break;
		case("resourcepenalty") :msg +=  + interval +" victory point for each resource";
    	    break;
	}
		return msg;

	}
	
	}
