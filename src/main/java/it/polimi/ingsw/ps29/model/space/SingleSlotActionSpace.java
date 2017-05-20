package it.polimi.ingsw.ps29.model.space;

import it.polimi.ingsw.ps29.model.game.Color;
import it.polimi.ingsw.ps29.model.game.DiceColor;
import it.polimi.ingsw.ps29.model.game.familymember.FamilyMember;

public class SingleSlotActionSpace {
	
	FamilyMember member;
	int powerRequired;
	State state;
	
	public SingleSlotActionSpace (int power){
		state = new Free();
		powerRequired = power;
	}
	
	public void setFamilyMember(FamilyMember dude){
		member = dude;
		state = new Occupied();
	}
	
	public int getPowerRequired(){
		return powerRequired;
	}
	
	public void returnStatus(){
		state.returnStatus();
	}
	
	public boolean isEmpty () {
		return member == null;
	}
	
	public boolean familiarHere (Color playerColor) {
		return member!= null && member.getPlayerColor()==playerColor && member.getFamiliarColor()!=DiceColor.NEUTRAL;
	}
	
	public boolean isEnoughPowerful (int valuePlacement) {
		return valuePlacement >= powerRequired;
	}
}