package it.polimi.ingsw.ps29.model.cards.effects;

import java.util.ArrayList;

import it.polimi.ingsw.ps29.model.game.Player;
import it.polimi.ingsw.ps29.model.game.resources.Resource;

/**
 * CARD EFFECT & LEADER: Allows player to do an extra TowerAction without placing a familiar.
 * @author Pietro Melzi
 * @author Giovanni Mele
 * @author Pietro Grotti
 * @see it.polimi.ingsw.ps29.model.action.TowerAction
 *
 */
public class BonusPlacementEffect extends BonusActionEffect {
	/**
	 * 
	 */
	private static final long serialVersionUID = -294451113077440329L;
	private String cardType;
	private ArrayList <Resource> discount;
	
	public BonusPlacementEffect(int valueAction, String cardType, ArrayList<Resource> discount) {
		this.cardType = cardType;
		this.valueAction = valueAction;
		this.discount = discount;
	}
	

	@Override
	public void performEffect(Player player) {
		//effect is performed differently, in Controller's visitor pattern
		
	}

	public String getType() {
		return cardType;
	}

	public ArrayList<Resource> getDiscount () {
		return discount;
	}

	@Override
	public BonusPlacementEffect clone() {
		ArrayList<Resource> discount = new ArrayList<Resource> ();
		for(Resource res: this.getDiscount())
			discount.add(res.clone());
		return new BonusPlacementEffect(this.getValue(), this.getType(), discount);
	}
	
	@Override
	public String toString () {
		String msg = super.toString()+"BonusPlacement of value: "+valueAction+" for  "+cardType.toUpperCase()+" with discount:";
		for(Resource res: discount) 
			msg+=res.toString()+",";
		return msg+" ";
	}
	

}
