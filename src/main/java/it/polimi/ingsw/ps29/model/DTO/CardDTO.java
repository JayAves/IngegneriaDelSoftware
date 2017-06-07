package it.polimi.ingsw.ps29.model.DTO;

import it.polimi.ingsw.ps29.model.cards.CardType;

public class CardDTO {
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
		return type.toString();
	}
	
}
