package it.polimi.ingsw.ps29.model.cards;

import it.polimi.ingsw.ps29.model.game.Period;

public class TerritoryCard extends Card {

	private final int harvestForce;
	
	public TerritoryCard(String name, Period period, CardType type, int harvestForce) {
		super(name, period, type);
		// TODO Auto-generated constructor stub
		this.harvestForce=harvestForce;
	}

	public int getHarvestForce() {
		return harvestForce;
	}


}
