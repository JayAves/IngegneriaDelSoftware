package it.polimi.ingsw.ps29.model.space;

import it.polimi.ingsw.ps29.model.game.Color;
import it.polimi.ingsw.ps29.model.game.familymember.FamilyMember;
import it.polimi.ingsw.ps29.model.game.familymember.FamilyMemberInterface;

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

	@Override
	public void cleanSpace() {
		head.cleanSpace();
		queue.cleanSpace();
		
	}

	@Override
	public void placeFamiliar(FamilyMemberInterface familiar) {
		if(head.isEmpty())
			head.setFamilyMember(familiar);
		else
			queue.addMember(familiar);
		
	}
	

}
