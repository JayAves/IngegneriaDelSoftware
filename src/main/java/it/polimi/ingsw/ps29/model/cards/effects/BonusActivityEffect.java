package it.polimi.ingsw.ps29.model.cards.effects;

import it.polimi.ingsw.ps29.model.game.Player;

/**
 * CARD EFFECT & LEADER: Allows player to do an extra activity (production or harvest) without placing a familiar. 
 * @author Pietro Melzi
 * @author Giovanni Mele
 * @author Pietro Grotti
 *
 */
public class BonusActivityEffect extends BonusActionEffect {
	
	//auto-generated serialVersionUID
	private static final long serialVersionUID = 2489229689117313929L;
	private String type;

	public BonusActivityEffect(int valueAction, String type) {
		this.valueAction = valueAction;
		this.type = type;
		//type can be harvest or production
	}
	public String getType() {
		return this.type;
	}
	
	@Override
	public void performEffect(Player player) {
		//effect is performed differently, in Controller's visitor pattern
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
