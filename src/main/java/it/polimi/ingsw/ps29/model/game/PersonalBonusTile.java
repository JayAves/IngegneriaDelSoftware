package it.polimi.ingsw.ps29.model.game;

import java.util.ArrayList;

import it.polimi.ingsw.ps29.model.cards.effects.ResourcesArray;
import it.polimi.ingsw.ps29.model.game.resources.Resource;

public class PersonalBonusTile {
	
	private final ArrayList<Resource> productionBonus;
	private final ArrayList<Resource> harvestBonus;
	
	
	public PersonalBonusTile(ArrayList<Resource> productionBonus,ArrayList<Resource> harvestBonus) {
		
		this.productionBonus = productionBonus;
		this.harvestBonus = harvestBonus;
	}


	public ArrayList<Resource> getProductionBonus() {
		return productionBonus;
	}


	public ArrayList<Resource> getHarvestBonus() {
		// TODO Auto-generated method stub
		return harvestBonus;
	}
	
	
	
	

}
