package it.polimi.ingsw.ps29.model.game;

import java.util.ArrayList;
import java.util.HashMap;

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
	private StateOfActionIdentifier stateOfAction; //utilizzato per permettere lo scambio di informazioni tra model e view
	private ArrayList<Player> playersOrder;
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
		
		stateOfAction = StateOfActionIdentifier.TO_ESTABILISH;
	}

	public void initContainers() {
		//devo dare monete a seconda dell'ordine di gioco
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
	
	/*
	@Override 
	public GameBoard clone () {
		GameBoard copy = new GameBoard (this.id_partita);
		copy.playersOrder = this.playersOrder;
		copy.dices = this.dices;
		copy.spaces = this.spaces;
		copy.stateOfAction = this.stateOfAction;
		return copy;
	}
	*/
	
	public StateOfActionIdentifier getStateOfAction () {
		return stateOfAction;
	}
	
	public void setStateOfAction (StateOfActionIdentifier newState) {
		stateOfAction = newState; 
	}



	
	

}
