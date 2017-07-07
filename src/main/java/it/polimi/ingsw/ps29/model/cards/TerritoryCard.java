package it.polimi.ingsw.ps29.model.cards;

import java.util.ArrayList;

import it.polimi.ingsw.ps29.model.cards.effects.Effect;
import it.polimi.ingsw.ps29.model.game.Period;
import it.polimi.ingsw.ps29.model.game.resources.Resource;

/**
 * 
 * @author Pietro Melzi
 * @author Pietro Grotti
 * @author Giovanni Mele
 *
 */
public class TerritoryCard extends Card  {

	private final int harvestForce;
	
	public TerritoryCard(String name, Period period, String type, int id, ArrayList<Effect> immediate,
			ArrayList<Effect> permanent, ArrayList<Resource> cost, int harvestForce) {
		
		super(name, period, type, id, immediate, permanent, cost);
		this.harvestForce = harvestForce;
	}



	public int getHarvestForce() {
		return harvestForce;
	}

	@Override
	public String toString () {
		return super.toString()+", force: "+harvestForce;
	}

}
