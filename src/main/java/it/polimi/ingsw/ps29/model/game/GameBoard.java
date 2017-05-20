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

public class GameBoard {
	private ArrayList <Player> playersOrder;
	private ArrayList <Dice> dices;
	private HashMap <String, ActionSpace> spaces;
	
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
	
}
