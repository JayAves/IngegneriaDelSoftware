package it.polimi.ingsw.ps29.model.provvisorio.packageTorre;

import it.polimi.ingsw.ps29.model.cards.Card;
import it.polimi.ingsw.ps29.model.game.familymember.FamilyMember;
import it.polimi.ingsw.ps29.model.game.resources.Resources;
import it.polimi.ingsw.ps29.model.space.tower.Floor;

public class CharacterTower {
	
	Floor firstFloor;
	Floor secondFloor;
	Floor thirdFloor;
	Floor fourthFloor;
	TowerState state;
	
	/*public Floor getFloor(){
		return
	}*/
	
	public Card placeFamilyMember(FamilyMember dude, Resources stuff, Floor floor){
		return state.placeFamilyMember(dude, stuff, floor);
	}

}
