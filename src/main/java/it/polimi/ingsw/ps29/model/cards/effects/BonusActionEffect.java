package it.polimi.ingsw.ps29.model.cards.effects;

import java.io.Serializable;

public abstract class BonusActionEffect extends Effect implements Cloneable, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7250316374154504961L;
	protected int valueAction;
	
	public int getValue () {
		return valueAction;
	}
	
	public abstract String getType ();
	
	@Override
	public abstract BonusActionEffect clone ();

	@Override
	public String toString() {
		return " BonusActionEffect for a value of" + valueAction + " ";
	}
	
	

}
