package it.polimi.ingsw.ps29.model.game;

import java.util.ArrayList;
import it.polimi.ingsw.ps29.model.game.resources.Resource;

/**
 * Contains harvest and production default bonuses.
 * @author Pietro Melzi
 * @author Pietro Grotti
 * @author Giovanni Mele
 * @see it.polimi.ingsw.ps29.model.action.HarvestAction
 * @see it.polimi.ingsw.ps29.model.action.ProductionAction
 *
 */
public class PersonalBonusTile {
	private int id;
	private final ArrayList<Resource> productionBonus;
	private final ArrayList<Resource> harvestBonus;
	
	
	public PersonalBonusTile(int id, ArrayList<Resource> productionBonus,ArrayList<Resource> harvestBonus) {
		this.id = id;
		this.productionBonus = productionBonus;
		this.harvestBonus = harvestBonus;
		this.id=id;
	}


	public ArrayList<Resource> getProductionBonus() {
		return productionBonus;
	}


	public ArrayList<Resource> getHarvestBonus() {
		// TODO Auto-generated method stub
		return harvestBonus;

	}


	@Override
	public String toString() {
		return " ProductionBonus=" + productionBonus + ";	 HarvestBonus:" + harvestBonus ;
	}
	
	public int getId () {
		return id;
	}

}
