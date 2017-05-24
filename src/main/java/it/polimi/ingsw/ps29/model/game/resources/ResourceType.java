package it.polimi.ingsw.ps29.model.game.resources;


public enum ResourceType {
	COIN ("coin"), WOOD ("wood"), STONE ("stone"), SERVANT ("servant"), FAITH ("faith"),
	MILITARY ("military"), VICTORY ("victory");

	private final String type;
	
	ResourceType (String type) {
		this.type = type;
	}

	String getType() {
		return type;
	}
	
	public static ResourceType parseInput (String input) {
		return Enum.valueOf(ResourceType.class, input.toUpperCase());
	}
	
	
}
