package it.polimi.ingsw.ps29.model.cards;

import java.util.ArrayList;
import java.util.HashMap;

import it.polimi.ingsw.ps29.model.cards.effects.Effect;
import it.polimi.ingsw.ps29.model.game.resources.Container;
import it.polimi.ingsw.ps29.model.game.resources.Resource;

public class LeaderCard {
	
	private String name;
	private final int id;
	private Effect leaderEffect; 
	boolean permantenteffect;
	private Container requirements;
	private ArrayList<String> cardTypeRequirements;
	private ArrayList<Integer> cardRequirements;
	
    public LeaderCard ( String name, int id, Effect effect, ArrayList<Resource> requirements, ArrayList<String> cardTypeRequirements, ArrayList<Integer> cardRequirements){
    	this.name = name;
    	this.id = id;
    	this.leaderEffect = effect;
    	this.requirements = new Container();
    	for (Resource res : requirements){
    		this.requirements.updateResource(res);
    	}
    	this.cardRequirements = new ArrayList<Integer>();
    	this.cardTypeRequirements = new ArrayList<String>();
    	this.cardRequirements = cardRequirements;
    	this.cardTypeRequirements = cardTypeRequirements;
    }

    @Override
    public String toString(){
		return name + "effect" + leaderEffect + "requirements :" + requirements + cardRequirements ;
    	
    }
    
    public int getID(){
    	return id;
    }
    
    public HashMap<String, Integer> getCardRequirements(){
    	
    	HashMap<String, Integer> cardRequirementsHash = new HashMap<String, Integer>();
    	for (int i = 0; i < cardRequirements.size(); i++)
    		cardRequirementsHash.put(cardTypeRequirements.get(i), cardRequirements.get(i));
		return cardRequirementsHash;
    	
    }
}
