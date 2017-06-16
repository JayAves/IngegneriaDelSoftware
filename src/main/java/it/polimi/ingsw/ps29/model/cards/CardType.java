package it.polimi.ingsw.ps29.model.cards;


public enum CardType {
	TERRITORY ("territory"), BUILDING ("building"), CHARACTER ("character"), VENTURE ("venture"), 
	EXCOMMUNICATION ("excommunication"), ALL ("all");
	//All è previsto per gli effetti che valgono per ogni tipo di carta

	private final String type;
	
	CardType (String type) {
		this.type = type;
	}

	public String getType() {
		return type.toLowerCase();
	}
	
	public static CardType parseInput (String input) {
		return Enum.valueOf(CardType.class, input.toUpperCase());
	}
}
