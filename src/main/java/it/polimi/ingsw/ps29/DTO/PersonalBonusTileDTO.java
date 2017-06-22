package it.polimi.ingsw.ps29.DTO;

import java.io.Serializable;

public class PersonalBonusTileDTO implements Serializable {
	private int id;
	private String tileToString;

	public PersonalBonusTileDTO(int id, String tileToString) {
		this.id = id;
		this.tileToString = tileToString;
	}
	
	@Override
	public String toString () {
		return tileToString;
	}

}
