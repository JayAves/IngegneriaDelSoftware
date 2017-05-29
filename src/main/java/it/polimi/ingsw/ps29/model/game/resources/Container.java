package it.polimi.ingsw.ps29.model.game.resources;

import java.util.HashMap;

public class Container {
	
	private HashMap <String, ResourceInterface> resources;
	
	public Container () {
		resources = new HashMap <String, ResourceInterface> ();
		resources.put("coin", new Resource("coin",0));
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
}
	
	
