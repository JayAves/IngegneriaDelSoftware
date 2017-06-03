package it.polimi.ingsw.ps29.model.action.actionstates;

import it.polimi.ingsw.ps29.model.game.Match;

public class PerformedState implements ActionState {

	@Override
	public ActionState beforeAction() {
		// metodo chiamato prima di interazione con la view
		//ho terminato l'azione: ne richiedo una da processare, imposto lo stato a to estabilish
		return new ToEstabilishState();
	}

	@Override
	public ActionState afterAction(Match model) {
		// metodo per aggiornare la view
		model.getBoard().changePlayerOrder();
		model.getBoard().setPlayersOrderMoved(true);
		return this;
	}

	@Override
	public String getState() {
		// TODO Auto-generated method stub
		return "performed";
	}

}