package it.polimi.ingsw.ps29.model.action;

import it.polimi.ingsw.ps29.messages.exception.FamiliarBusyException;
import it.polimi.ingsw.ps29.messages.exception.RejectException;
import it.polimi.ingsw.ps29.messages.exception.ServantsException;
import it.polimi.ingsw.ps29.model.action.actionstates.ActionState;
import it.polimi.ingsw.ps29.model.action.actionstates.PerformedState;
import it.polimi.ingsw.ps29.model.action.actionstates.PrivilegesState;
import it.polimi.ingsw.ps29.model.action.actionstates.RejectedState;
import it.polimi.ingsw.ps29.model.action.actionstates.ToEstablishState;
import it.polimi.ingsw.ps29.model.game.Match;
import it.polimi.ingsw.ps29.model.game.Move;
import it.polimi.ingsw.ps29.model.game.resources.ResourceInterface;
import it.polimi.ingsw.ps29.model.game.resources.Servants;

/**
 * Changes Model by triggering certain kind of effects.
 * @author Pietro Grotti
 * @author Pietro Melzi
 * @author Giovanni Mele
 *
 */
public abstract class Action {
	
	protected Match model;
	protected Move move;
	protected ActionState state;
	
	public Action (Match model, Move move) {
		this.model = model;
		this.move = move;
		state = new ToEstablishState();
	}
	
	//checks if it is possible to place the chosen familiar in the chosen space
	abstract boolean isPlaceable() throws RejectException;
	
	//called only if previous checks are true, is different for any ActionSpace
	abstract void performAction ();
		
	
	/**
	 * Does necessary checks and if possible performs Action. In the end:
	 * if state is REJECTED, a new Move is asked.
	 * if state is INCOMPLETE, View is updated and if BonusAction or ExchangeResource they are triggered.
	 * if state is PERFORMED , View is updated and next player for a new turn is picked.
	 * if some privilege are to be transformed, state is PRIVILEGES
	 * @return final state
	 * @throws RejectException
	 * @see it.polimi.ingsw.ps29.model.action.actionstates.StateOfActionIdentifier
	 */
	public ActionState actionHandler () throws RejectException {
		
		if(model.getCurrentPlayer().getPersonalBoard().getSpecificResource("servant").getAmount() <
				move.getServants())
			throw new ServantsException();
		
		if(move.getFamiliar().getBusy()) {
			throw new FamiliarBusyException();
		}
		
		if ( !isPlaceable()) {
			state = new RejectedState();
		}
		
		
		else {
			
			performAction();
			//pay servants used for Action
			model.getCurrentPlayer().getPersonalBoard().getResources().updateResource(
					new Servants(- move.getServants()));
		
			if(!state.getState().equals("bonus_action") && !state.getState().equals("ask_exchange")
					&& !state.getState().equals("privileges"))
				state = new PerformedState();
		}
		
		ResourceInterface privileges = model.getCurrentPlayer().getPersonalBoard().getResources().getResource("privilege");
		if(privileges.getAmount()!=0 )
			state = new PrivilegesState(state, privileges.getAmount(), true);
		else
			state = state.afterAction(model);
		
		return state;

	}
	
	

}
