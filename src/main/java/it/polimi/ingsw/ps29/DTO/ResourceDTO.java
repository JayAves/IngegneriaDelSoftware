package it.polimi.ingsw.ps29.DTO;

import java.io.Serializable;

public class ResourceDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4473321896259503015L;
	String type;
	int amount;
	
	public ResourceDTO(String type, int amount) {
		this.type = type.toLowerCase();
		this.amount = amount;
	}
	
	@Override
	public String toString () {
		return type.toUpperCase()+": "+amount;
	}
	
	public String getType () {
		return type;
	}
	
	public int getAmount () {
		return amount;
	}
	
	public void negativeAmount () {
		this.amount*=-1;
	}
	
}
