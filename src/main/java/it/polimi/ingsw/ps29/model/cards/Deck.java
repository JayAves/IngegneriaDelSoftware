package it.polimi.ingsw.ps29.model.cards;

import java.util.ArrayList;

import it.polimi.ingsw.ps29.model.game.Period;

public class Deck {
	private Period period;
	private CardType type;
	private ArrayList <Card> cards;
	
	public Deck(Period period, CardType type) {
		super();
		this.period = period;
		this.type = type;
		
		
	}
	
	public void addCard(Card c) {
		this.cards.add(c);
	}
	
}
