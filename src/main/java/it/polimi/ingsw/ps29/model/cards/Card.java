package it.polimi.ingsw.ps29.model.cards;

import java.io.Serializable;
import java.util.ArrayList;

import it.polimi.ingsw.ps29.model.cards.effects.Effect;
import it.polimi.ingsw.ps29.model.game.Period;
import it.polimi.ingsw.ps29.model.game.resources.Resource;

public abstract class Card {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final String name;
	private final Period period;
	private final CardType type;
	private ArrayList<Effect> immediateEffects;
	private ArrayList<Effect> permanentEffects;
	private ArrayList<Resource> cost;
	
	public Card(String name, Period period, String type, ArrayList<Effect> immediate, ArrayList<Effect> permanent, ArrayList<Resource> cost) {
		
		this.name = name;
		this.period = period;
		this.type = CardType.parseInput(type);
		this.immediateEffects = immediate;
		this.permanentEffects = permanent;
		this.cost= cost;
	}

	public ArrayList<Effect> getImmediateEffects() {
		return immediateEffects;
	}

	public void setImmediateEffects(ArrayList<Effect> immediateEffects) {
		this.immediateEffects = immediateEffects;
	}

	public ArrayList<Effect> getPermanentEffects() {
		return permanentEffects;
	}

	public void setPermanentEffects(ArrayList<Effect> permanentEffects) {
		this.permanentEffects = permanentEffects;
	}

	public String getType() {
		return type.getType();
	}
	
	public ArrayList<Resource> getCost() {
		return this.cost;
	}
}
