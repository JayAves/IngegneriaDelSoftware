package it.polimi.ingsw.ps29.model.game.resources;

import java.io.Serializable;

/**
 * 
 * @author Pietro Melzi
 * @author Giovanni Mele
 * @author Pietro Grotti
 *
 */
public class Resource implements ResourceInterface, Cloneable, Serializable {
	
	//auto-generated serialVersionUID
	private static final long serialVersionUID = -4007137112395000936L;
	protected int amount;
	protected String type;
	
	public Resource (String type, int amount) {
		this.amount = amount;
		this.type = type;
	}

	public int getAmount() {
		return amount;
	}

	public void modifyAmount(int amount) {
		this.amount += amount;
	}

	public String getType () {
		return type.toLowerCase();
	}
	
	public void negativeAmount () {
		this.amount *=-1;
	}
	
	@Override
	public Resource clone () {
		return new Resource(this.getType(), this.getAmount());
	}
	
	@Override
	public String toString() {
		return " "+type.toUpperCase() + ":" + amount + ",";
	}
	
	
}
