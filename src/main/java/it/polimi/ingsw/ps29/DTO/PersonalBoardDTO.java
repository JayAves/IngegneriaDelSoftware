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
		//return "Player:" + name + " has cards:" + cards + ", has resources:" + resources + ", has Bonustile=" + tile;
		StringBuilder bld = new StringBuilder().append("Player " + name + " situation :" );
		bld.append("\n\n has cards:\n");
		for (String cardKey: cards.keySet()){
			bld.append("\n " + cardKey);
			for (CardDTO carddto : cards.get(cardKey))
				bld.append("\n            " + carddto);
		}
		bld.append("\n\n has resources:\n");
		for (ResourceDTO rDTO : resources)
			bld.append("\n " + rDTO);
		bld.append("\n\n has bonus Tile:");
		bld.append("\n" + tile);
		bld.append("\n");
		
		return bld.toString();
	}

	public void setResources (ArrayList<ResourceDTO> res) {
		resources = res;
	}
	
	public void setTile (PersonalBonusTileDTO tile) {
		this.tile = tile;
	}
	
	public PersonalBonusTileDTO getTile () {
		return tile;
	}
	
	
	
	
}
