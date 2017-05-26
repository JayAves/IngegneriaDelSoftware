package it.polimi.ingsw.ps29.view;

import it.polimi.ingsw.ps29.model.game.DiceColor;
import it.polimi.ingsw.ps29.model.game.GameBoard;
import it.polimi.ingsw.ps29.model.game.familymember.FamilyMember;
import it.polimi.ingsw.ps29.model.space.ActionSpace;

public class MoveCreator {
	
	public static ActionSpace getActionSpace (GameBoard board, int code) {
		switch (code) {
			case 1:
				return board.getSpace("Harvest");
			case 2:
				return board.getSpace("Production");
			//aggiungere le 4 torri, i 4 market e il palazzo del consiglio
			default:
				return null;
			
		}
		
	}
	
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
