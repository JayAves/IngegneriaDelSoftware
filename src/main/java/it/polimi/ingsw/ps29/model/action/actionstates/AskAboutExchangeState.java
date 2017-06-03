package it.polimi.ingsw.ps29.model.action.actionstates;

import java.util.ArrayList;

import it.polimi.ingsw.ps29.model.cards.Card;
import it.polimi.ingsw.ps29.model.game.Match;

public class AskAboutExchangeState implements ActionState {
	private int indexProduction;
	private ArrayList<Card> cards;
	private int valueFamiliar;
	
	//indice che memorizza fino a quale effetto ho chiesto se l'utente vuole scambiare le risorse
	
	public AskAboutExchangeState(int indexProduction, ArrayList<Card> cards, int valueFamiliar) {
		this.indexProduction = indexProduction;
		this.cards = cards;
		this.valueFamiliar = valueFamiliar;
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

	public ArrayList<Card> getCards() {
		return cards;
	}

	public int getValueFamiliar() {
		return valueFamiliar;
	}


	

}
