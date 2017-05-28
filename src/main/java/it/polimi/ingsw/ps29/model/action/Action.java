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
		actionHandler();
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
		
		else {
			performAction();
		
			if(model.getBoard().getStateOfAction() != StateOfActionIdentifier.INCOMPLETE)
				model.getBoard().setStateOfAction(StateOfActionIdentifier.PERFORMED);
		}
		
		/* 
		se lo stato è REJECTED chiedo una nuova Move
		se lo stato è INCOMPLETE (posso dettagliarlo) aggiorno lo stato e: 
			se AzioneBonus passo i parametri "fissi" della Move e ne chiedo di altri
				gestione normale di una Move
			se ExchangeResources chiedo soltanto se effettuare lo scambio (se possibile)
				gestione straordinaria da pensare (discorso thread)
		se lo stato è PERFORMED aggiorno lo stato e cambio il turno
			
		*/
	}

}
