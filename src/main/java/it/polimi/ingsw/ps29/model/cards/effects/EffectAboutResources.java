package it.polimi.ingsw.ps29.model.cards.effects;

import java.util.ArrayList;

import it.polimi.ingsw.ps29.model.game.resources.Resource;

public abstract class EffectAboutResources extends Effect{

	protected ArrayList<Resource> resources;
	
	public EffectAboutResources(ArrayList<Resource> resources) {
		this.resources = resources;
	}
	
	public void addResource (Resource res) {
		resources.add(res);
	}
	
	public ArrayList<Resource> getResources () {
		return resources;
	}
	
	@Override
	public String toString () {
		String msg=super.toString()+"resources:\n";
		for(Resource res: resources)
			msg+=res.toString()+"\n";
		return msg;
	}
	
}
