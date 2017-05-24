package it.polimi.ingsw.ps29.model.game.resources;

public enum ResourceType {
	COIN ("coins"), WOOD ("woods"), STONE ("stones"), SERVANT ("servants"), FAITH ("faith"),
	MILITARY ("military"), VICTORY ("victory");

	private final String type;
	
	ResourceType (String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}
	
	
}
