package it.polimi.ingsw.ps29.model.action.actionstates;

import it.polimi.ingsw.ps29.messages.ActionChoice;
import it.polimi.ingsw.ps29.messages.InteractionMessage;
import it.polimi.ingsw.ps29.model.game.Match;

/**
 * When a user ActionChoice is rejected for some reason.
 * @author Pietro Melzi
 * @see it.polimi.ingsw.ps29.messages.RejectMessage
 *
 */
public class RejectedState implements ActionState {
	private final StateOfActionIdentifier state = StateOfActionIdentifier.REJECTED;

	@Override
	public ActionState beforeAction() {
		// before the interaction with view
		//need to ask a new action 
		return new ToEstablishState();
	}

	@Override
	public ActionState afterAction(Match model) {
		// players' order is not changed
		return this;
	}

	@Override
	public String getState() {

		return state.toString().toLowerCase();
	}

	@Override
	public InteractionMessage objectForView(String player) {
		return new ActionChoice(player);
	}

}
