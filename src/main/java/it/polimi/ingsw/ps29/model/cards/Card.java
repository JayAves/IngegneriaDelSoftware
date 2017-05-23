package it.polimi.ingsw.ps29.model.cards;

import java.util.ArrayList;

import it.polimi.ingsw.ps29.model.cards.effects.Effect;
import it.polimi.ingsw.ps29.model.game.Period;

public abstract class Card {
	private final String name;
	private final Period period;
	private final CardType type;
	private ArrayList<Effect> immediateEffects;
	private ArrayList<Effect> permanentEffects;
	//private ArrayList<ResourceOrPoint> cost;
	
	public Card(String name, Period period, CardType type) {
		
		this.name = name;
		this.period = period;
		this.type = type;
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
	
	
}
