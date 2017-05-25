package it.polimi.ingsw.ps29.model.cards.effects;

import java.util.ArrayList;

import it.polimi.ingsw.ps29.model.game.resources.Resource;


public abstract class ResourcesArray extends Effect {

	protected ArrayList<Resource> resourceGain;
	
	public ResourcesArray() {
		resourceGain = new ArrayList<Resource> ();
	}
	
	void addResource (Resource res) {
		resourceGain.add(res);
	}
	
}
