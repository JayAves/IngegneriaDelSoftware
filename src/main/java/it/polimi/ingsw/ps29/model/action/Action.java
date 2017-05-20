package it.polimi.ingsw.ps29.model.action;

import it.polimi.ingsw.ps29.view.Move;

abstract class Action {
	
	protected Move move;
	
	public Action(Move move) {
		this.move = move;
	}

	/*abstract void standardPlacement (FamilyMember familyMember, Servant servants);
	
	abstract boolean isPlaceable (FamilyMember familyMember, Servant servants);
	
	abstract void performAction ();*/
	
	abstract boolean isEnoughPowerful();
		
	

}
