package it.polimi.ingsw.ps29.model.game.familymember;

import java.util.HashMap;

import it.polimi.ingsw.ps29.model.game.PlayerColor;
import it.polimi.ingsw.ps29.model.game.DiceColor;

/**
 * FamilyMembers of any color.
 * @author Giovanni Mele
 *
 */
public class Family {
	
	private HashMap <DiceColor, FamilyMemberInterface> familyMembers;
	private FakeFamilyMemberInterface fakeFamilyMember;
	
	public Family(PlayerColor playerColor){
		
		familyMembers.put(DiceColor.NEUTRAL, new FamilyMember(DiceColor.NEUTRAL, playerColor));
		familyMembers.put(DiceColor.BLACK, new FamilyMember(DiceColor.BLACK, playerColor));
		familyMembers.put(DiceColor.WHITE, new FamilyMember(DiceColor.WHITE, playerColor));
		familyMembers.put(DiceColor.ORANGE, new FamilyMember(DiceColor.ORANGE, playerColor));
	}

}
