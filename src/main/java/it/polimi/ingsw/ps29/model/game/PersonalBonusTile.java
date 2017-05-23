package it.polimi.ingsw.ps29.model.game;

import java.util.ArrayList;

import it.polimi.ingsw.ps29.model.cards.effects.GainResourcesEffect;

public class PersonalBonusTile {
	
	private final ArrayList<GainResourcesEffect> productionBonus;
	private final ArrayList<GainResourcesEffect> harvestBonus;
	
	
	public PersonalBonusTile(ArrayList<GainResourcesEffect> productionBonus,ArrayList<GainResourcesEffect> harvestBonus) {
		
		this.productionBonus = productionBonus;
		this.harvestBonus = harvestBonus;
	}


	public ArrayList<GainResourcesEffect> getProductionBonus() {
		return productionBonus;
	}


	public ArrayList<GainResourcesEffect> getHarvestBonus() {
		// TODO Auto-generated method stub
		return harvestBonus;
	}
	
	
	
	

}
