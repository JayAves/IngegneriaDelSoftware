package it.polimi.ingsw.ps29.model.action.actionstates;

import it.polimi.ingsw.ps29.messages.ActionChoice;
import it.polimi.ingsw.ps29.messages.InteractionMessage;
import it.polimi.ingsw.ps29.model.game.Match;

/**
 * State at the beginning of turn.
 * @author Pietro Melzi
 * @author Pietro Grotti
 * @author Giovanni Mele
 *
 */
public class ToEstablishState implements ActionState {
	private final StateOfActionIdentifier state = StateOfActionIdentifier.TO_ESTABLISH;

	@Override
	public ActionState beforeAction() {

		return this;
	}

	@Override
	public ActionState afterAction(Match model) {

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
