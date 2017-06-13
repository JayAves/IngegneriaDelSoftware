package it.polimi.ingsw.ps29.model.space;

import it.polimi.ingsw.ps29.model.game.resources.Resource;

public class FaithSpace {
	
	Resource bonus;
	
	public FaithSpace (Resource res){ //da fare da file
		bonus = res;
	}
	
	public Resource getBonus(){
		return bonus;
	}

}
