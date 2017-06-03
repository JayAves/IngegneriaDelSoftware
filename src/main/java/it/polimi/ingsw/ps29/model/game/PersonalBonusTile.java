package it.polimi.ingsw.ps29.model.game;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

import com.google.gson.GsonBuilder;

import it.polimi.ingsw.ps29.model.cards.customadapters.ResourceAdapter;
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
	
	public static void main(String[] args ) throws FileNotFoundException{
	
		BufferedReader tiles = new BufferedReader(new FileReader("src/main/java/personalbonustile.json"));
    GsonBuilder gtiles = new GsonBuilder();
    
    gtiles.registerTypeAdapter(Resource.class, new ResourceAdapter());
    PersonalBonusTile[] tilez = gtiles.create().fromJson(tiles, PersonalBonusTile[].class);
	
    for (int i=0; i< tilez.length;i++) {
    	
    	for(Resource res: tilez[i].getProductionBonus()) {
    		
    		System.out.println(res.toString());
    	}
    	
    	}
	}

}
