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
		for(int i=0; i<24; i++)
			ids.add(-1);
		
		if(hash.get("territory")!=null)
			for(int i=0; i<hash.get("territory").size(); i++)
				ids.set(i*2, hash.get("territory").get(i));
		
		if(hash.get("building")!=null)
			for(int i=0; i<hash.get("building").size(); i++)
				ids.set(1+i*2, hash.get("building").get(i));
		
		//the order is different from previous two because the way in which they are showed is different
		if(hash.get("character")!=null)
			for(int i=0; i<hash.get("character").size(); i++)
				ids.set(12+i, hash.get("character").get(i));
		
		if(hash.get("venture")!=null)
			for(int i=0; i<hash.get("venture").size(); i++)
				ids.set(18+i, hash.get("venture").get(i));
		
		return ids;
	}
	
	public static void printUpdatedResources (GUICore gui, ArrayList<ResourceDTO> container) throws NullPointerException {
		for (ResourceDTO res: container)
			switch (res.getType()) {
			case "coin":
				gui.getCoin().setText(""+res.getAmount());
				break;
			case "stone":
				gui.getStone().setText(""+res.getAmount());
				break;
			case "wood":
				gui.getWood().setText(""+res.getAmount());
				break;
			case "servant":
				gui.getServant().setText(""+res.getAmount());
				break;
			case "faith":
				gui.getFaith().setText(""+res.getAmount());
				break;
			case "military":
				gui.getMilitary().setText(""+res.getAmount());
				break;
			case "victory":
				gui.getVictory().setText(""+res.getAmount());
				break;
			case "privilege":
				gui.getPrivileges().setText(""+res.getAmount());
				break;
			}
	}
	
	public static int getIndexFromHashMap (String first, String second) {
		switch (first) {
		case "harvest":
			return 16;
		case "production":
			return 17;
		case "territoryTower":
			return secondLevel(second, 0);
		case "buildingTower":
			return secondLevel(second, 8);
		case "characterTower":
			return secondLevel(second, 4);
		case "ventureTower":
			return secondLevel(second, 12);
		case "market":
			return secondLevel(second, 18);
		case "council":
			return 22;
			default:
				return 23;
		}
	}
	
	public static int secondLevel(String second, int index) {
		switch (second) {
		case "first":
			return index;
		case "second":
			return index+1;
		case "third":
			return index+2;
		case "fourth":
			return index+3;
			default:
				return -1;
		}
		
	}
}
