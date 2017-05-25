package it.polimi.ingsw.ps29.model.cards.effects;

import it.polimi.ingsw.ps29.model.cards.CardType;
import it.polimi.ingsw.ps29.model.game.Player;

public class NoVictoryPointExcommunication extends Effect{
	private CardType type;

	public NoVictoryPointExcommunication(CardType type) {
		this.type = type;
	}

	@Override
	void performEffect(Player player) {
		//da implementare quando facciamo le funzioni di fine partita
		
	}
	
	

}
