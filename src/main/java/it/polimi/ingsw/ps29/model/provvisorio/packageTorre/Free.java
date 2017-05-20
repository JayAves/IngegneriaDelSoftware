package it.polimi.ingsw.ps29.model.provvisorio.packageTorre;

import it.polimi.ingsw.ps29.model.cards.Card;
import it.polimi.ingsw.ps29.model.game.familymember.FamilyMember;
import it.polimi.ingsw.ps29.model.game.resources.Resources;

public class Free implements TowerState{

	@Override
	public Card placeFamilyMember(FamilyMember dude, Resources stuff, Floor floor) {
		return floor.isFamilyMemberPlaceable(dude);
	}

}
