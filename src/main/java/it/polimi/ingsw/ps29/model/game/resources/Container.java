package it.polimi.ingsw.ps29.model.game.resources;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * More compact and faster way to handle player resources' modifications.  Is assigned to every Player in his PersonalBoard.
 * @author Pietro Melzi
 * @author Giovanni Mele
 *
 */
public class Container {
	
	private HashMap <String, ResourceInterface> resources;
	
	public Container () {
		
		resources = new HashMap <String, ResourceInterface> ();
		
		resources.put("wood", new Resource("wood",2));
		resources.put("servant", new Resource("servant",3));
		resources.put("stone", new Resource("stone",2));
		resources.put("victory", new Resource("victory",0));
	}
	
	public void updateResource (ResourceInterface res) {
		if(resources.containsKey(res.getType())) 
			resources.get(res.getType()).modifyAmount(res.getAmount());
		else 
			resources.put(res.getType().toLowerCase(), new Resource(res.getType(), res.getAmount()));
		
	}
	
	public void removeResource (ResourceType type) {
		if(resources.get(type.getType().toLowerCase())!=null)
			resources.replace(type.getType().toLowerCase(), new Resource (type.getType().toLowerCase(), 0));
		else
			resources.put(type.getType().toLowerCase(), new Resource (type.getType().toLowerCase(), 0));
	}
	
	//for all resources
	public HashMap <String, ResourceInterface> getResources () {
		return resources;
	}
	
	//for a specific resource
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
	
	//transforms Hashmap into arrayList
	public ArrayList <ResourceInterface> hashMapToArrayListResources () {
		ArrayList <ResourceInterface> listRes = new ArrayList<ResourceInterface> ();
		for(HashMap.Entry<String,ResourceInterface> entry: resources.entrySet())
			listRes.add(entry.getValue());
		return listRes;	
	}
	
	//checks if there are enough resources to pay costs
	public boolean isPossibleToPay (ArrayList<Resource> cost) {
		for(Resource res: cost)
			if(resources.get(res.getType())==null || resources.get(res.getType()).getAmount()<res.getAmount())
				return false;
		return true;
		
	}
}
	
	
