package it.polimi.ingsw.ps29.model.game;

import java.util.ArrayList;
import java.util.HashMap;

import it.polimi.ingsw.ps29.model.cards.Card;
import it.polimi.ingsw.ps29.model.game.resources.Container;

public class PersonalBoard {
	
	private HashMap <String, ArrayList <Card>> cards;
	private PersonalBonusTile personalTile;
	private Container resources;
	
	public PersonalBoard (PersonalBonusTile personalTile) {
		cards = new HashMap <String, ArrayList<Card>> ();
		cards.put("Territory", new ArrayList<Card> ());
		cards.put("Building", new ArrayList<Card> ());
		cards.put("Character", new ArrayList<Card> ());
		cards.put("Venture", new ArrayList<Card> ());
		this.personalTile = personalTile;
		resources = new Container ();
	}
	
	
	public void addCard(Card card){
		cards.get(card.getType()).add(card);
	}
	
	public ArrayList<Card> getCards (String cardType) {
		return cards.get(cardType);
	}

	public PersonalBonusTile getPersonalBonusTile() {
		// TODO Auto-generated method stub
		return personalTile;
	}

	public Container getResources() {
		return resources;
	}

}
