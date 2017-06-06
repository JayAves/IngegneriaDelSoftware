package it.polimi.ingsw.ps29.model.game.resources;

public class Resource implements ResourceInterface, Cloneable {
	
	protected int amount;
	protected String type;
	
	public Resource (String type, int amount) {
		this.amount = amount;
		this.type = type;
	}

	public int getAmount() {
		return amount;
	}

	public void modifyAmount(int amount) {//da implementare eccezione nel caso diventa < 0
		this.amount += amount;
	}

	public String getType () {
		return type;
	}
	
	public void negativeAmount () {
		this.amount *=-1;
	}
	
	public Resource clone () {
		return new Resource(this.getType(), this.getAmount());
	}
	
	@Override
	public String toString() {
		return "Resource [amount=" + amount + ", type=" + type + "]";
	}
	
	
}
