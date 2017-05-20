package it.polimi.ingsw.ps29.model.action;

import org.omg.PortableServer.Servant;

import it.polimi.ingsw.ps29.model.game.FamilyMember;

public interface Action {
	
	abstract void standardPlacement (FamilyMember familyMember, Servant servants);
	
	abstract boolean isPlaceable (FamilyMember familyMember, Servant servants);
	
	abstract void performAction ();

}
