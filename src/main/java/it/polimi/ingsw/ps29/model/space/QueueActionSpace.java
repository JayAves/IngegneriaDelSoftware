package it.polimi.ingsw.ps29.model.space;

import java.util.ArrayList;

import it.polimi.ingsw.ps29.model.game.Color;
import it.polimi.ingsw.ps29.model.game.DiceColor;
import it.polimi.ingsw.ps29.model.game.familymember.FamilyMember;

public class QueueActionSpace {
	
	private ArrayList<FamilyMember> queue;
	final int powerRequired = 4;
	
	public boolean familiarHere (Color playerColor) {
		for (FamilyMember member: queue) 
			if (member.getPlayerColor() == playerColor && member.getFamiliarColor()!=DiceColor.NEUTRAL) 
				return true;
		return false;
	}

}
