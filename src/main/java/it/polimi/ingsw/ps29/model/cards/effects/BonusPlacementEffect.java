package it.polimi.ingsw.ps29.model.cards.effects;

import java.util.ArrayList;

import it.polimi.ingsw.ps29.model.game.Player;
import it.polimi.ingsw.ps29.model.game.resources.Resource;

public class BonusPlacementEffect extends BonusActionEffect {
	private String cardType;
	private ArrayList <Resource> discount;
	
	public BonusPlacementEffect(int valueAction, String cardType) {
		this.cardType = cardType;
		this.valueAction = valueAction;
		discount = new ArrayList <Resource> ();
	}
	
	void addResource (Resource res) {
		discount.add(res);
	}

	@Override
	void performEffect(Player player) {
		//interazione con l'utente, a cui devo passare il tipo di carta relativa alla torre su 
		//cui piazzare e il valore dell'azione (con eventuale sconto)
		//Non essendo un effetto permanente non memorizzo lo sconto.
		
	}
	
	

}
