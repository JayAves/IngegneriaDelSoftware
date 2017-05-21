package it.polimi.ingsw.ps29.view;

import it.polimi.ingsw.ps29.model.game.GameBoard;

public interface InputOutput {
	
	abstract void showUpdatedSituation (GameBoard board);
	
	abstract void showMessage (String message);
	
	abstract String askTypeOfAction ();
	
	abstract String askNumberOfServants ();
	
	abstract String askFamiliarColor ();

}
