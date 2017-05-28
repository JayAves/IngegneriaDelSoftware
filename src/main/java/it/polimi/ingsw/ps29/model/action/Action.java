package it.polimi.ingsw.ps29.model.action;

import it.polimi.ingsw.ps29.model.game.Match;
import it.polimi.ingsw.ps29.model.game.Move;
import it.polimi.ingsw.ps29.model.game.StateOfActionIdentifier;

abstract class Action {
	
	protected Match model;
	protected Move move;
	
	public Action (Match model, Move move) {
		this.model = model;
		this.move = move;
	}
	
	//questo metodo controlla se la mossa è impedita da una scomunica
	abstract boolean isForbidden (); 
	
	//questo metodo controlla se è possibile piazzare il famigliare (posizione libera, famigliare dello stesso colore non presente, 
	//valore dell'azione modificata con effetti e servitori maggiore o uguale al valore richiesto
	abstract boolean isPlaceable();
	
	//chiamato se i precedenti controlli vanno a buon fine, implementato in maniera diversa per ogni spazio azione
	abstract void performAction ();
		
	protected void actionHandler () {
		if (isForbidden() || !isPlaceable())
			model.getBoard().setStateOfAction(StateOfActionIdentifier.REJECTED);
		else
			performAction();
	}

}
