package it.polimi.ingsw.ps29.model.DTO;

import it.polimi.ingsw.ps29.model.game.resources.ResourceType;

public class ResourceDTO {
	String type;
	int amount;
	
	public ResourceDTO(String type, int amount) {
		this.type = type;
		this.amount = amount;
	}
	
	@Override
	public String toString () {
		return "Resource: "+type+" - amount: "+amount;
	}
	
	public String getType () {
		return type;
	}
	
	public int getAmount () {
		return amount;
	}
	
}
