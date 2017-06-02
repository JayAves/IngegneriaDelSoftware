package it.polimi.ingsw.ps29.model.action.state;

import it.polimi.ingsw.ps29.model.game.Match;

public class RejectedState implements ActionState {

	@Override
	public ActionState beforeAction() {
		// metodo chiamato appena prima di interazione con la view
		//devo richiedere una nuova azione da processare, imposto lo stato a to estabilish
		return new ToEstabilishState();
	}

	@Override
	public ActionState afterAction(Match model) {
		// TODO Auto-generated method stub
		return this;
	}

	@Override
	public String getState() {
		// TODO Auto-generated method stub
		return "rejected";
	}

}
