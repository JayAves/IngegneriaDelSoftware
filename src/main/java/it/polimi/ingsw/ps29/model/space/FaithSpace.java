package it.polimi.ingsw.ps29.model.space;

import java.util.ArrayList;

import it.polimi.ingsw.ps29.model.game.resources.Resource;

public class FaithSpace {
	
	ArrayList<Resource> bonus;
	
	public FaithSpace (ArrayList<Resource> res){ //da fare da file
		bonus = res;
	}
	
	public ArrayList<Resource> getBonus(){
		return bonus;
	}

}
