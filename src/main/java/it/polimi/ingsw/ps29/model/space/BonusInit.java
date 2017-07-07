package it.polimi.ingsw.ps29.model.space;

import java.util.ArrayList;

import it.polimi.ingsw.ps29.model.game.resources.Resource;

/**
 * Used to read resource bonuses from Bonus.json for all {@link BonusActionSpace}
 * @author Giovanni Mele
 */
public class BonusInit {
	
	ArrayList<Resource> bonus;
	
	public ArrayList<Resource> getBonus(){
		return bonus;
	}

}
