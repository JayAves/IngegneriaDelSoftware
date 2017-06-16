package it.polimi.ingsw.ps29.model.action;

import it.polimi.ingsw.ps29.model.action.actionstates.ActionState;
import it.polimi.ingsw.ps29.model.action.actionstates.PerformedState;
import it.polimi.ingsw.ps29.model.action.actionstates.PrivilegesState;
import it.polimi.ingsw.ps29.model.action.actionstates.RejectedState;
import it.polimi.ingsw.ps29.model.action.actionstates.ToEstabilishState;
import it.polimi.ingsw.ps29.model.game.Match;
import it.polimi.ingsw.ps29.model.game.Move;
import it.polimi.ingsw.ps29.model.game.resources.ResourceInterface;

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
		
	public ActionState actionHandler () {
		if (isForbidden() || !isPlaceable()) {
			state = new RejectedState();
		}
		
		
		else {
			performAction();
		
			if(!state.getState().equals("bonus action") && !state.getState().equals("ask exchange")
					&& !state.getState().equals("privileges"))
				state = new PerformedState();
		}
		ResourceInterface privileges = model.getBoard().getCurrentPlayer().getPersonalBoard().getResources().getResource("privilege");
		if(privileges!=null)
			state = new PrivilegesState(state, privileges.getAmount());
		else
			state = state.afterAction(model);
		
		return state;
		
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
	
	

}
