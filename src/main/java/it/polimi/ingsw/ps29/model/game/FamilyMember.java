package it.polimi.ingsw.ps29.model.game;

import it.polimi.ingsw.ps29.model.action.ActionSpace;

public class FamilyMember {
	private boolean busy;
	private final DiceColour colour;
	private final Colour playerColor;
	private ActionSpace space;
	
	public FamilyMember(DiceColour colour, Colour playerColor) {
		
		this.colour = colour;
		this.playerColor = playerColor;
		this.busy= false;
	}

	public Colour getPlayerColor() {
		return playerColor;
	}
	
	
}
