package it.polimi.ingsw.ps29.model.cards.effects;

import it.polimi.ingsw.ps29.model.game.Player;

public class BonusActivityEffect extends BonusActionEffect {
	private String type;

	public BonusActivityEffect(int valueAction, String type) {
		this.valueAction = valueAction;
		this.type = type;
		//type può essere "Harvest" o "Production"
	}

	@Override
	void performEffect(Player player) {
		//interazione con l'utente che passa il tipo di azione da cui si ricava lo spazio 
		//all'utente è richiesto il numero di servitori
		
	}
	
	

}