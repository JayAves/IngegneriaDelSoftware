package it.polimi.ingsw.ps29.model.DTO;

import java.util.HashMap;

public class PersonalBoardDTO {
	private HashMap <String, CardDTO> cards;
	private HashMap <String, ResourceDTO> resources;
	private PersonalBonusTileDTO tile;
	
	public PersonalBoardDTO(PersonalBonusTileDTO tileDTO) {
		cards = new HashMap <String, CardDTO> ();
		resources = new HashMap <String, ResourceDTO> ();
		tile = tileDTO;
	}
	
	public void insertCard (CardDTO card) {
		cards.put(card.getType(), card);
	}
	
	public void insertResource (ResourceDTO res) {
		if(resources.containsKey(res.getType())) {
			int quan = resources.remove(res.getType().toString()).getAmount()+res.getAmount();
			resources.put(res.getType().toString(), new ResourceDTO(res.getType(), quan));
		} else
			resources.put(res.getType().toString(), res);
	}

	@Override
	public String toString() {
		return "PersonalBoardDTO [cards=" + cards + ", resources=" + resources + ", tile=" + tile + "]";
	}
	
	
	
}
