package it.polimi.ingsw.ps29.model.cards.effects;

import it.polimi.ingsw.ps29.model.game.Player;

/** ESCOMMUNICATION EFFECT removes a VictoryPointsGetherer from a player finalScoring HashMap
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

}
