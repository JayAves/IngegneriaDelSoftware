package it.polimi.ingsw.ps29.view.GUI.utilities;

import it.polimi.ingsw.ps29.DTO.FamilyMemberDTO;
import it.polimi.ingsw.ps29.messages.InfoForView;
import it.polimi.ingsw.ps29.model.game.DiceColor;

public class PrintInfoFunctions {

	//return index in spaceCoords of the space where the user place
	public static int getIndex (int space, int floor) {
		switch(space) {
		case 1:
			return 16;
		case 2:
			return 17;
		case 3:
			return floor-1;
		case 4:
			return 7+floor;
		case 5:
			return 3+floor;
		case 6:
			return 11+floor;
		case 7:
			return 18;
		case 8:
			return 19;
		case 9:
			return 20;
		case 10:
			return 21;
		case 11:
			return 22;
		case 12:
			return 23;	//no action
			default:
				return -1;
		}
	}
	
	public static FamilyMemberDTO getFamiliarDTO (InfoForView info) {
		return new FamilyMemberDTO(info.playerColor, familiarColor(info.familiar));
	}
	
	public static DiceColor familiarColor (int i) {
		switch (i) {
			case 1:
				return DiceColor.BLACK;
				
			case 2:
				return DiceColor.WHITE;
				
			case 3:
				return DiceColor.ORANGE;
				
			default:
				return DiceColor.NEUTRAL;
		}
	}
	
	
}
