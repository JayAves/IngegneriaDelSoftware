package it.polimi.ingsw.ps29.model.cards.effects;

import java.util.ArrayList;

import it.polimi.ingsw.ps29.model.game.Player;
import it.polimi.ingsw.ps29.model.game.resources.Container;
import it.polimi.ingsw.ps29.model.game.resources.Resource;
import it.polimi.ingsw.ps29.model.game.resources.ResourceDecorator;

/**
 * EXCOMMUNICATION EFFECT Decorates a specific resource so that future gains are reduced by a constant
 * @author Giovanni Mele
 *
 */
public class MalusResourcesExcommunication extends EffectAboutResources {

	public MalusResourcesExcommunication(ArrayList<Resource> resources) {
		super(resources);

	}

	@Override
	public void performEffect(Player player) {
		Container playerResources = player.getPersonalBoard().getResources();
		for (Resource res: resources) {
			playerResources.substituteResource(new ResourceDecorator (playerResources.getResource(res.getType()), - (res.getAmount())));
			
		}
	}

	@Override
	public String toString() {
		String message= "MalusResourcesExcommunication for resources: ";
		for (Resource res: resources)
			message+= ""+ res.toString()+",";
		return message;
	}
	
}
