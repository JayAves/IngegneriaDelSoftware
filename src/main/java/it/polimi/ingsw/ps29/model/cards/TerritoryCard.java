package it.polimi.ingsw.ps29.model.cards;

import java.util.ArrayList;

import it.polimi.ingsw.ps29.model.cards.effects.Effect;
import it.polimi.ingsw.ps29.model.game.Period;
import it.polimi.ingsw.ps29.model.game.resources.Resource;

public class TerritoryCard extends Card {

	private final int harvestForce;
	
	public TerritoryCard(String name, Period period, String type, ArrayList<Effect> immediate,
			ArrayList<Effect> permanent, ArrayList<Resource> cost, int harvestForce) {
		
		super(name, period, type, immediate, permanent, cost);
		
		this.harvestForce = harvestForce;
	}



	public int getHarvestForce() {
		return harvestForce;
	}


}
