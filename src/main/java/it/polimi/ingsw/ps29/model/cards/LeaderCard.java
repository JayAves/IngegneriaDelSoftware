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
	private HashMap <String, Integer> cardRequirements;
	
    public LeaderCard ( String name, int id, Effect effect, ArrayList<Resource> requirements, ArrayList<Integer> cardRequirements){
    	this.name = name;
    	this.id = id;
    	this.leaderEffect = effect;
    	this.requirements = new Container();
    	for (Resource res : requirements){
    		this.requirements.updateResource(res);
    	}
    	this.cardRequirements = new HashMap<String, Integer>();
    	if (cardRequirements.get(0) > 0)
    		this.cardRequirements.put("territory", cardRequirements.get(0));
    	if (cardRequirements.get(1) > 0)
    		this.cardRequirements.put("Building", cardRequirements.get(0));
    	if (cardRequirements.get(2) > 0)
    		this.cardRequirements.put("character", cardRequirements.get(0));
    	if (cardRequirements.get(3) > 0)
    		this.cardRequirements.put("venture", cardRequirements.get(0));
    }

    @Override
    public String toString(){
		return name + "effect" + leaderEffect + "requirements :" + requirements + cardRequirements ;
    	
    }
    
    public int getID(){
    	return id;
    }
    
    public HashMap<String, Integer> getCardRequirements(){
    	return cardRequirements;
    }
}
