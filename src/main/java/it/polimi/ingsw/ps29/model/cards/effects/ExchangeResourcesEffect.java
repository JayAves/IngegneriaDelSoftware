package it.polimi.ingsw.ps29.model.cards.effects;

import java.util.ArrayList;

import it.polimi.ingsw.ps29.model.game.Player;
import it.polimi.ingsw.ps29.model.game.resources.Container;

public class ExchangeResourcesEffect extends Effect {

	private ArrayList<ExchangeResourceHandler> choices;
	
	public ExchangeResourcesEffect() {
		choices = new ArrayList <ExchangeResourceHandler> ();
	}
	
	//aggiunge le alternative tra cui scegliere
	void AddExchangeResource (ExchangeResourceHandler erh) {
		choices.add(erh);
	}
	
	ArrayList<ExchangeResourceHandler> getChoices () {
		return choices;
	}
	

	@Override
	void performEffect(Player player) {
		//richiesta dello scambio
		
	}

	//serve capire come contatta controller per chiedere dello scambio

}