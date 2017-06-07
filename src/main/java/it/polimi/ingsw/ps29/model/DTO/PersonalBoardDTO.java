package it.polimi.ingsw.ps29.model.DTO;

import java.io.Serializable;
import java.util.HashMap;

public class PersonalBoardDTO implements Serializable {
	String name;
	HashMap <String, CardDTO> cards;
	HashMap <String, ResourceDTO> resources;
	PersonalBonusTileDTO tile;
	
	public PersonalBoardDTO(String name, PersonalBonusTileDTO tileDTO) {
		this.name = name;
		cards = new HashMap <String, CardDTO> ();
		resources = new HashMap <String, ResourceDTO> ();
		tile = tileDTO;
	}
	
	public void insertCard (CardDTO card) {
		cards.put(card.getType(), card);
	}
	
	public void cleanContainer () {
		resources = new HashMap <String, ResourceDTO> ();
	}
	
	public void insertResource (ResourceDTO res) {
		if(resources.containsKey(res.getType())) {
			int quan = resources.remove(res.getType()).getAmount()+res.getAmount();
			resources.put(res.getType(), new ResourceDTO(res.getType(), quan));
		} else
			resources.put(res.getType(), res);
	}

	@Override
	public String toString() {
		return "PersonalBoardDTO [name=" + name + ", cards=" + cards + ", resources=" + resources + ", tile=" + tile
				+ "]";
	}

	
	
	
	
}
