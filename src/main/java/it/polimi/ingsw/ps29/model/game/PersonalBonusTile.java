package it.polimi.ingsw.ps29.model.game;

import java.util.ArrayList;

import it.polimi.ingsw.ps29.model.cards.ResourcesOrPointsEffect;

public class PersonalBonusTile {
	
	private final ArrayList<ResourcesOrPointsEffect> productionBonus;
	private final ArrayList<ResourcesOrPointsEffect> harvestBonus;
	
	
	public PersonalBonusTile(ArrayList<ResourcesOrPointsEffect> productionBonus,ArrayList<ResourcesOrPointsEffect> harvestBonus) {
		
		this.productionBonus = productionBonus;
		this.harvestBonus = harvestBonus;
	}


	public ArrayList<ResourcesOrPointsEffect> getProductionBonus() {
		return productionBonus;
	}


	public ArrayList<ResourcesOrPointsEffect> getHarvestBonus() {
		// TODO Auto-generated method stub
		return harvestBonus;
	}
	
	
	
	

}
