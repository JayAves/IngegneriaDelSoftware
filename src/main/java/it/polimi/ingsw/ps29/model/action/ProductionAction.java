package it.polimi.ingsw.ps29.model.action;

import it.polimi.ingsw.ps29.model.game.Move;
import it.polimi.ingsw.ps29.model.space.ActivityArea;

public class ProductionAction implements Action {
	
	private Move move; 
	private ActivityArea space;
	
	public ProductionAction (Move move) {
		this.move = move;
		space = (ActivityArea) move.getSpace();
	}
	
	@Override
	public boolean isForbidden() { //da verificare carte scomunica in arrayList di bonusAndMalusPlyer
		
		return false;
	}

	@Override
	public boolean isPlaceable() {
		return !space.familiarHere(move.getFamiliar().getPlayerColor()) && space.isEnoughPowerful(move.getFamiliar().getProductionPower());
	}

	@Override
	public void performAction() {
		//placement
		if (space.isEmpty()) space.headPlacement (move.getFamiliar());
		else space.queuePlacement(move.getFamiliar());
		//manca production()
		
	}
	
	


}
