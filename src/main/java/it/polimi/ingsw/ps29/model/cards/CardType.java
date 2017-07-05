package it.polimi.ingsw.ps29.model.cards;

/**
 * Describes card type
 * @author Pietro Melzi
 * @author Pietro Grotti
 * @author Giovanni Mele
 *
 */
public enum CardType {
	TERRITORY ("territory"), BUILDING ("building"), CHARACTER ("character"), VENTURE ("venture"), 
	EXCOMMUNICATION ("excommunication"), ALL ("all");
	//All is used for some special effects (such as Abess Character Card)

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
