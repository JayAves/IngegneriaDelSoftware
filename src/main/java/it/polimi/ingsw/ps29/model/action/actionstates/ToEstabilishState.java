package it.polimi.ingsw.ps29.model.action.actionstates;

import it.polimi.ingsw.ps29.model.game.Match;

public class ToEstabilishState implements ActionState {
	private final StateOfActionIdentifier state = StateOfActionIdentifier.TO_ESTABILISH;

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
		return state.toString();
	}

}
