package it.polimi.ingsw.ps29.DTO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class PersonalBoardDTO implements Serializable {
	String name;
	HashMap <String, ArrayList<CardDTO>> cards;
	ArrayList<ResourceDTO> resources;
	PersonalBonusTileDTO tile;
	
	public PersonalBoardDTO(String name, PersonalBonusTileDTO tileDTO) {
		this.name = name;
		cards = new HashMap <String, ArrayList<CardDTO>> ();
		
		cards.put("territory", new ArrayList<CardDTO> ());
		cards.put("building", new ArrayList<CardDTO> ());
		cards.put("character", new ArrayList<CardDTO> ());
		cards.put("venture", new ArrayList<CardDTO> ());
		
		tile = tileDTO;
	}
	
	public void insertCard (CardDTO card) {
		cards.get(card.getType().toLowerCase()).add(card);
	}
	
	public void cleanContainer () {
		resources = new ArrayList<ResourceDTO>();
	}

	@Override
	public String toString() {
		return "PersonalBoardDTO [name=" + name + ", cards=" + cards + ", resources=" + resources + ", tile=" + tile
				+ "]";
	}

	public void setResources (ArrayList<ResourceDTO> res) {
		resources = res;
	}
	
	
	
	
}
