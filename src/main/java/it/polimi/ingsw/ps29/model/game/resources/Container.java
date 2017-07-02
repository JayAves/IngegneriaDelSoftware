package it.polimi.ingsw.ps29.model.game.resources;

import java.util.ArrayList;
import java.util.HashMap;

public class Container {
	
	private HashMap <String, ResourceInterface> resources;
	
	public Container () {
		
		resources = new HashMap <String, ResourceInterface> ();
		
		resources.put("wood", new Resource("wood",2));
		resources.put("servant", new Resource("servant",3));
		resources.put("stone", new Resource("stone",2));
	}
	
	public void updateResource (ResourceInterface res) {
		if(resources.containsKey(res.getType())) 
			resources.get(res.getType()).modifyAmount(res.getAmount());
		else 
			resources.put(res.getType().toLowerCase(), res);
		
	}
	
	public ResourceInterface removeResource (ResourceType type) {
		if(resources.get(type.getType().toLowerCase())!=null)
			return resources.remove(type.getType().toLowerCase());
		return new Resource (type.getType().toLowerCase(), 0);
	}
	
	
	public HashMap <String, ResourceInterface> getResources () {
		return resources;
	}
	
	public ResourceInterface getResource(String resource){
		return resources.get(resource)!= null ? resources.get(resource) : new Resource(resource, 0);
	}

	
	public void substituteResource (ResourceInterface res) {
		resources.remove(res.getType());
		resources.put(res.getType(), res);
	}
	
	public void swipeResource(ResourceInterface toSwipe, ResourceInterface swipeFor){
		updateResource(toSwipe);
		updateResource(swipeFor);
	}
	
	@Override
	public String toString(){
		String msg = "Player resources: ";
		for(HashMap.Entry<String,ResourceInterface> entry: resources.entrySet())
			msg+="\n"+entry.getValue().toString();
		return msg;
	}
	
	public ArrayList <ResourceInterface> hashMapToArrayListResources () {
		ArrayList <ResourceInterface> listRes = new ArrayList<ResourceInterface> ();
		for(HashMap.Entry<String,ResourceInterface> entry: resources.entrySet())
			listRes.add(entry.getValue());
		return listRes;	
	}
	
	public boolean isPossibleToPay (ArrayList<Resource> cost) {
		for(Resource res: cost)
			if(resources.get(res.getType())==null || resources.get(res.getType()).getAmount()<res.getAmount())
				return false;
		return true;
		
	}
}
	
	
