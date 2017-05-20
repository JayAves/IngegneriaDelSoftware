package it.polimi.ingsw.ps29.model.action;

import it.polimi.ingsw.ps29.model.game.Colour;

public class MarketSpace {
	
	private SlotSpace[] space;
	
	public MarketSpace () {
		for (SlotSpace s: space) {
			s = new SlotSpace ();
		}
	}
	
	private boolean isEmpty (int slot) {
		return space [slot].isEmpty();
	}
	
	private boolean familiarHere (int slot, Colour c) {
		return space [slot].familiarHere(c);
	}
}
