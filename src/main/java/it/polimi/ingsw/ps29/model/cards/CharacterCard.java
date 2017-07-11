package it.polimi.ingsw.ps29.model.cards;


import java.util.ArrayList;

import it.polimi.ingsw.ps29.model.cards.effects.Effect;
import it.polimi.ingsw.ps29.model.game.Period;
import it.polimi.ingsw.ps29.model.game.resources.Resource;

/**
 * 
 * @author Pietro Melzi
 * @author Pietro Grotti
 * @author Giovanni Mele
 *
 */
public class CharacterCard extends Card {

	public CharacterCard(String name, Period period, String type, int id, ArrayList<Effect> immediate,
			ArrayList<Effect> permanent, ArrayList<Resource> cost) {
		super(name, period, type, id, immediate, permanent, cost);

	}
	
	
	


}
