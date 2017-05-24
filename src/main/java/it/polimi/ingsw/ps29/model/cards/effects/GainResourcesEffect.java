package it.polimi.ingsw.ps29.model.cards.effects;

import java.util.ArrayList;

import it.polimi.ingsw.ps29.model.game.Player;
import it.polimi.ingsw.ps29.model.game.resources.Container;
import it.polimi.ingsw.ps29.model.game.resources.Resource;


public class GainResourcesEffect extends Effect {

	private ArrayList<Resource> resourceGain;
	
	public GainResourcesEffect() {
		resourceGain = new ArrayList<Resource> ();
	}
	
	void addResource (Resource res) {
		resourceGain.add(res);
	}
	
	@Override
	void performEffect(Player player) {
		Container playerResources = player.getPersonalBoard().getResources();
		for (Resource res: resourceGain)
			playerResources.addResource(res);
	}

}
