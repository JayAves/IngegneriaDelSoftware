package it.polimi.ingsw.ps29.model.cards.effects;

import java.util.ArrayList;

import it.polimi.ingsw.ps29.model.game.resources.Resource;

/**
 * 
 * @author Pietro Melzi
 * @author Giovanni Mele
 * @author Pietro Grotti
 *
 */
public abstract class EffectAboutResources extends Effect{

	protected ArrayList<Resource> resources;
	
	public EffectAboutResources(ArrayList<Resource> resources) {
		this.resources = resources;
	}
	
	public void addResource (Resource res) {
		resources.add(res);
	}
	
	public ArrayList<Resource> getResources () {
		ArrayList<Resource> copy = new ArrayList<Resource> ();
		for(Resource res: resources)
			copy.add(new Resource(res.getType(), res.getAmount()));
		return copy;
	}
	
	
	
}
