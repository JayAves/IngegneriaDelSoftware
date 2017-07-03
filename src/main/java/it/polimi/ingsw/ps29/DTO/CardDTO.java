package it.polimi.ingsw.ps29.DTO;

import java.io.Serializable;

/**
 * DTO implementation of object Card
 * @author Pietro Melzi
 * @see it.polimi.ingsw.ps29.model.cards.Card
 *
 */
public class CardDTO implements Serializable {
	
	//auto-generated serialVersionUID
	private static final long serialVersionUID = 3101423843838642848L;
	private int id;
	private String type;
	private String cardToString;
	
	public CardDTO(int id, String type, String cardToString) {
		this.id = id;
		this.type = type.toLowerCase();
		this.cardToString = cardToString;
	}
	
	@Override
	public String toString () {
		return cardToString;
	}

	public String getType() {
		return type;
	}
	
	public int getId () {
		return id;
	}
}
