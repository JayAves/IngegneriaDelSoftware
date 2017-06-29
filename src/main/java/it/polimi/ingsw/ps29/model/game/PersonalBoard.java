package it.polimi.ingsw.ps29.model.game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

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
	
	public boolean checkIfCardsEqualsInSize( int size){
		
		boolean check = true;
		ArrayList<Integer> sizes = new ArrayList<Integer>();
		sizes = getCadsSizes();
		while (check){
			for (int i = 0; i < sizes.size(); i++)
				check = sizes.get(i) >= size;
		}
   
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
	
	
	public boolean satisfyRequirements( LeaderCard card){
		
			HashMap<String, Integer> requirements = card.getCardRequirements();
			ArrayList<Resource> resources = new ArrayList<Resource>(); 
			resources = card.getResourcesRequirements();
			boolean satisfiedc = true;
			boolean satisfiedr = true;
			System.out.println("\n sto controllando requisiti di "+ card.toString());
			if (!requirements.isEmpty()){
				if (requirements.containsKey("any")){
					satisfiedc = checkIfCardsEqualsInSize(requirements.get("any"));
				}
			
					System.out.println(" vettore requirements " + requirements.toString());
					Set<String> set = requirements.keySet();
					System.out.println(" i requisiti di carte sono " );
					for (String type : set){
						System.out.println("\n " + type);
						satisfiedc = satisfiedc &&  getCards(type).size() >= requirements.get(type);
					}
			}
		
			System.out.println("\n ho controlato le carte di " + card.toString() + " risultato " + satisfiedc );
		
			if (!resources.isEmpty()){
				satisfiedr = this.resources.isPossibleToPay(resources);
			}

			System.out.println(" ho controllato i requisiti di " + card.toString() + " e il risultato è " + (satisfiedc && satisfiedr));
			return (satisfiedc && satisfiedr);
	}
	
	public ArrayList<ArrayList<Object>> buildLeaderChoice(){
		
		ArrayList<ArrayList<Object>> LeaderChoice = new ArrayList<ArrayList<Object>>();
		
		for (LeaderCard card : leaderCards){

		    ArrayList<Object> cardVector = new ArrayList<Object>();
		    cardVector.add(card.getID());
		    cardVector.add(card.toString());
		    cardVector.add(0);
		    cardVector.add(satisfyRequirements(card));
		   	LeaderChoice.add(cardVector);
   
		}

		if (playedLeaderCards.size() > 0){

		for (LeaderCard card : playedLeaderCards){
		    ArrayList<Object> cardVector = new ArrayList<Object>();
		    cardVector.add(card.getID());
		    cardVector.add(card.toString());
		    cardVector.add(1);
		    cardVector.add(true);
		    LeaderChoice.add(cardVector);

		}

		}

		

		return LeaderChoice;
	}
	
	public void addLeaderCards(ArrayList<LeaderCard> card){
    	leaderCards = card;;
    }
	
	public LeaderCard getLeaderById(int id, ArrayList<LeaderCard> temp){
		
		for (LeaderCard card : temp){
			if (id == card.getID())
				return card;
		}
		return null;	
    }
	
    public void removeLeaderById( int id){
    	
		for (LeaderCard card : leaderCards){
			if (id == card.getID())
				leaderCards.remove(card);
			break;
		}

    }

    public void removePlayedLeaderById( int id){
    	
 		for (LeaderCard card : playedLeaderCards){
 			if (id == card.getID())
 				playedLeaderCards.remove(card);
 			break;
 		}

     }
    
    public void removeActivatedLeaderById( int id){
    	
 		for (LeaderCard card : activatedLeaderCards){
 			if (id == card.getID())
 				activatedLeaderCards.remove(card);
 			break;
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
