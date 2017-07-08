package it.polimi.ingsw.ps29.model.space;

import java.util.ArrayList;

import it.polimi.ingsw.ps29.messages.exception.NotEnoughPowerfulException;
import it.polimi.ingsw.ps29.model.game.PlayerColor;
import it.polimi.ingsw.ps29.model.game.familymember.FamilyMemberInterface;

/**
 * Where familiar is placed to change players' order or get privileges.
 * @author Pietro Melzi
 * @author Giovanni Mele
 * @author Pietro Grotti
 *
 */
public class CouncilPalaceArea implements ActionSpace {
	
	private QueueActionSpace queue;
	
	public CouncilPalaceArea (int power){
		queue = new QueueActionSpace(power, false);
	}

	@Override
	public boolean isEmpty() {
		return queue == null;
	}

	@Override
	public boolean familiarHere(PlayerColor playerColor) {
		return queue.familiarHere(playerColor);
	}

	@Override
	public boolean isEnoughPowerful(int valuePlacement) throws NotEnoughPowerfulException {
		if (queue.isEnoughPowerful(valuePlacement)) 
			return true;
		throw new NotEnoughPowerfulException();
	}

	public QueueActionSpace getQueue() {
		return queue;
	}
	
	public boolean containedIn(ArrayList<PlayerColor> order, FamilyMemberInterface fam) {
		for (PlayerColor col: order) {
			if(col==fam.getPlayerColor())
				return true;
		}
		return false;
	}
	
	public ArrayList<PlayerColor> playersOrder () {
		ArrayList<PlayerColor> order = new ArrayList<PlayerColor> ();
		for (int i=0; i<queue.getQueue().size(); i++)
			if(!containedIn(order, queue.getQueue().get(i)))
				order.add(queue.getQueue().get(i).getPlayerColor());
		return order;
	}

	@Override
	public void cleanSpace() {
		queue.cleanSpace();
		
	}

	@Override
	public void placeFamiliar(FamilyMemberInterface familiar, boolean ludovicoAriosto) {
		queue.addMember(familiar);
		
	}


	
	
}
