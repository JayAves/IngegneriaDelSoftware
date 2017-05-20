package it.polimi.ingsw.ps29.model.action;

import java.util.ArrayList;

import it.polimi.ingsw.ps29.model.game.Colour;
import it.polimi.ingsw.ps29.model.game.FamilyMember;

public abstract class ArrivalOrderSpace implements ActionSpace {

	private ArrayList<FamilyMember> arrivalQueue = null; 


	@Override
	public boolean isEmpty() {
		return arrivalQueue == null;
	}

	@Override
	public boolean familiarHere(Colour c) {
		for (FamilyMember f: arrivalQueue) {
			if (f.getPlayerColor() == c) 
				return true;
		}
		return false;
	}

}
