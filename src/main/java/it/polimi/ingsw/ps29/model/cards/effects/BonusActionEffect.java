package it.polimi.ingsw.ps29.model.cards.effects;


public abstract class BonusActionEffect extends Effect implements Cloneable {
	protected int valueAction;
	
	public int getValue () {
		return valueAction;
	}
	
	public abstract String getType ();
	
}
