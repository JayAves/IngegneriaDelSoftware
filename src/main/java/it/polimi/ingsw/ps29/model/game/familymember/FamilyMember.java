package it.polimi.ingsw.ps29.model.game.familymember;

public class FamilyMember implements FamilyMemberInterface{
	
	//enum Color?
	
	private int power;

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
