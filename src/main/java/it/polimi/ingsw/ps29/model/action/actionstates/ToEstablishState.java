package it.polimi.ingsw.ps29.model.action.actionstates;

import it.polimi.ingsw.ps29.messages.ActionChoice;
import it.polimi.ingsw.ps29.messages.InteractionMessage;
import it.polimi.ingsw.ps29.model.game.Match;

public class ToEstablishState implements ActionState {
	private final StateOfActionIdentifier state = StateOfActionIdentifier.TO_ESTABLISH;

	@Override
	public ActionState beforeAction() {
		// TODO Auto-generated method stub
		return this;
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
