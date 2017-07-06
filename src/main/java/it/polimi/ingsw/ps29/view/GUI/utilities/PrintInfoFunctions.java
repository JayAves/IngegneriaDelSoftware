package it.polimi.ingsw.ps29.view.GUI.utilities;

import java.util.ArrayList;
import java.util.HashMap;

import it.polimi.ingsw.ps29.DTO.FamilyMemberDTO;
import it.polimi.ingsw.ps29.DTO.ResourceDTO;
import it.polimi.ingsw.ps29.messages.InfoForView;
import it.polimi.ingsw.ps29.model.game.DiceColor;
import it.polimi.ingsw.ps29.view.GUI.GUICore;

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
	
	public static ArrayList<Integer> createIdCards (HashMap<String, ArrayList<Integer>> hash) {
		ArrayList<Integer> ids = new ArrayList<Integer>();
		for(int i=0; i<12; i++)
			ids.add(-1);
		
		if(hash.get("territory")!=null)
			for(int i=0; i<hash.get("territory").size(); i++)
				ids.set(i*2, hash.get("territory").get(i));
		
		if(hash.get("building")!=null)
			for(int i=0; i<hash.get("building").size(); i++)
				ids.set(1+i*2, hash.get("building").get(i));
		
		return ids;
	}
	
	public static void printUpdatedResources (GUICore gui, ArrayList<ResourceDTO> container) {
		for (ResourceDTO res: container)
			switch (res.getType()) {
			case "coin":
				gui.getCoin().setText("COINS: "+res.getAmount());
				break;
			case "stone":
				gui.getStone().setText("STONES: "+res.getAmount());
				break;
			case "wood":
				gui.getWood().setText("WOODS: "+res.getAmount());
				break;
			case "servant":
				gui.getServant().setText("SERVANTS: "+res.getAmount());
				break;
			case "faith":
				gui.getFaith().setText("FAITH: "+res.getAmount());
				break;
			case "military":
				gui.getMilitary().setText("MILITARY: "+res.getAmount());
				break;
			case "victory":
				gui.getVictory().setText("VICTORY: "+res.getAmount());
				break;
			case "privilege":
				gui.getPrivileges().setText("PRIVILEGES: "+res.getAmount());
				break;
			}
	}
}
