package it.polimi.ingsw.ps29.model.cards.effects;

import java.util.ArrayList;

import it.polimi.ingsw.ps29.model.game.Player;
import it.polimi.ingsw.ps29.model.game.resources.Container;
import it.polimi.ingsw.ps29.model.game.resources.Resource;

/**
 * CARD & LEADER EFFECT: Gives a certain amount of Resources.
 * @author Pietro Melzi
 * @author Pietro Grotti
 *
 */
public class GainResourcesEffect extends EffectAboutResources {
	
	public GainResourcesEffect(ArrayList<Resource> resources) {
		super(resources);
	}

	@Override
	public void performEffect(Player player) {
		Container playerResources = player.getPersonalBoard().getResources();
		for (Resource res: resources)
			playerResources.updateResource(res);

	}

	@Override
	public String toString() {
		String msg= "Resource Gain:";
		for (Resource r: resources)
			msg+=r.toString();
	return msg+" ";
	}


}
