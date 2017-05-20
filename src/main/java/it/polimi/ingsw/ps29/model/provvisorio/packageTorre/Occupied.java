package it.polimi.ingsw.ps29.model.provvisorio.packageTorre;

import it.polimi.ingsw.ps29.model.cards.Card;
import it.polimi.ingsw.ps29.model.game.familymember.FamilyMember;
import it.polimi.ingsw.ps29.model.game.resources.Resources;


public class Occupied implements TowerState{
	
	int additionalCost;

	@Override
	public Card placeFamilyMember(FamilyMember dude, Resources stuff, Floor floor) {
		stuff.setCoins(additionalCost);
		return floor.isFamilyMemberPlaceable(dude);
		//lancia eccezione che deve fare stuff.setcoins( -additionalCost)
	}

}
