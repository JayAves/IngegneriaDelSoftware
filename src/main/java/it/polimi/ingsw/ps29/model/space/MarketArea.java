package it.polimi.ingsw.ps29.model.space;

import java.util.ArrayList;

import it.polimi.ingsw.ps29.model.game.Color;
import it.polimi.ingsw.ps29.model.game.resources.Resource;

public class MarketArea implements ActionSpace {
	
	private BonusActionSpace slot;
	
	public MarketArea (int power, ArrayList <Resource> bonus) {
		slot = new BonusActionSpace (power, bonus);
	}
	
	public BonusActionSpace getSlot () {
		return slot;
	}

	@Override
	public boolean isEmpty() {
		return slot.isEmpty();
	}

	@Override
	public boolean familiarHere(Color playerColor) {
		return slot.familiarHere(playerColor);
	}

	@Override
	public boolean isEnoughPowerful(int valuePlacement) {
		return slot.isEnoughPowerful(valuePlacement);
	}

	@Override
	public void cleanSpace() {
		// TODO Auto-generated method stub
		
	}
	
	/*
	 * public ArrayList <Gift> getGifts(){
	 *     return  slot.getGift();
	 * }
	 */

}
