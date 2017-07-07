package it.polimi.ingsw.ps29.model.space;

import java.util.ArrayList;

import it.polimi.ingsw.ps29.model.game.resources.Resource;

/**
 * Used to give resource bonuses to players who sustain Church when asked. 
 * Each players changes his position in the Faith track when acquires new Faith Points.
 * @author Giovanni Mele
 *
 */
public class FaithSpace {
	
	ArrayList<Resource> bonus;
	
	public FaithSpace (ArrayList<Resource> res){ 
		bonus = res;
	}
	
	public ArrayList<Resource> getBonus(){
		return bonus;
	}

}
