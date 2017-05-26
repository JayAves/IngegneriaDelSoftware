package it.polimi.ingsw.ps29.model.space;

import java.util.ArrayList;

import it.polimi.ingsw.ps29.model.game.resources.Resource;

public class BonusActionSpace extends SingleSlotActionSpace {

	private ArrayList <Resource> bonus;
	
	public BonusActionSpace(int power, ArrayList <Resource> bonus) {
		super(power);
		this.bonus = bonus;
	}

	public ArrayList<Resource> getBonus() {
		return bonus;
	}
	
	/*
	 * private ArrayList <Gift> gifts;
	 * 
	 * public BonusActionSpace(int power, ArrayList <Gift> gifts) {
	 *	   super(power);
	 *	   this.gifts = gifts;
	 *
	 *public ArrayList <Gift> getGift(){
	 *    return gifts;
	 */
	

}
