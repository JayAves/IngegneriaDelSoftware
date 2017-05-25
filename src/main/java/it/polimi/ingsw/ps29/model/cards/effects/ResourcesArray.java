package it.polimi.ingsw.ps29.model.cards.effects;

import java.util.ArrayList;

import it.polimi.ingsw.ps29.model.game.Player;
import it.polimi.ingsw.ps29.model.game.resources.Container;
import it.polimi.ingsw.ps29.model.game.resources.Resource;


abstract class ResourcesArray extends Effect {

	protected ArrayList<Resource> resourceGain;
	
	public ResourcesArray() {
		resourceGain = new ArrayList<Resource> ();
	}
	
	void addResource (Resource res) {
		resourceGain.add(res);
	}
	
}
