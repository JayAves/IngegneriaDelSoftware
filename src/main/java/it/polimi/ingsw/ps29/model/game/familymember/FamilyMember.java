package it.polimi.ingsw.ps29.model.game.familymember;

import it.polimi.ingsw.ps29.model.action.ActionSpace;
import it.polimi.ingsw.ps29.model.game.Colour;
import it.polimi.ingsw.ps29.model.game.DiceColour;

public class FamilyMember implements FamilyMemberInterface{
	
	//enum Color?
	
	private int power;
	private boolean busy;
	private DiceColour colour;
	private Colour playerColor;
	private ActionSpace space;

	//public int setPower()
	// qui definiamo come associare il power in base al dado
	
	
	public int getHarvestPower(){
		 return getPower();
	}
	
	public int getProductionPower(){
		return getPower();
	}
	
	public int getTowerPower(){
		return getPower();
	}
	
	public int getMarketPower(){
		return getPower();
	}
	
	public int getCouncilPower(){
		return getPower();
	}

	public int getPower() {
		return this.power;
	}
	
	
}
