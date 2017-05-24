package it.polimi.ingsw.ps29.model.cards;

import it.polimi.ingsw.ps29.model.game.resources.ResourceType;

public enum CardType {
	TERRITORY ("Territory"), BUILDING ("Building"), CHARACTER ("Character"), VENTURE ("Venture"), EXCOMMUNICATION ("Excommunication");

	private final String type;
	
	CardType (String type) {
		this.type = type;
	}

	String getType() {
		return type;
	}
	
	public static CardType parseInput (String input) {
		return Enum.valueOf(CardType.class, input.toUpperCase());
	}
}
