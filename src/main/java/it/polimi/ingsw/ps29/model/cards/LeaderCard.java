package it.polimi.ingsw.ps29.model.cards;

import java.util.ArrayList;
import java.util.HashMap;

import it.polimi.ingsw.ps29.model.cards.effects.Effect;
import it.polimi.ingsw.ps29.model.game.resources.Resource;

public class LeaderCard {
	
	private String name;
	private final int id;
	private Effect leaderEffect; 
	int permanent;
	private ArrayList<Resource> requirements;
	private ArrayList<String> cardTypeRequirements;
	private ArrayList<Integer> cardRequirements;
	
	public LeaderCard ( String name, int id, int permanent, Effect effect, ArrayList<Resource> requirements, ArrayList<String> cardTypeRequirements, ArrayList<Integer> cardRequirements){
    	this.name = name;
    	this.id = id;
    	this.permanent = permanent;
    	this.leaderEffect = effect;
    	this.requirements = new ArrayList<Resource>();
    	for (Resource res : requirements){
    		this.requirements.add(res);
    	}
    	this.cardRequirements = new ArrayList<Integer>();
    	this.cardTypeRequirements = new ArrayList<String>();
    	this.cardRequirements = cardRequirements;
    	this.cardTypeRequirements = cardTypeRequirements;
    }

    @Override
    public String toString(){
		return name + " permanent " + isPermanent() +" effect " +leaderEffect + "\n     requirements :" + requirements + getCardRequirements() ;
    	
    }
    
    public int getID(){
    	return id;
    }
    
    public Effect getEffect() {
    	return leaderEffect;
    }
    
    public ArrayList<Resource> getResourcesRequirements(){
    	return requirements;
    }
    
    public boolean isPermanent(){
    	boolean isPermanent = false;
    	switch(permanent){
    	case(1) : isPermanent = true;
    		break;
    	case(0) : isPermanent = false;
    		break;	
    	}
    	return isPermanent;
    }
    
    
    public HashMap<String, Integer> getCardRequirements(){
    	
    	HashMap<String, Integer> cardRequirementsHash = new HashMap<String, Integer>();
    	for (int i = 0; i < cardRequirements.size(); i++)
    		cardRequirementsHash.put(cardTypeRequirements.get(i), cardRequirements.get(i));
		return cardRequirementsHash;
    	
    }
}
