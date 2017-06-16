package it.polimi.ingsw.ps29.viewclient.DTO;

import java.util.ArrayList;
import java.util.HashMap;

public class TowersDTO {
	private HashMap <String, ArrayList<CardDTO>> towers;
	
	public TowersDTO () {
		 towers = new HashMap <String, ArrayList<CardDTO>> ();
		 
		 towers.put("territory", new ArrayList<CardDTO> ());
		 towers.put("building", new ArrayList<CardDTO> ());
		 towers.put("character", new ArrayList<CardDTO> ());
		 towers.put("venture", new ArrayList<CardDTO> ());
		 
	}
	
	public void addCard (CardDTO card) {
		towers.get(card.getType().toLowerCase()).add(card);
	}
	
	public void takeCard (int space, int floor) {
		//tower da 3 a 6, floor da 1 a 4
		switch (space) {
			case 3:
				towers.get("territory").set(floor, new CardDTO(0, "", ""));
				break;
			case 4:
				towers.get("building").set(floor, new CardDTO(0, "", ""));
				break;
			case 5:
				towers.get("character").set(floor, new CardDTO(0, "", ""));
				break;
			case 6:
				towers.get("venture").set(floor, new CardDTO(0, "", ""));
				break;
		}
	}
}
