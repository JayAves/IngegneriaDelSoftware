package it.polimi.ingsw.ps29.model.provvisorio.packageAlternativoRisorse;

import it.polimi.ingsw.ps29.model.game.resources.ResourceType;

public class Resource {
	private int amount;
	private ResourceType type;
	
	
	public Resource(ResourceType type) {
		amount=0;
		this.type = type;
	}



}

