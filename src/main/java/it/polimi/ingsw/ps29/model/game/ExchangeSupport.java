package it.polimi.ingsw.ps29.model.game;

import java.util.ArrayList;

import it.polimi.ingsw.ps29.model.cards.effects.ExchangeResourceHandler;
import it.polimi.ingsw.ps29.model.cards.effects.ExchangeResourcesEffect;
import it.polimi.ingsw.ps29.model.game.resources.Container;
import it.polimi.ingsw.ps29.view.messages.Exchange;

public class ExchangeSupport {
	private ArrayList<ExchangeResourcesEffect> options;
	private Container initialResources;
	private Container actualResources;
	//initial resources impostato nel costruttore: le risorse ottenute negli effetti di produzione non possono essere riutilizzate negli scambi
	//actual resources: aggiornato ogni volta, controlla se è possibile uno scambio con lo stato attuale delle risorse
	
	public ExchangeSupport(ArrayList<ExchangeResourcesEffect> options, Container resources) {
		this.options = options;
		this.setInitialResources(resources);
		this.setActualResources(resources);
	}
	
	public ArrayList<ExchangeResourcesEffect> getOptions() {
		return options;
	}

	public void setOptions(ArrayList<ExchangeResourcesEffect> options) {
		this.options = options;
	}

	public Container getInitialResources() {
		return initialResources;
	}

	public void setInitialResources(Container initialResources) {
		this.initialResources = initialResources;
	}

	public Container getActualResources() {
		return actualResources;
	}

	public void setActualResources(Container actualResources) {
		this.actualResources = actualResources;
		checkVector();
	}

	public void checkVector () {
		ArrayList <ExchangeResourcesEffect> update = new ArrayList<ExchangeResourcesEffect>();
		for (ExchangeResourcesEffect effect: options){
			ArrayList<ExchangeResourceHandler> temp = new ArrayList<ExchangeResourceHandler>();
			for(ExchangeResourceHandler erh: effect.getChoices())
				if(actualResources.isPossibleToPay(erh.getResOut()))
					temp.add(erh);
			update.add(new ExchangeResourcesEffect(temp));
			}
		options = update;
	}

	
	

}
