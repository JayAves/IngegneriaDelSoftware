package it.polimi.ingsw.ps29.model.cards.effects;

import java.util.ArrayList;
import java.util.Iterator;

import it.polimi.ingsw.ps29.model.game.resources.Container;
import it.polimi.ingsw.ps29.model.game.resources.Resource;

public class ExchangeResourceHandler {
	
	private ArrayList<Resource> resourcesOut;
	private ArrayList<Resource> resourcesIn;
	private boolean chooseOut;
	private boolean chooseIn;
	//i booleani se sono veri indicano che la risorsa è a scelta tra quelle dell'ArrayList. 
	//Quando è a scelta l'ArrayList prevede esattamente due risorse
	
	public ExchangeResourceHandler () {
		resourcesOut = new ArrayList<Resource>();
		resourcesIn = new ArrayList<Resource>();
		chooseOut = false;
		chooseIn = false;
	}
	
	//agiunge una risorsa nell'Array giusto, in base al boolean 
	void addResources (Resource res, boolean in) {
		if(in)
			resourcesIn.add(res);
		else
			resourcesOut.add(res);
	}
	
	void setChooseOut (boolean value) {
		chooseOut = value;
	}
	
	void setChooseIn (boolean value) {
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
	
	// il metodo è chiamato se l'utente deve scegliere un'alternativa tra le resOut o le resIn
	//la scelta è già stata effettuata ed è indicata dal parametro i
	void performExchangeWithChoice (Container resources, int i) {
		if(chooseOut) {
			Resource resOut = choiceOutResource(i);
			resOut.negativeAmount();
			resources.addResource(resOut);
		}
		
		else if (chooseIn) 
			resources.addResource(choiceInResource(i));
			
		
			
		
	}
	
	//metodo chiamato se nello scambio non è richiesta una scelta tra risorse
	void performExchangeNoChoice (Container resources) {
		if(!isResourceToChoose()) {
			//resources contiene le risorse del giocatore che ha deciso di scambiare le sue risorse
			for(Resource resOut: resourcesOut) {
				resOut.negativeAmount();
				resources.addResource(resOut);
			}			
			for (Resource resIn: resourcesIn) 
				resources.addResource(resIn);	
		}
	}
	

}
