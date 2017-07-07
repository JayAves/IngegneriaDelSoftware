package it.polimi.ingsw.ps29.model.cards.effects;

import java.util.ArrayList;

import it.polimi.ingsw.ps29.model.game.Player;
import it.polimi.ingsw.ps29.model.game.resources.Container;
import it.polimi.ingsw.ps29.model.game.resources.Resource;
import it.polimi.ingsw.ps29.model.game.resources.ResourceDecorator;
import it.polimi.ingsw.ps29.model.game.resources.ResourceSantaRitaDecorator;

/**
 * LEADER Santa Rita
 * @author Giovanni Mele
 *
 */
public class SantaRitaEffect extends EffectAboutResources{

	public SantaRitaEffect(ArrayList<Resource> resources) {
		super(resources);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void performEffect(Player player){
		
		Container playerResources = player.getPersonalBoard().getResources();
		for (Resource res: resources) {
			playerResources.substituteResource(new ResourceSantaRitaDecorator(playerResources.getResource(res.getType()), 2));
			
		}
		
	}
	
}
