package it.polimi.ingsw.ps29.model.action;

import it.polimi.ingsw.ps29.model.game.Colour;

public class TowerSpace {
	
	private SlotSpace [] floor;
	
	private boolean isEmpty () {
		for (SlotSpace s: floor) {
			if(!s.isEmpty()) return false;
		}
		return true;
	}
	
	private boolean familiarHere (Colour c) {
		for (SlotSpace s: floor) {
			if(s.familiarHere(c)) return true;
		}
		return false;
	}

}
