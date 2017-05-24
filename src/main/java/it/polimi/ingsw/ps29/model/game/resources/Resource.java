package it.polimi.ingsw.ps29.model.game.resources;

public class Resource {
	
	protected int amount;
	protected ResourceType type;

	public int getAmount() {
		return amount;
	}

	public void modifyAmount(int amount) {//da implementare eccezione nel caso diventa < 0
		this.amount += amount;
	}

	public String getType () {
		return type.getType();
	}
}
