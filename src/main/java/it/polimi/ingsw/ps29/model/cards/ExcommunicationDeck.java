package it.polimi.ingsw.ps29.model.cards;

import java.util.ArrayList;

/**
 * 
 * @author Pietro Melzi
 * @author Pietro Grotti
 * @author Giovanni Mele
 *
 */
public class ExcommunicationDeck {
	
	private ArrayList<ExcommunicationCard> deck;

	public ExcommunicationDeck(ArrayList<ExcommunicationCard> deck) {
		
		this.deck = deck;
	} 
	
	public ArrayList<ExcommunicationCard> getDeck () {
		return deck;
	}
	
	public ExcommunicationCard getCard (int index) {
		return deck.get(index);
	}
	
	
}
