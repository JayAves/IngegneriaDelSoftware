package it.polimi.ingsw.ps29.DTO;

import java.io.Serializable;

import it.polimi.ingsw.ps29.model.game.Color;
import it.polimi.ingsw.ps29.model.game.DiceColor;

/**
 * DTO implementation of object FamilyMember
 * @author Pietro Melzi
 * @see it.polimi.ingsw.ps29.model.game.familymember.FamilyMember
 *
 */
public class FamilyMemberDTO implements Serializable {
	
	//auto-generated serialVersionUID
	private static final long serialVersionUID = -5829592886372144973L;
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
