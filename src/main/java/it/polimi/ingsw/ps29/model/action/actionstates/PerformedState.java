package it.polimi.ingsw.ps29.model.action.actionstates;

import it.polimi.ingsw.ps29.messages.ActionChoice;
import it.polimi.ingsw.ps29.messages.InteractionMessage;
import it.polimi.ingsw.ps29.model.game.Match;

/**
 * When Action and Bonus Action are finished. 
 * @author Pietro Melzi
 * @author Giovanni Mele
 * @author Pietro Grotti
 *
 */
public class PerformedState implements ActionState {
	private final StateOfActionIdentifier state = StateOfActionIdentifier.PERFORMED;

	@Override
	public ActionState beforeAction() {
		// before interaction with View
		//action is performed, next state is "ToEstablish"
		return new ToEstablishState();
	}

	@Override
	public ActionState afterAction(Match model) {
		model.getBoard().changePlayerOrder();
		
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
