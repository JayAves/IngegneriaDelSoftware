package it.polimi.ingsw.ps29.model.cards.effects;

import java.util.ArrayList;

import it.polimi.ingsw.ps29.model.game.Player;
import it.polimi.ingsw.ps29.model.game.resources.Container;
import it.polimi.ingsw.ps29.model.game.resources.Resource;

public class GainResourcesEffect extends EffectAboutResources {
	
	public GainResourcesEffect(ArrayList<Resource> resources) {
		super(resources);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void performEffect(Player player) {
		Container playerResources = player.getPersonalBoard().getResources();
		for (Resource res: resources)
			playerResources.updateResource(res);
	}

	@Override
	public String toString() {
		return "GainResourcesEffect []";
	}

	


}
