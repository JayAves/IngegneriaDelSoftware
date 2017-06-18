package it.polimi.ingsw.ps29.model.game;

import java.util.ArrayList;
import java.util.HashMap;

import it.polimi.ingsw.ps29.model.cards.Card;
import it.polimi.ingsw.ps29.model.cards.LeaderCard;
import it.polimi.ingsw.ps29.model.game.resources.Container;
import it.polimi.ingsw.ps29.model.game.resources.Resource;
import it.polimi.ingsw.ps29.model.game.resources.ResourceInterface;

public class PersonalBoard {
	
	private HashMap <String, ArrayList <Card>> cards;
	private ArrayList<LeaderCard> leaderCards;
	private ArrayList<LeaderCard> flippedLeaderCards;
	private PersonalBonusTile personalTile;
	private Container resources;
	
	public PersonalBoard (PersonalBonusTile personalTile) {
		cards = new HashMap <String, ArrayList<Card>> ();
		cards.put("territory", new ArrayList<Card> ());
		cards.put("building", new ArrayList<Card> ());
		cards.put("character", new ArrayList<Card> ());
		cards.put("venture", new ArrayList<Card> ());
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
	
	public void setTile (PersonalBonusTile pbt) {
		personalTile = pbt;
	}

	public Container getResources() {
		return resources;
	}
	 
	public ResourceInterface getSpecificResource (String res) {
		return resources.getResource(res)==null ? new Resource ("res", 0) : resources.getResource(res);
	}
	
	public void setResources (Container resource) {
		this.resources = resource;
	}
}
