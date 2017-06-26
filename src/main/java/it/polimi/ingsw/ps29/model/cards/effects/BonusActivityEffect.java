package it.polimi.ingsw.ps29.model.cards.effects;

import it.polimi.ingsw.ps29.model.game.Player;

public class BonusActivityEffect extends BonusActionEffect {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2489229689117313929L;
	private String type;

	public BonusActivityEffect(int valueAction, String type) {
		this.valueAction = valueAction;
		this.type = type;
		//type può essere "harvest" o "production"
	}
	public String getType() {
		return this.type;
	}
	@Override
	public void performEffect(Player player) {
		//interazione con l'utente che passa il tipo di azione da cui si ricava lo spazio 
		//all'utente è richiesto il numero di servitori
		
	}
	
	@Override
	public BonusActivityEffect clone() {
		return new BonusActivityEffect(this.getValue(), this.getType());
	}
	
	@Override 
	public String toString () {
		return super.toString()+"Bonus "+type+" for a value of "+valueAction+" ";
	}
	

}
