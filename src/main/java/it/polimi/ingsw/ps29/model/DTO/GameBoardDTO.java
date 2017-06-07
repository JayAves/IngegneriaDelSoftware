package it.polimi.ingsw.ps29.model.DTO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import it.polimi.ingsw.ps29.model.game.Color;
import it.polimi.ingsw.ps29.model.game.DiceColor;
import it.polimi.ingsw.ps29.view.usermessages.UserChoice;

public class GameBoardDTO implements Serializable{
	HashMap<String, HashMap <String, ArrayList<FamilyMemberDTO>>> boardMap;

	public GameBoardDTO() {
		boardMap = new HashMap <String, HashMap <String, ArrayList<FamilyMemberDTO>>> ();
		boardMap.put("harvest", new HashMap<String, ArrayList<FamilyMemberDTO>> ());
		boardMap.put("production", new HashMap<String, ArrayList<FamilyMemberDTO>> ());
		boardMap.put("territoryTower", new HashMap<String, ArrayList<FamilyMemberDTO>> ());
		boardMap.put("buildingTower", new HashMap<String, ArrayList<FamilyMemberDTO>> ());
		boardMap.put("characterTower", new HashMap<String, ArrayList<FamilyMemberDTO>> ());
		boardMap.put("ventureTower", new HashMap<String, ArrayList<FamilyMemberDTO>> ());
		boardMap.put("market", new HashMap<String, ArrayList<FamilyMemberDTO>> ());
		boardMap.put("council", new HashMap<String, ArrayList<FamilyMemberDTO>> ());
		boardMap.put("noaction", new HashMap<String, ArrayList <FamilyMemberDTO>> ());
		
		boardMap.get("harvest").put("head", new ArrayList <FamilyMemberDTO> ());
		boardMap.get("harvest").put("queue", new ArrayList <FamilyMemberDTO> ());
		boardMap.get("production").put("head", new ArrayList <FamilyMemberDTO> ());
		boardMap.get("production").put("queue", new ArrayList <FamilyMemberDTO> ());
		rapidInit("territoryTower");
		rapidInit("buildingTower");
		rapidInit("characterTower");
		rapidInit("ventureTower");
		rapidInit("market");
		boardMap.get("council").put("single", new ArrayList <FamilyMemberDTO> ());
		boardMap.get("noaction").put("noaction", new ArrayList <FamilyMemberDTO> ());
	}
	
	private void rapidInit (String key) {
		boardMap.get(key).put("first", new ArrayList <FamilyMemberDTO> ());
		boardMap.get(key).put("second", new ArrayList <FamilyMemberDTO> ());
		boardMap.get(key).put("third", new ArrayList <FamilyMemberDTO> ());
		boardMap.get(key).put("fourth", new ArrayList <FamilyMemberDTO> ());
	}
	
	private String firstLevel (int i) {
		String key;
		switch (i) {
			case 1:
				key = "harvest";
				break;
			case 2:
				key = "production";
				break;
			case 3:
				key = "territoryTower";
				break;
			case 4:
				key = "buildingTower";
				break;
			case 5:
				key = "characterTower";
				break;
			case 6:
				key = "ventureTower";
				break;
			case 7:
			case 8:
			case 9:
			case 10:
				key = "market";
				break;
			case 11:
				key = "council";
				break;
			default:
				key = "noaction";
		}
		return key;
	}
	
	private String secondLevel (UserChoice move) {
		String key;
		switch (move.getChoices(0)) {
			case 1:
				key = boardMap.get("harvest").get("head").isEmpty() ? "head" : "queue";
				break;
			case 2:
				key = boardMap.get("production").get("head").isEmpty() ? "head" : "queue";
				break;
			case 3:
			case 4:
			case 5:
			case 6:
				switch (move.getChoices(1)) {
					case 1:
						key = "first";
						break;
					case 2:
						key = "second";
						break;
					case 3:
						key = "third";
						break;
					case 4:
						key = "fourth";
						break;
					default:
						key = "noaction";
				}
				break;
			case 7:
				key = "first";
				break;
			case 8:
				key = "second";
				break;
			case 9:
				key = "third";
				break;
			case 10:
				key = "fourth";
				break;
			case 11:
				key = "single";
				break;
			default:
				key = "noaction";
		}
		return key;
	}
	
	public DiceColor familiarColor (int i) {
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
	
	public void insertFamiliar (UserChoice move, Color playerColor) {
		FamilyMemberDTO fmDTO = new FamilyMemberDTO(playerColor, familiarColor(move.getChoices(3)));
		boardMap.get(firstLevel(move.getChoices(0))).get(secondLevel(move)).add(fmDTO);
	}

	
	@Override
	public String toString () {
		StringBuilder bld = new StringBuilder().append("GameBoard\n");
		for (String firstKey: boardMap.keySet()) {
			bld.append(firstKey+" ");
			for(String secondKey: boardMap.get(firstKey).keySet()) {
				bld.append(secondKey+":\n");
				for (FamilyMemberDTO fmDTO: boardMap.get(firstKey).get(secondKey))
					bld.append(fmDTO.toString()+"\n");
			}
		}
		
		return bld.toString();
	}
	
}
