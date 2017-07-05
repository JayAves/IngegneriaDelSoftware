package it.polimi.ingsw.ps29.model.cards.effects;

import java.util.ArrayList;

import it.polimi.ingsw.ps29.model.cards.Card;
import it.polimi.ingsw.ps29.model.cards.CardType;
import it.polimi.ingsw.ps29.model.game.Player;
import it.polimi.ingsw.ps29.model.game.resources.Resource;

/**
 * EXCOMMUNICATION: every time VictoryPoints are gained, part of them is lost.
 * @author jaycaves
 *
 */
public class LoseVictoryPoint extends EffectAboutResources {
	
	private int interval;
	private CardType typeCard; //type is null when effect is on resources, not on cardss
	
	
	public LoseVictoryPoint(int interval, CardType typeCard, ArrayList<Resource> res) {
		super(res);
		this.interval = interval;
		this.typeCard = typeCard;
	}

	private int resourceCost (Card card, String resourceType) {
		for (Resource resourceCost: card.getCost()) 
			if (resourceCost.getType().equals(resourceType))
				return resourceCost.getAmount();
		
		return 0;
	}

	@Override
	public void performEffect(Player player) {
		int amount = 0;
		if(typeCard == null) 
			for(Resource res: resources) 
				amount += player.getPersonalBoard().getSpecificResource(res.getType()).getAmount();
			
		else {
			ArrayList<Card> cards = player.getPersonalBoard().getCards(typeCard.getType());
			for (Card card: cards) 
				for (Resource res: resources)
					amount += resourceCost (card, res.getType());
		}
		
		//sottrarre a fine turno "amount/interval" victoryPoint al giocatore
		
	}
	
	

}
