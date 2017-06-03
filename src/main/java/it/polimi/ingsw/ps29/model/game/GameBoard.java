package it.polimi.ingsw.ps29.model.game;

import java.util.ArrayList;
import java.util.HashMap;

import it.polimi.ingsw.ps29.model.cards.CardType;
import it.polimi.ingsw.ps29.model.cards.Deck;
import it.polimi.ingsw.ps29.model.space.ActionSpace;
import it.polimi.ingsw.ps29.model.space.ActivityArea;
import it.polimi.ingsw.ps29.model.space.CouncilPalaceArea;
import it.polimi.ingsw.ps29.model.space.MarketArea;
import it.polimi.ingsw.ps29.model.space.QueueActionSpace;
import it.polimi.ingsw.ps29.model.space.SingleSlotActionSpace;
import it.polimi.ingsw.ps29.model.space.tower.TowerArea;

public class GameBoard{
	
	private ArrayList <Dice> dices;
	private HashMap <String, ActionSpace> spaces;
	private ArrayList<Player> playersOrder;
	private boolean playersOrderMoved;
	
	public boolean isPlayersOrderMoved() {
		return playersOrderMoved;
	}


	public void setPlayersOrderMoved(boolean playersOrderMoved) {
		this.playersOrderMoved = playersOrderMoved;
	}

	public ArrayList<Deck> decks;
	
	public GameBoard (ArrayList<Player> players) {
		dices = new ArrayList<Dice> ();
		dices.add(new Dice(DiceColor.BLACK));
		dices.add(new Dice(DiceColor.WHITE));
		dices.add(new Dice(DiceColor.ORANGE));
		playersOrder = players;
		initSpaces();
	}
	
	
	public void initSpaces () {
		
		//devo leggere ed assegnare bonus da file
		spaces = new HashMap <String, ActionSpace> ();
		spaces.put("Harvest", new ActivityArea (new SingleSlotActionSpace(1), new QueueActionSpace(1)));
		spaces.put("Production", new ActivityArea (new SingleSlotActionSpace(1), new QueueActionSpace(1)));
		spaces.put("TerritoryTower", new TowerArea (null, null));
		spaces.put("BuildingTower", new TowerArea (null, null));
		spaces.put("CharcaterTower", new TowerArea (null, null));
		spaces.put("VentureTower", new TowerArea (null, null));
		spaces.put("FirstMarket", new MarketArea(1, null));
		spaces.put("SecondMarket", new MarketArea(1, null));
		spaces.put("ThirdMarket", new MarketArea(1, null));
		spaces.put("FourthMarket", new MarketArea(1, null));
		spaces.put("CouncilPalace", new CouncilPalaceArea(1));
		
	}

	
	
	
	public ArrayList <Player> getPlayers () {
		return playersOrder;
	}

	public void setPlayers (ArrayList<Player> playersOrder) {
		
		this.playersOrder = playersOrder;
		
	}


	public ActionSpace getSpace (String space) {
		return spaces.get(space);
	}
	
	
	
	/*public StateOfActionIdentifier getStateOfAction () {
		return stateOfAction;
	}
	
	public void setStateOfAction (StateOfActionIdentifier newState) {
		stateOfAction = newState; 
	}*/
	
	public void changePlayerOrder () {
		Player temp = playersOrder.get(0);
		playersOrder.remove(0);
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
	

}
