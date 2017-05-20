package it.polimi.ingsw.ps29.model.space;

import it.polimi.ingsw.ps29.model.game.Color;

abstract class ActivityArea {
	private final StdActionSpace head;
	private final QueueActionSpace queue;
	
	public ActivityArea(StdActionSpace head, QueueActionSpace queue) {
		super();
		this.head = head;
		this.queue = queue;
	}
	
	public boolean familyHere (Color playerColor) {
		return head.familiarHere(playerColor) || queue.familiarHere(playerColor);
	}
	
	
	

}
