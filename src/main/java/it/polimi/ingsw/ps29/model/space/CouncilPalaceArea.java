package it.polimi.ingsw.ps29.model.space;

import it.polimi.ingsw.ps29.model.game.Color;

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

}
