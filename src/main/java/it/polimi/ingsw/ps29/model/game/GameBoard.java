package it.polimi.ingsw.ps29.model.game;

import java.util.ArrayList;
import java.util.HashMap;

import it.polimi.ingsw.ps29.model.game.resources.ResourceType;
import it.polimi.ingsw.ps29.model.provvisorio.packageAlternativoRisorse.Resource;
import it.polimi.ingsw.ps29.model.space.ActionSpace;
import it.polimi.ingsw.ps29.model.space.ActivityArea;
import it.polimi.ingsw.ps29.model.space.MarketArea;
import it.polimi.ingsw.ps29.model.space.QueueActionSpace;
import it.polimi.ingsw.ps29.model.space.SingleSlotActionSpace;
import it.polimi.ingsw.ps29.model.space.tower.TowerArea;

public class GameBoard implements Cloneable {
	private ArrayList <Player> playersOrder;
	private ArrayList <Dice> dices;
	private HashMap <String, ActionSpace> spaces;
	private StateOfActionIdentifier stateOfAction; //utilizzato per permettere lo scambio di informazioni tra model e view
	//serve per sapere se terminare il turno oppure richiedere informazioni aggiuntive al giocatore
	
	public GameBoard () {
		initSpaces();
	}
	
	private void initSpaces () {
		ArrayList <Resource> resources = new ArrayList <Resource> ();
		
		spaces.put("Harvest", new ActivityArea (new SingleSlotActionSpace(1), new QueueActionSpace(1)));
		spaces.put("Production", new ActivityArea (new SingleSlotActionSpace(1), new QueueActionSpace(1)));
		spaces.put("TerritoryTower", new TowerArea (null, null, null));
		spaces.put("BuildingTower", new TowerArea (null, null, null));
		spaces.put("CharcaterTower", new TowerArea (null, null, null));
		spaces.put("VentureTower", new TowerArea (null, null, null));
		resources.add(new Resource (5, ResourceType.COIN));
		spaces.put("FirstMarket", new MarketArea(1, resources));
		
		stateOfAction = StateOfActionIdentifier.TO_ESTABILISH;
	}

	public ArrayList <Player> getPlayers () {
		return playersOrder;
	}

	public GameBoard(ArrayList<Player> playersOrder) {
		
		this.playersOrder = playersOrder;
		
		dices.add(new Dice(DiceColor.BLACK));
		dices.add(new Dice(DiceColor.WHITE));
		dices.add(new Dice(DiceColor.ORANGE));
		//manca aggiunta degli action spaces al tabellone!
	}

	public ActivityArea getHarvestSpace () {
		return (ActivityArea) spaces.get("Harvest");
	}
	
	@Override 
	public GameBoard clone () {
		GameBoard copy = new GameBoard ();
		copy.playersOrder = this.playersOrder;
		copy.dices = this.dices;
		copy.spaces = this.spaces;
		copy.stateOfAction = this.stateOfAction;
		return copy;
	}
	
	public StateOfActionIdentifier getStateOfAction () {
		return stateOfAction;
	}
	
	public void setStateOfAction (StateOfActionIdentifier newState) {
		stateOfAction = newState; 
	}
	
}
