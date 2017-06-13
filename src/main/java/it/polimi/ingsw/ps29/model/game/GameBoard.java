package it.polimi.ingsw.ps29.model.game;

import java.util.ArrayList;
import java.util.HashMap;

import it.polimi.ingsw.ps29.model.cards.CardType;
import it.polimi.ingsw.ps29.model.cards.Deck;
import it.polimi.ingsw.ps29.model.cards.ExcommunicationCard;
import it.polimi.ingsw.ps29.model.cards.ExcommunicationDeck;
import it.polimi.ingsw.ps29.model.cards.ExcommunicationDeck;
import it.polimi.ingsw.ps29.model.game.resources.Resource;
import it.polimi.ingsw.ps29.model.space.ActionSpace;
import it.polimi.ingsw.ps29.model.space.CouncilPalaceArea;
import it.polimi.ingsw.ps29.model.space.FaithSpace;
import it.polimi.ingsw.ps29.model.space.HarvestArea;
import it.polimi.ingsw.ps29.model.space.MarketArea;
import it.polimi.ingsw.ps29.model.space.ProductionArea;
import it.polimi.ingsw.ps29.model.space.QueueActionSpace;
import it.polimi.ingsw.ps29.model.space.SingleSlotActionSpace;
import it.polimi.ingsw.ps29.model.space.TowerArea;

public class GameBoard{
	
	private ArrayList <Dice> dices;
	private HashMap <String, ActionSpace> spaces;
	private ArrayList<Player> playersOrder;
	private boolean playersOrderMoved;
	//variabile utilizzata nel metodo GameEngine della classe Match
	private ArrayList<Deck> decks;
	private ArrayList<ExcommunicationDeck> excommunicationDeck;
	private HashMap<Integer, ExcommunicationCard> excommunications;
	private HashMap<Integer, FaithSpace> faithTrack;
	
	public Color getColorByName (String name) {
		for(Player player: playersOrder)
			if (player.getName().equals(name))
				return player.getColor();
		return null;
	}
	
	public GameBoard (ArrayList<Player> players) {
		dices = new ArrayList<Dice> ();
		dices.add(new Dice(DiceColor.BLACK));
		dices.add(new Dice(DiceColor.WHITE));
		dices.add(new Dice(DiceColor.ORANGE));
		playersOrder = players;
		playersOrderMoved = false;
		decks= new ArrayList<Deck>();
		initSpaces();
	}
	
	
	public void initSpaces () {
		
		ArrayList<Resource> temporaryBonus1= new ArrayList<Resource>();
		Resource r=new Resource("coin",2);
		temporaryBonus1.add(r);
		ArrayList<Resource> temporaryBonus2= new ArrayList<Resource>();
		Resource s=new Resource("wood",2);
		temporaryBonus2.add(s);
		ArrayList<Resource> temporaryBonus3= new ArrayList<Resource>();
		Resource t=new Resource("stone",2);
		temporaryBonus3.add(t);
		ArrayList<Resource> temporaryBonus4= new ArrayList<Resource>();
		Resource u=new Resource("servant",2);
		temporaryBonus4.add(u);
		
		//devo leggere ed assegnare bonus da file
		spaces = new HashMap <String, ActionSpace> ();
		spaces.put("Harvest", new HarvestArea (new SingleSlotActionSpace(1), new QueueActionSpace(1)));
		spaces.put("Production", new ProductionArea (new SingleSlotActionSpace(1), new QueueActionSpace(1)));
		spaces.put("territoryTower", new TowerArea ());
		((TowerArea)spaces.get("territoryTower")).setBonus(temporaryBonus1,3);
		((TowerArea)spaces.get("territoryTower")).setBonus(temporaryBonus2,4);
		spaces.put("buildingTower", new TowerArea ());
		((TowerArea)spaces.get("buildingTower")).setBonus(temporaryBonus3,3);
		((TowerArea)spaces.get("buildingTower")).setBonus(temporaryBonus4,4);
		spaces.put("characterTower", new TowerArea ());
		((TowerArea)spaces.get("characterTower")).setBonus(temporaryBonus1,3);
		((TowerArea)spaces.get("characterTower")).setBonus(temporaryBonus3,4);
		spaces.put("ventureTower", new TowerArea ());
		((TowerArea)spaces.get("ventureTower")).setBonus(temporaryBonus2,3);
		((TowerArea)spaces.get("ventureTower")).setBonus(temporaryBonus4,4);
		spaces.put("FirstMarket", new MarketArea(1, temporaryBonus1));
		spaces.put("SecondMarket", new MarketArea(1, temporaryBonus2));
		spaces.put("ThirdMarket", new MarketArea(1,temporaryBonus3));
		spaces.put("FourthMarket", new MarketArea(1, temporaryBonus4));
		spaces.put("CouncilPalace", new CouncilPalaceArea(1));
		for (int i = 0 ; i < 16; i++){
			faithTrack.put(i, new FaithSpace(new Resource("coins", i)));
		}
	}
	
	public ArrayList <Player> getPlayers () {
		return playersOrder;
	}

	public Player getPlayerByName (String playerName) {
		for (Player player: playersOrder)
			if(player.getName().equals(playerName))
				return player;
		return null;
	}
	
	public void setPlayers (ArrayList<Player> playersOrder) {
		this.playersOrder = playersOrder;	
	}

	public ActionSpace getSpace (String space) {
		return spaces.get(space);
	}
	
	public HashMap <String, ActionSpace> getSpaces () {
		return spaces;
	}
	
	public void changePlayerOrder () {
		Player temp = playersOrder.remove(0);
		playersOrder.add(temp);
		
	}

	public Deck getSpecificDeck(CardType type, Period period) {
		for (Deck deck: decks) {
			if ((deck.getType()==type)&&(deck.getPeriod().equals(period))) {
				return deck;
			}
	
		}
		return null;
	}

	public ArrayList<Dice> getDices(){
		return this.dices;
	}
	
	public ArrayList<Deck> getDecks(){
		return decks;
	}
	
	public Player getCurrentPlayer (){
		return playersOrder.get(0);
	}
	
	public boolean isPlayersOrderMoved() {
		return playersOrderMoved;
	}


	public void setPlayersOrderMoved(boolean playersOrderMoved) {
		this.playersOrderMoved = playersOrderMoved;
	}
	
	public ExcommunicationCard getExcommunication(int round){
		return excommunications.get(round);
	}

	public int getExcommunicationTreshold( int round ){
		int tresHold = 0;
		switch (round) {
		case 2: tresHold = 3;
		       break;
		case 4 : tresHold = 4;
		       break;
		case 6 : tresHold = 5;
		       break;
		}
		return tresHold;
	}
	
	public Resource getVaticanBonus( int round){
		Resource bonus = null;
		switch (round) {
		case 2: bonus = faithTrack.get(3).getBonus();
		       break;
		case 4 : bonus = faithTrack.get(4).getBonus();
		       break;
		case 6 : bonus = faithTrack.get(5).getBonus();
		       break;
		}
		return bonus ;
	}
	
}

