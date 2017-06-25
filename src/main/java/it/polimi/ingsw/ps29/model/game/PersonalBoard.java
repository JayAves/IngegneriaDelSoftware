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
	private ArrayList<LeaderCard> playedLeaderCards;
	private ArrayList<LeaderCard> activatedLeaderCards;
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
		leaderCards = new ArrayList<LeaderCard>();
		playedLeaderCards = new ArrayList<LeaderCard>();
		activatedLeaderCards = new ArrayList<LeaderCard>();
	}
	
	
	public void addCard(Card card){
		cards.get(card.getType()).add(card);
	}
	
	public ArrayList<Integer> getCadsSizes(){
		ArrayList<Integer> sizes = new ArrayList<Integer>();
		sizes.add(cards.get("territory").size());
		sizes.add(cards.get("building").size());
		sizes.add(cards.get("character").size());
		sizes.add(cards.get("venture").size());
		
		return sizes;
	}
	
	public ArrayList<Card> getCards (String cardType) {
		return cards.get(cardType);
	}
	
	public boolean checkIfCardsEqualsSize( int size){
		
		ArrayList<Integer> sizes = new ArrayList<Integer>();
		sizes = getCadsSizes();
		if (sizes.contains(size))
		   return true;
		return false;
		
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
		return resources.getResource(res)==null ? new Resource (res.toLowerCase(), 0) : resources.getResource(res);
	}
	
	public void setResources (Container resource) {
		this.resources = resource;
	}
	
	private int whereIsTheCard(LeaderCard card){ 
		
			if (leaderCards.contains(card)) 
					return 0; 
		 		else if (playedLeaderCards.contains(card)) 
		 			return 1; 
				else 
					return 2; 
			}
	
	
	public boolean satisfyRequirements(LeaderCard card){
		
		HashMap<String, Integer> requirements = card.getCardRequirements();
		boolean satisfied = true;
		while (satisfied){
			if (requirements.containsKey("any")){
				return checkIfCardsEqualsSize(requirements.get("any"));
			}
			for (HashMap.Entry<String, Integer> entry : requirements.entrySet()){
		        satisfied = getCards(entry.getKey()).size() >= entry.getValue();
			}
		}
		return satisfied;
	}
	
	public ArrayList<ArrayList<Object>> buildLeaderChoice(){
		
		ArrayList<ArrayList<Object>> LeaderChoice = new ArrayList<ArrayList<Object>>();
		
		for (LeaderCard card : leaderCards){
		    ArrayList<Object> cardVector = new ArrayList<Object>();
		    cardVector.add(0, card.getID());
		    cardVector.add(1, card.toString());
		    cardVector.add(2, 0);
		    cardVector.add(3, satisfyRequirements(card));
		    LeaderChoice.add(cardVector);
		}
		
		for (LeaderCard card : playedLeaderCards){
		    ArrayList<Object> cardVector = new ArrayList<Object>();
		    cardVector.add(0, card.getID());
		    cardVector.add(1, card.toString());
		    cardVector.add(2, 1);
		    cardVector.add(3, true);
		    LeaderChoice.add(cardVector);
		}
		
		return LeaderChoice;
	}
	
	public void addLeaderCards(ArrayList<LeaderCard> card){
    	leaderCards = card;;
    }
	
	public LeaderCard getLeaderById( int id, ArrayList<LeaderCard> deck){
		
		for (LeaderCard card : deck){
			if (id == card.getID())
				return card;
		}
		return null;	
    }
	
    public void removeLeaderById( int id, ArrayList<LeaderCard> deck){
		
		for (LeaderCard card : deck){
			if (id == card.getID())
				deck.remove(card);
		}
			
    }


	public ArrayList<LeaderCard> getLeaderCards() {
		return leaderCards;
	}

	public ArrayList<LeaderCard> getPlayedLeaderCards() {
		return playedLeaderCards;
	}
	
	public ArrayList<LeaderCard> getActivatedLeaderCards(){
		return activatedLeaderCards;
	}
	}
