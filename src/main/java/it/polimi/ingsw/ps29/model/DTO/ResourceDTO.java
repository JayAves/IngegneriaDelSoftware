package it.polimi.ingsw.ps29.model.DTO;

import it.polimi.ingsw.ps29.model.game.resources.ResourceType;

public class ResourceDTO {
	private ResourceType type;
	private int amount;
	
	public ResourceDTO(ResourceType type, int amount) {
		this.type = type;
		this.amount = amount;
	}
	
	@Override
	public String toString () {
		return "Resource: "+type+" - amount: "+amount;
	}
	
	public ResourceType getType () {
		return type;
	}
	
	public int getAmount () {
		return amount;
	}
	
}
