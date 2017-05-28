package it.polimi.ingsw.ps29.model.cards.effects;

import java.util.ArrayList;

import it.polimi.ingsw.ps29.model.game.resources.Resource;

public abstract class ResourcesArray extends Effect{

	protected ArrayList<Resource> resources;
	
	public ResourcesArray(ArrayList<Resource> resources) {
		this.resources = resources;
	}
	
	public void addResource (Resource res) {
		resources.add(res);
	}
	
	public ArrayList<Resource> getResources () {
		return resources;
	}
	
}
