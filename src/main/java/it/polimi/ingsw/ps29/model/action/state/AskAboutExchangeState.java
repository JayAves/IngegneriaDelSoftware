package it.polimi.ingsw.ps29.model.action.state;

import it.polimi.ingsw.ps29.model.game.Match;

public class AskAboutExchangeState implements ActionState {
	private int indexProduction;
	//indice che memorizza fino a quale effetto ho chiesto se l'utente vuole scambiare le risorse
	
	public AskAboutExchangeState(int indexProduction) {
		this.indexProduction = indexProduction;
	}

	@Override
	public ActionState beforeAction() {
		// TODO Auto-generated method stub
		return this;
	}

	

	@Override
	public ActionState afterAction(Match model) {
		// aggiorna view
		return this;
	}

	@Override
	public String getState() {
		// TODO Auto-generated method stub
		return "ask echnage";
	}

	public int getIndexProduction() {
		return indexProduction;
	}

	public void setIndexProduction(int indexProduction) {
		this.indexProduction = indexProduction;
	}
	
	public void next () {
		this.indexProduction++;
	}

	

}
