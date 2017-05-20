package it.polimi.ingsw.ps29.model.action;

import it.polimi.ingsw.ps29.model.game.Colour;

public interface ActionSpace {
	
	abstract boolean isEmpty ();
	
	abstract boolean familiarHere (Colour c);
	
	
}


