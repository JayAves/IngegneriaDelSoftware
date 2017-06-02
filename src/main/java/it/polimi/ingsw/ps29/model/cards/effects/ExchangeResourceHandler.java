package it.polimi.ingsw.ps29.model.cards.effects;


import java.util.ArrayList;

import it.polimi.ingsw.ps29.model.game.resources.Container;
import it.polimi.ingsw.ps29.model.game.resources.Resource;

public class ExchangeResourceHandler implements Cloneable {
	
	private ArrayList<Resource> resourcesOut;
	private ArrayList<Resource> resourcesIn;
	private boolean chooseOut;
	private boolean chooseIn;
	//i booleani se sono veri indicano che la risorsa è a scelta tra quelle dell'ArrayList. 
	
	public ExchangeResourceHandler (ArrayList<Resource> resOut, ArrayList<Resource> resIn, boolean cOut, boolean cIn) {
		resourcesOut = resOut;
		resourcesIn = resIn;
		chooseOut = cOut;
		chooseIn = cIn;
	}
	
	public ArrayList<Resource> getResOut () {
		return resourcesOut;
	}
	
	public ArrayList<Resource> getResIn () {
		return resourcesIn;
	}
	
	public boolean getBooleanOut () {
		return chooseOut;
	}
	
	public boolean getBooleanIn () {
		return chooseIn;
	}
	
	
	public void setChooseOut (boolean value) {
		chooseOut = value;
	}
	
	public void setChooseIn (boolean value) {
		chooseIn = value;
	}
	
	//avvertimento per chiedere a scelta all'utente
	boolean isResourceToChoose () {
		return chooseOut || chooseIn;
	}
	
	//questi due metodi tornano la risorsa nella posizione index
	//usati in caso di scelta:
	Resource choiceOutResource (int index) {
		return resourcesOut.get(index);
	}
	
	Resource choiceInResource (int index) {
		return resourcesIn.get(index);
	}
	
	//metodo chiamato se nello scambio non è richiesta una scelta tra risorse
	void performExchange (Container resources, int indexOut, int indexIn) {
		if(!chooseOut) {
			//resources contiene le risorse del giocatore che ha deciso di scambiare le sue risorse
			for(Resource resOut: resourcesOut) {
				resOut.negativeAmount();
				resources.updateResource(resOut);
			}	
		} else {
			Resource resOut = choiceOutResource(indexOut);
			resOut.negativeAmount();
			resources.updateResource(resOut);
		}
		
		if(!chooseIn) {
			for (Resource resIn: resourcesIn) 
				resources.updateResource(resIn);	
		} else 
			resources.updateResource(choiceInResource(indexIn));
		
	}
	
	public ExchangeResourceHandler clone () {
		ArrayList<Resource> resOut = new ArrayList<Resource> ();
		ArrayList<Resource> resIn = new ArrayList<Resource> ();
		for(Resource res: resourcesOut)
			resOut.add(res.clone());
		for(Resource res: resourcesIn)
			resIn.add(res.clone());
		return new ExchangeResourceHandler (resOut, resIn, this.chooseOut, this.chooseIn);
	}

}
