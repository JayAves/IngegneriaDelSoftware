package it.polimi.ingsw.ps29.model.cards.effects;

import it.polimi.ingsw.ps29.model.game.PersonalBoard;
import it.polimi.ingsw.ps29.model.game.Player;
import it.polimi.ingsw.ps29.model.game.resources.Container;
import it.polimi.ingsw.ps29.model.game.resources.Resource;

public class MultipliedResourcesEffect extends Effect {
	
	private String resourceType;
	private int amount;
	private String cardType;

	public MultipliedResourcesEffect(String resourceType, int amount, String cardType) {
		this.resourceType = resourceType;
		this.amount = amount;
		this.cardType = cardType;
	}

	@Override
	public void performEffect(Player player) {
		PersonalBoard board = player.getPersonalBoard();
		Container resources = board.getResources();
		int multiplier = board.getCards(cardType).size();
		resources.updateResource(new Resource(resourceType, multiplier*amount));
	}

	@Override
	public String toString () {
		return super.toString()+" resource type: "+resourceType+" = amount: "+amount+", X crad type: "+cardType+"\n";
	}
	
}
