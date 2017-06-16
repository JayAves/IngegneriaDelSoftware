package it.polimi.ingsw.ps29.viewclient.DTO;

import java.io.Serializable;
import java.util.HashMap;

public class PersonalBoardDTO implements Serializable {
	String name;
	HashMap <String, CardDTO> cards;
	String resources;
	PersonalBonusTileDTO tile;
	
	public PersonalBoardDTO(String name, PersonalBonusTileDTO tileDTO) {
		this.name = name;
		cards = new HashMap <String, CardDTO> ();
		tile = tileDTO;
	}
	
	public void insertCard (CardDTO card) {
		cards.put(card.getType(), card);
	}
	
	public void cleanContainer () {
		resources = "";
	}

	@Override
	public String toString() {
		return "PersonalBoardDTO [name=" + name + ", cards=" + cards + ", resources=" + resources + ", tile=" + tile
				+ "]";
	}

	public void setResources (String res) {
		resources = res;
	}
	
	
	
	
}
