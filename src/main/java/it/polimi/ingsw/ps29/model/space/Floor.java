package it.polimi.ingsw.ps29.model.space;

import java.util.ArrayList;

import it.polimi.ingsw.ps29.model.cards.Card;
import it.polimi.ingsw.ps29.model.game.Color;
import it.polimi.ingsw.ps29.model.game.resources.Resource;

public class Floor {
	
	private Card card;
	private SingleSlotActionSpace space;
	
	public Floor (SingleSlotActionSpace space) {
		this.space = space;
	}
	
	
	public boolean isEmpty () {
		return space.isEmpty();
	}
	
	public boolean familiarHere (Color playerColor) {
		return space.familiarHere(playerColor);
	}
	
	public boolean isEnoughPowerful (int valuePlacement) {
		return valuePlacement>=space.getPowerRequired();
	}


	public Card getCard() {
		return card;
	}


	public void setCard(Card card) {
		this.card = card;
	}
	
	public ArrayList <Resource> getResource () {
		if (space instanceof BonusActionSpace)
			return ((BonusActionSpace)space).getBonus();
		return null;
	}
	
	public SingleSlotActionSpace getSpace(){
		return this.space;
	}
}
