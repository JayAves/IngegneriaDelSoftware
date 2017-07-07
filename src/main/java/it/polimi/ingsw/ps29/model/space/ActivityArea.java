package it.polimi.ingsw.ps29.model.space;

import it.polimi.ingsw.ps29.messages.exception.FamiliarHereException;
import it.polimi.ingsw.ps29.messages.exception.NotEnoughPowerfulException;
import it.polimi.ingsw.ps29.model.game.PlayerColor;
import it.polimi.ingsw.ps29.model.game.familymember.FamilyMemberInterface;

/**
 * Where FamilyMember are placed to do a ProductionAction or an HarvestAction.
 * @author Pietro Melzi
 * @author Giovanni Mele
 * @author Pietro Grotti
 *
 */
public class ActivityArea implements ActionSpace {
	
	private final SingleSlotActionSpace head;
	private final QueueActionSpace queue;
	
	public ActivityArea(SingleSlotActionSpace head, QueueActionSpace queue) {
		super();
		this.head = head;
		this.queue = queue;
	}
	
	public boolean familiarHere (PlayerColor playerColor) throws FamiliarHereException{
		if( !head.familiarHere(playerColor) && !queue.familiarHere(playerColor))
			return false;
		throw new FamiliarHereException();
	}
	
	public boolean isEmpty () {
		return head.isEmpty();
	}
	
	public boolean isEnoughPowerful (int valuePlacement) throws NotEnoughPowerfulException {
		if ((isEmpty() && head.isEnoughPowerful(valuePlacement)) || (!isEmpty() && queue.isEnoughPowerful(valuePlacement-3)))
				return true;
		throw new NotEnoughPowerfulException();
	}

	@Override
	public void cleanSpace() {
		head.cleanSpace();
		queue.cleanSpace();
		
	}

	@Override
	public void placeFamiliar(FamilyMemberInterface familiar, boolean ludovicoAriosto) {
		if(!head.isEmpty() && !ludovicoAriosto)
			queue.addMember(familiar);
		else
			head.setFamilyMember(familiar);
		
	}
	
	public boolean getClosed() {
		return queue.getClosed();
	}

	public SingleSlotActionSpace getHead() {
		return head;
	}

	public QueueActionSpace getQueue() {
		return queue;
	}

	
}
