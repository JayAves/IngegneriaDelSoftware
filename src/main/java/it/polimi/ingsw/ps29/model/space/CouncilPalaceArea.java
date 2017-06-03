package it.polimi.ingsw.ps29.model.space;

import java.util.ArrayList;

import it.polimi.ingsw.ps29.model.game.Color;
import it.polimi.ingsw.ps29.model.game.familymember.FamilyMember;

public class CouncilPalaceArea implements ActionSpace {
	
	private QueueActionSpace queue;
	
	public CouncilPalaceArea (int power){
		queue = new QueueActionSpace(power);
	}

	@Override
	public boolean isEmpty() {
		return queue == null;
	}

	@Override
	public boolean familiarHere(Color playerColor) {
		return queue.familiarHere(playerColor);
	}

	@Override
	public boolean isEnoughPowerful(int valuePlacement) {
		return queue.isEnoughPowerful(valuePlacement);
	}

	public QueueActionSpace getQueue() {
		return queue;
	}
	
	public boolean containedIn(ArrayList<Color> order, FamilyMember fam) {
		for (Color col: order) {
			if(col==fam.getPlayerColor())
				return true;
		}
		return false;
	}
	
	public ArrayList<Color> playersOrder () {
		ArrayList<Color> order = new ArrayList<Color> ();
		for (int i=0; i<queue.getQueue().size(); i++)
			if(!containedIn(order, queue.getQueue().get(i)))
				order.add(queue.getQueue().get(i).getPlayerColor());
		return order;
	}

	@Override
	public void cleanSpace() {
		queue.cleanSpace();
		
	}


	
	
}
