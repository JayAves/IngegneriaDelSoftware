package it.polimi.ingsw.ps29.model.provvisorio.packageAlternativoRisorse;

import it.polimi.ingsw.ps29.model.game.resources.ResourceType;

public class Resource {
	private int amount;
	private ResourceType type;
	private int modifier;
	
	
	public Resource(ResourceType type) {
		amount=0;
		this.type = type;
		modifier = 0;
	}
	
	public Resource (int amount, ResourceType type) {
		this.amount = amount;
		this.type = type;
		modifier = 0; 
	}



}

