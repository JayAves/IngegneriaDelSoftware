package it.polimi.ingsw.ps29.model.game.resources;


public enum ResourceType {
	COIN ("coin"), WOOD ("wood"), STONE ("stone"), SERVANT ("servant"), FAITH ("faith"),
	MILITARY ("military"), VICTORY ("victory"), PRIVILEGE("privilege") ;

	private final String type;
	
	ResourceType (String type) {
		this.type = type;
	}

	String getType() {
		return type.toLowerCase();
	}
	
	public static ResourceType parseInput (String input) {
		return Enum.valueOf(ResourceType.class, input.toUpperCase());
	}
	
	
}
