package it.polimi.ingsw.ps29.model.game.resources;

import java.util.HashMap;

public class Container {
	
	private HashMap <String, Resource> resources;
	
	public Container () {
		resources = new HashMap <String, Resource> ();
		resources.put("coin", new Resource("coin",0));
	}
	
	public void updateResource (Resource res) {
		if(resources.containsKey(res.getType())) 
			resources.get(res.getType()).modifyAmount(res.getAmount());
		else 
			resources.put(res.getType(), res);
		
	}
	
	
	public HashMap <String, Resource> getResources () {
		return resources;
	}
	
	public Resource getResource(String resource){
		return resources.get(resource);
	}

}
	
	
