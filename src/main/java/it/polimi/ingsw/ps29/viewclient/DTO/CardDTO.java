package it.polimi.ingsw.ps29.viewclient.DTO;

import java.io.Serializable;

public class CardDTO implements Serializable {
	private int id;
	private String type;
	private String cardToString;
	
	public CardDTO(int id, String type, String cardToString) {
		this.id = id;
		this.type = type;
		this.cardToString = cardToString;
	}
	
	@Override
	public String toString () {
		return cardToString;
	}

	public String getType() {
		return type;
	}
	
}
