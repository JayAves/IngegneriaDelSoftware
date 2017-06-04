package it.polimi.ingsw.ps29.model.cards.effects;

import java.util.ArrayList;

import it.polimi.ingsw.ps29.model.game.Player;
import it.polimi.ingsw.ps29.model.game.resources.Resource;

public class DiscountForCardTypeEffect extends Effect{

	private ArrayList<Resource> discount;
	private final String cardType;
	
	
	
	public DiscountForCardTypeEffect(String cardType) {
		super();
		this.discount = new ArrayList<Resource>();
		this.cardType=cardType;
	}

	public ArrayList<Resource> getDiscount(){
		return this.discount;
	}

	public void setDiscount(ArrayList<Resource> discount) {
		this.discount = discount;
	}



	@Override
	public void performEffect(Player player) {
		// TODO Auto-generated method stub
		player.specialPermanentEffects.add(this);
	}

	@Override
	public String toString () {
		String msg = super.toString()+"cardType: "+cardType+"\n";
		for(Resource res: discount)
			msg+="discount: "+res.toString()+"\n";
		return msg;
	}
}
