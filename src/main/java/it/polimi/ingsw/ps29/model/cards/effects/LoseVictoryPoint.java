package it.polimi.ingsw.ps29.model.cards.effects;

import java.util.ArrayList;

import it.polimi.ingsw.ps29.model.cards.CardType;
import it.polimi.ingsw.ps29.model.game.Player;
import it.polimi.ingsw.ps29.model.game.resources.Resource;

public class LoseVictoryPoint extends EffectAboutResources {
	
	private int interval;
	private CardType typeCard;
	
	
	public LoseVictoryPoint(int interval, CardType typeCard, ArrayList<Resource> res) {
		super(res);
		this.interval = interval;
		this.typeCard = typeCard;
	}


	@Override
	public void performEffect(Player player) {
		//da fare
		
	}
	
	

}
