package it.polimi.ingsw.ps29.model.cards.effects;

import java.util.ArrayList;

import it.polimi.ingsw.ps29.model.game.Player;
import it.polimi.ingsw.ps29.model.game.resources.Container;
import it.polimi.ingsw.ps29.model.game.resources.Resource;

public class MalusResourcesExcommunication extends ResourcesArray {

	public MalusResourcesExcommunication(ArrayList<Resource> resources) {
		super(resources);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void performEffect(Player player) {
		Container playerResources = player.getPersonalBoard().getResources();
		for (Resource res: resourceGain) {
			//per ogni res bisogna settare il modificatore delle risorse (es. per prendere una risorsa in meno)
			//DA_FARE
		}
	}

}
