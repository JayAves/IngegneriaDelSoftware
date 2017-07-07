package it.polimi.ingsw.ps29.model.cards;

import java.util.ArrayList;

import it.polimi.ingsw.ps29.model.game.Period;

/**
 * 
 * @author Pietro Melzi
 * @author Pietro Grotti
 * @author Giovanni Mele
 *
 */
public class Deck {
	private Period period;
	private CardType type;
	private ArrayList <Card> cards;
	
	public Deck(Period period, CardType type) {
		this.period = period;
		this.type = type;
		cards= new ArrayList<Card>();
	}
	
	public void addCard(Card c) {
		this.cards.add(c);
	}
	
	public void removeCard(int index) {
		cards.remove(index);
	}
	
	public CardType getType() {
		return this.type;
	}
	
	public int getSize() {
		return cards.size();
	}
	
	public Card getCard(int index) {
		return cards.get(index);
	}
	
	public Period getPeriod() {
		return this.period;
	}
	
}
