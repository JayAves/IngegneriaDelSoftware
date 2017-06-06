package it.polimi.ingsw.ps29.model.DTO;

public class PersonalBonusTileDTO {
	private String tileToString;

	public PersonalBonusTileDTO(String tileToString) {
		this.tileToString = tileToString;
	}
	
	@Override
	public String toString () {
		return tileToString;
	}

}
