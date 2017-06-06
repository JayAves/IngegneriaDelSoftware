package it.polimi.ingsw.ps29.model.DTO;

import it.polimi.ingsw.ps29.model.game.Color;
import it.polimi.ingsw.ps29.model.game.DiceColor;

public class FamilyMemberDTO {
	private Color playerColor;
	private DiceColor familiarColor;
	
	
	public FamilyMemberDTO(Color playerColor, DiceColor familiarColor) {
		this.playerColor = playerColor;
		this.familiarColor = familiarColor;
	}
	
	@Override
	public String toString () {
		return "Familiar "+familiarColor+" - Player "+playerColor;
	}
}
