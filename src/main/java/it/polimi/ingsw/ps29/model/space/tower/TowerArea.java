package it.polimi.ingsw.ps29.model.space.tower;

import java.util.ArrayList;

import it.polimi.ingsw.ps29.model.cards.Card;
import it.polimi.ingsw.ps29.model.game.Color;
import it.polimi.ingsw.ps29.model.provvisorio.packageAlternativoRisorse.Resource;
import it.polimi.ingsw.ps29.model.space.ActionSpace;
import it.polimi.ingsw.ps29.model.space.BonusActionSpace;
import it.polimi.ingsw.ps29.model.space.SingleSlotActionSpace;

public class TowerArea implements ActionSpace {
	
	private Floor floors[];
	private final int NUMBER_OF_FLOORS = 4;
	private int placementFloor;
	//questo attributo serve per poter implementare correttamente il metodo ereditato isEnoughPowerful(). 
	//Ha valore compreso tra 1 e 4 
	
	public TowerArea (Card [] cards, ArrayList <Resource>[] bonus, int[] power) {
		for (int i=0; i<NUMBER_OF_FLOORS; i++) {
			if(i<2)
				floors[i] = new Floor (cards[i], new SingleSlotActionSpace (power[i]));
			else
				floors[i] = new Floor (cards[i], new BonusActionSpace (power[i], bonus[i+2]));
		}
	}

	public void setPlacementFloor (int placementFloor) {
		this.placementFloor = placementFloor;
	}
	@Override
	public boolean isEmpty() {
		for (Floor i: floors) 
			if (!i.isEmpty())
				return false;
		return true;
	}

	@Override
	public boolean familiarHere(Color playerColor) {
		for (Floor i: floors) 
			if (i.familiarHere(playerColor))
				return true;
		return false;
	}

	@Override
	public boolean isEnoughPowerful(int valuePlacement) {
		return floors[placementFloor-1].isEnoughPowerful(valuePlacement);
	}
	
	public Card takeCard () {
		return floors[placementFloor-1].getCard();
	}
	
	public ArrayList <Resource> takeResource () {
		if (placementFloor>2)
			return floors[placementFloor-1].getResource();
		return null;
	}

}
