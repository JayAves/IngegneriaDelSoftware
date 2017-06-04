package it.polimi.ingsw.ps29.model.game.resources;

import java.util.HashMap;

public class Container {
	
	private HashMap <String, ResourceInterface> resources;
	
	public Container () {
		
		resources = new HashMap <String, ResourceInterface> ();
		
		resources.put("wood", new Resource("wood",2));
		resources.put("servant", new Resource("servant",3));
		resources.put("stone", new Resource("stone",2));
	}
	
	public void updateResource (Resource res) {
		if(resources.containsKey(res.getType())) 
			resources.get(res.getType()).modifyAmount(res.getAmount());
		else 
			resources.put(res.getType(), res);
		
	}
	
	
	public HashMap <String, ResourceInterface> getResources () {
		return resources;
	}
	
	public ResourceInterface getResource(String resource){
		return resources.get(resource);
	}

	
	public void substituteResource (ResourceDecorator res) {
		resources.remove(res.getType());
		resources.put(res.getType(), res);
	}
	
	public void swipeResource(Resource toSwipe, Resource swipeFor){
		updateResource(toSwipe);
		updateResource(swipeFor);
	}
	
	@Override
	public String toString(){
		return "ci sono in totale :" + getResource("coin").toString() +
				"\n "+ getResource("wood").toString() +
				"\n "+ getResource("stone").toString() +
				"\n "+ getResource("servant").toString() +
				"\n "+ getResource("military").toString() +
				"\n "+ getResource("victory").toString() +
				"\n "+ getResource("faith").toString() ;
	}
}
	
	
