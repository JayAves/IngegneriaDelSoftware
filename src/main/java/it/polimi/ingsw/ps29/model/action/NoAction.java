package it.polimi.ingsw.ps29.model.action;

import it.polimi.ingsw.ps29.model.game.Match;
import it.polimi.ingsw.ps29.model.game.Move;
import it.polimi.ingsw.ps29.model.space.CouncilPalaceArea;

/**
 * Means player could not place any familiar or player is disconnected.
 * @author Pietro Melzi
 * @author Pietro Grotti
 */
 public class NoAction extends Action{


	public NoAction(Match model, Move move) {
		super(model, move);
		// TODO Auto-generated constructor stub
	}

	

	@Override
	boolean isPlaceable() {
		// TODO Auto-generated method stub
		return !move.getFamiliar().getBusy();
	}

	@Override
	void performAction() {
		((CouncilPalaceArea)model.getBoard().getSpace("NoAction")).placeFamiliar(move.getFamiliar(), false);
		move.getFamiliar().setBusy(true);
		
	}

}
