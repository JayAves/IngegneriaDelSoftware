package it.polimi.ingsw.ps29.model.action;

import java.util.ArrayList;

import it.polimi.ingsw.ps29.messages.exception.RejectException;
import it.polimi.ingsw.ps29.model.cards.effects.GainResourcesEffect;
import it.polimi.ingsw.ps29.model.game.Match;
import it.polimi.ingsw.ps29.model.game.Move;
import it.polimi.ingsw.ps29.model.game.resources.Coins;
import it.polimi.ingsw.ps29.model.game.resources.Privilege;
import it.polimi.ingsw.ps29.model.game.resources.Resource;
import it.polimi.ingsw.ps29.model.space.CouncilPalaceArea;

/**
 * When player tries to place a familiar in CouncilArea
 * @author Pietro Grotti
 * @author Pietro Melzi
 *
 */
public class CouncilPalaceAction extends Action{

	private CouncilPalaceArea space;
	

	public CouncilPalaceAction(Match model, Move move) {
		super(model, move);
		this.space = (CouncilPalaceArea) model.getBoard().getSpace(move.getSpace());
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean isForbidden() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isPlaceable() throws RejectException {
		return space.isEnoughPowerful(move.getFamiliar().getPower() + move.getServants());
	}

	@Override
	public void performAction() {
		
		space.placeFamiliar(move.getFamiliar(), move.getPlayer().getLudovicoAriosto());
		ArrayList<Resource> councilBonus = new ArrayList<Resource> ();
		councilBonus.add(new Coins(1));
		councilBonus.add(new Privilege(1));
		//l'array andrebbe creato da un'altra parte, con le risorse caricate da file
		
		GainResourcesEffect effect= new GainResourcesEffect(councilBonus);
		effect.performEffect(move.getPlayer());
		move.getFamiliar().setBusy(true);
		
	}
	
	

}
