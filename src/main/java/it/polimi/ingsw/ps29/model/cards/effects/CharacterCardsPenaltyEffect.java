package it.polimi.ingsw.ps29.model.cards.effects;

import it.polimi.ingsw.ps29.model.game.Player;

/**
 * EXCOMMUNICATION: At the end of the game owning CharacterCard s will not give Victory Points

 * @author Giovanni Mele

 *
 */
public class CharacterCardsPenaltyEffect extends Effect{

	@Override
	public void performEffect(Player player) {
		player.getFinalScoring().setCharacterCardsPenalty();
		
	}

}
