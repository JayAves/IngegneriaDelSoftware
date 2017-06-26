package it.polimi.ingsw.ps29.model.game;

import java.util.ArrayList;
import java.util.Map;

import it.polimi.ingsw.ps29.model.cards.effects.ExchangeResourceHandler;
import it.polimi.ingsw.ps29.model.cards.effects.ExchangeResourcesEffect;
import it.polimi.ingsw.ps29.model.game.resources.Container;
import it.polimi.ingsw.ps29.model.game.resources.ResourceInterface;

public class ExchangeSupport {
	private ArrayList<ExchangeResourcesEffect> options;
	private Container outResourcesUpdate;
	//outUpdatedResources: aggiorno solo le risorse in uscita per controllare se uno scambio è possibile
	
	public ExchangeSupport(ArrayList<ExchangeResourcesEffect> options, Container resources) {
		this.options = options;
		this.setOutResourcesUpdate(resources);
	}
	
	public ArrayList<ExchangeResourcesEffect> getOptions() {
		return options;
	}

	public Container getOutResourcesUpdate () {
		return outResourcesUpdate;
	}
	
	public void setOptions(ArrayList<ExchangeResourcesEffect> options) {
		this.options = options;
	}

	public void setOutResourcesUpdate (Container outResourcesUpdated) {
		Container copy = new Container();
		for(Map.Entry<String, ResourceInterface> entry: outResourcesUpdated.getResources().entrySet())
			copy.getResources().put(entry.getKey(), entry.getValue().clone());
		
		this.outResourcesUpdate = copy;
		checkVector();
	}

	public void checkVector () {
		ArrayList <ExchangeResourcesEffect> update = new ArrayList<ExchangeResourcesEffect>();
		for (ExchangeResourcesEffect effect: options){
			ArrayList<ExchangeResourceHandler> temp = new ArrayList<ExchangeResourceHandler>();
			for(ExchangeResourceHandler erh: effect.getChoices())
				if(outResourcesUpdate.isPossibleToPay(erh.getResOut()))
					temp.add(erh);
			if(!temp.isEmpty())
				update.add(new ExchangeResourcesEffect(temp));
		}
		options = update;
	}

	
	

}
