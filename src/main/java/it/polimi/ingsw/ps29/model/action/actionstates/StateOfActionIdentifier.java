package it.polimi.ingsw.ps29.model.action.actionstates;

public enum StateOfActionIdentifier {
	PERFORMED ("performed"),
	REJECTED ("rejected"),
	BONUS_ACTION ("bonus_action"),
	ASK_EXCHANGE("ask_exchange"),
	TO_ESTABLISH ("to_establish"),
	PRIVILEGES ("privileges");
	
	private final String name;
	
	StateOfActionIdentifier (String name) {
		this.name= name;
	}
	
	public String getName () {
		return name.toLowerCase(); 
	}

}
