package it.polimi.ingsw.ps29.model.game.resources;

public class Resource {
	
	private int amount;
	private final ResourceType type;
	
	public Resource (String type, int amount) {
		this.amount = amount;
		this.type = ResourceType.parseInput(type);
	}

	public int getAmount() {
		return amount;
	}

	public void modifyAmount(int amount) {//da implementare eccezione nel caso diventa < 0
		this.amount += amount;
	}

	public String getType () {
		return type.getType();
	}
	
	public void negativeAmount () {
		this.amount *=-1;
	}
}
