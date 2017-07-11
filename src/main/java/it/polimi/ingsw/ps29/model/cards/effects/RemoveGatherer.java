package it.polimi.ingsw.ps29.model.cards.effects;

import it.polimi.ingsw.ps29.model.game.Player;

/** EXCOMMUNICATION EFFECT removes a VictoryPointsGatherer from a player finalScoring HashMap
 * 
 * @author Giovanni Mele
 *
 */

public class RemoveGatherer extends Effect{
	
	String gathererType;
	
	public RemoveGatherer(String gathererType){
		this.gathererType = gathererType;
	}

	@Override
	public void performEffect(Player player) {
		// TODO Auto-generated method stub
		player.removeGatherer(gathererType);
	}
	
	@Override
	public String toString(){
		String msg = "you won't get victory points for your ";
		switch(gathererType){
		case("territory") :msg += " territory cards";
				break;
		case("character") :msg +=" character cards";
		    break;
	}
		return msg;

	}

}
