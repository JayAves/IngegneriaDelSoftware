package it.polimi.ingsw.ps29.viewclient.DTO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class TowersDTO implements Serializable {
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
	
	public CardDTO takeCard (int space, int floor) {
		CardDTO takenCard;
		String [] towersName = {"territory", "building", "character", "venture"};
		takenCard = towers.get(towersName[space-3]).get(floor-1);
		towers.get(towersName[space-3]).set(floor-1, new CardDTO(0, "", ""));
		return takenCard;
	}
	
	public HashMap <String, ArrayList<CardDTO>> getTowers () {
		return towers;
	}
}
