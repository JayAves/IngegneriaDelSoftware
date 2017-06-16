package it.polimi.ingsw.ps29.model.action.actionstates;

public enum StateOfActionIdentifier {
	PERFORMED ("performed"),
	REJECTED ("rejected"),
	BONUS_ACTION ("bonus action"),
	ASK_EXCHANGE("ask exchange"),
	TO_ESTABILISH ("to estabilish"),
	PRIVILEGES ("privileges");
	
	private final String name;
	
	StateOfActionIdentifier (String name) {
		this.name= name;
	}
	
	public String getName () {
		return name.toLowerCase(); 
	}

}
