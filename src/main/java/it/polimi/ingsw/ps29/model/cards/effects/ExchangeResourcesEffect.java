package it.polimi.ingsw.ps29.model.cards.effects;

import java.util.ArrayList;

import it.polimi.ingsw.ps29.model.game.Player;

public class ExchangeResourcesEffect extends Effect implements Cloneable {

	private ArrayList<ExchangeResourceHandler> choices;
	
	public ExchangeResourcesEffect(ArrayList <ExchangeResourceHandler> erh) {
		choices = erh;
	}
	
	/*
	//aggiunge le alternative tra cui scegliere
	void AddExchangeResource (ExchangeResourceHandler erh) {
		choices.add(erh);
	}*/
	
	public ArrayList<ExchangeResourceHandler> getChoices () {
		return choices;
	}
	

	@Override
	public void performEffect(Player player) {
		//mostra all'utente tutte le alternative presenti in choices e chiedi se vuole effettuare uno scambio
		//in caso ci sia una scelta da fare all'interno di un possibile scambio chiede anche quella
		
		
	}

	//indexExchange indica il tipo di scambio che si vuole effettuare tra quelli proposti
	//indexResource indica la risorsa da scambiare scelta all'interno di quelle proposte
	//vale -1 se non occorre scegliere tra queste risorse
	public void exchangeResources (Player player, int indexExchange, int indexOut, int indexIn) {
		choices.get(indexExchange).performExchange(player.getPersonalBoard().getResources(), indexOut, indexIn);
	}
	
	@Override
	public ExchangeResourcesEffect clone () {
		ArrayList <ExchangeResourceHandler> array = new ArrayList<ExchangeResourceHandler> ();
		for(ExchangeResourceHandler handler: this.getChoices())
			array.add(handler.clone());
		return new ExchangeResourcesEffect(array);
	}
	
	@Override
	public String toString () {
		String msg = super.toString()+"\n";
		for(ExchangeResourceHandler erh: choices)
			msg+=erh.toString()+"\n";
		return msg;
	}
}
