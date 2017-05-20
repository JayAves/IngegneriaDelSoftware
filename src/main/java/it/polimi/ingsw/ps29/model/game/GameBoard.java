package it.polimi.ingsw.ps29.model.game;

import java.util.ArrayList;

import it.polimi.ingsw.ps29.model.action.ActionSpace;
import it.polimi.ingsw.ps29.model.action.HarvestAction;
import it.polimi.ingsw.ps29.model.action.HarvestSpace;

public class GameBoard {
	private ArrayList <Player> playersOrder;
	private ArrayList <Dice> dices;
	private ArrayList <ActionSpace> spaces;
	

	public ArrayList <Player> getPlayers () {
		return playersOrder;
	}

	public GameBoard(ArrayList<Player> playersOrder) {
		
		this.playersOrder = playersOrder;
		
		dices.add(new Dice(DiceColour.BLACK));
		dices.add(new Dice(DiceColour.WHITE));
		dices.add(new Dice(DiceColour.ORANGE));
		//manca aggiunta degli action spaces al tabellone!
	}

	public ArrayList <ActionSpace> getSpaces() {
		return spaces;
	}

	public HarvestSpace getHarvestSpace () {
		for(ActionSpace temp: spaces) {
			if(temp.getClass().equals(HarvestAction.class))
				return (HarvestSpace) temp;
		}
		return null;
	}
	
	public void setSpaces(ArrayList <ActionSpace> spaces) {
		this.spaces = spaces;
	}
	
}
