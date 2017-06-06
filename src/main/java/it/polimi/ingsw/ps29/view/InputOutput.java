package it.polimi.ingsw.ps29.view;

import it.polimi.ingsw.ps29.model.cards.effects.ExchangeResourcesEffect;
import it.polimi.ingsw.ps29.model.game.GameBoard;
import it.polimi.ingsw.ps29.view.usermessages.UserExchange;

public interface InputOutput {
	
	abstract void showUpdatedSituation (GameBoard board);
	
	abstract void showMessage (String message);
	
	abstract int[] askTypeOfAction ();
	
	abstract int askNumberOfServants ();
	
	abstract int askFamiliarColor ();

	abstract UserExchange askExchange(ExchangeResourcesEffect effect);
	
	abstract int askFloor ();

}
