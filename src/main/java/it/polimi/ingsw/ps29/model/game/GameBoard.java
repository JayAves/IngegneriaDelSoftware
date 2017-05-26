package it.polimi.ingsw.ps29.model.game;

import java.util.ArrayList;
import java.util.HashMap;
import it.polimi.ingsw.ps29.model.action.Action;
import it.polimi.ingsw.ps29.model.game.resources.Resource;
import it.polimi.ingsw.ps29.model.provvisorio.packageAlternativoRisorse.Coin;
import it.polimi.ingsw.ps29.model.space.ActionSpace;
import it.polimi.ingsw.ps29.model.space.ActivityArea;
import it.polimi.ingsw.ps29.model.space.MarketArea;
import it.polimi.ingsw.ps29.model.space.QueueActionSpace;
import it.polimi.ingsw.ps29.model.space.SingleSlotActionSpace;
import it.polimi.ingsw.ps29.model.space.tower.TowerArea;

public class GameBoard implements Cloneable {
	private int id_partita;
	private ArrayList <Player> playersOrder;
	private ArrayList <Dice> dices;
	private HashMap <String, ActionSpace> spaces;
	private StateOfActionIdentifier stateOfAction; //utilizzato per permettere lo scambio di informazioni tra model e view
	//serve per sapere se terminare il turno oppure richiedere informazioni aggiuntive al giocatore
	
	public GameBoard (int id_partita) {
		this.id_partita = id_partita;
		playersOrder = new ArrayList<Player>();
		dices.add(new Dice(DiceColor.BLACK));
		dices.add(new Dice(DiceColor.WHITE));
		dices.add(new Dice(DiceColor.ORANGE));
		initSpaces();
	}
	
	private void initSpaces () {
		
		spaces.put("Harvest", new ActivityArea (new SingleSlotActionSpace(1), new QueueActionSpace(1)));
		spaces.put("Production", new ActivityArea (new SingleSlotActionSpace(1), new QueueActionSpace(1)));
		spaces.put("TerritoryTower", new TowerArea (null, null, null));
		spaces.put("BuildingTower", new TowerArea (null, null, null));
		spaces.put("CharcaterTower", new TowerArea (null, null, null));
		spaces.put("VentureTower", new TowerArea (null, null, null));
		spaces.put("FirstMarket", new MarketArea(1, null));
		//mancano altri spazi mercato
		
		stateOfAction = StateOfActionIdentifier.TO_ESTABILISH;
	}

	public ArrayList <Player> getPlayers () {
		return playersOrder;
	}

	public void setPlayers (ArrayList<Player> playersOrder) {
		
		this.playersOrder = playersOrder;
		
	}
	
	public int getIdPartita () {
		return id_partita;
	}

	public ActionSpace getSpace (String space) {
		return spaces.get(space);
	}
	
	@Override 
	public GameBoard clone () {
		GameBoard copy = new GameBoard (this.id_partita);
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
