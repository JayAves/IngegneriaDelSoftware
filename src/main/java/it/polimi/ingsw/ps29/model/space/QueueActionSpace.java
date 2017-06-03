package it.polimi.ingsw.ps29.model.space;

import java.util.ArrayList;

import it.polimi.ingsw.ps29.model.game.Color;
import it.polimi.ingsw.ps29.model.game.DiceColor;
import it.polimi.ingsw.ps29.model.game.familymember.FamilyMember;

public class QueueActionSpace {
	
	private ArrayList<FamilyMember> queue;
	final int powerRequired;
	
	public QueueActionSpace (int power) {
		queue = new ArrayList<FamilyMember>();
		powerRequired = power;
	}
	
	public boolean familiarHere (Color playerColor) {
		for (FamilyMember member: queue) 
			if (member.getPlayerColor() == playerColor && member.getFamiliarColor()!=DiceColor.NEUTRAL) 
				return true;
		return false;
	}
	
	public boolean isEnoughPowerful (int valuePlacement) {
		return valuePlacement >= powerRequired;
	}
	
	public void addMember (FamilyMember member) {
		queue.add(member);
	}
	
	public ArrayList <FamilyMember> getQueue () {
		return queue;
	}
	
	public void cleanSpace () {
		queue = new ArrayList<FamilyMember>();
	}

}
