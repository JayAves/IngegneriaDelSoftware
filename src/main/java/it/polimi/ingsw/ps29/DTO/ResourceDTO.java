package it.polimi.ingsw.ps29.DTO;

import java.io.Serializable;

public class ResourceDTO implements Serializable {
	String type;
	int amount;
	
	public ResourceDTO(String type, int amount) {
		this.type = type.toLowerCase();
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
