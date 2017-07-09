package it.polimi.ingsw.ps29.DTO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * DTO implementation of object PersonalBoard
 * @author Pietro Melzi
 * @see it.polimi.ingsw.ps29.model.game.PersonalBoard
 *
 */
public class PersonalBoardDTO implements Serializable {
	

	//auto-generated serialVersionUID
	private static final long serialVersionUID = 8473486441407471854L;
	
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
		bld.append("\n has resources:\n");
		for (ResourceDTO rDTO : resources)
			bld.append("\n " + rDTO);
		bld.append("\n has bonus Tile:");
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
	
	public String getName () {
		return name;
	}
	
	public HashMap<String, ArrayList<Integer>> getIdCards () {
		HashMap <String, ArrayList<Integer>> ids = new HashMap<String, ArrayList<Integer>> ();
		for(String index: cards.keySet())
			ids.put(index, new ArrayList<Integer>());
		
		for(Map.Entry<String, ArrayList<CardDTO>> row: cards.entrySet())
			for(CardDTO card: row.getValue())
				ids.get(row.getKey()).add(card.getId());
		
		return ids;
	}
	
	public ArrayList<ResourceDTO> getResources () {
		return resources;
	}
	
}
