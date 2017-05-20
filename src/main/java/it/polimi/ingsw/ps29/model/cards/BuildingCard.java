package it.polimi.ingsw.ps29.model.cards;

import it.polimi.ingsw.ps29.model.game.Period;
import it.polimi.ingsw.ps29.model.game.resources.Resource;

public class BuildingCard extends Card{
	
	
	private final Resource woodCost;
	private final Resource stoneCost;
	private final int productionForce;
	
	public BuildingCard(String name, Period period, CardType type,Resource stoneCost, Resource woodCost, int productionForce) {
		super(name, period, type);
		// TODO Auto-generated constructor stub
		this.woodCost=woodCost;
		this.stoneCost=stoneCost;
		this.productionForce=productionForce;
			
	}

}
