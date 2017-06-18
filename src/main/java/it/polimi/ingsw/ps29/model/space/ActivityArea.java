package it.polimi.ingsw.ps29.model.space;

import it.polimi.ingsw.ps29.model.game.Color;
import it.polimi.ingsw.ps29.model.game.familymember.FamilyMember;

public class ActivityArea implements ActionSpace {
	
	private final SingleSlotActionSpace head;
	private final QueueActionSpace queue;
	
	public ActivityArea(SingleSlotActionSpace head, QueueActionSpace queue) {
		super();
		this.head = head;
		this.queue = queue;
	}
	
	public boolean familiarHere (Color playerColor) {
		return head.familiarHere(playerColor) || queue.familiarHere(playerColor);
	}
	
	public boolean isEmpty () {
		return head == null;
	}
	
	public boolean isEnoughPowerful (int valuePlacement) {
		if (isEmpty()) return head.isEnoughPowerful(valuePlacement);
		else return queue.isEnoughPowerful(valuePlacement-3);
	}
	
	public void headPlacement(FamilyMember member) {
		head.setFamilyMember(member);
	}
	
	public void queuePlacement (FamilyMember member) {
		queue.addMember(member);
	}

	@Override
	public void cleanSpace() {
		head.cleanSpace();
		queue.cleanSpace();
		
	}
	

}
