package it.polimi.ingsw.ps29.DTO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * DTO implementation of object Tower
 * @author Pietro Melzi
 * @see it.polimi.ingsw.ps29.model.space.TowerArea
 *
 */
public class TowersDTO implements Serializable {
	
	//auto-generated serialVersionUID
	static final long serialVersionUID = 2547684498839129802L;
	private HashMap <String, ArrayList<CardDTO>> towers;
	private String [] types = {"territory", "character", "building", "venture"};
	
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
		if(space==4)
			space = 5;
		else if (space==5)
			space = 4;
		takenCard = towers.get(types[space-3]).get(floor-1);
		towers.get(types[space-3]).set(floor-1, new CardDTO(-1, "", ""));
		return takenCard;
	}
	
	public HashMap <String, ArrayList<CardDTO>> getTowers () {
		return towers;
	}
	
	//given a 0-15 index, looks for the corresponding card
	public int getIdCard (int index) {
		
		return towers.get(types[index/4]).get(index%4).getId();
	}
	
	public ArrayList<Integer> getIdCards () {
		ArrayList<Integer> id = new ArrayList<Integer>();
		for(String type: types)
			for(CardDTO card: towers.get(type))
				id.add(card.getId());
		return id;
	}
	
}
