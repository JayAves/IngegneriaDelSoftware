package it.polimi.ingsw.ps29.model.game.familymember;

import it.polimi.ingsw.ps29.model.game.Color;
import it.polimi.ingsw.ps29.model.game.DiceColor;

public class FamilyMember implements FamilyMemberInterface{
	
	//enum Color?
	
	private int power;
	private boolean busy;
	private DiceColor familiarColor;
	private Color playerColor;

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

	public DiceColor getFamiliarColor() {
		return familiarColor;
	}

	public Color getPlayerColor() {
		return playerColor;
	}

	
	
}
