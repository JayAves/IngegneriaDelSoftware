package it.polimi.ingsw.ps29.viewclient.DTO;

import java.io.Serializable;

public class PersonalBonusTileDTO implements Serializable {
	private String tileToString;

	public PersonalBonusTileDTO(String tileToString) {
		this.tileToString = tileToString;
	}
	
	@Override
	public String toString () {
		return tileToString;
	}

}
