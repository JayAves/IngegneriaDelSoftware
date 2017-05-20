package it.polimi.ingsw.ps29.model.cards;

import java.util.ArrayList;

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
	
	
}
