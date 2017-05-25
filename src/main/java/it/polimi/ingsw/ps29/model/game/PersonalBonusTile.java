package it.polimi.ingsw.ps29.model.game;

import java.util.ArrayList;

import it.polimi.ingsw.ps29.model.cards.effects.ResourcesArray;

public class PersonalBonusTile {
	
	private final ArrayList<ResourcesArray> productionBonus;
	private final ArrayList<ResourcesArray> harvestBonus;
	
	
	public PersonalBonusTile(ArrayList<ResourcesArray> productionBonus,ArrayList<ResourcesArray> harvestBonus) {
		
		this.productionBonus = productionBonus;
		this.harvestBonus = harvestBonus;
	}


	public ArrayList<ResourcesArray> getProductionBonus() {
		return productionBonus;
	}


	public ArrayList<ResourcesArray> getHarvestBonus() {
		// TODO Auto-generated method stub
		return harvestBonus;
	}
	
	
	
	

}
