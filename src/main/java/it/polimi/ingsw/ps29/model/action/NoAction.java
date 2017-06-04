package it.polimi.ingsw.ps29.model.action;

import it.polimi.ingsw.ps29.model.game.Match;
import it.polimi.ingsw.ps29.model.game.Move;

public class NoAction extends Action{

	public NoAction(Match model, Move move) {
		super(model, move);
		// TODO Auto-generated constructor stub
	}

	@Override
	boolean isForbidden() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	boolean isPlaceable() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	void performAction() {
		// TODO Auto-generated method stub
		
	}

}
