package it.polimi.ingsw.ps29.model.cards.effects;


import java.io.Serializable;
import java.util.ArrayList;

import it.polimi.ingsw.ps29.model.game.resources.Container;
import it.polimi.ingsw.ps29.model.game.resources.Resource;

/**
 * 
 * @author Pietro Melzi
 *
 */
public class ExchangeResourceHandler implements Cloneable, Serializable {
	
	//auto-generated serialVErsionUID
	private static final long serialVersionUID = 3524782719504036952L;
	private ArrayList<Resource> resourcesOut;
	private ArrayList<Resource> resourcesIn;
	private boolean chooseOut;
	private boolean chooseIn;
	//booleans mean there are alternatives in resources gain/pay 
	
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
	
	//warning for player if he has to choose
	public boolean isResourceToChoose () {
		return chooseOut || chooseIn;
	}
	
	//returns what alternative was chosen
	Resource choiceOutResource (int index) {
		return resourcesOut.get(index);
	}
	
	Resource choiceInResource (int index) {
		return resourcesIn.get(index);
	}
	
	public void performExchange (Container resources, int indexOut, int indexIn) {

		// if no alternatives in paying
		if(!chooseOut) {
			//resources contais players chose to pay
			for(Resource resOut: resourcesOut) {
				resOut.negativeAmount();
				resources.updateResource(resOut);
			}	
		} else {
			Resource resOut = choiceOutResource(indexOut);
			resOut.negativeAmount();
			resources.updateResource(resOut);
		}
		
		// if no alternatives in getting
		if(!chooseIn) {
			for (Resource resIn: resourcesIn) 
				resources.updateResource(resIn);	
		} else 
			resources.updateResource(choiceInResource(indexIn));
		
	}
	
	public ArrayList<Resource> resOut (int indexOut) {
		if(!chooseOut)
			return resourcesOut;
		ArrayList<Resource> temp = new ArrayList<Resource> ();
		//use if user has to choose sth
		temp.add(choiceOutResource(indexOut));
		return temp;
	}
	
	@Override
	public ExchangeResourceHandler clone () {
		ArrayList<Resource> resOut = new ArrayList<Resource> ();
		ArrayList<Resource> resIn = new ArrayList<Resource> ();
		for(Resource res: resourcesOut)
			resOut.add(res.clone());
		for(Resource res: resourcesIn)
			resIn.add(res.clone());
		return new ExchangeResourceHandler (resOut, resIn, this.chooseOut, this.chooseIn);
	}

	@Override
	public String toString () {
		String msg="Res out: ";
		for(Resource res: resourcesOut)
			msg+=res.toString();
		if(chooseOut)
			msg+=", [Optional]: ";
		msg+="; Res in: ";
		for(Resource res: resourcesIn)
			msg+=res.toString();
		if(chooseIn)
			msg+="[Optional]: ";
		return msg;
	}
}
