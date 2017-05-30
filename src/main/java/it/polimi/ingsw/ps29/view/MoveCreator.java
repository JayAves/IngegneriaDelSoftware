package it.polimi.ingsw.ps29.view;

import it.polimi.ingsw.ps29.model.game.DiceColor;
import it.polimi.ingsw.ps29.model.game.GameBoard;
import it.polimi.ingsw.ps29.model.game.familymember.FamilyMember;

public class MoveCreator {
	
	public static FamilyMember getFamilyMember (GameBoard board, int code) {
		DiceColor color;
		switch (code) {
			case 1:
				color = DiceColor.BLACK;
				break;
			case 2:
				color = DiceColor.WHITE;
				break;
			case 3:
				color = DiceColor.ORANGE;
				break;
			default:
				color = DiceColor.NEUTRAL;
				break;
		}
		return board.getPlayers().get(0).getFamiliarByColor(color);
	}
	
	
	
}
