package it.polimi.ingsw.ps29.model.action;

import it.polimi.ingsw.ps29.model.game.Move;

interface Action {
	
	//questo metodo controlla se la mossa è impedita da una scomunica
	abstract boolean isForbidden (Move move); 
	
	//questo metodo controlla se è possibile piazzare il famigliare (posizione libera, famigliare dello stesso colore non presente, 
	//valore dell'azione modificata con effetti e servitori maggiore o uguale al valore richiesto
	abstract boolean isPlaceable(Move move);
	
	//chiamato se i precedenti controlli vanno a buon fine, implementato in maniera diversa per ogni spazio azione
	abstract void performAction (Move move);
		
	

}
