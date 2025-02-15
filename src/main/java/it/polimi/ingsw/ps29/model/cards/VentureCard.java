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
public class VentureCard extends Card {
	
	private int neededPoints;

	public VentureCard(String name, int neeededPoints, Period period, String type, int id, ArrayList<Effect> immediate,
			ArrayList<Effect> permanent, ArrayList<Resource> cost) {
		super(name, period, type, id, immediate, permanent, cost);
		this.neededPoints = neededPoints;
	}
	
	@Override
	public String toString () {
		return super.toString()+", neededPoints: "+ neededPoints;
	}
	
	public int getNeededPoints() {
		return neededPoints;
	}
}
