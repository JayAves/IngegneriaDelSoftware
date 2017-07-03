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
		// metodo chiamato appena prima di interazione con la view
		//devo richiedere una nuova azione da processare, imposto lo stato a to estabilish
		return new ToEstablishState();
	}

	@Override
	public ActionState afterAction(Match model) {
		// TODO Auto-generated method stub
		return this;
	}

	@Override
	public String getState() {
		// TODO Auto-generated method stub
		return state.toString().toLowerCase();
	}

	@Override
	public InteractionMessage objectForView(String player) {
		return new ActionChoice(player);
	}

}
