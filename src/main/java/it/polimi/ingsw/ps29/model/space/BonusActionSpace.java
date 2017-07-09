package it.polimi.ingsw.ps29.model.space;

import java.util.ArrayList;

import it.polimi.ingsw.ps29.model.game.resources.Resource;

/**
 * When a familiar is placed here, he receives an immediate resource bonus. 
 * @author Pietro Melzi
 * @author Giovanni Mele
 * @author Pietro Grotti
 *
 */
public class BonusActionSpace extends SingleSlotActionSpace {

	private ArrayList <Resource> bonus;
	
	public BonusActionSpace(int power) {
		super(power);
		
	}

	public ArrayList<Resource> getBonus() {
		ArrayList<Resource> copy = new ArrayList<Resource> ();
		for (Resource res: bonus)
			copy.add(new Resource(res.getType(), res.getAmount()));
		return copy;
	}
	
	public void setBonus(ArrayList<Resource> bonus) {
		this.bonus=bonus;
	}

}
