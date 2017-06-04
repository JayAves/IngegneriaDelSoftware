package it.polimi.ingsw.ps29.model.cards;

import java.util.ArrayList;

import it.polimi.ingsw.ps29.model.cards.effects.Effect;
import it.polimi.ingsw.ps29.model.game.Period;
import it.polimi.ingsw.ps29.model.game.resources.Resource;

public class BuildingCard extends Card{
	
	private final int productionForce;
	
	public BuildingCard(String name, Period period, String type, ArrayList<Effect> immediate,
			ArrayList<Effect> permanent, ArrayList<Resource> cost, int productionForce) {
		
		super(name, period, type, immediate, permanent, cost);
		
		this.productionForce = productionForce;
	}

	public int getProductionForce() {
		// TODO Auto-generated method stub
		
		return productionForce;
	}
	
	@Override
	public String toString () {
		return super.toString()+"\nforce: "+productionForce;
	}

}
