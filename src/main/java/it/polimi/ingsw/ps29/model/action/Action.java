package it.polimi.ingsw.ps29.model.action;

import it.polimi.ingsw.ps29.model.game.resources.*;
import it.polimi.ingsw.ps29.model.provvisorio.packageAlternativoRisorse.Servants;
import it.polimi.ingsw.ps29.model.game.FamilyMember;

public interface Action {
	
	abstract void standardPlacement (FamilyMember familyMember, int servants);
	
	abstract boolean isPlaceable (FamilyMember familyMember, int servants);
	
	abstract void performAction ();

}
