package it.polimi.ingsw.ps29.model.space;

import java.util.ArrayList;

import it.polimi.ingsw.ps29.model.game.PlayerColor;
import it.polimi.ingsw.ps29.model.game.DiceColor;
import it.polimi.ingsw.ps29.model.game.familymember.FamilyMemberInterface;

/**
 * In all areas where more than one FamilyMember can be placed, represents the tail after first slot.
 * @author Pietro Melzi
 * @author Giovanni Mele
 * @author Pietro Grotti
 *
 */
public class QueueActionSpace {
	
	private ArrayList<FamilyMemberInterface> queue;
	final int powerRequired;
	private boolean closed;
	
	public QueueActionSpace (int power, boolean close) {
		queue = new ArrayList<FamilyMemberInterface>();
		powerRequired = power;
		this.closed= close;
	}
	
	public boolean familiarHere (PlayerColor playerColor) {
	
		for (FamilyMemberInterface member: queue) 
			if (member.getPlayerColor() == playerColor && member.getFamiliarColor()!=DiceColor.NEUTRAL) 
				return true;
		return false;
	}
	
	public boolean isEnoughPowerful (int valuePlacement) {
		return valuePlacement >= powerRequired;
	}
	
	public void addMember (FamilyMemberInterface member) {
		queue.add(member);
	}
	
	public ArrayList <FamilyMemberInterface> getQueue () {
		return queue;
	}
	
	public void cleanSpace () {
		queue = new ArrayList<FamilyMemberInterface>();
	}
	
	public boolean getClosed() {
		return closed;
	}
}
