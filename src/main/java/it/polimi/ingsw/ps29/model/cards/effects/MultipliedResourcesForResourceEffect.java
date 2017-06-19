package it.polimi.ingsw.ps29.model.cards.effects;

import it.polimi.ingsw.ps29.model.game.PersonalBoard;
import it.polimi.ingsw.ps29.model.game.Player;
import it.polimi.ingsw.ps29.model.game.resources.Container;
import it.polimi.ingsw.ps29.model.game.resources.Resource;

public class MultipliedResourcesForResourceEffect extends Effect{
	
	private String multiplied;
	private double amount;
	private String gained;

	public MultipliedResourcesForResourceEffect(String multiplied, String gained, double amount) {
		super();
		this.multiplied = multiplied;
		this.gained = gained;
		this.amount = amount;
	}

	@Override
	public void performEffect(Player player) {
		PersonalBoard board = player.getPersonalBoard();
		Container resources = board.getResources();
		int multiplier = (int) (resources.getResource(multiplied).getAmount()/amount);
		//int multiplier = board.getCards(cardType).size();
		resources.updateResource(new Resource(gained, multiplier));
		
	}
	
	@Override
	public String toString () {
		return super.toString()+"Gain: "+amount+" "+gained+" X multiplied: "+multiplied;
	}

}
