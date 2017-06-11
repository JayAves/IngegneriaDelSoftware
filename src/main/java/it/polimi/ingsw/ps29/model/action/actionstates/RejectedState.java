package it.polimi.ingsw.ps29.model.action.actionstates;

import it.polimi.ingsw.ps29.model.game.Match;
import it.polimi.ingsw.ps29.view.messages.ActionChoice;
import it.polimi.ingsw.ps29.view.messages.InteractionMessage;

public class RejectedState implements ActionState {
	private final StateOfActionIdentifier state = StateOfActionIdentifier.REJECTED;

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
		return state.toString();
	}

	@Override
	public InteractionMessage objectForView(String player) {
		return new ActionChoice(player);
	}

}
