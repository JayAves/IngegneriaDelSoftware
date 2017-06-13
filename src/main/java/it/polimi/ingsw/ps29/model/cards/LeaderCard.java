package it.polimi.ingsw.ps29.model.cards;

import java.util.ArrayList;

import it.polimi.ingsw.ps29.model.cards.effects.Effect;
import it.polimi.ingsw.ps29.model.game.resources.Container;
import it.polimi.ingsw.ps29.model.game.resources.Resource;

public class LeaderCard {
	
	private String name;
	private Effect leaderEffect;
	private Container requirements;
	
    public LeaderCard ( String name, Effect effect, ArrayList<Resource> requirements){
    	this.name = name;
    	this.leaderEffect = effect;
    	for (Resource res : requirements){
    		requirements.add(res);
    	}
    }
}
