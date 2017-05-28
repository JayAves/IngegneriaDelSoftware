package it.polimi.ingsw.ps29.model.cards;


public enum CardType {
	TERRITORY ("Territory"), BUILDING ("Building"), CHARACTER ("Character"), VENTURE ("Venture"), 
	EXCOMMUNICATION ("Excommunication"), ALL ("All");
	//All è previsto per gli effetti che valgono per ogni tipo di carta

	private final String type;
	
	CardType (String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}
	
	public static CardType parseInput (String input) {
		return Enum.valueOf(CardType.class, input.toUpperCase());
	}
}
