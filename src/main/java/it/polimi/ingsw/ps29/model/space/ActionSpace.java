package it.polimi.ingsw.ps29.model.space;

import it.polimi.ingsw.ps29.model.game.Color;
import it.polimi.ingsw.ps29.model.game.familymember.FamilyMember;

public interface ActionSpace {
	
	abstract boolean isEmpty();
	
	abstract boolean familiarHere (Color playerColor);
	
	abstract boolean isEnoughPowerful (int valuePlacement);

}
