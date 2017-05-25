package it.polimi.ingsw.ps29.model.cards.effects;

import it.polimi.ingsw.ps29.model.cards.CardType;
import it.polimi.ingsw.ps29.model.game.Player;

public class LoseVictoryPoint extends ResourcesArray {
	
	private int interval;
	private CardType typeCard;
	
	
	public LoseVictoryPoint(int interval, CardType typeCard) {
		super();
		this.interval = interval;
		this.typeCard = typeCard;
	}


	@Override
	public void performEffect(Player player) {
		//da fare
		
	}
	
	

}
