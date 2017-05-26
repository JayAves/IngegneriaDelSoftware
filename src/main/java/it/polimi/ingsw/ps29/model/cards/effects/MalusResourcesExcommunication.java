package it.polimi.ingsw.ps29.model.cards.effects;

import java.util.ArrayList;

import it.polimi.ingsw.ps29.model.game.Player;
import it.polimi.ingsw.ps29.model.game.resources.Container;
import it.polimi.ingsw.ps29.model.game.resources.Resource;
import it.polimi.ingsw.ps29.model.game.resources.ResourceDecorator;
import it.polimi.ingsw.ps29.model.game.resources.ResourceInterface;

public class MalusResourcesExcommunication extends ResourcesArray {

	public MalusResourcesExcommunication(ArrayList<Resource> resources) {
		super(resources);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void performEffect(Player player) {
		Container playerResources = player.getPersonalBoard().getResources();
		for (Resource res: resources) {
			ResourceInterface temp = new ResourceDecorator (playerResources.getResource(res.getType()), -1);
		}
	}

}
