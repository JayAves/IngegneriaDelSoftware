package it.polimi.ingsw.ps29.model.space;

import it.polimi.ingsw.ps29.model.game.DiceColor;
import it.polimi.ingsw.ps29.model.game.PlayerColor;
import it.polimi.ingsw.ps29.model.game.familymember.FamilyMemberInterface;

/**
 * Base ActionSpace. Only for one FamilyMember, no bonuses.
 * @author Pietro Melzi
 * @author Giovanni Mele
 * @author Pietro Grotti
 *
 */
public class SingleSlotActionSpace {
	
	FamilyMemberInterface member;
	int powerRequired;
	
	
	public SingleSlotActionSpace (int power){
		
		powerRequired = power;
	}
	
	public void setFamilyMember(FamilyMemberInterface dude){
		member = dude;
		
	}
	
	public int getPowerRequired(){
		return powerRequired;
	}
	
	
	
	public boolean isEmpty () {
		return member==null;
	}
	
	public boolean familiarHere (PlayerColor playerColor) {
		return member!= null && member.getPlayerColor()==playerColor && member.getFamiliarColor()!=DiceColor.NEUTRAL;
	}
	
	public boolean isEnoughPowerful (int valuePlacement) {
		return valuePlacement >= powerRequired;
	}
	
	public void cleanSpace () {
		member = null;
		
	}
	
	public FamilyMemberInterface getFamiliar () {
		return member;
	}
}
