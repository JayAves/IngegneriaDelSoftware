package it.polimi.ingsw.ps29.model.space.tower;

import java.util.ArrayList;
import it.polimi.ingsw.ps29.model.cards.Card;
import it.polimi.ingsw.ps29.model.game.Color;
import it.polimi.ingsw.ps29.model.game.resources.Resource;
import it.polimi.ingsw.ps29.model.space.ActionSpace;
import it.polimi.ingsw.ps29.model.space.BonusActionSpace;
import it.polimi.ingsw.ps29.model.space.SingleSlotActionSpace;

public class TowerArea implements ActionSpace {
	
	private ArrayList<Floor> floors;
	private final int NUMBER_OF_FLOORS = 4;
	private int placementFloor;
	//questo attributo serve per poter implementare correttamente il metodo ereditato isEnoughPowerful(). contiene indice del piano dove si vuole piazzare
	//Ha valore compreso tra 1 e 4 
	
	public TowerArea () {
		
		floors = new ArrayList<Floor> ();
		int[] power= new int[] {1,3,5,7};
		
		for (int i=0; i<NUMBER_OF_FLOORS; i++) {
			if(i<2)
				floors.add(new Floor (new SingleSlotActionSpace (power[i])));
			else
				floors.add(new Floor (new BonusActionSpace (power[i])));
		}
		placementFloor = 0;
	}

	public void setPlacementFloor (int placementFloor) {
		this.placementFloor = placementFloor;
	}
	
	public Floor getPlacementFloor(){ //serve per capire da quale piano devo togliere la carta
		return floors.get(placementFloor-1);
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
		return floors.get(placementFloor-1).isEnoughPowerful(valuePlacement);
	}
	
	

	@Override
	public void cleanSpace() {
		// TODO Auto-generated method stub
		
	}
	
	public Card takeCard () {
		return floors.get(placementFloor-1).getCard();
	}
	
	public ArrayList <Resource> takeResource () {
		if (placementFloor>2)
			return floors.get(placementFloor-1).getResource();
		return null;
	}
	
	public void fill(ArrayList<Card> cards) {
		for (int i=0; i<NUMBER_OF_FLOORS; i++) {
			floors.get(i).setCard(cards.get(i));
		}
		
	}

}
