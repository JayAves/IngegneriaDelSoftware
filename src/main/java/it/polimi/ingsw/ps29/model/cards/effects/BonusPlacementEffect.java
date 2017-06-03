package it.polimi.ingsw.ps29.model.cards.effects;

import java.util.ArrayList;

import it.polimi.ingsw.ps29.model.game.Player;
import it.polimi.ingsw.ps29.model.game.resources.Resource;

public class BonusPlacementEffect extends BonusActionEffect {
	private String cardType;
	private ArrayList <Resource> discount;
	
	public BonusPlacementEffect(int valueAction, String cardType, ArrayList<Resource> discount) {
		this.cardType = cardType;
		this.valueAction = valueAction;
		this.discount = discount;
	}
	

	@Override
	public void performEffect(Player player) {
		//interazione con l'utente, a cui devo passare il tipo di carta relativa alla torre su 
		//cui piazzare e il valore dell'azione (con eventuale sconto)
		//Non essendo un effetto permanente non memorizzo lo sconto ma lo considero solamente per la mossa attuale.
		
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
	
	
	

}
