package it.polimi.ingsw.ps29.model.provvisorio.packageSpazio;

import it.polimi.ingsw.ps29.model.game.familymember.FamilyMember;

public class StdActionSpace {
	
	FamilyMember member;
	int powerRequired;
	State state;
	
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
	
}
