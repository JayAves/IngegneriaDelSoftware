package it.polimi.ingsw.ps29.model.action;

import it.polimi.ingsw.ps29.model.action.actionstates.ActionState;
import it.polimi.ingsw.ps29.model.action.actionstates.PerformedState;
import it.polimi.ingsw.ps29.model.action.actionstates.RejectedState;
import it.polimi.ingsw.ps29.model.action.actionstates.ToEstabilishState;
import it.polimi.ingsw.ps29.model.game.Match;
import it.polimi.ingsw.ps29.model.game.Move;

public abstract class Action {
	
	protected Match model;
	protected Move move;
	protected ActionState state;
	
	public Action (Match model, Move move) {
		this.model = model;
		this.move = move;
		state = new ToEstabilishState();
	}
	
	//questo metodo controlla se la mossa è impedita da una scomunica
	abstract boolean isForbidden (); 
	
	//questo metodo controlla se è possibile piazzare il famigliare (posizione libera, famigliare dello stesso colore non presente, 
	//valore dell'azione modificata con effetti e servitori maggiore o uguale al valore richiesto
	abstract boolean isPlaceable();
	
	//chiamato se i precedenti controlli vanno a buon fine, implementato in maniera diversa per ogni spazio azione
	abstract void performAction ();
		
	public void actionHandler () {
		if (isForbidden() || !isPlaceable()) {
			state = new RejectedState();
		}
		
		
		else {
			performAction();
		
			if(!state.getState().equals("bonus action") && !state.getState().equals("ask exchange"))
				state = new PerformedState();
			System.out.println("Il giocatore "+ move.getPlayer().getName()+" ha eseguito correttamente l'azione su "+ move.getSpace()+ ",piazzando il familiare"+move.getFamiliar().toString()+" e spendendo "+move.getServants()+" servitori");
		}
		System.out.println(move.getPlayer().getPersonalBoard().getResources().getResource("coin"));
		state = state.afterAction(model);
		
		/* 
		se lo stato è REJECTED chiedo una nuova Move
		se lo stato è INCOMPLETE (posso dettagliarlo) aggiorno la view e: 
			se AzioneBonus passo i parametri "fissi" della Move e ne chiedo di altri
				gestione normale di una Move
			se ExchangeResources chiedo soltanto se effettuare lo scambio (se possibile)
				gestione straordinaria da pensare (discorso thread)
		se lo stato è PERFORMED aggiorno la view e cambio il turno
			
		*/
	}
	
	public ActionState getState () {
		return state;
	}

}
