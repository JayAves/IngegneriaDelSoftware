package it.polimi.ingsw.ps29.model.provvisorio.packageTorre;

import it.polimi.ingsw.ps29.model.cards.Card;
import it.polimi.ingsw.ps29.model.game.familymember.FamilyMember;
import it.polimi.ingsw.ps29.model.game.resources.Resources;

public class Tower {
	
	Floor firstFloor;
	Floor secondFloor;
	Floor thirdFloor;
	Floor fourthFloor;
	TowerState state;
	
	public Card placeFamilyMember(FamilyMember dude, Resources stuff, Floor floor){
		return state.placeFamilyMember(dude, stuff, floor);
	}

}
