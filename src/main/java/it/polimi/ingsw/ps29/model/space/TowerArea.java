package it.polimi.ingsw.ps29.model.space;

import java.util.ArrayList;

import it.polimi.ingsw.ps29.messages.exception.FamiliarHereException;
import it.polimi.ingsw.ps29.messages.exception.NotEnoughPowerfulException;
import it.polimi.ingsw.ps29.model.cards.Card;
import it.polimi.ingsw.ps29.model.game.PlayerColor;
import it.polimi.ingsw.ps29.model.game.familymember.FamilyMemberInterface;
import it.polimi.ingsw.ps29.model.game.resources.Resource;

/**
 * Represents a Tower with four floors in GameBoard.
 * @author Pietro Melzi
 * @author Giovanni Mele
 * @author Pietro Grotti
 *
 */
public class TowerArea implements ActionSpace {
	
	private ArrayList<Floor> floors;
	private final int NUMBER_OF_FLOORS = 4;
	private int placementFloor; //is used in return right dice power needed in PowefulEnough method. Is a number between 1 and 4
	
	
	public TowerArea () {
		
		floors = new ArrayList<Floor> ();
		int[] power= new int[] {1,3,5,7};
		
		for (int i=0; i<NUMBER_OF_FLOORS; i++) {
			if(i<2)
				floors.add(new Floor (new SingleSlotActionSpace (power[i])));
			else
				floors.add(new Floor (new BonusActionSpace (power[i]))); //top floors have bonuses
		}
		placementFloor = 0;
	}

	public void setPlacementFloor (int placementFloor) {
		this.placementFloor = placementFloor;
	}
	
	public Floor getPlacementFloor(){ 
		return floors.get(placementFloor-1);
	}
	
	public String printCards () {
		String msg = "";
		for(Floor floor: floors)
			msg+=floor.getCard().toString()+"\n";
		return msg;
	}
	
	@Override
	public boolean isEmpty() {
		for (Floor i: floors) 
			if (!i.isEmpty())
				return false;
		return true;
	}

	@Override
	public boolean familiarHere(PlayerColor playerColor) throws FamiliarHereException {
		for (Floor i: floors) 
			if (i.familiarHere(playerColor))
				throw new FamiliarHereException();
		return false;
	}

	@Override
	public boolean isEnoughPowerful(int valuePlacement) throws NotEnoughPowerfulException {
		if( floors.get(placementFloor-1).isEnoughPowerful(valuePlacement))
			return true;
		throw new NotEnoughPowerfulException();
	}
	
	

	@Override
	public void cleanSpace() {
		for(Floor singleFloor: floors) 
			singleFloor.getSpace().cleanSpace();
		
	}
	
	public Card takeCard () {
		return floors.get(placementFloor-1).getCard();
	}
	
	public ArrayList <Resource> takeResource () {
		if (placementFloor>2)
			return floors.get(placementFloor-1).getResource();
		return new ArrayList<Resource>();
	}
	
	public void fill(ArrayList<Card> cards) {
		for (int i=0; i<NUMBER_OF_FLOORS; i++) {
			floors.get(i).setCard(cards.get(i));
		}
		
	}
	public void setBonus(ArrayList<Resource> bonus, int floor){
		if (floor>2){
			((BonusActionSpace)floors.get(floor-1).getSpace()).setBonus(bonus);
		}
	}

	public ArrayList<Floor> getFloors() {
		return floors;
	}

	@Override
	public void placeFamiliar(FamilyMemberInterface familiar, boolean ludovicoAriosto) {
		floors.get(placementFloor-1).getSpace().setFamilyMember(familiar);
	}

	public void setCard(Card card, int floor) {
		floors.get(floor-1).setCard(card);
	}
	
}
