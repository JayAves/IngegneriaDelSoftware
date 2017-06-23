package it.polimi.ingsw.ps29.model.action.actionstates;

import it.polimi.ingsw.ps29.model.game.Match;
import it.polimi.ingsw.ps29.view.messages.ActionChoice;
import it.polimi.ingsw.ps29.view.messages.InteractionMessage;

public class PerformedState implements ActionState {
	private final StateOfActionIdentifier state = StateOfActionIdentifier.PERFORMED;

	@Override
	public ActionState beforeAction() {
		// metodo chiamato prima di interazione con la view
		//ho terminato l'azione: ne richiedo una da processare, imposto lo stato a to estabilish
		return new ToEstablishState();
	}

	@Override
	public ActionState afterAction(Match model) {
		model.getBoard().changePlayerOrder();
		//model.getBoard().setPlayersOrderMoved(true);
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
